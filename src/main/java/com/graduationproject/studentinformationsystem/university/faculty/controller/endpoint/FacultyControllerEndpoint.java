package com.graduationproject.studentinformationsystem.university.faculty.controller.endpoint;

public class FacultyControllerEndpoint {

    private FacultyControllerEndpoint() {
    }

    public static final String FACULTY_ID = "/{facultyId}";
    public static final String SAVE = "/save";
    public static final String UPDATE = "/update";
    public static final String DELETE = "/delete";
    public static final String PASSIVATE = "/passivate";
    public static final String ACTIVATE = "/activate";
    public static final String UPDATE_BY_FACULTY_ID = UPDATE + FACULTY_ID;
}
