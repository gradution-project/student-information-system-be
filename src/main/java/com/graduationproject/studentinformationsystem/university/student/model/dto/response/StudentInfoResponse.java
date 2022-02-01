package com.graduationproject.studentinformationsystem.university.student.model.dto.response;

import com.graduationproject.studentinformationsystem.university.department.model.dto.response.DepartmentResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentInfoResponse {

    private Long studentId;
    private String degree;
    private String classLevel;
    private String name;
    private String surname;
    private String email;
    private String status;
    private String registrationDate;

    private DepartmentResponse departmentResponse;
}
