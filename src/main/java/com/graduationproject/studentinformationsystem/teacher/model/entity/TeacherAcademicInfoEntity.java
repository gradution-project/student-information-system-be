package com.graduationproject.studentinformationsystem.teacher.model.entity;

import com.graduationproject.studentinformationsystem.common.model.entity.BaseEntity;
import com.graduationproject.studentinformationsystem.teacher.model.enums.TeacherGroup;
import com.graduationproject.studentinformationsystem.teacher.model.enums.TeacherRole;
import com.graduationproject.studentinformationsystem.teacher.model.enums.TeacherStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@SuperBuilder
public class TeacherAcademicInfoEntity extends BaseEntity {

    private Long teacherId;
    private Long departmentId;
    private TeacherGroup group;
    private TeacherRole role;
    private String fieldOfStudy;
    private String phoneNumber;
    private String email;
    private TeacherStatus status;
    private Date registrationDate;
}
