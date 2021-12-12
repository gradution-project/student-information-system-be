package com.graduationproject.studentinformationsystem.university.mail.service.impl;

import com.graduationproject.studentinformationsystem.login.common.service.PasswordService;
import com.graduationproject.studentinformationsystem.login.student.repository.StudentLoginRepository;
import com.graduationproject.studentinformationsystem.university.mail.model.entity.MailEntity;
import com.graduationproject.studentinformationsystem.university.mail.service.MailService;
import com.graduationproject.studentinformationsystem.university.mail.service.StudentMailService;
import com.graduationproject.studentinformationsystem.university.parameter.repository.ParameterRepository;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentInfoDetailResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentMailServiceImpl implements StudentMailService {

    private final MailService mailService;

    private final StudentLoginRepository loginRepository;
    private final PasswordService passwordService;

    private final ParameterRepository parameterRepository;

    private MailEntity mailEntity;

    private static final String STUDENT_NAME = "studentName";
    private static final String STUDENT_NUMBER = "studentNumber";
    private static final String PASSWORD = "password";
    private static final String DATE = "date";

    @PostConstruct
    void before() {
        final String senderEmail = parameterRepository.getStudentParameterByName("MAIL_SMTP_SENDER_EMAIL");
        final String senderPassword = parameterRepository.getStudentParameterByName("MAIL_SMTP_SENDER_PASSWORD");
        final String senderName = parameterRepository.getStudentParameterByName("MAIL_SMTP_SENDER_NAME");

        final Properties properties = getProperties();

        mailEntity = MailEntity.builder()
                .senderEmail(senderEmail)
                .senderPassword(senderPassword)
                .senderName(senderName)
                .properties(properties).build();
    }

    @Override
    public void sendFirstPasswordEmail(StudentInfoDetailResponse studentInfoDetailResponse) {
        Map<String, String> values = new HashMap<>();
        values.put(STUDENT_NAME, getStudentName(studentInfoDetailResponse));
        values.put(STUDENT_NUMBER, getStudentNumber(studentInfoDetailResponse));
        values.put(PASSWORD, getFirstPassword(studentInfoDetailResponse));
        values.put(DATE, getFormattedDate());

        mailEntity.setTitle("Öğrenci Hesabınız Başarıyla Oluşturuldu!");
        mailEntity.setTemplate(parameterRepository.getStudentParameterByName("MAIL_TEMPLATE_FIRST_PASSWORD"));
        mailEntity.setTo(getStudentPersonalEmail(studentInfoDetailResponse));
        mailEntity.setValues(values);

        mailService.sendMail(mailEntity);
        log.info("First Password Email Successfully Sent to " + mailEntity.getTo());
    }

    @Override
    public void sendForgotPasswordEmail(StudentInfoDetailResponse studentInfoDetailResponse) {
        Map<String, String> values = new HashMap<>();
        values.put(STUDENT_NAME, getStudentName(studentInfoDetailResponse));
        values.put(STUDENT_NUMBER, getStudentNumber(studentInfoDetailResponse));
        values.put(PASSWORD, getChangedPassword(studentInfoDetailResponse));
        values.put(DATE, getFormattedDate());

        mailEntity.setTitle("Şifreniz Başarıyla Değiştirildi!");
        mailEntity.setTemplate(parameterRepository.getStudentParameterByName("MAIL_TEMPLATE_FORGOT_PASSWORD"));
        mailEntity.setTo(getStudentPersonalEmail(studentInfoDetailResponse));
        mailEntity.setValues(values);

        mailService.sendMail(mailEntity);
        log.info("Forgot Password Email Successfully Sent to " + mailEntity.getTo());
    }


    private Properties getProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", parameterRepository.getStudentParameterByName("MAIL_SMTP_HOST"));
        final String smtpPort = parameterRepository.getStudentParameterByName("MAIL_SMTP_PORT");
        if (smtpPort != null) {
            properties.put("mail.smtp.port", smtpPort);
        }
        properties.put("mail.smtp.auth", parameterRepository.getStudentParameterByName("MAIL_SMTP_AUTH"));
        properties.put("mail.smtp.starttls.enable", parameterRepository.getStudentParameterByName("MAIL_SMTP_START_TLS_ENABLE"));
        return properties;
    }

    private String getFormattedDate() {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return dateFormat.format(new Date());
    }

    private String getStudentName(StudentInfoDetailResponse studentInfoDetailResponse) {
        return studentInfoDetailResponse.getPersonalInfoResponse().getName() + " " + studentInfoDetailResponse.getPersonalInfoResponse().getSurname();
    }

    private String getStudentNumber(StudentInfoDetailResponse studentInfoDetailResponse) {
        return String.valueOf(studentInfoDetailResponse.getAcademicInfoResponse().getStudentId());
    }

    private String getStudentPersonalEmail(StudentInfoDetailResponse studentInfoDetailResponse) {
        return studentInfoDetailResponse.getPersonalInfoResponse().getEmail();
    }


    protected String getFirstPassword(StudentInfoDetailResponse studentInfoDetailResponse) {
        final Long studentId = studentInfoDetailResponse.getAcademicInfoResponse().getStudentId();
        final String password = passwordService.generatePassword();
        loginRepository.saveFirstPassword(studentId, password);
        return password;
    }

    protected String getChangedPassword(StudentInfoDetailResponse studentInfoDetailResponse) {
        final Long studentId = studentInfoDetailResponse.getAcademicInfoResponse().getStudentId();
        final String password = passwordService.generatePassword();
        loginRepository.updatePassword(studentId, password);
        return password;
    }
}
