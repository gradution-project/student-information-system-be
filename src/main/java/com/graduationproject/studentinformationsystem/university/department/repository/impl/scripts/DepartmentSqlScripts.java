package com.graduationproject.studentinformationsystem.university.department.repository.impl.scripts;

import com.graduationproject.studentinformationsystem.common.util.sql.SisSqlUtil;
import com.graduationproject.studentinformationsystem.university.officer.model.enums.OfficerStatus;

import static com.graduationproject.studentinformationsystem.university.department.model.mapping.DepartmentMapping.DEPARTMENT_ID;

public class DepartmentSqlScripts {

    private static final StringBuilder sqlBuilder = new StringBuilder();

    private DepartmentSqlScripts() {
    }

    /**
     * SELECT DEPARTMENT_ID, FACULTY_ID, NAME, STATUS, TOTAL_CLASS_LEVEL, IS_THERE_PREPARATORY_CLASS,
     * CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID FROM UNIV_DEPARTMENT;
     */
    private static final String GET_UNIV_DEPARTMENTS =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT DEPARTMENT_ID, FACULTY_ID, NAME, STATUS, TOTAL_CLASS_LEVEL, " +
                            "IS_THERE_PREPARATORY_CLASS, CREATED_DATE, CREATED_USER_ID, " +
                            "MODIFIED_DATE, MODIFIED_USER_ID FROM UNIV_DEPARTMENT ").toString();

    /**
     * SELECT DEPARTMENT_ID, FACULTY_ID, NAME, STATUS, TOTAL_CLASS_LEVEL, IS_THERE_PREPARATORY_CLASS,
     * CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID FROM UNIV_DEPARTMENT
     * WHERE STATUS IN '=:status';
     */
    public static final String GET_ALL_DEPARTMENTS_BY_STATUS =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append(GET_UNIV_DEPARTMENTS)
                    .append("WHERE STATUS IN ").toString();

    /**
     * SELECT DEPARTMENT_ID, FACULTY_ID, NAME, STATUS, TOTAL_CLASS_LEVEL, IS_THERE_PREPARATORY_CLASS,
     * CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID FROM UNIV_DEPARTMENT
     * WHERE DEPARTMENT_ID=:departmentId;
     */
    public static final String GET_DEPARTMENT_BY_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append(GET_UNIV_DEPARTMENTS)
                    .append("WHERE DEPARTMENT_ID=:departmentId").toString();

    /**
     * INSERT INTO UNIV_DEPARTMENT (DEPARTMENT_ID, FACULTY_ID, NAME, STATUS, TOTAL_CLASS_LEVEL,
     * IS_THERE_PREPARATORY_CLASS, CREATED_DATE, CREATED_USER_ID) VALUES (:departmentId, :facultyId, :name,
     * :status, :totalClassLevel, :isTherePreparatoryClass, :createdDate, :createdUserId);
     */
    public static final String SAVE_DEPARTMENT =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("INSERT INTO UNIV_DEPARTMENT (DEPARTMENT_ID, FACULTY_ID, NAME, STATUS, " +
                            "TOTAL_CLASS_LEVEL, IS_THERE_PREPARATORY_CLASS, CREATED_DATE, CREATED_USER_ID) " +
                            "VALUES (:departmentId, :facultyId, :name, :status, :totalClassLevel, " +
                            ":isTherePreparatoryClass, :createdDate, :createdUserId)").toString();

    /**
     * UPDATE UNIV_DEPARTMENT SET FACULTY_ID=:facultyId, NAME=:name, TOTAL_CLASS_LEVEL=:totalClassLevel,
     * IS_THERE_PREPARATORY_CLASS=:isTherePreparatoryClass, MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId
     * WHERE DEPARTMENT_ID=:departmentId;
     */
    public static final String UPDATE_DEPARTMENT =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE UNIV_DEPARTMENT SET FACULTY_ID=:facultyId, NAME=:name, " +
                            "TOTAL_CLASS_LEVEL=:totalClassLevel, IS_THERE_PREPARATORY_CLASS=:isTherePreparatoryClass, " +
                            "MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId " +
                            "WHERE DEPARTMENT_ID=:departmentId").toString();

    /**
     * UPDATE UNIV_DEPARTMENT SET STATUS=:status, MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId
     * WHERE DEPARTMENT_ID=:departmentId;
     */
    public static final String UPDATE_DEPARTMENT_STATUS =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE UNIV_DEPARTMENT SET STATUS=:status, " +
                            "MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId " +
                            "WHERE DEPARTMENT_ID=:departmentId").toString();

    /**
     * SELECT DEPARTMENT_ID FROM UNIV_DEPARTMENT WHERE FACULTY_ID=:facultyId;
     */
    public static final String GET_ALL_DEPARTMENT_IDS_BY_FACULTY_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT DEPARTMENT_ID FROM UNIV_DEPARTMENT WHERE FACULTY_ID=:facultyId").toString();

    /**
     * SELECT CASE WHEN MAX(DEPARTMENT_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM UNIV_DEPARTMENT WHERE DEPARTMENT_ID=:departmentId;
     */
    public static final String IS_DEPARTMENT_EXIST_BY_ID = SisSqlUtil.isExistByColumnName("UNIV_DEPARTMENT",
            DEPARTMENT_ID.getColumnName(),
            DEPARTMENT_ID.getModelName());

    /**
     * SELECT CASE WHEN MAX(DEPARTMENT_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM UNIV_DEPARTMENT WHERE DEPARTMENT_ID=:departmentId && STATUS='DELETED';
     */
    public static final String IS_DEPARTMENT_DELETED_BY_ID = SisSqlUtil
            .isExistByColumnNameAndStatus("UNIV_DEPARTMENT",
                    DEPARTMENT_ID.getColumnName(),
                    DEPARTMENT_ID.getModelName(),
                    OfficerStatus.DELETED.toString());

    /**
     * SELECT CASE WHEN MAX(DEPARTMENT_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM UNIV_DEPARTMENT WHERE DEPARTMENT_ID=:departmentId && STATUS='PASSIVE';
     */
    public static final String IS_DEPARTMENT_PASSIVE_BY_ID = SisSqlUtil
            .isExistByColumnNameAndStatus("UNIV_DEPARTMENT",
                    DEPARTMENT_ID.getColumnName(),
                    DEPARTMENT_ID.getModelName(),
                    OfficerStatus.PASSIVE.toString());

    /**
     * SELECT CASE WHEN MAX(DEPARTMENT_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM UNIV_DEPARTMENT WHERE DEPARTMENT_ID=:departmentId && STATUS='ACTIVE';
     */
    public static final String IS_DEPARTMENT_ACTIVE_BY_ID = SisSqlUtil
            .isExistByColumnNameAndStatus("UNIV_DEPARTMENT",
                    DEPARTMENT_ID.getColumnName(),
                    DEPARTMENT_ID.getModelName(),
                    OfficerStatus.ACTIVE.toString());
}
