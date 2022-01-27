package com.graduationproject.studentinformationsystem.university.mail.service;

import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherInfoDetailResponse;

public interface TeacherMailService {

    void sendFirstPasswordEmail(final TeacherInfoDetailResponse infoDetailResponse);

    void sendForgotPasswordEmail(final TeacherInfoDetailResponse infoDetailResponse);
}
