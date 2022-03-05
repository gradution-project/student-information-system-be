package com.graduationproject.studentinformationsystem.university.mail.service.impl;

import com.graduationproject.studentinformationsystem.common.util.SisUtil;
import com.graduationproject.studentinformationsystem.login.officer.password.service.OfficerPasswordOperationOutService;
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

    private final OfficerPasswordOperationOutService passwordOperationOutService;

    private final MailService mailService;

    private final ParameterRepository parameterRepository;

    private SisMailEntity mailEntity;

    private static final String OFFICER_NAME = "officerName";
    private static final String OFFICER_NUMBER = "officerNumber";
    private static final String DATE = "date";
    private static final String CHANGE_PASSWORD_URL = "changePasswordUrl";

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
    public void sendSavedEmail(final OfficerInfoDetailResponse infoDetailResponse) {
        final String personalEmail = infoDetailResponse.getPersonalInfoResponse().getEmail();

        Map<String, String> values = getMailValues(infoDetailResponse);

        mailEntity.setTitle("Personel Hesabınız Başarıyla Oluşturuldu!");
        mailEntity.setTemplate(parameterRepository.getOfficerParameterByName("MAIL_TEMPLATE_FIRST_PASSWORD"));
        mailEntity.setTo(personalEmail);
        mailEntity.setValues(values);

        mailService.sendMail(mailEntity);
        log.info("Officer First Password Email Successfully Sent to " + mailEntity.getTo());
    }

    @Override
    public void sendForgotPasswordEmail(final OfficerInfoDetailResponse infoDetailResponse) {
        final String personalEmail = infoDetailResponse.getPersonalInfoResponse().getEmail();

        Map<String, String> values = getMailValues(infoDetailResponse);

        mailEntity.setTitle("Şifre Değiştirme İsteğiniz İle İlgili!");
        mailEntity.setTemplate(parameterRepository.getOfficerParameterByName("MAIL_TEMPLATE_FORGOT_PASSWORD"));
        mailEntity.setTo(personalEmail);
        mailEntity.setValues(values);

        mailService.sendMail(mailEntity);
        log.info("Officer Forgot Password Email Successfully Sent to " + mailEntity.getTo());
    }


    private Map<String, String> getMailValues(final OfficerInfoDetailResponse infoDetailResponse) {
        final Long officerId = infoDetailResponse.getAcademicInfoResponse().getOfficerId();
        final String name = infoDetailResponse.getPersonalInfoResponse().getName();
        final String surname = infoDetailResponse.getPersonalInfoResponse().getSurname();

        Map<String, String> values = new HashMap<>();
        values.put(OFFICER_NAME, getOfficerFullName(name, surname));
        values.put(OFFICER_NUMBER, getOfficerNumber(officerId));
        values.put(DATE, SisUtil.getCurrentFormattedDateTime());
        values.put(CHANGE_PASSWORD_URL, getChangePasswordUrl(officerId));
        return values;
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", parameterRepository.getOfficerParameterByName("MAIL_SMTP_HOST"));
        properties.put("mail.smtp.port", parameterRepository.getOfficerParameterByName("MAIL_SMTP_PORT"));
        properties.put("mail.smtp.auth", parameterRepository.getOfficerParameterByName("MAIL_SMTP_AUTH"));
        properties.put("mail.smtp.starttls.enable", parameterRepository.getOfficerParameterByName("MAIL_SMTP_START_TLS_ENABLE"));
        return properties;
    }

    private String getOfficerFullName(final String name, final String surname) {
        return name + " " + surname;
    }

    private String getOfficerNumber(final Long officerId) {
        return String.valueOf(officerId);
    }


    protected String getChangePasswordUrl(final Long officerId) {
        return passwordOperationOutService.getPasswordChangeUrl(officerId);
    }
}
