package com.graduationproject.studentinformationsystem.university.teacher.model.dto.response;

import com.graduationproject.studentinformationsystem.university.department.model.dto.response.DepartmentResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeacherInfoResponse {

    private Long teacherId;
    private String degree;
    private String role;
    private String fieldOfStudy;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String status;
    private String registrationDate;

    private DepartmentResponse departmentResponse;
}
