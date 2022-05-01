package com.graduationproject.studentinformationsystem.university.lesson.common.repository.impl.scripts;

import com.graduationproject.studentinformationsystem.common.util.sql.SisSqlUtil;
import com.graduationproject.studentinformationsystem.university.officer.model.enums.OfficerStatus;

import static com.graduationproject.studentinformationsystem.university.lesson.common.model.mapping.LessonMapping.LESSON_ID;

public class LessonSqlScripts {

    private static final StringBuilder sqlBuilder = new StringBuilder();

    private LessonSqlScripts() {
    }

    /**
     * SELECT LESSON_ID, DEPARTMENT_ID, NAME, STATUS, SEMESTER, CREDIT, THEORETICAL_HOURS, PRACTICE_HOURS,
     * COMPULSORY_OR_ELECTIVE, CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID FROM UNIV_LESSON;
     */
    private static final String GET_UNIV_LESSONS =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT LESSON_ID, DEPARTMENT_ID, NAME, STATUS, SEMESTER, CREDIT, THEORETICAL_HOURS, " +
                            "PRACTICE_HOURS, COMPULSORY_OR_ELECTIVE, CREATED_DATE, CREATED_USER_ID, " +
                            "MODIFIED_DATE, MODIFIED_USER_ID FROM UNIV_LESSON ").toString();

    /**
     * SELECT LESSON_ID, DEPARTMENT_ID, NAME, STATUS, SEMESTER, CREDIT, THEORETICAL_HOURS, PRACTICE_HOURS,
     * COMPULSORY_OR_ELECTIVE, CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID FROM UNIV_LESSON
     * WHERE STATUS IN '=:status';
     */
    public static final String GET_ALL_LESSONS_BY_STATUS =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append(GET_UNIV_LESSONS)
                    .append("WHERE STATUS IN ").toString();

    /**
     * SELECT LESSON_ID, DEPARTMENT_ID, NAME, STATUS, SEMESTER, CREDIT, THEORETICAL_HOURS, PRACTICE_HOURS,
     * COMPULSORY_OR_ELECTIVE, CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID FROM UNIV_LESSON
     * WHERE LESSON_ID=:lessonId;
     */
    public static final String GET_LESSON_BY_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append(GET_UNIV_LESSONS)
                    .append("WHERE LESSON_ID=:lessonId").toString();

    /**
     * INSERT INTO UNIV_LESSON (LESSON_ID, DEPARTMENT_ID, NAME, STATUS, SEMESTER, CREDIT, THEORETICAL_HOURS,
     * PRACTICE_HOURS, COMPULSORY_OR_ELECTIVE, CREATED_DATE, CREATED_USER_ID) VALUES (:lessonId, :departmentId,
     * :name, :status, :semester, :credit, :theoreticalHours, :practiceHours, :compulsoryOrElective,
     * :createdDate, :createdUserId);
     */
    public static final String SAVE_LESSON =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("INSERT INTO UNIV_LESSON (LESSON_ID, DEPARTMENT_ID, NAME, STATUS, SEMESTER, CREDIT, " +
                            "THEORETICAL_HOURS, PRACTICE_HOURS, COMPULSORY_OR_ELECTIVE, CREATED_DATE, CREATED_USER_ID)" +
                            " VALUES (:lessonId, :departmentId, :name, :status, :semester, :credit, :theoreticalHours, " +
                            ":practiceHours, :compulsoryOrElective, :createdDate, :createdUserId)").toString();

    /**
     * UPDATE UNIV_LESSON SET LESSON_ID=:lessonId, DEPARTMENT_ID=:departmentId, NAME=:name, SEMESTER=:semester,
     * CREDIT=:credit, THEORETICAL_HOURS=:theoreticalHours, PRACTICE_HOURS=:practiceHours,
     * COMPULSORY_OR_ELECTIVE=:compulsoryOrElective, MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId
     * WHERE LESSON_ID=:lessonId;
     */
    public static final String UPDATE_LESSON =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE UNIV_LESSON SET LESSON_ID=:lessonId, DEPARTMENT_ID=:departmentId, NAME=:name, " +
                            "SEMESTER=:semester, CREDIT=:credit, THEORETICAL_HOURS=:theoreticalHours, " +
                            "PRACTICE_HOURS=:practiceHours, COMPULSORY_OR_ELECTIVE=:compulsoryOrElective, " +
                            "MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId " +
                            "WHERE LESSON_ID=:lessonId").toString();

    /**
     * UPDATE UNIV_LESSON SET STATUS=:status, MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId
     * WHERE LESSON_ID=:lessonId;
     */
    public static final String UPDATE_LESSON_STATUS =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE UNIV_LESSON SET STATUS=:status, " +
                            "MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId " +
                            "WHERE LESSON_ID=:lessonId").toString();

    /**
     * SELECT LESSON_ID FROM UNIV_LESSON WHERE DEPARTMENT_ID=:departmentId;
     */
    public static final String GET_ALL_LESSON_IDS_BY_DEPARTMENT_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT LESSON_ID FROM UNIV_LESSON WHERE DEPARTMENT_ID=:departmentId").toString();

    /**
     * SELECT CASE WHEN MAX(LESSON_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM UNIV_LESSON WHERE LESSON_ID=:lessonId;
     */
    public static final String IS_LESSON_EXIST_BY_ID = SisSqlUtil.isExistByColumnName("UNIV_LESSON",
            LESSON_ID.getColumnName(),
            LESSON_ID.getModelName());

    /**
     * SELECT CASE WHEN MAX(LESSON_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM UNIV_LESSON WHERE LESSON_ID=:lessonId && STATUS='DELETED';
     */
    public static final String IS_LESSON_DELETED_BY_ID = SisSqlUtil
            .isExistByColumnNameAndStatus("UNIV_LESSON",
                    LESSON_ID.getColumnName(),
                    LESSON_ID.getModelName(),
                    OfficerStatus.DELETED.toString());

    /**
     * SELECT CASE WHEN MAX(LESSON_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM UNIV_LESSON WHERE LESSON_ID=:lessonId && STATUS='PASSIVE';
     */
    public static final String IS_LESSON_PASSIVE_BY_ID = SisSqlUtil
            .isExistByColumnNameAndStatus("UNIV_LESSON",
                    LESSON_ID.getColumnName(),
                    LESSON_ID.getModelName(),
                    OfficerStatus.PASSIVE.toString());

    /**
     * SELECT CASE WHEN MAX(LESSON_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM UNIV_LESSON WHERE LESSON_ID=:lessonId && STATUS='ACTIVE';
     */
    public static final String IS_LESSON_ACTIVE_BY_ID = SisSqlUtil
            .isExistByColumnNameAndStatus("UNIV_LESSON",
                    LESSON_ID.getColumnName(),
                    LESSON_ID.getModelName(),
                    OfficerStatus.ACTIVE.toString());
}
