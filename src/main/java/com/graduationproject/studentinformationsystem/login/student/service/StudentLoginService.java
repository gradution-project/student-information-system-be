package com.graduationproject.studentinformationsystem.login.student.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.login.common.model.response.ForgotPasswordResponse;
import com.graduationproject.studentinformationsystem.login.common.model.response.LoginResponse;
import com.graduationproject.studentinformationsystem.login.student.model.dto.request.StudentForgotPasswordRequest;
import com.graduationproject.studentinformationsystem.login.student.model.dto.request.StudentLoginRequest;

public interface StudentLoginService {

    LoginResponse login(StudentLoginRequest loginRequest);

    ForgotPasswordResponse forgotPassword(StudentForgotPasswordRequest forgotPasswordRequest) throws SisNotExistException;
}
