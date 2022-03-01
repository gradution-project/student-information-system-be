package com.graduationproject.studentinformationsystem.login.officer.password.model.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class OfficerPasswordOperationResponse {

    private String operationId;
    private Long officerId;
    private LocalDateTime expireDate;
    private String passwordChangeUrl;
}
