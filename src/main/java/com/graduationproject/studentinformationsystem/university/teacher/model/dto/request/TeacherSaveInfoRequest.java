package com.graduationproject.studentinformationsystem.university.teacher.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
public class TeacherSaveInfoRequest {

    @Valid
    @NotNull
    private TeacherAcademicInfoRequest academicInfoRequest;

    @Valid
    @NotNull
    private TeacherPersonalInfoRequest personalInfoRequest;

    @Valid
    @NotNull
    private SisOperationInfoRequest operationInfoRequest;
}
