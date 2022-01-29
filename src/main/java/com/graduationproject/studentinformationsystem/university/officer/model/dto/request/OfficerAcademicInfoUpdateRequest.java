package com.graduationproject.studentinformationsystem.university.officer.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class OfficerAcademicInfoUpdateRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 6391085425145205086L;

    @Valid
    @NotNull
    private OfficerAcademicInfoRequest academicInfoRequest;

    @Valid
    @NotNull
    private SisOperationInfoRequest operationInfoRequest;
}
