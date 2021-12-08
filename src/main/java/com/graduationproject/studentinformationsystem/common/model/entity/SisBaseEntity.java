package com.graduationproject.studentinformationsystem.common.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@SuperBuilder
public abstract class SisBaseEntity {

    protected Date createdDate;
    protected Long createdUserId;
    protected Date modifiedDate;
    protected Long modifiedUserId;
}
