package com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.response;

import com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.response.LessonResponse;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class TeacherLessonResponse {

    private Long teacherId;
    private Long createdUserId;
    private String createdDate;

    private LessonResponse lessonResponse;
}
