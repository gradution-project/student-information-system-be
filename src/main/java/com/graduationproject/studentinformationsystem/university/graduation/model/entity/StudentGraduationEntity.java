package com.graduationproject.studentinformationsystem.university.graduation.model.entity;

import com.graduationproject.studentinformationsystem.common.model.entity.SisBaseEntity;
import com.graduationproject.studentinformationsystem.university.graduation.model.enums.StudentGraduationStatus;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class StudentGraduationEntity extends SisBaseEntity {

    private String graduationId;
    private Long studentId;
    private StudentGraduationStatus status;
}
