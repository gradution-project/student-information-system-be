package com.graduationproject.studentinformationsystem.university.mail.service.impl;

import com.graduationproject.studentinformationsystem.university.mail.model.entity.MailEntity;
import com.graduationproject.studentinformationsystem.university.mail.service.MailService;
import com.graduationproject.studentinformationsystem.university.mail.service.TeacherMailService;
import com.graduationproject.studentinformationsystem.university.parameter.repository.ParameterRepository;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherInfoDetailResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeacherMailServiceImpl implements TeacherMailService {

    private final ParameterRepository parameterRepository;
    private final MailService mailService;

    private MailEntity mailEntity;

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

        mailEntity = MailEntity.builder()
                .senderEmail(senderEmail)
                .senderPassword(senderPassword)
                .senderName(senderName)
                .properties(properties).build();
    }

    @Override
    public void sendFirstPasswordEmail(TeacherInfoDetailResponse teacherInfoDetailResponse) {
        Map<String, String> values = new HashMap<>();
        values.put(TEACHER_NAME, getTeacherName(teacherInfoDetailResponse));
        values.put(TEACHER_NUMBER, getTeacherNumber(teacherInfoDetailResponse));
        values.put(PASSWORD, String.valueOf(UUID.randomUUID()));
        values.put(DATE, getFormattedDate());

        mailEntity.setTitle("Öğrenci Hesabınız Başarıyla Oluşturuldu!");
        mailEntity.setTemplate(parameterRepository.getTeacherParameterByName("MAIL_TEMPLATE_FIRST_PASSWORD"));
        mailEntity.setTo(getTeacherPersonalEmail(teacherInfoDetailResponse));
        mailEntity.setValues(values);

        mailService.sendMail(mailEntity);
        log.info("First Password Email Successfully Sent to " + mailEntity.getTo());
    }

    @Override
    public void sendForgotPasswordEmail(TeacherInfoDetailResponse teacherInfoDetailResponse) {
        Map<String, String> values = new HashMap<>();
        values.put(TEACHER_NAME, getTeacherName(teacherInfoDetailResponse));
        values.put(TEACHER_NUMBER, getTeacherNumber(teacherInfoDetailResponse));
        values.put(PASSWORD, String.valueOf(UUID.randomUUID()));
        values.put(DATE, getFormattedDate());

        mailEntity.setTitle("Şifreniz Başarıyla Değiştirildi!");
        mailEntity.setTemplate(parameterRepository.getTeacherParameterByName("MAIL_TEMPLATE_FORGOT_PASSWORD"));
        mailEntity.setTo(getTeacherPersonalEmail(teacherInfoDetailResponse));
        mailEntity.setValues(values);

        mailService.sendMail(mailEntity);
        log.info("Forgot Password Email Successfully Sent to " + mailEntity.getTo());
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", parameterRepository.getTeacherParameterByName("MAIL_SMTP_HOST"));
        final String smtpPort = parameterRepository.getTeacherParameterByName("MAIL_SMTP_PORT");
        if (smtpPort != null) {
            properties.put("mail.smtp.port", smtpPort);
        }
        properties.put("mail.smtp.auth", parameterRepository.getTeacherParameterByName("MAIL_SMTP_AUTH"));
        properties.put("mail.smtp.starttls.enable", parameterRepository.getTeacherParameterByName("MAIL_SMTP_START_TLS_ENABLE"));
        return properties;
    }

    private String getFormattedDate() {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return dateFormat.format(new Date());
    }

    private String getTeacherName(TeacherInfoDetailResponse teacherInfoDetailResponse) {
        return teacherInfoDetailResponse.getPersonalInfoResponse().getName() + " " + teacherInfoDetailResponse.getPersonalInfoResponse().getSurname();
    }

    private String getTeacherNumber(TeacherInfoDetailResponse teacherInfoDetailResponse) {
        return String.valueOf(teacherInfoDetailResponse.getAcademicInfoResponse().getTeacherId());
    }

    private String getTeacherPersonalEmail(TeacherInfoDetailResponse teacherInfoDetailResponse) {
        return teacherInfoDetailResponse.getPersonalInfoResponse().getEmail();
    }
}
