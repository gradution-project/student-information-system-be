package com.graduationproject.studentinformationsystem.university.lesson.teacher.controller.endpoint;

public class TeacherLessonControllerEndpoint {

    private TeacherLessonControllerEndpoint() {
    }

    private static final String TEACHER_ID = "/{teacherId}";
    public static final String GET_BY_TEACHER_ID = "/get" + TEACHER_ID;
    public static final String SAVE = "/save";
    public static final String DELETE = "/delete";
}
