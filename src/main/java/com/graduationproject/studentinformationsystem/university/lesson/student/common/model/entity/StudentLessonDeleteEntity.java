package com.graduationproject.studentinformationsystem.university.lesson.student.common.model.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentLessonDeleteEntity {

    private Long studentId;
    private Long lessonId;
}
