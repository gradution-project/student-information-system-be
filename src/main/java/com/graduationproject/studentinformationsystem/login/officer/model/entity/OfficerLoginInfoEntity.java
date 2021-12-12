package com.graduationproject.studentinformationsystem.login.officer.model.entity;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class OfficerLoginInfoEntity {

    private Long officerId;
    private Integer failCounter;
    private Date lastLoginDate;
}
