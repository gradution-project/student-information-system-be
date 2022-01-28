package com.graduationproject.studentinformationsystem.common.model.dto.response;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public abstract class SisBaseResponse {

    private Long createdUserId;
    private String createdDate;
    private Long modifiedUserId;
    private String modifiedDate;
}
