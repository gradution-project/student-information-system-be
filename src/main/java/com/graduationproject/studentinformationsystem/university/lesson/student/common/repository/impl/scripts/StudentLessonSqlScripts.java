package com.graduationproject.studentinformationsystem.university.lesson.student.common.repository.impl.scripts;

import com.graduationproject.studentinformationsystem.common.util.sql.SisSqlUtil;

import static com.graduationproject.studentinformationsystem.university.lesson.student.common.model.mapping.StudentLessonMapping.LESSON_ID;
import static com.graduationproject.studentinformationsystem.university.lesson.student.common.model.mapping.StudentLessonMapping.STUDENT_ID;

public class StudentLessonSqlScripts {

    private static final StringBuilder sqlBuilder = new StringBuilder();

    private StudentLessonSqlScripts() {
    }

    /**
     * SELECT STUDENT_ID, LESSON_ID, CREATED_USER_ID, CREATED_DATE FROM STUDENT_LESSON WHERE STUDENT_ID=:studentId;
     */
    public static final String GET_STUDENT_ALL_LESSONS =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT STUDENT_ID, LESSON_ID, CREATED_USER_ID, CREATED_DATE " +
                            "FROM STUDENT_LESSON WHERE STUDENT_ID=:studentId ").toString();

    /**
     * SELECT STUDENT_ID, LESSON_ID, CREATED_USER_ID, CREATED_DATE FROM STUDENT_LESSON
     * WHERE STUDENT_ID=:studentId AND LESSON_ID=:lessonId;
     */
    public static final String GET_STUDENT_LESSON =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append(GET_STUDENT_ALL_LESSONS)
                    .append("AND LESSON_ID=:lessonId").toString();

    /**
     * INSERT INTO STUDENT_LESSON (STUDENT_ID, LESSON_ID, CREATED_DATE, CREATED_USER_ID)
     * VALUES (:studentId, :lessonId, :createdDate, :createdUserId);
     */
    public static final String SAVE_STUDENT_LESSON =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("INSERT INTO STUDENT_LESSON (STUDENT_ID, LESSON_ID, CREATED_DATE, CREATED_USER_ID) " +
                            "VALUES (:studentId, :lessonId, :createdDate, :createdUserId)").toString();

    /**
     * DELETE FROM STUDENT_LESSON WHERE STUDENT_ID=:studentId AND LESSON_ID=:lessonId;
     */
    public static final String DELETE_STUDENT_LESSONS =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("DELETE FROM STUDENT_LESSON WHERE STUDENT_ID=:studentId").toString();

    /**
     * SELECT CASE WHEN MAX(STUDENT_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM STUDENT_LESSON WHERE STUDENT_ID=:studentId;
     */
    public static final String IS_STUDENT_LESSONS_EXIST = SisSqlUtil
            .isExistByColumnName("STUDENT_LESSON",
                    STUDENT_ID.getColumnName(),
                    STUDENT_ID.getModelName());

    /**
     * SELECT CASE WHEN MAX(STUDENT_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM STUDENT_LESSON WHERE STUDENT_ID=:studentId AND LESSON_ID=:lessonId;
     */
    public static final String IS_STUDENT_LESSON_EXIST = SisSqlUtil
            .isExistByColumnTwoColumns("STUDENT_LESSON",
                    STUDENT_ID.getColumnName(),
                    STUDENT_ID.getModelName(),
                    LESSON_ID.getColumnName(),
                    LESSON_ID.getModelName());
}
