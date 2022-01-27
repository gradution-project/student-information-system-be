package com.graduationproject.studentinformationsystem.university.mail.service.impl;

import com.graduationproject.studentinformationsystem.common.util.SisUtil;
import com.graduationproject.studentinformationsystem.login.common.service.PasswordService;
import com.graduationproject.studentinformationsystem.login.teacher.repository.TeacherLoginRepository;
import com.graduationproject.studentinformationsystem.university.mail.model.entity.SisMailEntity;
import com.graduationproject.studentinformationsystem.university.mail.service.MailService;
import com.graduationproject.studentinformationsystem.university.mail.service.TeacherMailService;
import com.graduationproject.studentinformationsystem.university.parameter.repository.ParameterRepository;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherInfoDetailResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeacherMailServiceImpl implements TeacherMailService {

    private final MailService mailService;

    private final TeacherLoginRepository loginRepository;
    private final PasswordService passwordService;

    private final ParameterRepository parameterRepository;

    private SisMailEntity mailEntity;

    private static final String TEACHER_NAME = "teacherName";
    private static final String TEACHER_NUMBER = "teacherNumber";
    private static final String PASSWORD = "password";
    private static final String DATE = "date";

    @PostConstruct
    void before() {
        final String senderEmail = parameterRepository.getTeacherParameterByName("MAIL_SMTP_SENDER_EMAIL");
        final String senderPassword = parameterRepository.getTeacherParameterByName("MAIL_SMTP_SENDER_PASSWORD");
        final String senderName = parameterRepository.getTeacherParameterByName("MAIL_SMTP_SENDER_NAME");

        final Properties properties = getProperties();

        mailEntity = SisMailEntity.builder()
                .senderEmail(senderEmail)
                .senderPassword(senderPassword)
                .senderName(senderName)
                .properties(properties).build();
    }

    @Override
    public void sendFirstPasswordEmail(final TeacherInfoDetailResponse infoDetailResponse) {
        Map<String, String> values = new HashMap<>();
        values.put(TEACHER_NAME, getTeacherName(infoDetailResponse));
        values.put(TEACHER_NUMBER, getTeacherNumber(infoDetailResponse));
        values.put(PASSWORD, getFirstPassword(infoDetailResponse));
        values.put(DATE, SisUtil.getCurrentFormattedDateTime());

        mailEntity.setTitle("Öğretmen Hesabınız Başarıyla Oluşturuldu!");
        mailEntity.setTemplate(parameterRepository.getTeacherParameterByName("MAIL_TEMPLATE_FIRST_PASSWORD"));
        mailEntity.setTo(getTeacherPersonalEmail(infoDetailResponse));
        mailEntity.setValues(values);

        mailService.sendMail(mailEntity);
        log.info("First Password Email Successfully Sent to " + mailEntity.getTo());
    }

    @Override
    public void sendForgotPasswordEmail(final TeacherInfoDetailResponse infoDetailResponse) {
        Map<String, String> values = new HashMap<>();
        values.put(TEACHER_NAME, getTeacherName(infoDetailResponse));
        values.put(TEACHER_NUMBER, getTeacherNumber(infoDetailResponse));
        values.put(PASSWORD, getChangedPassword(infoDetailResponse));
        values.put(DATE, SisUtil.getCurrentFormattedDateTime());

        mailEntity.setTitle("Şifreniz Başarıyla Değiştirildi!");
        mailEntity.setTemplate(parameterRepository.getTeacherParameterByName("MAIL_TEMPLATE_FORGOT_PASSWORD"));
        mailEntity.setTo(getTeacherPersonalEmail(infoDetailResponse));
        mailEntity.setValues(values);

        mailService.sendMail(mailEntity);
        log.info("Forgot Password Email Successfully Sent to " + mailEntity.getTo());
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", parameterRepository.getTeacherParameterByName("MAIL_SMTP_HOST"));
        properties.put("mail.smtp.port", parameterRepository.getTeacherParameterByName("MAIL_SMTP_PORT"));
        properties.put("mail.smtp.auth", parameterRepository.getTeacherParameterByName("MAIL_SMTP_AUTH"));
        properties.put("mail.smtp.starttls.enable", parameterRepository.getTeacherParameterByName("MAIL_SMTP_START_TLS_ENABLE"));
        return properties;
    }

    private String getTeacherName(final TeacherInfoDetailResponse infoDetailResponse) {
        final String name = infoDetailResponse.getPersonalInfoResponse().getName();
        final String surname = infoDetailResponse.getPersonalInfoResponse().getSurname();
        return name + " " + surname;
    }

    private String getTeacherNumber(final TeacherInfoDetailResponse infoDetailResponse) {
        return String.valueOf(infoDetailResponse.getAcademicInfoResponse().getTeacherId());
    }

    private String getTeacherPersonalEmail(final TeacherInfoDetailResponse infoDetailResponse) {
        return infoDetailResponse.getPersonalInfoResponse().getEmail();
    }


    protected String getFirstPassword(final TeacherInfoDetailResponse infoDetailResponse) {
        final Long teacherId = infoDetailResponse.getAcademicInfoResponse().getTeacherId();
        final String password = passwordService.generatePassword();
        loginRepository.saveFirstPassword(teacherId, password);
        return password;
    }

    protected String getChangedPassword(final TeacherInfoDetailResponse infoDetailResponse) {
        final Long teacherId = infoDetailResponse.getAcademicInfoResponse().getTeacherId();
        final String password = passwordService.generatePassword();
        loginRepository.updatePassword(teacherId, password);
        return password;
    }
}
