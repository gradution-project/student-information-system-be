package com.graduationproject.studentinformationsystem.login.student.password.model.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class StudentPasswordOperationResponse {

    private String operationId;
    private Long studentId;
    private LocalDateTime expireDate;
    private String passwordChangeUrl;
}
