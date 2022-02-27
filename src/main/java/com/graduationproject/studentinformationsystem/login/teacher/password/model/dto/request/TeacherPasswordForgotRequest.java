package com.graduationproject.studentinformationsystem.login.teacher.password.model.dto.request;

import com.graduationproject.studentinformationsystem.common.util.validation.id.TeacherID;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class TeacherPasswordForgotRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 150037221929036553L;

    @NotNull
    @TeacherID
    private Long teacherId;

    @NotNull
    private String feUrl;
}
