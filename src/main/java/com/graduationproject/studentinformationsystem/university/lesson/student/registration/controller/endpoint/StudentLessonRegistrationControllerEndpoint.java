package com.graduationproject.studentinformationsystem.university.lesson.student.registration.controller.endpoint;

public class StudentLessonRegistrationControllerEndpoint {

    private StudentLessonRegistrationControllerEndpoint() {
    }

    public static final String BY_REGISTRATION_ID = "/{registrationId}";
    public static final String BY_STUDENT_ID = "/{studentId}";
    public static final String SAVE = "/save";
    public static final String APPROVE = "/approve";
    public static final String REJECT = "/reject";
}
