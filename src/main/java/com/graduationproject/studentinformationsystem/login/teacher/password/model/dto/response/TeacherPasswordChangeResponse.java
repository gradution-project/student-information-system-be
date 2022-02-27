package com.graduationproject.studentinformationsystem.login.teacher.password.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeacherPasswordChangeResponse {

    private boolean isPasswordChanged;

    public static TeacherPasswordChangeResponse getSuccessResponse() {
        return TeacherPasswordChangeResponse.builder()
                .isPasswordChanged(true).build();
    }

    public static TeacherPasswordChangeResponse getFailResponse() {
        return TeacherPasswordChangeResponse.builder()
                .isPasswordChanged(false).build();
    }
}
