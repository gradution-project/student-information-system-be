package com.graduationproject.studentinformationsystem.login.officer.password.controller.endpoint;

import com.graduationproject.studentinformationsystem.common.util.controller.endpoint.SisControllerEndpoint;

public class OfficerPasswordOperationControllerEndpoint {

    private OfficerPasswordOperationControllerEndpoint() {
    }

    public static final String BASE = SisControllerEndpoint.Path.OFFICER_PASSWORD_OPERATION;
    public static final String ENABLED = BASE + "/enabled/{operationId}";
    public static final String CHANGE = BASE + "/change";
    public static final String FORGOT = BASE + "/forgot";
}
