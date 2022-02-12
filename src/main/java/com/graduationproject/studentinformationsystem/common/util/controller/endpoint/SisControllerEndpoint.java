package com.graduationproject.studentinformationsystem.common.util.controller.endpoint;

public class SisControllerEndpoint {

    private SisControllerEndpoint() {
    }

    public static class Path {
        private Path() {
        }

        public static final String FACULTY = "/faculty";
        public static final String DEPARTMENT = "/department";
        public static final String LESSON = "/lesson";
        public static final String EXAM_SCHEDULE_FILE = "/exam-schedule-file";
        public static final String LESSON_SCHEDULE_FILE = "/lesson-schedule-file";
        public static final String STUDENT = "/student";
        public static final String TEACHER = "/teacher";
        public static final String TEACHER_LESSON = TEACHER + LESSON;
        public static final String OFFICER = "/officer";
        public static final String LOGIN = "/login";
    }
}
