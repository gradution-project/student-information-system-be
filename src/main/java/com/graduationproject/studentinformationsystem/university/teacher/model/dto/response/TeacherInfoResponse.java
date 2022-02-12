package com.graduationproject.studentinformationsystem.university.teacher.model.dto.response;

import com.graduationproject.studentinformationsystem.university.department.model.dto.response.DepartmentResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.enums.TeacherDegree;
import com.graduationproject.studentinformationsystem.university.teacher.model.enums.TeacherRole;
import com.graduationproject.studentinformationsystem.university.teacher.model.enums.TeacherStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeacherInfoResponse {

    private Long teacherId;
    private TeacherDegree degree;
    private TeacherRole role;
    private String fieldOfStudy;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private TeacherStatus status;
    private String registrationDate;

    private DepartmentResponse departmentResponse;
}
