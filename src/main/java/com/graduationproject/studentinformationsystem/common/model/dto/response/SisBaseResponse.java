package com.graduationproject.studentinformationsystem.common.model.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class SisBaseResponse {

    protected Date createdDate;
    protected Long createdUserId;
    protected Date modifiedDate;
    protected Long modifiedUserId;
}
