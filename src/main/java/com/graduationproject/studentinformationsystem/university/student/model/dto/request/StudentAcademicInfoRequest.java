package com.graduationproject.studentinformationsystem.university.student.model.dto.request;

import com.graduationproject.studentinformationsystem.common.util.validation.id.DepartmentID;
import com.graduationproject.studentinformationsystem.university.student.model.enums.StudentClassLevel;
import com.graduationproject.studentinformationsystem.university.student.model.enums.StudentDegree;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class StudentAcademicInfoRequest {

    @NotNull
    @DepartmentID
    private Long departmentId;

    @NotNull
    private StudentDegree degree;

    @NotNull
    private StudentClassLevel classLevel;
}
