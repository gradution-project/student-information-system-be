package com.graduationproject.studentinformationsystem.university.teacher.model.entity;

import com.graduationproject.studentinformationsystem.common.model.entity.SisBasePersonalInfoEntity;
import com.graduationproject.studentinformationsystem.university.teacher.model.enums.TeacherStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class TeacherPersonalInfoEntity extends SisBasePersonalInfoEntity {

    private Long teacherId;
    private TeacherStatus status;
}