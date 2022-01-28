package com.graduationproject.studentinformationsystem.university.lesson.teacher.model.entity;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class TeacherLessonSaveEntity {

    private Long teacherId;
    private Long lessonId;
    private Long createdUserId;
    private Date createdDate;
}
