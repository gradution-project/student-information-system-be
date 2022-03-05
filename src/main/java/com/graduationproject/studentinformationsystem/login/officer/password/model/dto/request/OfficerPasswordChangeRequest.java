package com.graduationproject.studentinformationsystem.login.officer.password.model.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class OfficerPasswordChangeRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 2439291607232931077L;

    @NotNull
    private String operationId;

    @NotNull
    private String newPassword;

    @NotNull
    private String newPasswordRepeat;
}
