package com.graduationproject.studentinformationsystem.university.lesson.student.common.model.entity;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class StudentLessonSaveEntity {

    private Long studentId;
    private Long lessonId;
    private Long createdUserId;
    private Date createdDate;
}
