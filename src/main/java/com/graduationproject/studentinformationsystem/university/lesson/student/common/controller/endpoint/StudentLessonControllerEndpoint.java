package com.graduationproject.studentinformationsystem.university.lesson.student.common.controller.endpoint;

import com.graduationproject.studentinformationsystem.common.util.controller.endpoint.SisControllerEndpoint;

public class StudentLessonControllerEndpoint {

    private StudentLessonControllerEndpoint() {
    }

    public static final String ALL = SisControllerEndpoint.Path.STUDENT_LESSONS;
    public static final String BASE = SisControllerEndpoint.Path.STUDENT_LESSON;
    public static final String BY_STUDENT_ID = BASE + SisControllerEndpoint.Path.By.BY_STUDENT_ID;
    public static final String ALL_BY_STUDENT_ID = ALL + SisControllerEndpoint.Path.By.BY_STUDENT_ID;
}
