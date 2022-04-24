package com.graduationproject.studentinformationsystem.university.note.controller.endpoint;

public class StudentLessonNoteControllerEndpoint {

    private StudentLessonNoteControllerEndpoint() {
    }

    private static final String ID = "/{id}";
    private static final String STUDENT_ID = "/{studentId}";
    private static final String LESSON_ID = "/{lessonId}";
    //    public static final String GET_BY_ID = "/get" + ID;
    public static final String GET_ALL_BY_STUDENT_ID = "/get/student" + STUDENT_ID;
    public static final String GET_ALL_BY_LESSON_ID = "/get/lesson" + LESSON_ID;
    public static final String MIDTERM = "/midterm";
    public static final String MIDTERM_CONFIRM = MIDTERM + "/confirm";
    public static final String FINAL = "/final";
    public static final String FINAL_CONFIRM = FINAL + "/confirm";
    public static final String RESIT = "/resit";
    public static final String RESIT_CONFIRM = RESIT + "/confirm";
}
