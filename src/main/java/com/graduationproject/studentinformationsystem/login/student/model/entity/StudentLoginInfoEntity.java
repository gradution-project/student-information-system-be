package com.graduationproject.studentinformationsystem.login.student.model.entity;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class StudentLoginInfoEntity {

    private Long studentId;
    private Integer failCounter;
    private Date lastLoginDate;
}
