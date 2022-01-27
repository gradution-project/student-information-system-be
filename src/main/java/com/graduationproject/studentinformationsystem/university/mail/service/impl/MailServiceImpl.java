package com.graduationproject.studentinformationsystem.university.mail.service.impl;

import com.graduationproject.studentinformationsystem.university.mail.model.entity.SisMailEntity;
import com.graduationproject.studentinformationsystem.university.mail.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Map;

@Slf4j
@Service
public class MailServiceImpl implements MailService {

    @Override
    public void sendMail(final SisMailEntity mailEntity) {
        try {
            final Authenticator auth = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mailEntity.getSenderEmail(), mailEntity.getSenderPassword());
                }
            };
            final Session session = Session.getInstance(mailEntity.getProperties(), auth);
            final MimeMessage message = new MimeMessage(session);

            final String mailContent = placeValuesToMailTemplateAndGetMailContent(mailEntity.getTemplate(), mailEntity.getValues());

            message.addHeader("Content-type", "text/HTML; charset=UTF-8");
            message.addHeader("format", "flowed");
            message.addHeader("Content-Transfer-Encoding", "8bit");
            message.setFrom(new InternetAddress(mailEntity.getSenderEmail(), mailEntity.getSenderName()));
            message.setReplyTo(InternetAddress.parse(mailEntity.getSenderEmail(), false));
            message.setSubject(mailEntity.getTitle(), "UTF-8");
            message.setContent(mailContent, "text/HTML; charset=UTF-8");
            message.setSentDate(new Date());
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailEntity.getTo(), false));

            Transport.send(message);
        } catch (Exception e) {
            log.error("Received Error While Sending Mail!", e);
        }
    }

    private String placeValuesToMailTemplateAndGetMailContent(final String mailTemplate, final Map<String, String> values) {
        return values.entrySet().stream()
                .reduce(mailTemplate, (s, e) -> s.replace("{" + e.getKey() + "}", e.getValue()), (s, s2) -> s);
    }
}
