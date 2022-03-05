package com.graduationproject.studentinformationsystem.login.teacher.common.service;

import com.graduationproject.studentinformationsystem.login.common.model.response.LoginResponse;
import com.graduationproject.studentinformationsystem.login.teacher.common.model.dto.request.TeacherLoginRequest;

public interface TeacherLoginService {

    LoginResponse login(TeacherLoginRequest loginRequest);
}
