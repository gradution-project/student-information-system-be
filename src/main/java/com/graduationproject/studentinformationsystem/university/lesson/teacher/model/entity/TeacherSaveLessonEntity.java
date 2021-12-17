package com.graduationproject.studentinformationsystem.university.lesson.teacher.model.entity;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class TeacherSaveLessonEntity {

    private Long teacherId;
    private Long lessonId;
    private Date createdDate;
    private Long createdUserId;
}
