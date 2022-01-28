package com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import lombok.Builder;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Builder
public class TeacherLessonSaveRequest {

    @Valid
    @NotNull
    private TeacherLessonRequest lessonRequest;

    @Valid
    @NotNull
    private SisOperationInfoRequest operationInfoRequest;
}
