package com.graduationproject.studentinformationsystem.university.mail.service.impl;

import com.graduationproject.studentinformationsystem.common.util.SisUtil;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.login.student.password.service.StudentPasswordOperationOutService;
import com.graduationproject.studentinformationsystem.university.mail.model.entity.SisMailEntity;
import com.graduationproject.studentinformationsystem.university.mail.service.MailService;
import com.graduationproject.studentinformationsystem.university.mail.service.StudentMailService;
import com.graduationproject.studentinformationsystem.university.parameter.repository.ParameterRepository;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentInfoDetailResponse;
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
public class StudentMailServiceImpl implements StudentMailService {

    private final StudentPasswordOperationOutService passwordOperationOutService;

    private final MailService mailService;

    private final ParameterRepository parameterRepository;

    private SisMailEntity mailEntity;

    private static final String STUDENT_NAME = "studentName";
    private static final String STUDENT_NUMBER = "studentNumber";
    private static final String DATE = "date";
    private static final String CHANGE_PASSWORD_URL = "changePasswordUrl";

    @PostConstruct
    void before() {
        final String senderEmail = parameterRepository.getStudentParameterByName("MAIL_SMTP_SENDER_EMAIL");
        final String senderPassword = parameterRepository.getStudentParameterByName("MAIL_SMTP_SENDER_PASSWORD");
        final String senderName = parameterRepository.getStudentParameterByName("MAIL_SMTP_SENDER_NAME");

        final Properties properties = getProperties();

        mailEntity = SisMailEntity.builder()
                .senderEmail(senderEmail)
                .senderPassword(senderPassword)
                .senderName(senderName)
                .properties(properties).build();
    }

    @Override
    public void sendSavedEmail(final StudentInfoDetailResponse infoDetailResponse) throws SisNotExistException {
        final String personalEmail = infoDetailResponse.getPersonalInfoResponse().getEmail();

        Map<String, String> values = getMailValues(infoDetailResponse);

        mailEntity.setTitle("Öğrenci Hesabınız Başarıyla Oluşturuldu!");
        mailEntity.setTemplate(parameterRepository.getStudentParameterByName("MAIL_TEMPLATE_FIRST_PASSWORD"));
        mailEntity.setTo(personalEmail);
        mailEntity.setValues(values);

        mailService.sendMail(mailEntity);
        log.info("Student Saved Email Successfully Sent to " + mailEntity.getTo());
    }

    @Override
    public void sendForgotPasswordEmail(final StudentInfoDetailResponse infoDetailResponse) throws SisNotExistException {
        final String personalEmail = infoDetailResponse.getPersonalInfoResponse().getEmail();

        Map<String, String> values = getMailValues(infoDetailResponse);

        mailEntity.setTitle("Şifre Değiştirme İsteğiniz İle İlgili!");
        mailEntity.setTemplate(parameterRepository.getStudentParameterByName("MAIL_TEMPLATE_FORGOT_PASSWORD"));
        mailEntity.setTo(personalEmail);
        mailEntity.setValues(values);

        mailService.sendMail(mailEntity);
        log.info("Student Forgot Password Email Successfully Sent to " + mailEntity.getTo());
    }


    private Map<String, String> getMailValues(final StudentInfoDetailResponse infoDetailResponse) throws SisNotExistException {
        final Long studentId = infoDetailResponse.getAcademicInfoResponse().getStudentId();
        final String name = infoDetailResponse.getPersonalInfoResponse().getName();
        final String surname = infoDetailResponse.getPersonalInfoResponse().getSurname();

        Map<String, String> values = new HashMap<>();
        values.put(STUDENT_NAME, getStudentFullName(name, surname));
        values.put(STUDENT_NUMBER, getStudentNumber(studentId));
        values.put(DATE, SisUtil.getCurrentFormattedDateTime());
        values.put(CHANGE_PASSWORD_URL, getChangePasswordUrl(studentId));
        return values;
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", parameterRepository.getStudentParameterByName("MAIL_SMTP_HOST"));
        properties.put("mail.smtp.port", parameterRepository.getStudentParameterByName("MAIL_SMTP_PORT"));
        properties.put("mail.smtp.auth", parameterRepository.getStudentParameterByName("MAIL_SMTP_AUTH"));
        properties.put("mail.smtp.starttls.enable", parameterRepository.getStudentParameterByName("MAIL_SMTP_START_TLS_ENABLE"));
        return properties;
    }

    private String getStudentFullName(final String name, final String surname) {
        return name + " " + surname;
    }

    private String getStudentNumber(final Long studentId) {
        return String.valueOf(studentId);
    }


    protected String getChangePasswordUrl(final Long studentId) {
        return passwordOperationOutService.getPasswordChangeUrl(studentId);
    }
}
