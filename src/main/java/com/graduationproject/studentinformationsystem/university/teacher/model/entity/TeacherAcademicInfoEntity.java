package com.graduationproject.studentinformationsystem.university.teacher.model.entity;

import com.graduationproject.studentinformationsystem.common.model.entity.SisBaseEntity;
import com.graduationproject.studentinformationsystem.university.teacher.model.enums.TeacherDegree;
import com.graduationproject.studentinformationsystem.university.teacher.model.enums.TeacherRole;
import com.graduationproject.studentinformationsystem.university.teacher.model.enums.TeacherStatus;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@SuperBuilder
public class TeacherAcademicInfoEntity extends SisBaseEntity {

    private Long teacherId;
    private Long departmentId;
    private TeacherDegree degree;
    private TeacherRole role;
    private String fieldOfStudy;
    private Long phoneNumber;
    private String email;
    private TeacherStatus status;
    private Date registrationDate;
}
