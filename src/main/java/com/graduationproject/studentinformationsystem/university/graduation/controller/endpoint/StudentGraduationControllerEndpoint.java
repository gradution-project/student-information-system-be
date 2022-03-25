package com.graduationproject.studentinformationsystem.university.graduation.controller.endpoint;

public class StudentGraduationControllerEndpoint {

    private StudentGraduationControllerEndpoint() {
    }

    private static final String GRADUATION_ID = "/{graduationId}";
    public static final String GET = "/get";
    public static final String GET_BY_GRADUATION_ID = GET + GRADUATION_ID;
    public static final String SAVE = "/save";
    public static final String APPROVE = "/approve";
    public static final String REJECT = "/reject";
    public static final String CONFIRM = "/confirm";
    public static final String UNCONFIRM = "/unconfirm";
}
