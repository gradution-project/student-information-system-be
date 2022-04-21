package com.graduationproject.studentinformationsystem.university.lesson.student.registration.controller.endpoint;

public class StudentLessonRegistrationControllerEndpoint {

    private StudentLessonRegistrationControllerEndpoint() {
    }

    private static final String REGISTRATION_ID = "/{registrationId}";
    private static final String STUDENT_ID = "/{studentId}";
    public static final String GET = "/get";
    public static final String GET_BY_REGISTRATION_ID = GET + REGISTRATION_ID;
    public static final String SAVE = "/save";
    public static final String APPROVE = "/approve";
    public static final String REJECT = "/reject";
    public static final String WAITING_BY_STUDENT_ID = "/waiting" + STUDENT_ID;
    public static final String APPROVED_BY_REGISTRATION_ID = "/approved" + REGISTRATION_ID;
}
