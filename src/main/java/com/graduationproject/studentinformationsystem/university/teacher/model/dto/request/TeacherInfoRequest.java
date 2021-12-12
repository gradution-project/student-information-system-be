package com.graduationproject.studentinformationsystem.university.teacher.model.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TeacherInfoRequest {

    @Valid
    @NotNull
    private TeacherAcademicInfoRequest academicInfoRequest;

    @Valid
    @NotNull
    private TeacherPersonalInfoRequest personalInfoRequest;
}
