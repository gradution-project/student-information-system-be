package com.graduationproject.studentinformationsystem.university.officer.controller.endpoint;

import com.graduationproject.studentinformationsystem.common.util.controller.endpoint.SisControllerEndpoint;

public class OfficerControllerEndpoint {

    private OfficerControllerEndpoint() {
    }

    public static final String ALL = SisControllerEndpoint.Path.OFFICERS;
    public static final String BASE = SisControllerEndpoint.Path.OFFICER;
    public static final String BY_OFFICER_ID = BASE + "/{officerId}";
    public static final String ACADEMIC_INFO_BY_OFFICER_ID = BASE + "/academic/info/{officerId}";
    public static final String PERSONAL_INFO_BY_OFFICER_ID = BASE + "/personal/info/{officerId}";
    public static final String PASSIVATE = BASE + "/passivate";
    public static final String ACTIVATE = BASE + "/activate";
}
