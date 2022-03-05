package com.graduationproject.studentinformationsystem.login.officer.common.service;

import com.graduationproject.studentinformationsystem.login.common.model.response.LoginResponse;
import com.graduationproject.studentinformationsystem.login.officer.common.model.dto.request.OfficerLoginRequest;

public interface OfficerLoginService {

    LoginResponse login(OfficerLoginRequest loginRequest);
}
