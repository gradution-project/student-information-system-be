package com.graduationproject.studentinformationsystem.university.teacher.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TeacherResponse {

    private Long teacherId;
    private Long departmentId;
    private String degree;
    private String role;
    private String fieldOfStudy;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String status;
    private String registrationDate;
}
