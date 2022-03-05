package com.graduationproject.studentinformationsystem.university.lesson.student.common.model.dto.request;

import com.graduationproject.studentinformationsystem.common.util.validation.id.LessonID;
import com.graduationproject.studentinformationsystem.common.util.validation.id.StudentID;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class StudentLessonInfoRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -3737062791585675014L;

    @NotNull
    @StudentID
    private Long studentId;

    @NotNull
    @LessonID
    private Long lessonId;
}
