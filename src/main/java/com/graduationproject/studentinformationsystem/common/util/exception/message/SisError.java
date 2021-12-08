package com.graduationproject.studentinformationsystem.common.util.exception.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

import static com.graduationproject.studentinformationsystem.common.util.constant.SisConstants.DATE_TIME_PATTERN;

@Getter
@Builder
public class SisError {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_PATTERN)
    private LocalDateTime requestTime;
    private HttpStatus httpStatus;
    private String message;
    private boolean isSuccess;
    private List<SisSubError> subErrors;
    private String detail;

    public SisError withSubErrors(List<SisSubError> subErrors) {
        this.subErrors = subErrors;
        return this;
    }

    public SisError withDetail(String detail) {
        this.detail = detail;
        return this;
    }
}
