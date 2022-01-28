package com.graduationproject.studentinformationsystem.university.teacher.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class TeacherAcademicInfoUpdateRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 8306201408057203529L;

    @Valid
    @NotNull
    private TeacherAcademicInfoRequest academicInfoRequest;

    @Valid
    @NotNull
    private SisOperationInfoRequest operationInfoRequest;
}
