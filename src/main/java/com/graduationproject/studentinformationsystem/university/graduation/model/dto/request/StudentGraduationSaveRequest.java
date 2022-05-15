package com.graduationproject.studentinformationsystem.university.graduation.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.validation.id.StudentID;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class StudentGraduationSaveRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -482762006018299741L;

    @NotNull
    @StudentID
    private Long studentId;

    @Valid
    @NotNull
    private SisOperationInfoRequest operationInfoRequest;
}
