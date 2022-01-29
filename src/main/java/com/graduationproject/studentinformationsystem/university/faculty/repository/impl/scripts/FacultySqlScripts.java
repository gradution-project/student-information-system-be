package com.graduationproject.studentinformationsystem.university.faculty.repository.impl.scripts;

import com.graduationproject.studentinformationsystem.common.util.sql.SisSqlUtil;
import com.graduationproject.studentinformationsystem.university.officer.model.enums.OfficerStatus;

import static com.graduationproject.studentinformationsystem.university.officer.model.mapping.OfficerAcademicInfoMapping.FACULTY_ID;

public class FacultySqlScripts {

    private static final StringBuilder sqlBuilder = new StringBuilder();

    private FacultySqlScripts() {
    }

    /**
     * SELECT FACULTY_ID, NAME, STATUS,
     * CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID FROM UNIV_FACULTY;
     */
    private static final String GET_UNIV_FACULTIES =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT FACULTY_ID, NAME, STATUS, " +
                            "CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID " +
                            "FROM UNIV_FACULTY ").toString();

    /**
     * SELECT FACULTY_ID, NAME, STATUS,
     * CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID FROM UNIV_FACULTY
     * WHERE STATUS IN '=:status';
     */
    public static final String GET_ALL_FACULTIES_BY_STATUS =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append(GET_UNIV_FACULTIES)
                    .append("WHERE STATUS IN ").toString();

    /**
     * SELECT FACULTY_ID, NAME, STATUS,
     * CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID FROM UNIV_FACULTY
     * WHERE FACULTY_ID=:facultyId;
     */
    public static final String GET_FACULTY_BY_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append(GET_UNIV_FACULTIES)
                    .append("WHERE FACULTY_ID=:facultyId").toString();

    /**
     * INSERT INTO UNIV_FACULTY (FACULTY_ID, NAME, STATUS, CREATED_DATE, CREATED_USER_ID)
     * VALUES (:facultyId, :name, :status, :createdDate, :createdUserId);
     */
    public static final String SAVE_FACULTY =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("INSERT INTO UNIV_FACULTY (FACULTY_ID, NAME, STATUS, CREATED_DATE, CREATED_USER_ID) " +
                            "VALUES (:facultyId, :name, :status, :createdDate, :createdUserId)").toString();

    /**
     * UPDATE UNIV_FACULTY SET NAME=:name, MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId
     * WHERE FACULTY_ID=:facultyId;
     */
    public static final String UPDATE_FACULTY =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE UNIV_FACULTY SET NAME=:name, " +
                            "MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId " +
                            "WHERE FACULTY_ID=:facultyId").toString();

    /**
     * UPDATE UNIV_FACULTY SET STATUS=:status, MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId
     * WHERE FACULTY_ID=:facultyId;
     */
    public static final String UPDATE_FACULTY_STATUS =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE UNIV_FACULTY SET STATUS=:status, " +
                            "MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId " +
                            "WHERE FACULTY_ID=:facultyId").toString();

    /**
     * SELECT FACULTY_ID FROM UNIV_FACULTY;
     */
    public static final String GET_ALL_FACULTY_IDS =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT FACULTY_ID FROM UNIV_FACULTY").toString();

    /**
     * SELECT CASE WHEN MAX(FACULTY_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM UNIV_FACULTY WHERE FACULTY_ID=:facultyId;
     */
    public static final String IS_FACULTY_EXIST_BY_ID = SisSqlUtil.isExistByColumnName("UNIV_FACULTY",
            FACULTY_ID.getColumnName(),
            FACULTY_ID.getModelName());

    /**
     * SELECT CASE WHEN MAX(FACULTY_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM UNIV_FACULTY WHERE FACULTY_ID=:facultyId && STATUS='DELETED';
     */
    public static final String IS_FACULTY_DELETED_BY_ID = SisSqlUtil
            .isExistByColumnNameAndStatus("UNIV_FACULTY",
                    FACULTY_ID.getColumnName(),
                    FACULTY_ID.getModelName(),
                    OfficerStatus.DELETED.toString());

    /**
     * SELECT CASE WHEN MAX(FACULTY_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM UNIV_FACULTY WHERE FACULTY_ID=:facultyId && STATUS='PASSIVE';
     */
    public static final String IS_FACULTY_PASSIVE_BY_ID = SisSqlUtil
            .isExistByColumnNameAndStatus("UNIV_FACULTY",
                    FACULTY_ID.getColumnName(),
                    FACULTY_ID.getModelName(),
                    OfficerStatus.PASSIVE.toString());

    /**
     * SELECT CASE WHEN MAX(FACULTY_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM UNIV_FACULTY WHERE FACULTY_ID=:facultyId && STATUS='ACTIVE';
     */
    public static final String IS_FACULTY_ACTIVE_BY_ID = SisSqlUtil
            .isExistByColumnNameAndStatus("UNIV_FACULTY",
                    FACULTY_ID.getColumnName(),
                    FACULTY_ID.getModelName(),
                    OfficerStatus.ACTIVE.toString());
}
