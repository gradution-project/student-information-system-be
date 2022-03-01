package com.graduationproject.studentinformationsystem.login.student.password.model.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentPasswordChangeEntity {

    protected String newPassword;
    protected String newPasswordRepeat;
}
