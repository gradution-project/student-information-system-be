package com.graduationproject.studentinformationsystem.student.model.entity;

import com.graduationproject.studentinformationsystem.common.model.entity.BasePersonalInfoEntity;
import com.graduationproject.studentinformationsystem.student.model.enums.StudentStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class StudentPersonalInfoEntity extends BasePersonalInfoEntity {

    private Long studentId;
    private StudentStatus status;
}
