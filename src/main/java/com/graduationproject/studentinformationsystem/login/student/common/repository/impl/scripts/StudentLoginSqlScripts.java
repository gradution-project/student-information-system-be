package com.graduationproject.studentinformationsystem.login.student.common.repository.impl.scripts;

public class StudentLoginSqlScripts {

    private static final StringBuilder sqlBuilder = new StringBuilder();

    private StudentLoginSqlScripts() {
    }

    /**
     * SELECT PASSWORD FROM AUTH_STUDENT_LOGIN WHERE STUDENT_ID=:studentId;
     */
    public static final String GET_STUDENT_PASSWORD =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT PASSWORD FROM AUTH_STUDENT_LOGIN WHERE STUDENT_ID=:studentId").toString();

    /**
     * SELECT FAIL_COUNTER FROM AUTH_STUDENT_LOGIN WHERE STUDENT_ID=:studentId;
     */
    public static final String GET_STUDENT_LOGIN_FAIL_COUNTER =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT FAIL_COUNTER FROM AUTH_STUDENT_LOGIN WHERE STUDENT_ID=:studentId").toString();

    /**
     * INSERT INTO AUTH_STUDENT_LOGIN SET STUDENT_ID=:studentId, PASSWORD=:password, FAIL_COUNTER=:failCounter;
     */
    public static final String SAVE_STUDENT_PASSWORD =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("INSERT INTO AUTH_STUDENT_LOGIN SET STUDENT_ID=:studentId, PASSWORD=:password," +
                            " FAIL_COUNTER=:failCounter").toString();

    /**
     * UPDATE AUTH_STUDENT_LOGIN SET PASSWORD=:password, FAIL_COUNTER=:failCounter WHERE STUDENT_ID=:studentId;
     */
    public static final String UPDATE_STUDENT_PASSWORD =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE AUTH_STUDENT_LOGIN SET PASSWORD=:password, FAIL_COUNTER=:failCounter " +
                            "WHERE STUDENT_ID=:studentId").toString();

    /**
     * UPDATE AUTH_STUDENT_LOGIN SET FAIL_COUNTER=:failCounter, LAST_LOGIN_DATE=:lastLoginDate
     * WHERE STUDENT_ID=:studentId;
     */
    public static final String UPDATE_STUDENT_LOGIN_INFO =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE AUTH_STUDENT_LOGIN SET FAIL_COUNTER=:failCounter, LAST_LOGIN_DATE=:lastLoginDate " +
                            "WHERE STUDENT_ID=:studentId").toString();

    /**
     * UPDATE AUTH_STUDENT_LOGIN SET FAIL_COUNTER=:failCounter WHERE STUDENT_ID=:studentId;
     */
    public static final String UPDATE_STUDENT_FAIL_COUNTER =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE AUTH_STUDENT_LOGIN SET FAIL_COUNTER=:failCounter " +
                            "WHERE STUDENT_ID=:studentId").toString();

    /**
     * SELECT CASE WHEN MAX(STUDENT_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM AUTH_STUDENT_LOGIN WHERE STUDENT_ID=:studentId;
     */
    public static final String IS_STUDENT_PASSWORD_EXIST =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT CASE WHEN MAX(STUDENT_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST " +
                            "FROM AUTH_STUDENT_LOGIN WHERE STUDENT_ID=:studentId").toString();
}
