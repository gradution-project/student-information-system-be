package com.graduationproject.studentinformationsystem.login.officer.password.model.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OfficerPasswordChangeEntity {

    protected String newPassword;
    protected String newPasswordRepeat;
}
