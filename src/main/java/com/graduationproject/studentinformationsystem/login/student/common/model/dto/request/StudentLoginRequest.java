package com.graduationproject.studentinformationsystem.login.student.common.model.dto.request;

import com.graduationproject.studentinformationsystem.common.util.validation.id.StudentID;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class StudentLoginRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -3581535510670599203L;

    @NotNull
    @StudentID
    private Long studentId;

    @NotNull
    private String password;
}
