package com.graduationproject.studentinformationsystem.login.student.common.model.entity;

import com.graduationproject.studentinformationsystem.common.model.entity.SisBaseLoginInfoEntity;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class StudentLoginInfoEntity extends SisBaseLoginInfoEntity {

    private Long studentId;
}
