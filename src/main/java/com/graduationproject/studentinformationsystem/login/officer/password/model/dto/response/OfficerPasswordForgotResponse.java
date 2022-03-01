package com.graduationproject.studentinformationsystem.login.officer.password.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OfficerPasswordForgotResponse {

    private boolean isForgotPasswordSuccess;
}
