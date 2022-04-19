package com.graduationproject.studentinformationsystem.login.student.password.model.dto.request;

import com.graduationproject.studentinformationsystem.common.util.validation.id.StudentID;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class StudentPasswordForgotRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -6073520178120747403L;

    @NotNull
    @StudentID
    private Long studentId;
}
