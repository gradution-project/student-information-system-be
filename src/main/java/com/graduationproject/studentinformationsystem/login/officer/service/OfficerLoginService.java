package com.graduationproject.studentinformationsystem.login.officer.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.login.common.model.response.ForgotPasswordResponse;
import com.graduationproject.studentinformationsystem.login.common.model.response.LoginResponse;
import com.graduationproject.studentinformationsystem.login.officer.model.dto.request.OfficerForgotPasswordRequest;
import com.graduationproject.studentinformationsystem.login.officer.model.dto.request.OfficerLoginRequest;

public interface OfficerLoginService {

    LoginResponse login(final OfficerLoginRequest loginRequest);

    ForgotPasswordResponse forgotPassword(final OfficerForgotPasswordRequest forgotPasswordRequest)
            throws SisNotExistException;
}
