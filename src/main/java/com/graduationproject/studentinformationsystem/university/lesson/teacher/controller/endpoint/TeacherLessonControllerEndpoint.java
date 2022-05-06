package com.graduationproject.studentinformationsystem.university.lesson.teacher.controller.endpoint;

import com.graduationproject.studentinformationsystem.common.util.controller.endpoint.SisControllerEndpoint;

public class TeacherLessonControllerEndpoint {

    private TeacherLessonControllerEndpoint() {
    }

    public static final String ALL = SisControllerEndpoint.Path.TEACHER_LESSONS;
    public static final String BASE = SisControllerEndpoint.Path.TEACHER_LESSON;
    public static final String BY_TEACHER_ID = BASE + SisControllerEndpoint.Path.By.BY_TEACHER_ID;
}
