package com.graduationproject.studentinformationsystem.university.lesson.student.registration.controller.endpoint;

public class StudentLessonRegistrationControllerEndpoint {

    private StudentLessonRegistrationControllerEndpoint() {
    }

    public static final String DETAIL_BY_REGISTRATION_ID = "/detail/{registrationId}";
    public static final String ID_BY_STUDENT_ID = "/id/{studentId}";
    public static final String SAVE = "/save";
    public static final String APPROVE = "/approve";
    public static final String REJECT = "/reject";
}
