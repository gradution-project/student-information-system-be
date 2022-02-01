package com.graduationproject.studentinformationsystem.university.teacher.model.dto.response;

import com.graduationproject.studentinformationsystem.common.util.controller.response.SisBaseAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.department.model.dto.response.DepartmentResponse;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class TeacherAcademicInfoResponse extends SisBaseAcademicInfoResponse {

    private Long teacherId;
    private String degree;
    private String role;
    private String fieldOfStudy;
    private String phoneNumber;

    private DepartmentResponse departmentResponse;
}
