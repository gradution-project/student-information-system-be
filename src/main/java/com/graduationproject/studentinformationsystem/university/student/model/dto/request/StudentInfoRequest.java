package com.graduationproject.studentinformationsystem.university.student.model.dto.request;

import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
public class StudentInfoRequest {

    @Valid
    @NotNull
    private StudentAcademicInfoRequest academicInfoRequest;

    @Valid
    @NotNull
    private StudentPersonalInfoRequest personalInfoRequest;
}
