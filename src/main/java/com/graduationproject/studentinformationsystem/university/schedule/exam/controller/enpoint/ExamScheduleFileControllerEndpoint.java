package com.graduationproject.studentinformationsystem.university.schedule.exam.controller.enpoint;

public class ExamScheduleFileControllerEndpoint {

    private ExamScheduleFileControllerEndpoint() {
    }

    private static final String FILE_ID = "/{fileId}";
    private static final String FACULTY = "/faculty";
    private static final String DEPARTMENT = "/department";
    public static final String FACULTY_ID = FACULTY + "/{facultyId}";
    public static final String DEPARTMENT_ID = DEPARTMENT + "/{departmentId}";
    public static final String VIEW = "/view" + FILE_ID;
    public static final String DOWNLOAD = "/download" + FILE_ID;
    public static final String SAVE = "/save";
    public static final String DELETE = "/delete" + DEPARTMENT_ID;
}
