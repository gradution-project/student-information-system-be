package com.graduationproject.studentinformationsystem.university.graduation.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class StudentGraduationRejectRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1986448762153152464L;

    @NotNull
    private String graduationId;

    @Valid
    private SisOperationInfoRequest operationInfoRequest;
}
