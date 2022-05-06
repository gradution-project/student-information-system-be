package com.graduationproject.studentinformationsystem.university.note.controller.endpoint;

import com.graduationproject.studentinformationsystem.common.util.controller.endpoint.SisControllerEndpoint;

public class StudentLessonNoteControllerEndpoint {

    private StudentLessonNoteControllerEndpoint() {
    }

    public static final String ALL = SisControllerEndpoint.Path.STUDENT_LESSON_NOTES;
    public static final String ALL_BY_LESSON_ID = ALL + SisControllerEndpoint.Path.By.BY_LESSON_ID;
    public static final String ALL_BY_STUDENT_ID = ALL + SisControllerEndpoint.Path.By.BY_STUDENT_ID;
    public static final String BASE = SisControllerEndpoint.Path.STUDENT_LESSON_NOTE;
    public static final String MIDTERM = BASE + "/midterm";
    public static final String MIDTERM_CONFIRM = BASE + "/midterm/confirm";
    public static final String FINAL = BASE + "/final";
    public static final String FINAL_CONFIRM = BASE + "/final/confirm";
    public static final String RESIT = BASE + "/resit";
    public static final String RESIT_CONFIRM = BASE + "/resit/confirm";
}
