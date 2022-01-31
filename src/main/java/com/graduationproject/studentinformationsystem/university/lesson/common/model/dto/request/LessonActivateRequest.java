package com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.validation.id.LessonID;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class LessonActivateRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -4594839699721593126L;

    @LessonID
    @NotNull
    private Long lessonId;

    @Valid
    @NotNull
    private SisOperationInfoRequest operationInfoRequest;
}
