package com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.request;

import com.graduationproject.studentinformationsystem.common.util.validation.id.LessonID;
import com.graduationproject.studentinformationsystem.common.util.validation.id.TeacherID;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class TeacherLessonRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -1980596814982587730L;

    @NotNull
    @TeacherID
    private Long teacherId;

    @NotNull
    @LessonID
    private Long lessonId;
}
