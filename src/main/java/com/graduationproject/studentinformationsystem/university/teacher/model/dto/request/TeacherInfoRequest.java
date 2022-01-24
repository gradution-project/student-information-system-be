package com.graduationproject.studentinformationsystem.university.teacher.model.dto.request;

import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
public class TeacherInfoRequest {

    @Valid
    @NotNull
    private TeacherAcademicInfoRequest academicInfoRequest;

    @Valid
    @NotNull
    private TeacherPersonalInfoRequest personalInfoRequest;
}
