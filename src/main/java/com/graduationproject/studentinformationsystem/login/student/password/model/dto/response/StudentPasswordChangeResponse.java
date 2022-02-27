package com.graduationproject.studentinformationsystem.login.student.password.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentPasswordChangeResponse {

    private boolean isPasswordChanged;

    public static StudentPasswordChangeResponse getSuccessResponse() {
        return StudentPasswordChangeResponse.builder()
                .isPasswordChanged(true).build();
    }

    public static StudentPasswordChangeResponse getFailResponse() {
        return StudentPasswordChangeResponse.builder()
                .isPasswordChanged(false).build();
    }
}
