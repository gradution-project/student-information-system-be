package com.graduationproject.studentinformationsystem.login.teacher.password.repository.impl.scripts;

public class TeacherPasswordOperationSqlScripts {

    private static final StringBuilder sqlBuilder = new StringBuilder();

    private TeacherPasswordOperationSqlScripts() {
    }

    /**
     * SELECT OPERATION_ID, TEACHER_ID, EXPIRE_DATE FROM AUTH_TEACHER_PASSWORD_OPERATION
     * WHERE TEACHER_ID=:teacherId;
     */
    public static final String GET_TEACHER_PASSWORD_OPERATION =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT OPERATION_ID, TEACHER_ID, EXPIRE_DATE " +
                            "FROM AUTH_TEACHER_PASSWORD_OPERATION WHERE TEACHER_ID=:teacherId").toString();

    /**
     * INSERT INTO AUTH_TEACHER_PASSWORD_OPERATION SET OPERATION_ID=:operationId,
     * TEACHER_ID=:teacherId, EXPIRE_DATE=:expireDate;
     */
    public static final String SAVE_TEACHER_PASSWORD_OPERATION =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("INSERT INTO AUTH_TEACHER_PASSWORD_OPERATION SET OPERATION_ID=:operationId, " +
                            "TEACHER_ID=:teacherId, EXPIRE_DATE=:expireDate").toString();

    /**
     * UPDATE AUTH_TEACHER_PASSWORD_OPERATION SET OPERATION_ID=:operationId, EXPIRE_DATE=:expireDate
     * WHERE TEACHER_ID=:teacherId;
     */
    public static final String UPDATE_TEACHER_PASSWORD_OPERATION =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE AUTH_TEACHER_PASSWORD_OPERATION SET OPERATION_ID=:operationId, " +
                            "EXPIRE_DATE=:expireDate WHERE TEACHER_ID=:teacherId").toString();

    /**
     * DELETE FROM AUTH_TEACHER_PASSWORD_OPERATION WHERE OPERATION_ID=:operationId;
     */
    public static final String DELETE_TEACHER_PASSWORD_OPERATION =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("DELETE FROM AUTH_TEACHER_PASSWORD_OPERATION WHERE OPERATION_ID=:operationId").toString();

    /**
     * SELECT CASE WHEN MAX(EXPIRE_DATE) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM AUTH_TEACHER_PASSWORD_OPERATION WHERE OPERATION_ID=:operationId AND EXPIRE_DATE > NOW();
     */
    public static final String IS_PASSWORD_CHANGE_ENABLED =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT CASE WHEN MAX(EXPIRE_DATE) IS NULL THEN 'false' ELSE 'true' END IS_EXIST " +
                            "FROM AUTH_TEACHER_PASSWORD_OPERATION " +
                            "WHERE OPERATION_ID=:operationId AND EXPIRE_DATE > NOW()").toString();

    /**
     * SELECT CASE WHEN MAX(TEACHER_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM AUTH_TEACHER_PASSWORD_OPERATION WHERE TEACHER_ID=:teacherId;
     */
    public static final String IS_OPERATION_EXIST =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT CASE WHEN MAX(TEACHER_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST " +
                            "FROM AUTH_TEACHER_PASSWORD_OPERATION WHERE TEACHER_ID=:teacherId").toString();

    /**
     * SELECT TEACHER_ID FROM AUTH_TEACHER_PASSWORD_OPERATION WHERE OPERATION_ID=:operationId;
     */
    public static final String GET_TEACHER_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT TEACHER_ID FROM AUTH_TEACHER_PASSWORD_OPERATION " +
                            "WHERE OPERATION_ID=:operationId").toString();
}
