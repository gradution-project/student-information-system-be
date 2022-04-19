package com.graduationproject.studentinformationsystem.login.officer.password.repository.impl.scripts;

public class OfficerPasswordOperationSqlScripts {

    private static final StringBuilder sqlBuilder = new StringBuilder();

    private OfficerPasswordOperationSqlScripts() {
    }

    /**
     * SELECT OPERATION_ID, OFFICER_ID, EXPIRE_DATE FROM AUTH_OFFICER_PASSWORD_OPERATION
     * WHERE OFFICER_ID=:officerId;
     */
    public static final String GET_OFFICER_PASSWORD_OPERATION =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT OPERATION_ID, OFFICER_ID, EXPIRE_DATE " +
                            "FROM AUTH_OFFICER_PASSWORD_OPERATION WHERE OFFICER_ID=:officerId").toString();

    /**
     * INSERT INTO AUTH_OFFICER_PASSWORD_OPERATION SET OPERATION_ID=:operationId,
     * OFFICER_ID=:officerId, EXPIRE_DATE=:expireDate;
     */
    public static final String SAVE_OFFICER_PASSWORD_OPERATION =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("INSERT INTO AUTH_OFFICER_PASSWORD_OPERATION SET OPERATION_ID=:operationId, " +
                            "OFFICER_ID=:officerId, EXPIRE_DATE=:expireDate").toString();

    /**
     * UPDATE AUTH_OFFICER_PASSWORD_OPERATION SET OPERATION_ID=:operationId, EXPIRE_DATE=:expireDate
     * WHERE OFFICER_ID=:officerId;
     */
    public static final String UPDATE_OFFICER_PASSWORD_OPERATION =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE AUTH_OFFICER_PASSWORD_OPERATION SET OPERATION_ID=:operationId, " +
                            "EXPIRE_DATE=:expireDate WHERE OFFICER_ID=:officerId").toString();

    /**
     * DELETE FROM AUTH_OFFICER_PASSWORD_OPERATION WHERE OPERATION_ID=:operationId;
     */
    public static final String DELETE_OFFICER_PASSWORD_OPERATION =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("DELETE FROM AUTH_OFFICER_PASSWORD_OPERATION WHERE OPERATION_ID=:operationId").toString();

    /**
     * SELECT CASE WHEN MAX(EXPIRE_DATE) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM AUTH_OFFICER_PASSWORD_OPERATION WHERE OPERATION_ID=:operationId AND EXPIRE_DATE > NOW();
     */
    public static final String IS_PASSWORD_CHANGE_ENABLED =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT CASE WHEN MAX(EXPIRE_DATE) IS NULL THEN 'false' ELSE 'true' END IS_EXIST " +
                            "FROM AUTH_OFFICER_PASSWORD_OPERATION " +
                            "WHERE OPERATION_ID=:operationId AND EXPIRE_DATE > NOW()").toString();

    /**
     * SELECT CASE WHEN MAX(OFFICER_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM AUTH_OFFICER_PASSWORD_OPERATION WHERE OFFICER_ID=:officerId;
     */
    public static final String IS_OPERATION_EXIST =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT CASE WHEN MAX(OFFICER_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST " +
                            "FROM AUTH_OFFICER_PASSWORD_OPERATION WHERE OFFICER_ID=:officerId").toString();

    /**
     * SELECT OFFICER_ID FROM AUTH_OFFICER_PASSWORD_OPERATION WHERE OPERATION_ID=:operationId;
     */
    public static final String GET_OFFICER_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT OFFICER_ID FROM AUTH_OFFICER_PASSWORD_OPERATION " +
                            "WHERE OPERATION_ID=:operationId").toString();
}
