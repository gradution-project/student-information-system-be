package com.graduationproject.studentinformationsystem.login.teacher.model.dto.request;

import com.graduationproject.studentinformationsystem.common.util.validation.id.TeacherID;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class TeacherLoginRequest {

    @NotNull
    @TeacherID
    private Long teacherId;

    @NotNull
    private String password;
}
