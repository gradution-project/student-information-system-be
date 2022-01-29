package com.graduationproject.studentinformationsystem.login.teacher.model.entity;

import com.graduationproject.studentinformationsystem.common.model.entity.SisBaseLoginEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeacherLoginInfoEntity extends SisBaseLoginEntity {

    private Long teacherId;
}
