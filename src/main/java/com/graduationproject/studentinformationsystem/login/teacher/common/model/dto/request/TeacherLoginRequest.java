package com.graduationproject.studentinformationsystem.login.teacher.common.model.dto.request;

import com.graduationproject.studentinformationsystem.common.util.validation.id.TeacherID;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class TeacherLoginRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -1662653221462978195L;

    @NotNull
    @TeacherID
    private Long teacherId;

    @NotNull
    private String password;
}
