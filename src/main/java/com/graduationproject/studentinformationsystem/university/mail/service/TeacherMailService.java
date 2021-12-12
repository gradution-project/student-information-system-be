package com.graduationproject.studentinformationsystem.university.mail.service;

import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherInfoDetailResponse;

public interface TeacherMailService {

    void sendFirstPasswordEmail(TeacherInfoDetailResponse infoDetailResponse);

    void sendForgotPasswordEmail(TeacherInfoDetailResponse infoDetailResponse);
}
