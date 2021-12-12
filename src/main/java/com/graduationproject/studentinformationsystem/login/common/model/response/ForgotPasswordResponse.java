package com.graduationproject.studentinformationsystem.login.common.model.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ForgotPasswordResponse {

    private boolean isForgotPasswordSuccess;
}
