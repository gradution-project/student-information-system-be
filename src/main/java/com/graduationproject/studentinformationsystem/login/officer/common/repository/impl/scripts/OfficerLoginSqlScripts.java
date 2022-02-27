package com.graduationproject.studentinformationsystem.login.officer.common.repository.impl.scripts;

public class OfficerLoginSqlScripts {

    private static final StringBuilder sqlBuilder = new StringBuilder();

    private OfficerLoginSqlScripts() {
    }

    /**
     * SELECT PASSWORD FROM AUTH_OFFICER_LOGIN WHERE OFFICER_ID=:officerId;
     */
    public static final String GET_OFFICER_PASSWORD =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT PASSWORD FROM AUTH_OFFICER_LOGIN WHERE OFFICER_ID=:officerId").toString();

    /**
     * SELECT FAIL_COUNTER FROM AUTH_OFFICER_LOGIN WHERE OFFICER_ID=:officerId;
     */
    public static final String GET_OFFICER_LOGIN_FAIL_COUNTER =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT FAIL_COUNTER FROM AUTH_OFFICER_LOGIN WHERE OFFICER_ID=:officerId").toString();

    /**
     * INSERT INTO AUTH_OFFICER_LOGIN SET OFFICER_ID=:officerId, PASSWORD=:password, FAIL_COUNTER=:failCounter;
     */
    public static final String SAVE_OFFICER_FIRST_PASSWORD =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("INSERT INTO AUTH_OFFICER_LOGIN SET OFFICER_ID=:officerId, PASSWORD=:password," +
                            " FAIL_COUNTER=:failCounter").toString();

    /**
     * UPDATE AUTH_OFFICER_LOGIN SET PASSWORD=:password WHERE OFFICER_ID=:officerId;
     */
    public static final String UPDATE_OFFICER_PASSWORD =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE AUTH_OFFICER_LOGIN SET PASSWORD=:password WHERE OFFICER_ID=:officerId").toString();

    /**
     * UPDATE AUTH_OFFICER_LOGIN SET FAIL_COUNTER=:failCounter, LAST_LOGIN_DATE=:lastLoginDate
     * WHERE OFFICER_ID=:officerId;
     */
    public static final String UPDATE_OFFICER_LOGIN_INFO =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE AUTH_OFFICER_LOGIN SET FAIL_COUNTER=:failCounter, LAST_LOGIN_DATE=:lastLoginDate " +
                            "WHERE OFFICER_ID=:officerId").toString();

    /**
     * UPDATE AUTH_OFFICER_LOGIN SET FAIL_COUNTER=:failCounter WHERE OFFICER_ID=:officerId;
     */
    public static final String UPDATE_OFFICER_FAIL_COUNTER =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE AUTH_OFFICER_LOGIN SET FAIL_COUNTER=:failCounter " +
                            "WHERE OFFICER_ID=:officerId").toString();
}
