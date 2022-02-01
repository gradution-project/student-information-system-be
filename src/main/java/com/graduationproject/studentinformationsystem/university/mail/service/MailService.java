package com.graduationproject.studentinformationsystem.university.mail.service;

import com.graduationproject.studentinformationsystem.university.mail.model.entity.SisMailEntity;

public interface MailService {

    void sendMail(SisMailEntity mailEntity);
}
