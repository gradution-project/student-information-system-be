package com.graduationproject.studentinformationsystem.university.faculty.controller.endpoint;

import com.graduationproject.studentinformationsystem.common.util.controller.endpoint.SisControllerEndpoint;

public class FacultyControllerEndpoint {

    private FacultyControllerEndpoint() {
    }

    public static final String ALL = SisControllerEndpoint.Path.FACULTIES;
    public static final String BASE = SisControllerEndpoint.Path.FACULTY;
    public static final String BY_FACULTY_ID = BASE + "/{facultyId}";
    public static final String PASSIVATE = BASE + "/passivate";
    public static final String ACTIVATE = BASE + "/activate";
}
