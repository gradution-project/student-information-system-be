package com.graduationproject.studentinformationsystem.login.teacher.common.repository.impl.scripts;

public class TeacherLoginSqlScripts {

    private static final StringBuilder sqlBuilder = new StringBuilder();

    private TeacherLoginSqlScripts() {
    }

    /**
     * SELECT PASSWORD FROM AUTH_TEACHER_LOGIN WHERE TEACHER_ID=:teacherId;
     */
    public static final String GET_TEACHER_PASSWORD =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT PASSWORD FROM AUTH_TEACHER_LOGIN WHERE TEACHER_ID=:teacherId").toString();

    /**
     * SELECT FAIL_COUNTER FROM AUTH_TEACHER_LOGIN WHERE TEACHER_ID=:teacherId;
     */
    public static final String GET_TEACHER_LOGIN_FAIL_COUNTER =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT FAIL_COUNTER FROM AUTH_TEACHER_LOGIN WHERE TEACHER_ID=:teacherId").toString();

    /**
     * INSERT INTO AUTH_TEACHER_LOGIN SET TEACHER_ID=:teacherId, PASSWORD=:password, FAIL_COUNTER=:failCounter;
     */
    public static final String SAVE_TEACHER_PASSWORD =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("INSERT INTO AUTH_TEACHER_LOGIN SET TEACHER_ID=:teacherId, PASSWORD=:password," +
                            " FAIL_COUNTER=:failCounter").toString();

    /**
     * UPDATE AUTH_TEACHER_LOGIN SET PASSWORD=:password, FAIL_COUNTER=:failCounter WHERE TEACHER_ID=:teacherId;
     */
    public static final String UPDATE_TEACHER_PASSWORD =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE AUTH_TEACHER_LOGIN SET PASSWORD=:password, FAIL_COUNTER=:failCounter " +
                            "WHERE TEACHER_ID=:teacherId").toString();

    /**
     * UPDATE AUTH_TEACHER_LOGIN SET FAIL_COUNTER=:failCounter, LAST_LOGIN_DATE=:lastLoginDate
     * WHERE TEACHER_ID=:teacherId;
     */
    public static final String UPDATE_TEACHER_LOGIN_INFO =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE AUTH_TEACHER_LOGIN SET FAIL_COUNTER=:failCounter, LAST_LOGIN_DATE=:lastLoginDate " +
                            "WHERE TEACHER_ID=:teacherId").toString();

    /**
     * UPDATE AUTH_TEACHER_LOGIN SET FAIL_COUNTER=:failCounter WHERE TEACHER_ID=:teacherId;
     */
    public static final String UPDATE_TEACHER_FAIL_COUNTER =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE AUTH_TEACHER_LOGIN SET FAIL_COUNTER=:failCounter " +
                            "WHERE TEACHER_ID=:teacherId").toString();

    /**
     * SELECT CASE WHEN MAX(TEACHER_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM AUTH_TEACHER_LOGIN WHERE TEACHER_ID=:teacherId;
     */
    public static final String IS_TEACHER_PASSWORD_EXIST =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT CASE WHEN MAX(TEACHER_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST " +
                            "FROM AUTH_TEACHER_LOGIN WHERE TEACHER_ID=:teacherId").toString();
}
