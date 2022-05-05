package com.graduationproject.studentinformationsystem.common.util.controller.endpoint;

public class SisControllerEndpoint {

    private SisControllerEndpoint() {
    }

    public static class Path {
        private Path() {
        }

        private static final String REGISTRATION = "/registration";
        private static final String PASSWORD_OPERATION = "/password-operation";
        private static final String NOTE = "/note";
        private static final String ABSENTEEISM = "/absenteeism";
        private static final String GRADUATION = "/graduation";

        public static final String FACULTY = "/faculty";
        public static final String DEPARTMENT = "/department";
        public static final String LESSON = "/lesson";
        public static final String EXAM_SCHEDULE_FILE = "/exam-schedule-file";
        public static final String LESSON_SCHEDULE_FILE = "/lesson-schedule-file";
        public static final String FEATURE_TOGGLE = "/feature-toggle";
        public static final String STUDENT = "/student";
        public static final String STUDENT_LESSON = STUDENT + LESSON;
        public static final String STUDENT_LESSON_REGISTRATION = STUDENT_LESSON + REGISTRATION;
        public static final String STUDENT_LESSON_NOTE = STUDENT + LESSON + NOTE;
        public static final String STUDENT_LESSON_ABSENTEEISM = STUDENT + LESSON + ABSENTEEISM;
        public static final String STUDENT_GRADUATION = STUDENT + GRADUATION;
        public static final String TEACHER = "/teacher";
        public static final String TEACHER_LESSON = TEACHER + LESSON;
        public static final String OFFICER = "/officer";
        public static final String LOGIN = "/login";
        public static final String STUDENT_PASSWORD_OPERATION = STUDENT + PASSWORD_OPERATION;
        public static final String TEACHER_PASSWORD_OPERATION = TEACHER + PASSWORD_OPERATION;
        public static final String OFFICER_PASSWORD_OPERATION = OFFICER + PASSWORD_OPERATION;
    }
}
