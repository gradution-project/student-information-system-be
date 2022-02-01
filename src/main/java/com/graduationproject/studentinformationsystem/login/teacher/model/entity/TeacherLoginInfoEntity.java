package com.graduationproject.studentinformationsystem.login.teacher.model.entity;

import com.graduationproject.studentinformationsystem.common.model.entity.SisBaseLoginInfoEntity;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class TeacherLoginInfoEntity extends SisBaseLoginInfoEntity {

    private Long teacherId;
}
