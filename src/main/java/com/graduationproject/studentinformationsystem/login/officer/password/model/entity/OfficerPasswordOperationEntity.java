package com.graduationproject.studentinformationsystem.login.officer.password.model.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class OfficerPasswordOperationEntity {

    private String operationId;
    private Long officerId;
    private LocalDateTime expireDate;
    private String feUrl;
}
