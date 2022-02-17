package com.graduationproject.studentinformationsystem.university.lesson.teacher.model.entity;

import com.graduationproject.studentinformationsystem.university.lesson.common.model.entity.LessonEntity;
import com.graduationproject.studentinformationsystem.university.teacher.model.entity.TeacherAcademicInfoEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Builder
public class TeacherLessonEntity {

    private Long teacherId;
    private Long lessonId;
    private Long createdUserId;
    private Date createdDate;

    @Setter
    private LessonEntity lessonEntity;

    @Setter
    private TeacherAcademicInfoEntity teacherAcademicInfoEntity;
}
