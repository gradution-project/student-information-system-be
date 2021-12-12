package com.graduationproject.studentinformationsystem.login.officer.model.dto.request;

import com.graduationproject.studentinformationsystem.common.util.validation.id.OfficerID;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class OfficerForgotPasswordRequest {

    @NotNull
    @OfficerID
    private Long officerId;
}
