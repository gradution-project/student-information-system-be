package com.graduationproject.studentinformationsystem.university.graduation.repository.impl.scripts;

import com.graduationproject.studentinformationsystem.common.util.sql.SisSqlUtil;
import com.graduationproject.studentinformationsystem.university.graduation.model.enums.StudentGraduationStatus;

import static com.graduationproject.studentinformationsystem.university.graduation.model.mapping.StudentGraduationMapping.GRADUATION_ID;

public class StudentGraduationSqlScripts {

    private static final StringBuilder sqlBuilder = new StringBuilder();

    private StudentGraduationSqlScripts() {
    }

    /**
     * SELECT GRADUATION_ID, STUDENT_ID, STATUS,
     * CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID FROM STUDENT_GRADUATION
     * WHERE STATUS IN 'status';
     */
    public static final String GET_ALL_STUDENT_GRADUATIONS =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT GRADUATION_ID, STUDENT_ID, STATUS, " +
                            "CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID " +
                            "FROM STUDENT_GRADUATION WHERE STATUS IN ").toString();

    /**
     * SELECT GRADUATION_ID, STUDENT_ID, STATUS,
     * CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID FROM STUDENT_GRADUATION
     * WHERE GRADUATION_ID=:graduationId;
     */
    public static final String GET_STUDENT_GRADUATION =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT GRADUATION_ID, STUDENT_ID, STATUS, " +
                            "CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID " +
                            "FROM STUDENT_GRADUATION WHERE GRADUATION_ID=:graduationId ").toString();

    /**
     * INSERT INTO STUDENT_GRADUATION (GRADUATION_ID, STUDENT_ID, STATUS,
     * CREATED_DATE, CREATED_USER_ID) VALUES (:graduationId, :studentId, status,
     * :createdDate, :createdUserId);
     */
    public static final String SAVE_STUDENT_GRADUATION =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("INSERT INTO STUDENT_GRADUATION (GRADUATION_ID, STUDENT_ID, " +
                            "STATUS, CREATED_DATE, CREATED_USER_ID) VALUES (:graduationId, :studentId, " +
                            ":status, :createdDate, :createdUserId) ").toString();

    /**
     * UPDATE STUDENT_GRADUATION SET STATUS=:status,
     * MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId
     * WHERE GRADUATION_ID=:graduationId;
     */
    public static final String UPDATE_STUDENT_GRADUATION_STATUS =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE STUDENT_GRADUATION SET STATUS=:status, " +
                            "MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId " +
                            "WHERE GRADUATION_ID=:graduationId").toString();

    /**
     * SELECT CASE WHEN MAX(GRADUATION_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM STUDENT_GRADUATION WHERE GRADUATION_ID=:graduationId;
     */
    public static final String IS_STUDENT_GRADUATION_EXIST_BY_GRADUATION_ID = SisSqlUtil
            .isExistByColumnName("STUDENT_GRADUATION",
                    GRADUATION_ID.getColumnName(),
                    GRADUATION_ID.getModelName());

    /**
     * SELECT CASE WHEN MAX(GRADUATION_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM STUDENT_GRADUATION WHERE GRADUATION_ID=:graduationId AND STATUS='WAITING';
     */
    public static final String IS_STUDENT_GRADUATION_WAITING_BY_GRADUATION_ID = SisSqlUtil
            .isExistByColumnNameAndStatus("STUDENT_GRADUATION",
                    GRADUATION_ID.getColumnName(),
                    GRADUATION_ID.getModelName(),
                    StudentGraduationStatus.WAITING.toString());

    /**
     * SELECT CASE WHEN MAX(GRADUATION_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM STUDENT_GRADUATION WHERE GRADUATION_ID=:graduationId AND STATUS='APPROVED';
     */
    public static final String IS_STUDENT_GRADUATION_APPROVED_BY_GRADUATION_ID = SisSqlUtil
            .isExistByColumnNameAndStatus("STUDENT_GRADUATION",
                    GRADUATION_ID.getColumnName(),
                    GRADUATION_ID.getModelName(),
                    StudentGraduationStatus.APPROVED.toString());

    /**
     * SELECT CASE WHEN MAX(GRADUATION_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM STUDENT_GRADUATION WHERE GRADUATION_ID=:graduationId AND STATUS='REJECTED';
     */
    public static final String IS_STUDENT_GRADUATION_REJECTED_BY_GRADUATION_ID = SisSqlUtil
            .isExistByColumnNameAndStatus("STUDENT_GRADUATION",
                    GRADUATION_ID.getColumnName(),
                    GRADUATION_ID.getModelName(),
                    StudentGraduationStatus.REJECTED.toString());

    /**
     * SELECT CASE WHEN MAX(GRADUATION_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM STUDENT_GRADUATION WHERE GRADUATION_ID=:graduationId AND STATUS='CONFIRMED';
     */
    public static final String IS_STUDENT_GRADUATION_CONFIRMED_BY_GRADUATION_ID = SisSqlUtil
            .isExistByColumnNameAndStatus("STUDENT_GRADUATION",
                    GRADUATION_ID.getColumnName(),
                    GRADUATION_ID.getModelName(),
                    StudentGraduationStatus.CONFIRMED.toString());

    /**
     * SELECT CASE WHEN MAX(GRADUATION_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM STUDENT_GRADUATION WHERE GRADUATION_ID=:graduationId AND STATUS='UNCONFIRMED';
     */
    public static final String IS_STUDENT_GRADUATION_UNCONFIRMED_BY_GRADUATION_ID = SisSqlUtil
            .isExistByColumnNameAndStatus("STUDENT_GRADUATION",
                    GRADUATION_ID.getColumnName(),
                    GRADUATION_ID.getModelName(),
                    StudentGraduationStatus.UNCONFIRMED.toString());

    /**
     * SELECT GRADUATION_ID FROM STUDENT_GRADUATION WHERE STUDENT_ID=:studentId;
     */
    public static final String GET_GRADUATION_ID_BY_STUDENT_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT GRADUATION_ID FROM STUDENT_GRADUATION " +
                            "WHERE STUDENT_ID=:studentId").toString();
}
