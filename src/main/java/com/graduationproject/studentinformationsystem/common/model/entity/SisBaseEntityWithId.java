package com.graduationproject.studentinformationsystem.common.model.entity;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@SuperBuilder
public abstract class SisBaseEntityWithId {

    protected Long id;
    protected Date createdDate;
    protected Long createdUserId;
    protected Date modifiedDate;
    protected Long modifiedUserId;
}
