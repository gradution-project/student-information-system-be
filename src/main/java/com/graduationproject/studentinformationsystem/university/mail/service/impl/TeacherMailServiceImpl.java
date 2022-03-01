package com.graduationproject.studentinformationsystem.university.mail.service.impl;

import com.graduationproject.studentinformationsystem.common.util.SisUtil;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.login.teacher.password.service.TeacherPasswordOperationOutService;
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

    private final TeacherPasswordOperationOutService passwordOperationOutService;

    private final MailService mailService;

    private final ParameterRepository parameterRepository;

    private SisMailEntity mailEntity;

    private static final String TEACHER_NAME = "teacherName";
    private static final String TEACHER_NUMBER = "teacherNumber";
    private static final String DATE = "date";
    private static final String CHANGE_PASSWORD_URL = "changePasswordUrl";

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
    public void sendSavedEmail(final TeacherInfoDetailResponse infoDetailResponse) throws SisNotExistException {
        final String personalEmail = infoDetailResponse.getPersonalInfoResponse().getEmail();

        Map<String, String> values = getMailValues(infoDetailResponse);

        mailEntity.setTitle("Öğretmen Hesabınız Başarıyla Oluşturuldu!");
        mailEntity.setTemplate(parameterRepository.getTeacherParameterByName("MAIL_TEMPLATE_FIRST_PASSWORD"));
        mailEntity.setTo(personalEmail);
        mailEntity.setValues(values);

        mailService.sendMail(mailEntity);
        log.info("Teacher Saved Email Successfully Sent to " + mailEntity.getTo());
    }

    @Override
    public void sendForgotPasswordEmail(final TeacherInfoDetailResponse infoDetailResponse) throws SisNotExistException {
        final String personalEmail = infoDetailResponse.getPersonalInfoResponse().getEmail();

        Map<String, String> values = getMailValues(infoDetailResponse);

        mailEntity.setTitle("Şifre Değiştirme İsteğiniz İle İlgili!");
        mailEntity.setTemplate(parameterRepository.getTeacherParameterByName("MAIL_TEMPLATE_FORGOT_PASSWORD"));
        mailEntity.setTo(personalEmail);
        mailEntity.setValues(values);

        mailService.sendMail(mailEntity);
        log.info("Teacher Forgot Password Email Successfully Sent to " + mailEntity.getTo());
    }


    private Map<String, String> getMailValues(final TeacherInfoDetailResponse infoDetailResponse) {
        final Long teacherId = infoDetailResponse.getAcademicInfoResponse().getTeacherId();
        final String name = infoDetailResponse.getPersonalInfoResponse().getName();
        final String surname = infoDetailResponse.getPersonalInfoResponse().getSurname();

        Map<String, String> values = new HashMap<>();
        values.put(TEACHER_NAME, getTeacherFullName(name, surname));
        values.put(TEACHER_NUMBER, getTeacherNumber(teacherId));
        values.put(DATE, SisUtil.getCurrentFormattedDateTime());
        values.put(CHANGE_PASSWORD_URL, getChangePasswordUrl(teacherId));
        return values;
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", parameterRepository.getTeacherParameterByName("MAIL_SMTP_HOST"));
        properties.put("mail.smtp.port", parameterRepository.getTeacherParameterByName("MAIL_SMTP_PORT"));
        properties.put("mail.smtp.auth", parameterRepository.getTeacherParameterByName("MAIL_SMTP_AUTH"));
        properties.put("mail.smtp.starttls.enable", parameterRepository.getTeacherParameterByName("MAIL_SMTP_START_TLS_ENABLE"));
        return properties;
    }

    private String getTeacherFullName(final String name, final String surname) {
        return name + " " + surname;
    }

    private String getTeacherNumber(final Long teacherId) {
        return String.valueOf(teacherId);
    }


    protected String getChangePasswordUrl(final Long teacherId) {
        return passwordOperationOutService.getPasswordChangeUrl(teacherId);
    }
}
