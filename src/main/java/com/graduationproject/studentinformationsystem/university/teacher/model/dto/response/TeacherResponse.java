package com.graduationproject.studentinformationsystem.university.teacher.model.dto.response;

import com.graduationproject.studentinformationsystem.university.teacher.model.enums.TeacherDegree;
import com.graduationproject.studentinformationsystem.university.teacher.model.enums.TeacherRole;
import com.graduationproject.studentinformationsystem.university.teacher.model.enums.TeacherStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class TeacherResponse {

    private Long teacherId;
    private Long departmentId;
    private TeacherDegree degree;
    private TeacherRole role;
    private String fieldOfStudy;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private TeacherStatus status;
    private Date registrationDate;
}
