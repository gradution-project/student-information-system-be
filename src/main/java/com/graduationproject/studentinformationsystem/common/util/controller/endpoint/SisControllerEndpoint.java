package com.graduationproject.studentinformationsystem.common.util.controller.endpoint;

public class SisControllerEndpoint {

    private SisControllerEndpoint() {
    }

    public static class Path {
        private Path() {
        }

        public static final String LOGIN = "/login";
        public static final String STUDENT = "/student";
        public static final String TEACHER = "/teacher";
        public static final String LESSON = "/lesson";
    }
}
