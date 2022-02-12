package com.graduationproject.studentinformationsystem.common.model.dto.response;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public abstract class SisBaseAcademicInfoResponse extends SisBaseResponse {

    protected String email;
    protected String registrationDate;
}
