package com.graduationproject.studentinformationsystem.common.util.constant;

public class SisConstants {

    private SisConstants() {
    }

    public static final String DATE_PATTERN = "dd.MM.yyyy";
    public static final String DATE_TIME_PATTERN = "dd.MM.yyyy HH:mm";
    public static final String DATE_TIME_SECONDS_PATTERN = "dd.MM.yyyy HH:mm:ss";
    public static final String PHONE_NUMBER_PATTERN = "+90 (XXX) XXX XX XX";

    public static class DbProfile {

        private DbProfile() {
        }

        public static final String DEFAULT = "default";
        public static final String MYSQL = "mysql";
        public static final String MYSQL_LIVE = "mysql-live";
    }
}
