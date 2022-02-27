package com.graduationproject.studentinformationsystem.login.teacher.password.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeacherPasswordForgotResponse {

    private boolean isForgotPasswordSuccess;
}
