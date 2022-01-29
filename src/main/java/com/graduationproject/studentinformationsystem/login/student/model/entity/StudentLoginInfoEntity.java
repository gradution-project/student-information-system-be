package com.graduationproject.studentinformationsystem.login.student.model.entity;

import com.graduationproject.studentinformationsystem.common.model.entity.SisBaseLoginEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentLoginInfoEntity extends SisBaseLoginEntity {

    private Long studentId;
}
