package com.graduationproject.studentinformationsystem.student.repository.impl.scripts;

import com.graduationproject.studentinformationsystem.common.util.sql.SisSqlUtil;

import static com.graduationproject.studentinformationsystem.student.model.mapping.StudentAcademicInfoMapping.STUDENT_ID;

public class StudentAcademicInfoSqlScripts {

    private static final StringBuilder sqlBuilder = new StringBuilder();

    private StudentAcademicInfoSqlScripts() {
    }

    /**
     * SELECT STUDENT_ID, DEPARTMENT_ID, DEGREE, CLASS_LEVEL, EMAIL, STATUS, REGISTRATION_DATE,
     * CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID FROM STUDENT_ACADEMIC_INFO;
     */
    public static final String GET_STUDENT_ACADEMIC_INFOS =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT STUDENT_ID, DEPARTMENT_ID, DEGREE, CLASS_LEVEL, EMAIL, STATUS, REGISTRATION_DATE, " +
                            "CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID " +
                            "FROM STUDENT_ACADEMIC_INFO ").toString();

    /**
     * SELECT STUDENT_ID, DEPARTMENT_ID, DEGREE, CLASS_LEVEL, EMAIL, STATUS, REGISTRATION_DATE,
     * CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID FROM STUDENT_ACADEMIC_INFO
     * WHERE STATUS IN ':=status';
     */
    public static final String GET_ALL_STUDENT_ACADEMIC_INFOS_BY_STATUS =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append(GET_STUDENT_ACADEMIC_INFOS)
                    .append("WHERE STATUS IN ").toString();

    /**
     * SELECT STUDENT_ID, DEPARTMENT_ID, DEGREE, CLASS_LEVEL, EMAIL, STATUS, REGISTRATION_DATE,
     * CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID FROM STUDENT_ACADEMIC_INFO
     * WHERE STUDENT_ID=:studentId;
     */
    public static final String GET_STUDENT_ACADEMIC_INFO_BY_STUDENT_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append(GET_STUDENT_ACADEMIC_INFOS)
                    .append("WHERE STUDENT_ID=:studentId").toString();

    /**
     * INSERT INTO STUDENT_ACADEMIC_INFO (STUDENT_ID, DEPARTMENT_ID, DEGREE, CLASS_LEVEL, EMAIL, STATUS,
     * REGISTRATION_DATE, CREATED_DATE, CREATED_USER_ID) VALUES (:studentId, :departmentId, :degree, :classLevel,
     * :email, :status, :registrationDate, :createdDate, :createdUserId);
     */
    public static final String SAVE_STUDENT_ACADEMIC_INFO =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("INSERT INTO STUDENT_ACADEMIC_INFO (STUDENT_ID, DEPARTMENT_ID, DEGREE, CLASS_LEVEL, " +
                            "EMAIL, STATUS, REGISTRATION_DATE, CREATED_DATE, CREATED_USER_ID) " +
                            "VALUES (:studentId, :departmentId, :degree, :classLevel, :email, :status, " +
                            ":registrationDate, :createdDate, :createdUserId)").toString();

    /**
     * UPDATE STUDENT_ACADEMIC_INFO SET DEPARTMENT_ID=:departmentId, DEGREE=:degree, CLASS_LEVEL=:classLevel,
     * MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId WHERE STUDENT_ID=:studentId;
     */
    public static final String UPDATE_STUDENT_ACADEMIC_INFO =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE STUDENT_ACADEMIC_INFO SET DEPARTMENT_ID=:departmentId, DEGREE=:degree, " +
                            "CLASS_LEVEL=:classLevel, MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId " +
                            "WHERE STUDENT_ID=:studentId").toString();

    /**
     * UPDATE STUDENT_ACADEMIC_INFO SET STATUS=:status, MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId
     * WHERE STUDENT_ID=:studentId;
     */
    public static final String UPDATE_STUDENT_ACADEMIC_INFO_STATUS =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE STUDENT_ACADEMIC_INFO SET STATUS=:status, " +
                            "MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId " +
                            "WHERE STUDENT_ID=:studentId").toString();

    /**
     * SELECT STUDENT_ID FROM STUDENT_ACADEMIC_INFO WHERE DEPARTMENT_ID=:departmentId;
     */
    public static final String GET_ALL_STUDENT_IDS_BY_DEPARTMENT_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT STUDENT_ID FROM STUDENT_ACADEMIC_INFO WHERE DEPARTMENT_ID=:departmentId").toString();

    /**
     * SELECT CASE WHEN MAX(STUDENT_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM STUDENT_ACADEMIC_INFO WHERE STUDENT_ID=:studentId;
     */
    public static final String IS_STUDENT_EXIST_BY_ID = SisSqlUtil.isExistByColumnName("STUDENT_ACADEMIC_INFO",
            STUDENT_ID.getColumnName(),
            STUDENT_ID.getModelName());
}
