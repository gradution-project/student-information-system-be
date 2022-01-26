package com.graduationproject.studentinformationsystem.university.student.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
public class StudentUpdatePersonalInfoRequest {

    @Valid
    @NotNull
    private StudentPersonalInfoRequest personalInfoRequest;

    @Valid
    @NotNull
    private SisOperationInfoRequest operationInfoRequest;
}
