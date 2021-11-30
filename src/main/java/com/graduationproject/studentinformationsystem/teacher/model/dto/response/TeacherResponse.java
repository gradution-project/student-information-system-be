package com.graduationproject.studentinformationsystem.teacher.model.dto.response;

import com.graduationproject.studentinformationsystem.teacher.model.enums.TeacherGroup;
import com.graduationproject.studentinformationsystem.teacher.model.enums.TeacherRole;
import com.graduationproject.studentinformationsystem.teacher.model.enums.TeacherStatus;
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
    private TeacherGroup group;
    private TeacherRole role;
    private String fieldOfStudy;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private TeacherStatus status;
    private Date registrationDate;
}