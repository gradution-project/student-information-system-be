package com.graduationproject.studentinformationsystem.login.student.password.repository.impl.scripts;

public class StudentPasswordOperationSqlScripts {

    private static final StringBuilder sqlBuilder = new StringBuilder();

    private StudentPasswordOperationSqlScripts() {
    }

    /**
     * SELECT OPERATION_ID, STUDENT_ID, EXPIRE_DATE FROM AUTH_STUDENT_PASSWORD_OPERATION
     * WHERE STUDENT_ID=:studentId;
     */
    public static final String GET_STUDENT_PASSWORD_OPERATION =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT OPERATION_ID, STUDENT_ID, EXPIRE_DATE " +
                            "FROM AUTH_STUDENT_PASSWORD_OPERATION WHERE STUDENT_ID=:studentId").toString();

    /**
     * INSERT INTO AUTH_STUDENT_PASSWORD_OPERATION SET OPERATION_ID=:operationId,
     * STUDENT_ID=:studentId, EXPIRE_DATE=:expireDate;
     */
    public static final String SAVE_STUDENT_PASSWORD_OPERATION =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("INSERT INTO AUTH_STUDENT_PASSWORD_OPERATION SET OPERATION_ID=:operationId, " +
                            "STUDENT_ID=:studentId, EXPIRE_DATE=:expireDate").toString();

    /**
     * UPDATE AUTH_STUDENT_PASSWORD_OPERATION SET OPERATION_ID=:operationId, EXPIRE_DATE=:expireDate
     * WHERE STUDENT_ID=:studentId;
     */
    public static final String UPDATE_STUDENT_PASSWORD_OPERATION =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE AUTH_STUDENT_PASSWORD_OPERATION SET OPERATION_ID=:operationId, " +
                            "EXPIRE_DATE=:expireDate WHERE STUDENT_ID=:studentId").toString();

    /**
     * DELETE FROM AUTH_STUDENT_PASSWORD_OPERATION WHERE OPERATION_ID=:operationId;
     */
    public static final String DELETE_STUDENT_PASSWORD_OPERATION =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("DELETE FROM AUTH_STUDENT_PASSWORD_OPERATION WHERE OPERATION_ID=:operationId").toString();

    /**
     * SELECT CASE WHEN MAX(EXPIRE_DATE) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM AUTH_STUDENT_PASSWORD_OPERATION WHERE OPERATION_ID=:operationId AND EXPIRE_DATE > NOW();
     */
    public static final String IS_PASSWORD_CHANGE_ENABLED =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT CASE WHEN MAX(EXPIRE_DATE) IS NULL THEN 'false' ELSE 'true' END IS_EXIST " +
                            "FROM AUTH_STUDENT_PASSWORD_OPERATION " +
                            "WHERE OPERATION_ID=:operationId AND EXPIRE_DATE > NOW()").toString();

    /**
     * SELECT CASE WHEN MAX(STUDENT_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM AUTH_STUDENT_PASSWORD_OPERATION WHERE STUDENT_ID=:studentId;
     */
    public static final String IS_OPERATION_EXIST =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT CASE WHEN MAX(STUDENT_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST " +
                            "FROM AUTH_STUDENT_PASSWORD_OPERATION WHERE STUDENT_ID=:studentId").toString();

    /**
     * SELECT STUDENT_ID FROM AUTH_STUDENT_PASSWORD_OPERATION WHERE OPERATION_ID=:operationId;
     */
    public static final String GET_STUDENT_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT STUDENT_ID FROM AUTH_STUDENT_PASSWORD_OPERATION " +
                            "WHERE OPERATION_ID=:operationId").toString();
}
