package com.graduationproject.studentinformationsystem.university.lesson.student.common.controller.endpoint;

public class StudentLessonControllerEndpoint {

    private StudentLessonControllerEndpoint() {
    }

    private static final String STUDENT_ID = "/{studentId}";
    public static final String GET_BY_STUDENT_ID = "/get" + STUDENT_ID;
    public static final String DELETE_BY_STUDENT_ID = "/delete" + STUDENT_ID;
}
