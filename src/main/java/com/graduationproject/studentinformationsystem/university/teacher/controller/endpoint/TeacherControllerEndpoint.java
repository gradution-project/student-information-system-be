package com.graduationproject.studentinformationsystem.university.teacher.controller.endpoint;

public class TeacherControllerEndpoint {

    private TeacherControllerEndpoint() {
    }

    public static final String TEACHER_ID = "/{teacherId}";
    public static final String SAVE = "/save";
    public static final String UPDATE = "/update";
    public static final String DELETE = "/delete";
    public static final String PASSIVATE = "/passivate";
    public static final String ACTIVATE = "/activate";
    public static final String UPDATE_ACADEMIC_INFO_BY_TEACHER_ID = UPDATE + "/academic-info" + TEACHER_ID;
    public static final String UPDATE_PERSONAL_INFO_BY_TEACHER_ID = UPDATE + "/personal-info" + TEACHER_ID;
}
