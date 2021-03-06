package com.graduationproject.studentinformationsystem.university.student.model.entity;

import com.graduationproject.studentinformationsystem.common.model.entity.SisBasePersonalInfoEntity;
import com.graduationproject.studentinformationsystem.university.student.model.enums.StudentStatus;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class StudentPersonalInfoEntity extends SisBasePersonalInfoEntity {

    private Long studentId;
    private StudentStatus status;
}
