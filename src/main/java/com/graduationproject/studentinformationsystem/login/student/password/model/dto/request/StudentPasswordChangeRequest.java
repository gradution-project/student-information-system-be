package com.graduationproject.studentinformationsystem.login.student.password.model.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class StudentPasswordChangeRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 6980895876807894275L;

    @NotNull
    private String operationId;

    @NotNull
    private String newPassword;

    @NotNull
    private String newPasswordRepeat;
}
