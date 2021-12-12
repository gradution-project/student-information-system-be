package com.graduationproject.studentinformationsystem.teacher.model.dto.request;

import com.graduationproject.studentinformationsystem.common.util.validation.id.DepartmentID;
import com.graduationproject.studentinformationsystem.teacher.model.enums.TeacherDegree;
import com.graduationproject.studentinformationsystem.teacher.model.enums.TeacherRole;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class TeacherAcademicInfoRequest {

    @NotNull
    @DepartmentID
    private Long departmentId;

    @NotNull
    private TeacherDegree degree;

    @NotNull
    private TeacherRole role;

    @NotNull
    @Size(min = 3, max = 256)
    private String fieldOfStudy;

    @NotNull
    private Long phoneNumber;
}
