package com.graduationproject.studentinformationsystem.common.util.exception.message;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SisSubError {
    private String message;
    private Object value;
    private String type;
}
