package com.graduationproject.studentinformationsystem.university.student.controller.endpoint;

import com.graduationproject.studentinformationsystem.common.util.controller.endpoint.SisControllerEndpoint;

public class StudentControllerEndpoint {

    private StudentControllerEndpoint() {
    }

    public static final String ALL = SisControllerEndpoint.Path.STUDENTS;
    public static final String BASE = SisControllerEndpoint.Path.STUDENT;
    public static final String BY_STUDENT_ID = BASE + "/{studentId}";
    public static final String ACADEMIC_INFO_BY_STUDENT_ID = BASE + "/academic/info/{studentId}";
    public static final String PERSONAL_INFO_BY_STUDENT_ID = BASE + "/personal/info/{studentId}";
    public static final String PASSIVATE = BASE + "/passivate";
    public static final String ACTIVATE = BASE + "/activate";
}
