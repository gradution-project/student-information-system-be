package com.graduationproject.studentinformationsystem.university.schedule.lesson.controller.endpoint;

import com.graduationproject.studentinformationsystem.common.util.controller.endpoint.SisControllerEndpoint;

public class LessonScheduleFileControllerEndpoint {

    private LessonScheduleFileControllerEndpoint() {
    }

    public static final String ALL = SisControllerEndpoint.Path.LESSON_SCHEDULE_FILES;
    public static final String ALL_BY_FACULTY_ID = ALL + SisControllerEndpoint.Path.By.BY_FACULTY_ID;
    public static final String BASE = SisControllerEndpoint.Path.LESSON_SCHEDULE_FILE;
    public static final String VIEW_BY_FILE_ID = BASE + "/view/{fileId}";
    public static final String DOWNLOAD_BY_FILE_ID = BASE + "/download/{fileId}";
    public static final String BY_DEPARTMENT_ID = BASE + SisControllerEndpoint.Path.By.BY_DEPARTMENT_ID;

    public static class Out {
        private Out() {
        }

        public static final String DOWNLOAD = BASE + "/";
        public static final String VIEW = BASE + "/";
    }
}
