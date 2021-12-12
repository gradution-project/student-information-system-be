package com.graduationproject.studentinformationsystem.student.model.dto.request;

import com.graduationproject.studentinformationsystem.common.util.validation.id.DepartmentID;
import com.graduationproject.studentinformationsystem.student.model.enums.StudentClassLevel;
import com.graduationproject.studentinformationsystem.student.model.enums.StudentDegree;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class StudentAcademicInfoRequest {

    @NotNull
    @DepartmentID
    private Long departmentId;

    @NotNull
    private StudentDegree degree;

    @NotNull
    private StudentClassLevel classLevel;
}
