package com.graduationproject.studentinformationsystem.common.model.entity;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@SuperBuilder
public abstract class SisBaseLoginInfoEntity {

    protected Integer failCounter;
    protected Date lastLoginDate;
}
