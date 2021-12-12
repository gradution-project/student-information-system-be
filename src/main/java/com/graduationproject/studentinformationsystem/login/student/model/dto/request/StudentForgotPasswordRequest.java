package com.graduationproject.studentinformationsystem.login.student.model.dto.request;

import com.graduationproject.studentinformationsystem.common.util.validation.id.StudentID;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class StudentForgotPasswordRequest {

    @NotNull
    @StudentID
    private Long studentId;
}
