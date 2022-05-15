package com.graduationproject.studentinformationsystem.university.graduation.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class StudentGraduationApproveRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 2432971035008247674L;

    @NotNull
    private String graduationId;

    @Valid
    @NotNull
    private SisOperationInfoRequest operationInfoRequest;
}
