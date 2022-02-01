package com.graduationproject.studentinformationsystem.university.officer.repository.impl.scripts;

import com.graduationproject.studentinformationsystem.common.util.sql.SisSqlUtil;
import com.graduationproject.studentinformationsystem.university.officer.model.enums.OfficerStatus;

import static com.graduationproject.studentinformationsystem.university.officer.model.mapping.OfficerAcademicInfoMapping.OFFICER_ID;

public class OfficerAcademicInfoSqlScripts {

    private static final StringBuilder sqlBuilder = new StringBuilder();

    private OfficerAcademicInfoSqlScripts() {
    }

    /**
     * SELECT OFFICER_ID, FACULTY_ID, EMAIL, PHONE_NUMBER, STATUS, REGISTRATION_DATE,
     * CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID FROM OFFICER_ACADEMIC_INFO;
     */
    private static final String GET_OFFICER_ACADEMIC_INFOS =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT OFFICER_ID, FACULTY_ID, EMAIL, PHONE_NUMBER, STATUS, REGISTRATION_DATE, " +
                            "CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID " +
                            "FROM OFFICER_ACADEMIC_INFO ").toString();

    /**
     * SELECT OFFICER_ID, FACULTY_ID, EMAIL, PHONE_NUMBER, STATUS, REGISTRATION_DATE,
     * CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID FROM OFFICER_ACADEMIC_INFO
     * WHERE STATUS IN '=:status';
     */
    public static final String GET_ALL_OFFICER_ACADEMIC_INFOS_BY_STATUS =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append(GET_OFFICER_ACADEMIC_INFOS)
                    .append("WHERE STATUS IN ").toString();

    /**
     * SELECT OFFICER_ID, FACULTY_ID, EMAIL, PHONE_NUMBER, STATUS, REGISTRATION_DATE,
     * CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID FROM OFFICER_ACADEMIC_INFO
     * WHERE OFFICER_ID=:officerId;
     */
    public static final String GET_OFFICER_ACADEMIC_INFO_BY_OFFICER_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append(GET_OFFICER_ACADEMIC_INFOS)
                    .append("WHERE OFFICER_ID=:officerId").toString();

    /**
     * INSERT INTO OFFICER_ACADEMIC_INFO (OFFICER_ID, FACULTY_ID, EMAIL, PHONE_NUMBER, STATUS, REGISTRATION_DATE,
     * CREATED_DATE, CREATED_USER_ID) VALUES (:officerId, :facultyId, :email, :phoneNumber, :status,
     * :registrationDate, :createdDate, :createdUserId);
     */
    public static final String SAVE_OFFICER_ACADEMIC_INFO =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("INSERT INTO OFFICER_ACADEMIC_INFO (OFFICER_ID, FACULTY_ID, EMAIL, PHONE_NUMBER, STATUS, " +
                            "REGISTRATION_DATE, CREATED_DATE, CREATED_USER_ID) VALUES (:officerId, :facultyId, " +
                            ":email, :phoneNumber, :status, :registrationDate, :createdDate, :createdUserId)").toString();

    /**
     * UPDATE OFFICER_ACADEMIC_INFO SET FACULTY_ID=:facultyId, PHONE_NUMBER=:phoneNumber,
     * MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId WHERE OFFICER_ID=:officerId;
     */
    public static final String UPDATE_OFFICER_ACADEMIC_INFO =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE OFFICER_ACADEMIC_INFO SET FACULTY_ID=:facultyId, PHONE_NUMBER=:phoneNumber, " +
                            "MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId " +
                            "WHERE OFFICER_ID=:officerId").toString();

    /**
     * UPDATE OFFICER_ACADEMIC_INFO SET STATUS=:status, MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId
     * WHERE OFFICER_ID=:officerId;
     */
    public static final String UPDATE_OFFICER_ACADEMIC_INFO_STATUS =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE OFFICER_ACADEMIC_INFO SET STATUS=:status, " +
                            "MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId " +
                            "WHERE OFFICER_ID=:officerId").toString();

    /**
     * SELECT OFFICER_ID FROM OFFICER_ACADEMIC_INFO WHERE FACULTY_ID=:facultyId;
     */
    public static final String GET_ALL_OFFICER_IDS_BY_FACULTY_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT OFFICER_ID FROM OFFICER_ACADEMIC_INFO WHERE FACULTY_ID=:facultyId").toString();

    /**
     * SELECT CASE WHEN MAX(OFFICER_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM OFFICER_ACADEMIC_INFO WHERE OFFICER_ID=:officerId;
     */
    public static final String IS_OFFICER_EXIST_BY_ID = SisSqlUtil.isExistByColumnName("OFFICER_ACADEMIC_INFO",
            OFFICER_ID.getColumnName(),
            OFFICER_ID.getModelName());


    /**
     * SELECT CASE WHEN MAX(OFFICER_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM OFFICER_ACADEMIC_INFO WHERE OFFICER_ID=:officerId && STATUS='DELETED';
     */
    public static final String IS_OFFICER_DELETED_BY_ID = SisSqlUtil
            .isExistByColumnNameAndStatus("OFFICER_ACADEMIC_INFO",
                    OFFICER_ID.getColumnName(),
                    OFFICER_ID.getModelName(),
                    OfficerStatus.DELETED.toString());

    /**
     * SELECT CASE WHEN MAX(OFFICER_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM OFFICER_ACADEMIC_INFO WHERE OFFICER_ID=:officerId && STATUS='PASSIVE';
     */
    public static final String IS_OFFICER_PASSIVE_BY_ID = SisSqlUtil
            .isExistByColumnNameAndStatus("OFFICER_ACADEMIC_INFO",
                    OFFICER_ID.getColumnName(),
                    OFFICER_ID.getModelName(),
                    OfficerStatus.PASSIVE.toString());

    /**
     * SELECT CASE WHEN MAX(OFFICER_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM OFFICER_ACADEMIC_INFO WHERE OFFICER_ID=:officerId && STATUS='ACTIVE';
     */
    public static final String IS_OFFICER_ACTIVE_BY_ID = SisSqlUtil
            .isExistByColumnNameAndStatus("OFFICER_ACADEMIC_INFO",
                    OFFICER_ID.getColumnName(),
                    OFFICER_ID.getModelName(),
                    OfficerStatus.ACTIVE.toString());
}
