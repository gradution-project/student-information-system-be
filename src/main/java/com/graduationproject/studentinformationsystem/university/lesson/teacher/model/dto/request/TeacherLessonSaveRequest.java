package com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class TeacherLessonSaveRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 6170499453085780287L;

    @Valid
    @NotNull
    private TeacherLessonRequest lessonRequest;

    @Valid
    @NotNull
    private SisOperationInfoRequest operationInfoRequest;
}
