package com.graduationproject.studentinformationsystem.university.officer.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.validation.id.OfficerID;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class OfficerDeleteRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -5696186564804029839L;

    @OfficerID
    @NotNull
    private Long officerId;

    @Valid
    @NotNull
    private SisOperationInfoRequest operationInfoRequest;
}
