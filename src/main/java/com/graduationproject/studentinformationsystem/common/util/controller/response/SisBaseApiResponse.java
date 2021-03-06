package com.graduationproject.studentinformationsystem.common.util.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static com.graduationproject.studentinformationsystem.common.util.constant.SisConstants.DATE_TIME_SECONDS_PATTERN;

@Getter
@Builder
public class SisBaseApiResponse<T> {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_SECONDS_PATTERN)
    private LocalDateTime requestTime;
    private HttpStatus httpStatus;
    private T response;
    private boolean isSuccess;
}
