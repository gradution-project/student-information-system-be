package com.graduationproject.studentinformationsystem.common.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@SuperBuilder
public class BaseEntity {

    private Date createdDate;
    private Long createdUserId;
    private Date modifiedDate;
    private Long modifiedUserId;
}
