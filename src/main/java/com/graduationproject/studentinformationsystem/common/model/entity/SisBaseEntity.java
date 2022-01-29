package com.graduationproject.studentinformationsystem.common.model.entity;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@SuperBuilder
public abstract class SisBaseEntity {

    protected Long createdUserId;
    protected Date createdDate;
    protected Long modifiedUserId;
    protected Date modifiedDate;
}
