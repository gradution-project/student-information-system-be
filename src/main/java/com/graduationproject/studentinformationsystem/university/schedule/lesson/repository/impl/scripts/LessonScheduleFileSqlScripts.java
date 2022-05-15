package com.graduationproject.studentinformationsystem.university.schedule.lesson.repository.impl.scripts;

import com.graduationproject.studentinformationsystem.common.util.sql.SisSqlUtil;

import static com.graduationproject.studentinformationsystem.university.schedule.common.model.mapping.ScheduleFileMapping.FILE_ID;

public class LessonScheduleFileSqlScripts {

    private static final StringBuilder sqlBuilder = new StringBuilder();

    private LessonScheduleFileSqlScripts() {
    }

    /**
     * SELECT FILE_ID, FACULTY_ID, DEPARTMENT_ID, FILE, FILE_NAME, FILE_TYPE, FILE_BYTE, FILE_SIZE, API_URL,
     * CREATED_USER_ID, CREATED_DATE FROM UNIV_LESSON_SCHEDULE_FILE WHERE FACULTY_ID=:facultyId;
     */
    public static final String GET_LESSON_SCHEDULE_FILES_BY_FACULTY_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT FILE_ID, FACULTY_ID, DEPARTMENT_ID, API_URL, FILE, FILE_NAME, FILE_TYPE, FILE_BYTE, " +
                            "FILE_SIZE, API_URL, CREATED_USER_ID, CREATED_DATE FROM UNIV_LESSON_SCHEDULE_FILE " +
                            "WHERE FACULTY_ID=:facultyId").toString();

    /**
     * SELECT FILE_NAME, FILE_BYTE, FILE_SIZE, FILE FROM UNIV_LESSON_SCHEDULE_FILE WHERE FILE_ID=:fileId;
     */
    public static final String GET_LESSON_SCHEDULE_FILE_BY_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT FILE_NAME, FILE_BYTE, FILE_SIZE, FILE FROM UNIV_LESSON_SCHEDULE_FILE WHERE FILE_ID=:fileId").toString();

    /**
     * SELECT FILE_ID, DEPARTMENT_ID, API_URL, FILE, FILE_NAME, FILE_TYPE, FILE_BYTE, FILE_SIZE, CREATED_USER_ID, CREATED_DATE
     * FROM UNIV_LESSON_SCHEDULE_FILE WHERE DEPARTMENT_ID=:departmentId;
     */
    public static final String GET_LESSON_SCHEDULE_FILE_BY_DEPARTMENT_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT FILE_ID, DEPARTMENT_ID, FILE, FILE_NAME, FILE_TYPE, FILE_BYTE, FILE_SIZE, " +
                            "API_URL, CREATED_USER_ID, CREATED_DATE FROM UNIV_LESSON_SCHEDULE_FILE " +
                            "WHERE DEPARTMENT_ID=:departmentId").toString();

    /**
     * INSERT INTO UNIV_LESSON_SCHEDULE_FILE SET FILE_ID=:fileId, FACULTY_ID=:facultyId, DEPARTMENT_ID=:departmentId,
     * FILE=:file, FILE_NAME=:fileName, FILE_TYPE=:fileType, FILE_BYTE=:fileByte, FILE_SIZE=:fileSize,
     * CREATED_USER_ID=:createdUserId, CREATED_DATE=:createdDate;
     */
    public static final String SAVE_LESSON_SCHEDULE_FILE =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("INSERT INTO UNIV_LESSON_SCHEDULE_FILE SET FILE_ID=:fileId, FACULTY_ID=:facultyId," +
                            "DEPARTMENT_ID=:departmentId, FILE=:file, FILE_NAME=:fileName, FILE_TYPE=:fileType, " +
                            "FILE_BYTE=:fileByte, FILE_SIZE=:fileSize, " +
                            "CREATED_USER_ID=:createdUserId, CREATED_DATE=:createdDate").toString();

    /**
     * DELETE FROM UNIV_LESSON_SCHEDULE_FILE WHERE DEPARTMENT_ID=:departmentId;
     */
    public static final String DELETE_LESSON_SCHEDULE_FILE =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("DELETE FROM UNIV_LESSON_SCHEDULE_FILE WHERE DEPARTMENT_ID=:departmentId").toString();

    /**
     * SELECT CASE WHEN MAX(FILE_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM UNIV_LESSON_SCHEDULE_FILE WHERE FILE_ID=:fileId;
     */
    public static final String IS_LESSON_SCHEDULE_FILE = SisSqlUtil
            .isExistByColumnName("UNIV_LESSON_SCHEDULE_FILE",
                    FILE_ID.getColumnName(),
                    FILE_ID.getModelName());
}
