package com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.request;

import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class TeacherLessonDeleteRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 4957172483526663787L;

    @Valid
    @NotNull
    private TeacherLessonInfoRequest teacherLessonInfoRequest;
}
