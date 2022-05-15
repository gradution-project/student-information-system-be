package com.graduationproject.studentinformationsystem.university.graduation.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class StudentGraduationConfirmRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -1281697323837473257L;

    @NotNull
    private String graduationId;

    @Valid
    @NotNull
    private SisOperationInfoRequest operationInfoRequest;
}
