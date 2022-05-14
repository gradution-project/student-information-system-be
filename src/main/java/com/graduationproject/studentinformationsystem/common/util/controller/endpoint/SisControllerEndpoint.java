package com.graduationproject.studentinformationsystem.common.util.controller.endpoint;

public class SisControllerEndpoint {

    private SisControllerEndpoint() {
    }

    public static class Path {
        private Path() {
        }

        private static final String PREFIX = "/api/v1";

        public static final String FACULTY = PREFIX + "/faculty";
        public static final String FACULTIES = PREFIX + "/faculties";

        public static final String DEPARTMENT = PREFIX + "/department";
        public static final String DEPARTMENTS = PREFIX + "/departments";

        public static final String LESSON = PREFIX + "/lesson";
        public static final String LESSONS = PREFIX + "/lessons";

        public static final String EXAM_SCHEDULE_FILE = PREFIX + "/exam/schedule/file";
        public static final String EXAM_SCHEDULE_FILES = PREFIX + "/exam/schedule/files";

        public static final String LESSON_SCHEDULE_FILE = PREFIX + "/lesson/schedule/file";
        public static final String LESSON_SCHEDULE_FILES = PREFIX + "/lesson/schedule/files";

        public static final String FEATURE_TOGGLE = PREFIX + "/feature/toggle";
        public static final String FEATURE_TOGGLES = PREFIX + "/feature/toggles";

        public static final String STUDENT = PREFIX + "/student";
        public static final String STUDENTS = PREFIX + "/students";

        public static final String STUDENT_LESSON = PREFIX + "/student/lesson";
        public static final String STUDENT_LESSONS = PREFIX + "/student/lessons";

        public static final String STUDENT_LESSON_REGISTRATION = PREFIX + "/student/lesson/registration";
        public static final String STUDENT_LESSON_REGISTRATIONS = PREFIX + "/student/lesson/registrations";

        public static final String STUDENT_LESSON_NOTE = PREFIX + "/student/lesson/note";
        public static final String STUDENT_LESSON_NOTES = PREFIX + "/student/lesson/notes";

        public static final String STUDENT_LESSON_ABSENTEEISM = PREFIX + "/student/lesson/absenteeism";

        public static final String STUDENT_GRADUATION = PREFIX + "/student/graduation";
        public static final String STUDENT_GRADUATIONS = PREFIX + "/student/graduations";

        public static final String STUDENT_TRANSCRIPT = PREFIX + "/student/transcript";

        public static final String TEACHER = PREFIX + "/teacher";
        public static final String TEACHERS = PREFIX + "/teachers";

        public static final String TEACHER_LESSON = PREFIX + "/teacher/lesson";
        public static final String TEACHER_LESSONS = PREFIX + "/teacher/lessons";

        public static final String OFFICER = PREFIX + "/officer";
        public static final String OFFICERS = PREFIX + "/officers";

        public static final String STUDENT_LOGIN = PREFIX + "/student/login";
        public static final String TEACHER_LOGIN = PREFIX + "/teacher/login";
        public static final String OFFICER_LOGIN = PREFIX + "/officer/login";

        public static final String STUDENT_PASSWORD_OPERATION = PREFIX + "/student/password/operation";
        public static final String TEACHER_PASSWORD_OPERATION = PREFIX + "/teacher/password/operation";
        public static final String OFFICER_PASSWORD_OPERATION = PREFIX + "/officer/password/operation";

        public static class By {
            private By() {
            }

            private static final String FACULTY_ID = "/{facultyId}";
            public static final String BY_FACULTY_ID = "/by/faculty" + FACULTY_ID;

            private static final String DEPARTMENT_ID = "/{departmentId}";
            public static final String BY_DEPARTMENT_ID = "/by/department" + DEPARTMENT_ID;

            private static final String LESSON_ID = "/{lessonId}";
            public static final String BY_LESSON_ID = "/by/lesson" + LESSON_ID;

            private static final String TEACHER_ID = "/{teacherId}";
            public static final String BY_TEACHER_ID = "/by/teacher" + TEACHER_ID;

            private static final String STUDENT_ID = "/{studentId}";
            public static final String BY_STUDENT_ID = "/by/student" + STUDENT_ID;
        }
    }
}
