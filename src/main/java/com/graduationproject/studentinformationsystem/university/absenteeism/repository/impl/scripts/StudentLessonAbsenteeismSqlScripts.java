package com.graduationproject.studentinformationsystem.university.absenteeism.repository.impl.scripts;

import com.graduationproject.studentinformationsystem.common.util.sql.SisSqlUtil;

import static com.graduationproject.studentinformationsystem.university.absenteeism.model.mapping.StudentLessonAbsenteeismMapping.ID;
import static com.graduationproject.studentinformationsystem.university.absenteeism.model.mapping.StudentLessonAbsenteeismMapping.STUDENT_ID;

public class StudentLessonAbsenteeismSqlScripts {

    private static final StringBuilder sqlBuilder = new StringBuilder();

    private StudentLessonAbsenteeismSqlScripts() {
    }

    /**
     * SELECT ID, TEACHER_ID, STUDENT_ID, LESSON_ID, WEEK,
     * THEORETICAL_HOURS_STATE, THEORETICAL_HOURS, MAX_THEORETICAL_HOURS,
     * PRACTICE_HOURS_STATE, PRACTICE_HOURS, MAX_PRACTICE_HOURS,
     * STATUS, CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID FROM STUDENT_LESSON_ABSENTEEISM;
     */
    private static final String GET_ALL_STUDENTS_LESSONS_ABSENTEEISM =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT ID, TEACHER_ID, STUDENT_ID, LESSON_ID, WEEK, " +
                            "THEORETICAL_HOURS_STATE, THEORETICAL_HOURS, MAX_THEORETICAL_HOURS, " +
                            "PRACTICE_HOURS_STATE, PRACTICE_HOURS, MAX_PRACTICE_HOURS, " +
                            "STATUS, CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID " +
                            "FROM STUDENT_LESSON_ABSENTEEISM ").toString();

    /**
     * SELECT ID, TEACHER_ID, STUDENT_ID, LESSON_ID, WEEK,
     * THEORETICAL_HOURS_STATE, THEORETICAL_HOURS, MAX_THEORETICAL_HOURS,
     * PRACTICE_HOURS_STATE, PRACTICE_HOURS, MAX_PRACTICE_HOURS,
     * STATUS, CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID
     * FROM STUDENT_LESSON_ABSENTEEISM WHERE STUDENT_ID=:studentId;
     */
    public static final String GET_ALL_STUDENT_LESSONS_ABSENTEEISM_BY_STUDENT_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append(GET_ALL_STUDENTS_LESSONS_ABSENTEEISM)
                    .append("WHERE STUDENT_ID=:studentId").toString();

    /**
     * SELECT ID, TEACHER_ID, STUDENT_ID, LESSON_ID, WEEK,
     * THEORETICAL_HOURS_STATE, THEORETICAL_HOURS,
     * PRACTICE_HOURS_STATE, PRACTICE_HOURS,
     * STATUS, CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID
     * FROM STUDENT_LESSON_ABSENTEEISM WHERE LESSON_ID=:lessonId AND WEEK=:week;
     */
    public static final String GET_ALL_STUDENTS_LESSON_ABSENTEEISM_BY_LESSON_ID_AND_WEEK =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT ID, TEACHER_ID, STUDENT_ID, LESSON_ID, WEEK, " +
                            "THEORETICAL_HOURS_STATE, THEORETICAL_HOURS, " +
                            "PRACTICE_HOURS_STATE, PRACTICE_HOURS, " +
                            "STATUS, CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID " +
                            "FROM STUDENT_LESSON_ABSENTEEISM WHERE LESSON_ID=:lessonId AND WEEK=:week").toString();

    /**
     * SELECT ID, TEACHER_ID, STUDENT_ID, LESSON_ID,
     * THEORETICAL_HOURS_STATE, THEORETICAL_HOURS,
     * PRACTICE_HOURS_STATE, PRACTICE_HOURS,
     * STATUS, CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID
     * FROM STUDENT_LESSON_ABSENTEEISM WHERE ID=:id;
     */
    public static final String GET_STUDENT_LESSON_ABSENTEEISM_BY_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT ID, TEACHER_ID, STUDENT_ID, LESSON_ID, WEEK, " +
                            "THEORETICAL_HOURS_STATE, THEORETICAL_HOURS, " +
                            "PRACTICE_HOURS_STATE, PRACTICE_HOURS, " +
                            "STATUS, CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID " +
                            "FROM STUDENT_LESSON_ABSENTEEISM WHERE ID=:id").toString();

    /**
     * INSERT INTO STUDENT_LESSON_ABSENTEEISM (ID, TEACHER_ID, STUDENT_ID, LESSON_ID, WEEK,
     * THEORETICAL_HOURS_STATE, MAX_THEORETICAL_HOURS, PRACTICE_HOURS_STATE, MAX_PRACTICE_HOURS,
     * STATUS, CREATED_DATE, CREATED_USER_ID) VALUES (:id, :teacherId, :studentId, :lessonId, :week,
     * :theoreticalHoursState, :maxTheoreticalHours, :practiceHoursState, :maxPracticeHours,
     * :status, :createdDate, :createdUserId);
     */
    public static final String SAVE_STUDENT_LESSON_ABSENTEEISM =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("INSERT INTO STUDENT_LESSON_ABSENTEEISM (ID, TEACHER_ID, STUDENT_ID, LESSON_ID, WEEK, " +
                            "THEORETICAL_HOURS_STATE, MAX_THEORETICAL_HOURS, PRACTICE_HOURS_STATE, MAX_PRACTICE_HOURS, " +
                            "STATUS, CREATED_DATE, CREATED_USER_ID) VALUES (:id, :teacherId, :studentId, :lessonId, :week, " +
                            ":theoreticalHoursState, :maxTheoreticalHours, :practiceHoursState, :maxPracticeHours, " +
                            ":status, :createdDate, :createdUserId)").toString();

    /**
     * UPDATE STUDENT_LESSON_ABSENTEEISM
     * SET THEORETICAL_HOURS_STATE:theoreticalHoursState, THEORETICAL_HOURS=:theoreticalHours,
     * PRACTICE_HOURS_STATE:practiceHoursState, PRACTICE_HOURS=:practiceHours, STATUS=:status,
     * MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId WHERE ID=:id;
     */
    public static final String UPDATE_STUDENT_LESSON_ABSENTEEISM =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE STUDENT_LESSON_ABSENTEEISM " +
                            "SET THEORETICAL_HOURS_STATE=:theoreticalHoursState, THEORETICAL_HOURS=:theoreticalHours, " +
                            "PRACTICE_HOURS_STATE=:practiceHoursState, PRACTICE_HOURS=:practiceHours, STATUS=:status, " +
                            "MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId WHERE ID=:id").toString();

    /**
     * UPDATE STUDENT_LESSON_ABSENTEEISM SET STATUS=:status WHERE ID=:id;
     */
    public static final String UPDATE_STUDENT_LESSON_ABSENTEEISM_STATUS =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE STUDENT_LESSON_ABSENTEEISM SET STATUS=:status WHERE ID=:id").toString();

    /**
     * SELECT CASE WHEN MAX(ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM STUDENT_LESSON_ABSENTEEISM WHERE ID=:id;
     */
    public static final String IS_STUDENT_LESSON_ABSENTEEISM_EXIST_BY_ID = SisSqlUtil
            .isExistByColumnName("STUDENT_LESSON_ABSENTEEISM",
                    ID.getColumnName(),
                    ID.getModelName());

    /**
     * SELECT STUDENT_ID FROM STUDENT_LESSON_ABSENTEEISM WHERE ID=:id;
     */
    public static final String GET_STUDENT_ID_BY_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT STUDENT_ID FROM STUDENT_LESSON_ABSENTEEISM WHERE ID=:id").toString();

    /**
     * SELECT LESSON_ID FROM STUDENT_LESSON_ABSENTEEISM WHERE ID=:id;
     */
    public static final String GET_LESSON_ID_BY_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT LESSON_ID FROM STUDENT_LESSON_ABSENTEEISM WHERE ID=:id").toString();

    /**
     * SELECT CASE WHEN MAX(ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM STUDENT_LESSON_ABSENTEEISM WHERE STUDENT_ID=:studentId;
     */
    public static final String IS_STUDENT_LESSON_ABSENTEEISM_EXIST_BY_STUDENT_ID = SisSqlUtil
            .isExistByColumnName("STUDENT_LESSON_ABSENTEEISM",
                    STUDENT_ID.getColumnName(),
                    STUDENT_ID.getModelName());

    /**
     * SELECT SUM(THEORETICAL_HOURS) AS TOTAL_THEORETICAL_HOURS FROM STUDENT_LESSON_ABSENTEEISM
     * WHERE
     * STUDENT_ID=(SELECT STUDENT_ID FROM STUDENT_LESSON_ABSENTEEISM WHERE ID=:id)
     * AND
     * LESSON_ID=(SELECT LESSON_ID FROM STUDENT_LESSON_ABSENTEEISM WHERE ID=:id);
     */
    public static final String CALCULATE_TOTAL_THEORETICAL_HOURS_BY_STUDENT_ID_AND_LESSON_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT SUM(THEORETICAL_HOURS) AS TOTAL_THEORETICAL_HOURS FROM STUDENT_LESSON_ABSENTEEISM " +
                            "WHERE " +
                            "STUDENT_ID=(SELECT STUDENT_ID FROM STUDENT_LESSON_ABSENTEEISM WHERE ID=:id) " +
                            "AND " +
                            "LESSON_ID=(SELECT LESSON_ID FROM STUDENT_LESSON_ABSENTEEISM WHERE ID=:id)").toString();

    /**
     * SELECT SUM(PRACTICE_HOURS) AS TOTAL_PRACTICE_HOURS FROM STUDENT_LESSON_ABSENTEEISM
     * WHERE
     * STUDENT_ID=(SELECT STUDENT_ID FROM STUDENT_LESSON_ABSENTEEISM WHERE ID=:id)
     * AND
     * LESSON_ID=(SELECT LESSON_ID FROM STUDENT_LESSON_ABSENTEEISM WHERE ID=:id);
     */
    public static final String CALCULATE_TOTAL_PRACTICE_HOURS_BY_STUDENT_ID_AND_LESSON_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT SUM(PRACTICE_HOURS) AS TOTAL_PRACTICE_HOURS FROM STUDENT_LESSON_ABSENTEEISM " +
                            "WHERE " +
                            "STUDENT_ID=(SELECT STUDENT_ID FROM STUDENT_LESSON_ABSENTEEISM WHERE ID=:id) " +
                            "AND " +
                            "LESSON_ID=(SELECT LESSON_ID FROM STUDENT_LESSON_ABSENTEEISM WHERE ID=:id)").toString();

    /**
     * SELECT MAX_THEORETICAL_HOURS FROM STUDENT_LESSON_ABSENTEEISM
     * WHERE
     * STUDENT_ID=(SELECT STUDENT_ID FROM STUDENT_LESSON_ABSENTEEISM WHERE ID=:id)
     * AND
     * LESSON_ID=(SELECT LESSON_ID FROM STUDENT_LESSON_ABSENTEEISM WHERE ID=:id);
     */
    public static final String GET_MAX_THEORETICAL_HOURS_BY_STUDENT_ID_AND_LESSON_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT MAX_THEORETICAL_HOURS FROM STUDENT_LESSON_ABSENTEEISM " +
                            "WHERE " +
                            "STUDENT_ID=(SELECT STUDENT_ID FROM STUDENT_LESSON_ABSENTEEISM WHERE ID=:id) " +
                            "AND " +
                            "LESSON_ID=(SELECT LESSON_ID FROM STUDENT_LESSON_ABSENTEEISM WHERE ID=:id)").toString();

    /**
     * SELECT MAX_PRACTICE_HOURS FROM STUDENT_LESSON_ABSENTEEISM
     * WHERE
     * STUDENT_ID=(SELECT STUDENT_ID FROM STUDENT_LESSON_ABSENTEEISM WHERE ID=:id)
     * AND
     * LESSON_ID=(SELECT LESSON_ID FROM STUDENT_LESSON_ABSENTEEISM WHERE ID=:id);
     */
    public static final String GET_MAX_PRACTICE_HOURS_BY_STUDENT_ID_AND_LESSON_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT MAX_PRACTICE_HOURS FROM STUDENT_LESSON_ABSENTEEISM " +
                            "WHERE " +
                            "STUDENT_ID=(SELECT STUDENT_ID FROM STUDENT_LESSON_ABSENTEEISM WHERE ID=:id) " +
                            "AND " +
                            "LESSON_ID=(SELECT LESSON_ID FROM STUDENT_LESSON_ABSENTEEISM WHERE ID=:id)").toString();
}
