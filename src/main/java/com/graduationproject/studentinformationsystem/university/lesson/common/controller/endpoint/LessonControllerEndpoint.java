package com.graduationproject.studentinformationsystem.university.lesson.common.controller.endpoint;

public class LessonControllerEndpoint {

    private LessonControllerEndpoint() {
    }

    public static final String TEACHER = "/teacher";
    public static final String TEACHER_BY_ID = TEACHER + "/{teacherId}";
}
