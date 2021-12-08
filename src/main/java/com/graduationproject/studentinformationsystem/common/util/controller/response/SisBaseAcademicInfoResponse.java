package com.graduationproject.studentinformationsystem.common.util.controller.response;

import com.graduationproject.studentinformationsystem.common.model.dto.response.SisBaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class SisBaseAcademicInfoResponse extends SisBaseResponse {

    protected String email;
}
