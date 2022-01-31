package com.graduationproject.studentinformationsystem.common.model.dto.response;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public abstract class SisBaseResponse {

    protected Long createdUserId;
    protected String createdDate;
    protected Long modifiedUserId;
    protected String modifiedDate;
}
