package com.graduationproject.studentinformationsystem.login.teacher.password.model.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class TeacherPasswordOperationResponse {

    private String operationId;
    private Long teacherId;
    private LocalDateTime expireDate;
    private String passwordChangeUrl;
}
