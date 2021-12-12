package com.graduationproject.studentinformationsystem.login.teacher.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.login.common.model.response.ForgotPasswordResponse;
import com.graduationproject.studentinformationsystem.login.common.model.response.LoginResponse;
import com.graduationproject.studentinformationsystem.login.teacher.model.dto.request.TeacherForgotPasswordRequest;
import com.graduationproject.studentinformationsystem.login.teacher.model.dto.request.TeacherLoginRequest;

public interface TeacherLoginService {

    LoginResponse login(TeacherLoginRequest loginRequest);

    ForgotPasswordResponse forgotPassword(TeacherForgotPasswordRequest forgotPasswordRequest) throws SisNotExistException;
}
