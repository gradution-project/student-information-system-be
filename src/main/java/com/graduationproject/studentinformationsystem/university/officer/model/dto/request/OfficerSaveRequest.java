package com.graduationproject.studentinformationsystem.university.officer.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class OfficerSaveRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 4305585560721919646L;

    @Valid
    @NotNull
    private OfficerAcademicInfoRequest academicInfoRequest;

    @Valid
    @NotNull
    private OfficerPersonalInfoRequest personalInfoRequest;

    @Valid
    @NotNull
    private SisOperationInfoRequest operationInfoRequest;
}
