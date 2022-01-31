package com.graduationproject.studentinformationsystem.university.officer.controller.endpoint;

public class OfficerControllerEndpoint {

    private OfficerControllerEndpoint() {
    }

    public static final String OFFICER_ID = "/{officerId}";
    public static final String SAVE = "/save";
    public static final String UPDATE = "/update";
    public static final String DELETE = "/delete";
    public static final String PASSIVATE = "/passivate";
    public static final String ACTIVATE = "/activate";
    public static final String UPDATE_ACADEMIC_INFO_BY_OFFICER_ID = UPDATE + "/academic-info" + OFFICER_ID;
    public static final String UPDATE_PERSONAL_INFO_BY_OFFICER_ID = UPDATE + "/personal-info" + OFFICER_ID;
}
