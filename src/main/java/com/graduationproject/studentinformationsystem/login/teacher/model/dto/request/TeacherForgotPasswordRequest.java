package com.graduationproject.studentinformationsystem.login.teacher.model.dto.request;

import com.graduationproject.studentinformationsystem.common.util.validation.id.TeacherID;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class TeacherForgotPasswordRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 7656983099642564936L;

    @NotNull
    @TeacherID
    private Long teacherId;
}
