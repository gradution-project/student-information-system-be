package com.graduationproject.studentinformationsystem.university.mail.service;

import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentInfoDetailResponse;

public interface StudentMailService {

    void sendFirstPasswordEmail(final StudentInfoDetailResponse infoDetailResponse);

    void sendForgotPasswordEmail(final StudentInfoDetailResponse infoDetailResponse);
}
