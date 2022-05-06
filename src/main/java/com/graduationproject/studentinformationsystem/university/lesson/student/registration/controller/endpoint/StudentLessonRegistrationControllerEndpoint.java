package com.graduationproject.studentinformationsystem.university.lesson.student.registration.controller.endpoint;

import com.graduationproject.studentinformationsystem.common.util.controller.endpoint.SisControllerEndpoint;

public class StudentLessonRegistrationControllerEndpoint {

    private StudentLessonRegistrationControllerEndpoint() {
    }

    public static final String ALL = SisControllerEndpoint.Path.STUDENT_LESSON_REGISTRATIONS;
    public static final String BASE = SisControllerEndpoint.Path.STUDENT_LESSON_REGISTRATION;
    public static final String BY_REGISTRATION_ID = BASE + "/{registrationId}";
    public static final String ID_BY_STUDENT_ID = BASE + "/id" + SisControllerEndpoint.Path.By.BY_STUDENT_ID;
    public static final String APPROVE = BASE + "/approve";
    public static final String REJECT = BASE + "/reject";
}
