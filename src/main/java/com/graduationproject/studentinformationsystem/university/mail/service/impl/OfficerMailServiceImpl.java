package com.graduationproject.studentinformationsystem.university.mail.service.impl;

import com.graduationproject.studentinformationsystem.common.util.SisUtil;
import com.graduationproject.studentinformationsystem.login.common.service.PasswordService;
import com.graduationproject.studentinformationsystem.login.officer.repository.OfficerLoginRepository;
import com.graduationproject.studentinformationsystem.university.mail.model.entity.SisMailEntity;
import com.graduationproject.studentinformationsystem.university.mail.service.MailService;
import com.graduationproject.studentinformationsystem.university.mail.service.OfficerMailService;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.response.OfficerInfoDetailResponse;
import com.graduationproject.studentinformationsystem.university.parameter.repository.ParameterRepository;
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
public class OfficerMailServiceImpl implements OfficerMailService {

    private final MailService mailService;

    private final OfficerLoginRepository loginRepository;
    private final PasswordService passwordService;

    private final ParameterRepository parameterRepository;

    private SisMailEntity mailEntity;

    private static final String OFFICER_NAME = "officerName";
    private static final String OFFICER_NUMBER = "officerNumber";
    private static final String PASSWORD = "password";
    private static final String DATE = "date";

    @PostConstruct
    void before() {
        final String senderEmail = parameterRepository.getOfficerParameterByName("MAIL_SMTP_SENDER_EMAIL");
        final String senderPassword = parameterRepository.getOfficerParameterByName("MAIL_SMTP_SENDER_PASSWORD");
        final String senderName = parameterRepository.getOfficerParameterByName("MAIL_SMTP_SENDER_NAME");

        final Properties properties = getProperties();

        mailEntity = SisMailEntity.builder()
                .senderEmail(senderEmail)
                .senderPassword(senderPassword)
                .senderName(senderName)
                .properties(properties).build();
    }

    @Override
    public void sendFirstPasswordEmail(final OfficerInfoDetailResponse infoDetailResponse) {
        Map<String, String> values = new HashMap<>();
        values.put(OFFICER_NAME, getOfficerName(infoDetailResponse));
        values.put(OFFICER_NUMBER, getOfficerNumber(infoDetailResponse));
        values.put(PASSWORD, getFirstPassword(infoDetailResponse));
        values.put(DATE, SisUtil.getCurrentFormattedDateTime());

        mailEntity.setTitle("Personel Hesabınız Başarıyla Oluşturuldu!");
        mailEntity.setTemplate(parameterRepository.getOfficerParameterByName("MAIL_TEMPLATE_FIRST_PASSWORD"));
        mailEntity.setTo(getOfficerPersonalEmail(infoDetailResponse));
        mailEntity.setValues(values);

        mailService.sendMail(mailEntity);
        log.info("First Password Email Successfully Sent to " + mailEntity.getTo());
    }

    @Override
    public void sendForgotPasswordEmail(final OfficerInfoDetailResponse infoDetailResponse) {
        Map<String, String> values = new HashMap<>();
        values.put(OFFICER_NAME, getOfficerName(infoDetailResponse));
        values.put(OFFICER_NUMBER, getOfficerNumber(infoDetailResponse));
        values.put(PASSWORD, getChangedPassword(infoDetailResponse));
        values.put(DATE, SisUtil.getCurrentFormattedDateTime());

        mailEntity.setTitle("Şifreniz Başarıyla Değiştirildi!");
        mailEntity.setTemplate(parameterRepository.getOfficerParameterByName("MAIL_TEMPLATE_FORGOT_PASSWORD"));
        mailEntity.setTo(getOfficerPersonalEmail(infoDetailResponse));
        mailEntity.setValues(values);

        mailService.sendMail(mailEntity);
        log.info("Forgot Password Email Successfully Sent to " + mailEntity.getTo());
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", parameterRepository.getOfficerParameterByName("MAIL_SMTP_HOST"));
        properties.put("mail.smtp.port", parameterRepository.getOfficerParameterByName("MAIL_SMTP_PORT"));
        properties.put("mail.smtp.auth", parameterRepository.getOfficerParameterByName("MAIL_SMTP_AUTH"));
        properties.put("mail.smtp.starttls.enable", parameterRepository.getOfficerParameterByName("MAIL_SMTP_START_TLS_ENABLE"));
        return properties;
    }

    private String getOfficerName(final OfficerInfoDetailResponse infoDetailResponse) {
        final String name = infoDetailResponse.getPersonalInfoResponse().getName();
        final String surname = infoDetailResponse.getPersonalInfoResponse().getSurname();
        return name + " " + surname;
    }

    private String getOfficerNumber(final OfficerInfoDetailResponse infoDetailResponse) {
        return String.valueOf(infoDetailResponse.getAcademicInfoResponse().getOfficerId());
    }

    private String getOfficerPersonalEmail(final OfficerInfoDetailResponse infoDetailResponse) {
        return infoDetailResponse.getPersonalInfoResponse().getEmail();
    }


    protected String getFirstPassword(final OfficerInfoDetailResponse infoDetailResponse) {
        final Long officerId = infoDetailResponse.getAcademicInfoResponse().getOfficerId();
        final String password = passwordService.generatePassword();
        loginRepository.saveFirstPassword(officerId, password);
        return password;
    }

    protected String getChangedPassword(final OfficerInfoDetailResponse infoDetailResponse) {
        final Long officerId = infoDetailResponse.getAcademicInfoResponse().getOfficerId();
        final String password = passwordService.generatePassword();
        loginRepository.updatePassword(officerId, password);
        return password;
    }
}
