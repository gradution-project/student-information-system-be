package com.graduationproject.studentinformationsystem.university.mail.service;

import com.graduationproject.studentinformationsystem.university.mail.model.entity.MailEntity;

public interface MailService {

    void sendMail(MailEntity mailEntity);
}
