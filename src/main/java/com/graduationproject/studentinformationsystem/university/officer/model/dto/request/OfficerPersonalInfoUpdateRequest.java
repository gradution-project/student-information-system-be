package com.graduationproject.studentinformationsystem.university.officer.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class OfficerPersonalInfoUpdateRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1091296670483009641L;

    @Valid
    @NotNull
    private OfficerPersonalInfoRequest personalInfoRequest;

    @Valid
    @NotNull
    private SisOperationInfoRequest operationInfoRequest;
}
