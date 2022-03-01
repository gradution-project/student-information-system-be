package com.graduationproject.studentinformationsystem.login.student.password.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentPasswordForgotResponse {

    private boolean isForgotPasswordSuccess;
}
