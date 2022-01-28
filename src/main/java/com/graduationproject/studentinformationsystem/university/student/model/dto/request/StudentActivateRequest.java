package com.graduationproject.studentinformationsystem.university.student.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.validation.id.StudentID;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
public class StudentActivateRequest {

    @StudentID
    @NotNull
    private Long studentId;

    @Valid
    @NotNull
    private SisOperationInfoRequest operationInfoRequest;
}
