package com.graduationproject.studentinformationsystem.login.student.password.controller.endpoint;

import com.graduationproject.studentinformationsystem.common.util.controller.endpoint.SisControllerEndpoint;

public class StudentPasswordOperationControllerEndpoint {

    private StudentPasswordOperationControllerEndpoint() {
    }

    public static final String BASE = SisControllerEndpoint.Path.STUDENT_PASSWORD_OPERATION;
    public static final String ENABLED = BASE + "/enabled/{operationId}";
    public static final String CHANGE = BASE + "/change";
    public static final String FORGOT = BASE + "/forgot";
}
