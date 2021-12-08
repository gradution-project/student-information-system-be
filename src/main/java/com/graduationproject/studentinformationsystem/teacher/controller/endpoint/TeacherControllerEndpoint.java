package com.graduationproject.studentinformationsystem.teacher.controller.endpoint;

public class TeacherControllerEndpoint {

    private TeacherControllerEndpoint() {
    }

    public static final String TEACHER_ID = "/{teacherId}";
    public static final String SAVE = "/save";
    public static final String UPDATE = "/update";
    public static final String DELETE_BY_TEACHER_ID = "/delete" + TEACHER_ID;
    public static final String PASSIVATE_BY_TEACHER_ID = "/passivate" + TEACHER_ID;
    public static final String ACTIVATE_BY_TEACHER_ID = "/activate" + TEACHER_ID;
    public static final String UPDATE_ACADEMIC_INFO_BY_TEACHER_ID = UPDATE + "/academic-info" + TEACHER_ID;
    public static final String UPDATE_PERSONAL_INFO_BY_TEACHER_ID = UPDATE + "/personal-info" + TEACHER_ID;
}
