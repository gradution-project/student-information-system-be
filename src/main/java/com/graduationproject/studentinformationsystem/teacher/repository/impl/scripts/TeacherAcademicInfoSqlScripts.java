package com.graduationproject.studentinformationsystem.teacher.repository.impl.scripts;

import com.graduationproject.studentinformationsystem.common.util.sql.SisSqlUtil;

import static com.graduationproject.studentinformationsystem.teacher.model.mapping.TeacherAcademicInfoMapping.TEACHER_ID;

public class TeacherAcademicInfoSqlScripts {

    private static final StringBuilder sqlBuilder = new StringBuilder();

    private TeacherAcademicInfoSqlScripts() {
    }

    /**
     * SELECT TEACHER_ID, DEGREE, ROLE, DEPARTMENT_ID, FIELD_OF_STUDY, EMAIL, PHONE_NUMBER, STATUS, REGISTRATION_DATE,
     * CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID FROM TEACHER_ACADEMIC_INFO;
     */
    private static final String GET_TEACHER_ACADEMIC_INFOS =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT TEACHER_ID, DEGREE, ROLE, DEPARTMENT_ID, FIELD_OF_STUDY, EMAIL, PHONE_NUMBER, " +
                            "STATUS, REGISTRATION_DATE, CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID " +
                            "FROM TEACHER_ACADEMIC_INFO ").toString();

    /**
     * SELECT TEACHER_ID, DEGREE, ROLE, DEPARTMENT_ID, FIELD_OF_STUDY, EMAIL, PHONE_NUMBER, STATUS, REGISTRATION_DATE,
     * CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID FROM TEACHER_ACADEMIC_INFO
     * WHERE STATUS IN '=:status';
     */
    public static final String GET_ALL_TEACHER_ACADEMIC_INFOS_BY_STATUS =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append(GET_TEACHER_ACADEMIC_INFOS)
                    .append("WHERE STATUS IN ").toString();

    /**
     * SELECT TEACHER_ID, DEGREE, ROLE, DEPARTMENT_ID, FIELD_OF_STUDY, EMAIL, PHONE_NUMBER, STATUS, REGISTRATION_DATE,
     * CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID FROM TEACHER_ACADEMIC_INFO
     * WHERE TEACHER_ID=:teacherId;
     */
    public static final String GET_TEACHER_ACADEMIC_INFO_BY_TEACHER_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append(GET_TEACHER_ACADEMIC_INFOS)
                    .append("WHERE TEACHER_ID=:teacherId").toString();

    /**
     * INSERT INTO TEACHER_ACADEMIC_INFO (TEACHER_ID, DEGREE, ROLE, DEPARTMENT_ID, FIELD_OF_STUDY, EMAIL, PHONE_NUMBER,
     * STATUS, REGISTRATION_DATE, CREATED_DATE, CREATED_USER_ID) VALUES (:teacherId, :degree, :role, :departmentId,
     * :fieldOfStudy, :email, :phoneNumber, :status, :registrationDate, :createdDate, :createdUserId);
     */
    public static final String SAVE_TEACHER_ACADEMIC_INFO =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("INSERT INTO TEACHER_ACADEMIC_INFO (TEACHER_ID, DEGREE, ROLE, DEPARTMENT_ID, " +
                            "FIELD_OF_STUDY, EMAIL, PHONE_NUMBER, STATUS, REGISTRATION_DATE, " +
                            "CREATED_DATE, CREATED_USER_ID) VALUES (:teacherId, :degree, :role, :departmentId, " +
                            ":fieldOfStudy, :email, :phoneNumber, :status, :registrationDate, " +
                            ":createdDate, :createdUserId)").toString();

    /**
     * UPDATE TEACHER_ACADEMIC_INFO ∂SET DEGREE=:degree, ROLE=:role DEPARTMENT_ID=:departmentId,
     * FIELD_OF_STUDY=:fieldOfStudy, PHONE_NUMBER=:phoneNumber, MODIFIED_DATE=:modifiedDate,
     * MODIFIED_USER_ID=:modifiedUserId WHERE TEACHER_ID=:teacherId;
     */
    public static final String UPDATE_TEACHER_ACADEMIC_INFO =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE TEACHER_ACADEMIC_INFO SET DEGREE=:degree, ROLE=:role, DEPARTMENT_ID=:departmentId, " +
                            "FIELD_OF_STUDY=:fieldOfStudy, PHONE_NUMBER=:phoneNumber, MODIFIED_DATE=:modifiedDate, " +
                            "MODIFIED_USER_ID=:modifiedUserId WHERE TEACHER_ID=:teacherId").toString();

    /**
     * UPDATE TEACHER_ACADEMIC_INFO SET STATUS=:status, MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId
     * WHERE TEACHER_ID=:teacherId;
     */
    public static final String UPDATE_TEACHER_ACADEMIC_INFO_STATUS =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE TEACHER_ACADEMIC_INFO SET STATUS=:status, " +
                            "MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId " +
                            "WHERE TEACHER_ID=:teacherId").toString();

    /**
     * SELECT TEACHER_ID FROM TEACHER_ACADEMIC_INFO WHERE DEPARTMENT_ID=:departmentId;
     */
    public static final String GET_ALL_TEACHER_IDS_BY_DEPARTMENT_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT TEACHER_ID FROM TEACHER_ACADEMIC_INFO WHERE DEPARTMENT_ID=:departmentId").toString();

    /**
     * SELECT CASE WHEN MAX(TEACHER_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM TEACHER_ACADEMIC_INFO WHERE TEACHER_ID=:teacherId;
     */
    public static final String IS_TEACHER_EXIST_BY_ID = SisSqlUtil.isExistByColumnName("TEACHER_ACADEMIC_INFO",
            TEACHER_ID.getColumnName(),
            TEACHER_ID.getModelName());
}
