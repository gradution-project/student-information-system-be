package com.graduationproject.studentinformationsystem.university.lesson.common.controller.endpoint;

public class LessonControllerEndpoint {

    private LessonControllerEndpoint() {
    }

    public static final String LESSON_ID = "/{lessonId}";
    public static final String SAVE = "/save";
    public static final String UPDATE = "/update";
    public static final String DELETE = "/delete";
    public static final String PASSIVATE = "/passivate";
    public static final String ACTIVATE = "/activate";
    public static final String UPDATE_BY_LESSON_ID = UPDATE + LESSON_ID;
}
