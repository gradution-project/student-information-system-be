package com.graduationproject.studentinformationsystem.login.teacher.password.model.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeacherPasswordChangeEntity {

    protected String newPassword;
    protected String newPasswordRepeat;
}
