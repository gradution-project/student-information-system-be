package com.graduationproject.studentinformationsystem.login.officer.password.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OfficerPasswordChangeResponse {

    private boolean isPasswordChanged;

    public static OfficerPasswordChangeResponse getSuccessResponse() {
        return OfficerPasswordChangeResponse.builder()
                .isPasswordChanged(true).build();
    }

    public static OfficerPasswordChangeResponse getFailResponse() {
        return OfficerPasswordChangeResponse.builder()
                .isPasswordChanged(false).build();
    }
}
