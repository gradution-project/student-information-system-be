package com.graduationproject.studentinformationsystem.university.student.model.dto.response;

import com.graduationproject.studentinformationsystem.common.util.controller.response.SisBaseAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.department.model.dto.response.DepartmentResponse;
import com.graduationproject.studentinformationsystem.university.student.model.enums.StudentClassLevel;
import com.graduationproject.studentinformationsystem.university.student.model.enums.StudentDegree;
import com.graduationproject.studentinformationsystem.university.student.model.enums.StudentStatus;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class StudentAcademicInfoResponse extends SisBaseAcademicInfoResponse {

    private Long studentId;
    private StudentStatus status;
    private StudentDegree degree;
    private StudentClassLevel classLevel;

    private DepartmentResponse departmentResponse;
}
