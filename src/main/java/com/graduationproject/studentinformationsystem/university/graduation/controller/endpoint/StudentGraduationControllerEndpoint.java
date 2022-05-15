package com.graduationproject.studentinformationsystem.university.graduation.controller.endpoint;

import com.graduationproject.studentinformationsystem.common.util.controller.endpoint.SisControllerEndpoint;

public class StudentGraduationControllerEndpoint {

    private StudentGraduationControllerEndpoint() {
    }

    public static final String ALL = SisControllerEndpoint.Path.STUDENT_GRADUATIONS;
    public static final String BASE = SisControllerEndpoint.Path.STUDENT_GRADUATION;
    public static final String BY_GRADUATION_ID = BASE + "/{graduationId}";
    public static final String APPROVE = BASE + "/approve";
    public static final String REJECT = BASE + "/reject";
    public static final String CONFIRM = BASE + "/confirm";
    public static final String UNCONFIRM = BASE + "/unconfirm";
    public static final String ENABLED_BY_STUDENT_ID = BASE + "/enabled" + SisControllerEndpoint.Path.By.BY_STUDENT_ID;
}
