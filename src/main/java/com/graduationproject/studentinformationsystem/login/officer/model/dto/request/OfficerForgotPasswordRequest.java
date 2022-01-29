package com.graduationproject.studentinformationsystem.login.officer.model.dto.request;

import com.graduationproject.studentinformationsystem.common.util.validation.id.OfficerID;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class OfficerForgotPasswordRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 5433368077043727358L;

    @NotNull
    @OfficerID
    private Long officerId;
}
