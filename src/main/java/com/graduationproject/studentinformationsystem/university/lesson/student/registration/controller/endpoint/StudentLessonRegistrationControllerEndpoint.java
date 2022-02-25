package com.graduationproject.studentinformationsystem.university.lesson.student.registration.controller.endpoint;

public class StudentLessonRegistrationControllerEndpoint {

    private StudentLessonRegistrationControllerEndpoint() {
    }

    private static final String REGISTRATION_ID = "/{registrationId}";
    public static final String GET = "/get";
    public static final String GET_BY_REGISTRATION_ID = GET + REGISTRATION_ID;
    public static final String SAVE = "/save";
    public static final String APPROVE = "/approve";
    public static final String REJECT = "/reject";
}
