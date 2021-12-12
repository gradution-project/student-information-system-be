package com.graduationproject.studentinformationsystem.login.common.controller.enpoint;

public class LoginControllerEndpoint {

    private LoginControllerEndpoint() {
    }

    public static final String STUDENT = "/student";
    public static final String STUDENT_FORGOT_PASSWORD = STUDENT + "/forgot-password";

    public static final String TEACHER = "/teacher";
    public static final String TEACHER_FORGOT_PASSWORD = TEACHER + "/forgot-password";

    public static final String OFFICER = "/officer";
    public static final String OFFICER_FORGOT_PASSWORD = OFFICER + "/forgot-password";
}
