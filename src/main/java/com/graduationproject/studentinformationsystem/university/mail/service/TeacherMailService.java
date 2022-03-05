package com.graduationproject.studentinformationsystem.university.mail.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherInfoDetailResponse;

public interface TeacherMailService {

    void sendSavedEmail(TeacherInfoDetailResponse infoDetailResponse) throws SisNotExistException;

    void sendForgotPasswordEmail(TeacherInfoDetailResponse infoDetailResponse) throws SisNotExistException;
}
