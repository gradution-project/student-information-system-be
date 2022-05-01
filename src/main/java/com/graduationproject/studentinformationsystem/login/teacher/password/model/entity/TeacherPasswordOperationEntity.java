package com.graduationproject.studentinformationsystem.login.teacher.password.model.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class TeacherPasswordOperationEntity {

    private String operationId;
    private Long teacherId;
    private LocalDateTime expireDate;
}
