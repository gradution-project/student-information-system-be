package com.graduationproject.studentinformationsystem.university.teacher.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class TeacherSaveRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 3910560473819652137L;

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
