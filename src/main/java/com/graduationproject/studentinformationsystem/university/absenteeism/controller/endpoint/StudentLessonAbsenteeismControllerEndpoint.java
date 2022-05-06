package com.graduationproject.studentinformationsystem.university.absenteeism.controller.endpoint;

import com.graduationproject.studentinformationsystem.common.util.controller.endpoint.SisControllerEndpoint;

public class StudentLessonAbsenteeismControllerEndpoint {

    private StudentLessonAbsenteeismControllerEndpoint() {
    }

    public static final String BASE = SisControllerEndpoint.Path.STUDENT_LESSON_ABSENTEEISM;
    public static final String BY_STUDENT_ID = BASE + SisControllerEndpoint.Path.By.BY_STUDENT_ID;
    public static final String BY_LESSON_ID = BASE + SisControllerEndpoint.Path.By.BY_LESSON_ID;
    public static final String TOTAL_WEEK = BASE + "/total/week";
}
