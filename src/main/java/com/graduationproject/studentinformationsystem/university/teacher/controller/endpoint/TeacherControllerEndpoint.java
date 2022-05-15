package com.graduationproject.studentinformationsystem.university.teacher.controller.endpoint;

import com.graduationproject.studentinformationsystem.common.util.controller.endpoint.SisControllerEndpoint;

public class TeacherControllerEndpoint {

    private TeacherControllerEndpoint() {
    }

    public static final String ALL = SisControllerEndpoint.Path.TEACHERS;
    public static final String BASE = SisControllerEndpoint.Path.TEACHER;
    public static final String BY_TEACHER_ID = BASE + "/{teacherId}";
    public static final String ACADEMIC_INFO_BY_TEACHER_ID = BASE + "/academic/info/{teacherId}";
    public static final String PERSONAL_INFO_BY_TEACHER_ID = BASE + "/personal/info/{teacherId}";
    public static final String PASSIVATE = BASE + "/passivate";
    public static final String ACTIVATE = BASE + "/activate";
}
