package com.graduationproject.studentinformationsystem.university.lesson.student.common.model.dto.request;

import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class StudentLessonDeleteRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 8186474487495643349L;

    @Valid
    @NotNull
    private StudentLessonInfoRequest studentLessonInfoRequest;
}
