package com.graduationproject.studentinformationsystem.university.lesson.teacher.model.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeacherDeleteLessonEntity {

    private Long teacherId;
    private Long lessonId;
}
