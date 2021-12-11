package com.graduationproject.studentinformationsystem.university.mail.service;

import com.graduationproject.studentinformationsystem.student.model.dto.response.StudentInfoDetailResponse;

public interface StudentMailService {

    void sendFirstPasswordEmail(StudentInfoDetailResponse studentInfoDetailResponse);

    void sendForgotPasswordEmail(StudentInfoDetailResponse studentInfoDetailResponse);
}
