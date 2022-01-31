package com.graduationproject.studentinformationsystem.university.student.controller.endpoint;

public class StudentControllerEndpoint {

    private StudentControllerEndpoint() {
    }

    public static final String STUDENT_ID = "/{studentId}";
    public static final String SAVE = "/save";
    public static final String UPDATE = "/update";
    public static final String DELETE = "/delete";
    public static final String PASSIVATE = "/passivate";
    public static final String ACTIVATE = "/activate";
    public static final String GRADUATE = "/graduate";
    public static final String UPDATE_ACADEMIC_INFO_BY_STUDENT_ID = UPDATE + "/academic-info" + STUDENT_ID;
    public static final String UPDATE_PERSONAL_INFO_BY_STUDENT_ID = UPDATE + "/personal-info" + STUDENT_ID;
}
