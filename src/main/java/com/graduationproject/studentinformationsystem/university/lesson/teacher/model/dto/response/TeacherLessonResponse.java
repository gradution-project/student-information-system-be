package com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.response;

import com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.response.LessonResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherAcademicInfoResponse;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class TeacherLessonResponse {

    private Long createdUserId;
    private String createdDate;

    private LessonResponse lessonResponse;
    private TeacherAcademicInfoResponse teacherAcademicInfoResponse;
}
