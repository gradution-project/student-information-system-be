package com.graduationproject.studentinformationsystem.university.teacher.model.dto.response;

import com.graduationproject.studentinformationsystem.common.model.dto.response.SisBaseAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.department.model.dto.response.DepartmentResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.enums.TeacherDegree;
import com.graduationproject.studentinformationsystem.university.teacher.model.enums.TeacherRole;
import com.graduationproject.studentinformationsystem.university.teacher.model.enums.TeacherStatus;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class TeacherAcademicInfoResponse extends SisBaseAcademicInfoResponse {

    private Long teacherId;
    private TeacherStatus status;
    private TeacherDegree degree;
    private TeacherRole role;
    private String fieldOfStudy;
    private String phoneNumber;

    private DepartmentResponse departmentResponse;
}
