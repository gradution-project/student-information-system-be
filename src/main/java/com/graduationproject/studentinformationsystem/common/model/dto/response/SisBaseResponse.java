package com.graduationproject.studentinformationsystem.common.model.dto.response;

import lombok.Getter;

import java.util.Date;

@Getter
public abstract class SisBaseResponse {

    protected Date createdDate;
    protected Long createdUserId;
    protected Date modifiedDate;
    protected Long modifiedUserId;
}
