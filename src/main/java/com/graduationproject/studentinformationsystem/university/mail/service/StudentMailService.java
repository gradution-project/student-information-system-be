package com.graduationproject.studentinformationsystem.university.mail.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentInfoDetailResponse;

public interface StudentMailService {

    void sendSavedEmail(StudentInfoDetailResponse infoDetailResponse) throws SisNotExistException;

    void sendForgotPasswordEmail(StudentInfoDetailResponse infoDetailResponse) throws SisNotExistException;
}
