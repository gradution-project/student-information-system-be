package com.graduationproject.studentinformationsystem.login.officer.password.model.dto.request;

import com.graduationproject.studentinformationsystem.common.util.validation.id.OfficerID;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class OfficerPasswordForgotRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 8073197753505975378L;

    @NotNull
    @OfficerID
    private Long officerId;
}
