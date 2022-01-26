package com.graduationproject.studentinformationsystem.common.model.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class SisOperationInfoRequest {

    @NotNull
    private Long operationUserId;
}
