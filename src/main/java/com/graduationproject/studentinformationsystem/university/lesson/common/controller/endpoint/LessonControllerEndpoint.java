package com.graduationproject.studentinformationsystem.university.lesson.common.controller.endpoint;

import com.graduationproject.studentinformationsystem.common.util.controller.endpoint.SisControllerEndpoint;

public class LessonControllerEndpoint {

    private LessonControllerEndpoint() {
    }

    public static final String ALL = SisControllerEndpoint.Path.LESSONS;
    public static final String BASE = SisControllerEndpoint.Path.LESSON;
    public static final String BY_LESSON_ID = BASE + "/{lessonId}";
    public static final String PASSIVATE = BASE + "/passivate";
    public static final String ACTIVATE = BASE + "/activate";
}
