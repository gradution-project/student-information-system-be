package com.graduationproject.studentinformationsystem.login.teacher.model.entity;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class TeacherLoginInfoEntity {

    private Long teacherId;
    private Integer failCounter;
    private Date lastLoginDate;
}
