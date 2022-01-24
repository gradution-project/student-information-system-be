package com.graduationproject.studentinformationsystem.common.model.dto.response;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public abstract class SisBaseResponse {

    private String createdDate;
    private Long createdUserId;
    private String modifiedDate;
    private Long modifiedUserId;
}
