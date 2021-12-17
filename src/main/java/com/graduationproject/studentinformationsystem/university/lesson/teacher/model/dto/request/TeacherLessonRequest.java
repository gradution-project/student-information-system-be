package com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.request;

import com.graduationproject.studentinformationsystem.common.util.validation.id.LessonID;
import com.graduationproject.studentinformationsystem.common.util.validation.id.TeacherID;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
public class TeacherLessonRequest {

    @NotNull
    @TeacherID
    private Long teacherId;

    @NotNull
    @LessonID
    private Long lessonId;
}
