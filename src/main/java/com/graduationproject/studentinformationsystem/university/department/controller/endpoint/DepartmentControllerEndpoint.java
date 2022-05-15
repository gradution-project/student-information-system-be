package com.graduationproject.studentinformationsystem.university.department.controller.endpoint;

import com.graduationproject.studentinformationsystem.common.util.controller.endpoint.SisControllerEndpoint;

public class DepartmentControllerEndpoint {

    private DepartmentControllerEndpoint() {
    }

    public static final String ALL = SisControllerEndpoint.Path.DEPARTMENTS;
    public static final String BASE = SisControllerEndpoint.Path.DEPARTMENT;
    public static final String BY_DEPARTMENT_ID = BASE + "/{departmentId}";
    public static final String PASSIVATE = BASE + "/passivate";
    public static final String ACTIVATE = BASE + "/activate";
}
