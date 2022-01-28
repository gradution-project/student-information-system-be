package com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.request;

import lombok.Builder;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Builder
public class TeacherLessonDeleteRequest {

    @Valid
    @NotNull
    private TeacherLessonRequest lessonRequest;
}
