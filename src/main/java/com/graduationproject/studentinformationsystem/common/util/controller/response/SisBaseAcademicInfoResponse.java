package com.graduationproject.studentinformationsystem.common.util.controller.response;

import com.graduationproject.studentinformationsystem.common.model.dto.response.SisBaseResponse;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public abstract class SisBaseAcademicInfoResponse extends SisBaseResponse {

    protected String status;
    protected String email;
    protected String registrationDate;
}
