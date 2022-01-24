package com.graduationproject.studentinformationsystem.university.student.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentResponse {

    private Long studentId;
    private Long departmentId;
    private String degree;
    private String classLevel;
    private String name;
    private String surname;
    private String email;
    private String status;
    private String registrationDate;
}
