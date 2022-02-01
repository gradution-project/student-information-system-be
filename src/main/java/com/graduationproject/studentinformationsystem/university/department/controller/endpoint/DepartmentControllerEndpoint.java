package com.graduationproject.studentinformationsystem.university.department.controller.endpoint;

public class DepartmentControllerEndpoint {

    private DepartmentControllerEndpoint() {
    }

    public static final String DEPARTMENT_ID = "/{departmentId}";
    public static final String SAVE = "/save";
    public static final String UPDATE = "/update";
    public static final String DELETE = "/delete";
    public static final String PASSIVATE = "/passivate";
    public static final String ACTIVATE = "/activate";
    public static final String UPDATE_BY_DEPARTMENT_ID = UPDATE + DEPARTMENT_ID;
}
