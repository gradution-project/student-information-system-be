package com.graduationproject.studentinformationsystem.university.schedule.exam.repository.impl.scripts;

import com.graduationproject.studentinformationsystem.common.util.sql.SisSqlUtil;

import static com.graduationproject.studentinformationsystem.university.schedule.exam.model.mapping.ExamScheduleFileMapping.FILE_ID;

public class ExamScheduleFileSqlScripts {

    private static final StringBuilder sqlBuilder = new StringBuilder();

    private ExamScheduleFileSqlScripts() {
    }

    /**
     * SELECT FILE_ID, FACULTY_ID, DEPARTMENT_ID, FILE, FILE_NAME, FILE_TYPE, FILE_BYTE, FILE_SIZE, API_URL,
     * CREATED_USER_ID, CREATED_DATE FROM UNIV_EXAM_SCHEDULE_FILE WHERE FACULTY_ID=:facultyId;
     */
    public static final String GET_EXAM_SCHEDULE_FILES_BY_FACULTY_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT FILE_ID, FACULTY_ID, DEPARTMENT_ID, API_URL, FILE, FILE_NAME, FILE_TYPE, FILE_BYTE, " +
                            "FILE_SIZE, API_URL, CREATED_USER_ID, CREATED_DATE FROM UNIV_EXAM_SCHEDULE_FILE " +
                            "WHERE FACULTY_ID=:facultyId").toString();

    /**
     * SELECT FILE_NAME, FILE_BYTE, FILE_SIZE, FILE FROM UNIV_EXAM_SCHEDULE_FILE WHERE FILE_ID=:fileId;
     */
    public static final String GET_EXAM_SCHEDULE_FILE_BY_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT FILE_NAME, FILE_BYTE, FILE_SIZE, FILE FROM UNIV_EXAM_SCHEDULE_FILE WHERE FILE_ID=:fileId").toString();

    /**
     * SELECT FILE_ID, DEPARTMENT_ID, API_URL, FILE, FILE_NAME, FILE_TYPE, FILE_BYTE, FILE_SIZE, CREATED_USER_ID, CREATED_DATE
     * FROM UNIV_EXAM_SCHEDULE_FILE WHERE DEPARTMENT_ID=:departmentId;
     */
    public static final String GET_EXAM_SCHEDULE_FILE_BY_DEPARTMENT_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT FILE_ID, DEPARTMENT_ID, FILE, FILE_NAME, FILE_TYPE, FILE_BYTE, FILE_SIZE, " +
                            "API_URL, CREATED_USER_ID, CREATED_DATE FROM UNIV_EXAM_SCHEDULE_FILE " +
                            "WHERE DEPARTMENT_ID=:departmentId").toString();

    /**
     * INSERT INTO UNIV_EXAM_SCHEDULE_FILE SET FILE_ID=:fileId, FACULTY_ID=:facultyId, DEPARTMENT_ID=:departmentId,
     * FILE=:file, FILE_NAME=:fileName, FILE_TYPE=:fileType, FILE_BYTE=:fileByte, FILE_SIZE=:fileSize, API_URL=:apiUrl,
     * CREATED_USER_ID=:createdUserId, CREATED_DATE=:createdDate;
     */
    public static final String SAVE_EXAM_SCHEDULE_FILE =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("INSERT INTO UNIV_EXAM_SCHEDULE_FILE SET FILE_ID=:fileId, FACULTY_ID=:facultyId," +
                            "DEPARTMENT_ID=:departmentId, FILE=:file, FILE_NAME=:fileName, FILE_TYPE=:fileType, " +
                            "FILE_BYTE=:fileByte, FILE_SIZE=:fileSize, API_URL=:apiUrl, " +
                            "CREATED_USER_ID=:createdUserId, CREATED_DATE=:createdDate").toString();

    /**
     * DELETE FROM UNIV_EXAM_SCHEDULE_FILE WHERE DEPARTMENT_ID=:departmentId;
     */
    public static final String DELETE_EXAM_SCHEDULE_FILE =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("DELETE FROM UNIV_EXAM_SCHEDULE_FILE WHERE DEPARTMENT_ID=:departmentId").toString();

    /**
     * SELECT CASE WHEN MAX(FILE_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM UNIV_EXAM_SCHEDULE_FILE WHERE FILE_ID=:fileId;
     */
    public static final String IS_EXAM_SCHEDULE_FILE = SisSqlUtil
            .isExistByColumnName("UNIV_EXAM_SCHEDULE_FILE",
                    FILE_ID.getColumnName(),
                    FILE_ID.getModelName());
}
