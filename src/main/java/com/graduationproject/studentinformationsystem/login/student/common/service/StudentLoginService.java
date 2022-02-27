package com.graduationproject.studentinformationsystem.login.student.common.service;

import com.graduationproject.studentinformationsystem.login.common.model.response.LoginResponse;
import com.graduationproject.studentinformationsystem.login.student.common.model.dto.request.StudentLoginRequest;

public interface StudentLoginService {

    LoginResponse login(StudentLoginRequest loginRequest);
}
