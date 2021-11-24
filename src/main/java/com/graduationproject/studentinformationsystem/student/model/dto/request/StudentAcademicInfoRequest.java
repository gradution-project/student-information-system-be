package com.graduationproject.studentinformationsystem.student.model.dto.request;

import com.graduationproject.studentinformationsystem.common.util.validation.OnlyNumber;
import com.graduationproject.studentinformationsystem.student.model.enums.StudentClassLevel;
import com.graduationproject.studentinformationsystem.student.model.enums.StudentDegree;
import com.graduationproject.studentinformationsystem.student.model.enums.StudentStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class StudentAcademicInfoRequest {

    @NotNull
    @OnlyNumber
    @Size(max = 3)
    private Long departmentId;

    @NotNull
    private StudentDegree degree;

    @NotNull
    private StudentClassLevel classLevel;

    @NotNull
    @Email
    @Size(max = 100)
    private String email;

    @NotNull
    private StudentStatus status;
}
