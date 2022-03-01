package com.graduationproject.studentinformationsystem.login.student.password.model.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class StudentPasswordOperationEntity {

    private String operationId;
    private Long studentId;
    private LocalDateTime expireDate;
    private String feUrl;
}
