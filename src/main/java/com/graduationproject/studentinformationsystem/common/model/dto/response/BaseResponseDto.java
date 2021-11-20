package com.graduationproject.studentinformationsystem.common.model.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BaseResponseDto {

    private Date createdDate;
    private Long createdUserId;
    private Date modifiedDate;
    private Long modifiedUserId;
}
