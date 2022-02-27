package com.graduationproject.studentinformationsystem.login.teacher.password.model.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class TeacherPasswordChangeRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -2742530497673012860L;

    @NotNull
    private String operationId;

    @NotNull
    private String newPassword;

    @NotNull
    private String newPasswordRepeat;
}
