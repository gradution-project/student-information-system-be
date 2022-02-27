package com.graduationproject.studentinformationsystem.login.teacher.common.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.login.common.model.response.LoginResponse;
import com.graduationproject.studentinformationsystem.login.student.password.model.dto.response.StudentPasswordForgotResponse;
import com.graduationproject.studentinformationsystem.login.teacher.common.model.dto.request.TeacherForgotPasswordRequest;
import com.graduationproject.studentinformationsystem.login.teacher.common.model.dto.request.TeacherLoginRequest;

public interface TeacherLoginService {

    LoginResponse login(TeacherLoginRequest loginRequest);

    StudentPasswordForgotResponse forgotPassword(TeacherForgotPasswordRequest forgotPasswordRequest) throws SisNotExistException;
}
