package com.graduationproject.studentinformationsystem.university.lesson.teacher.repository.impl.scripts;

import com.graduationproject.studentinformationsystem.common.util.sql.SisSqlUtil;

import static com.graduationproject.studentinformationsystem.university.lesson.teacher.model.mapping.TeacherLessonMapping.LESSON_ID;
import static com.graduationproject.studentinformationsystem.university.lesson.teacher.model.mapping.TeacherLessonMapping.TEACHER_ID;

public class TeacherLessonSqlScripts {

    private static final StringBuilder sqlBuilder = new StringBuilder();

    private TeacherLessonSqlScripts() {
    }

    /**
     * SELECT TEACHER_ID, LESSON_ID, CREATED_USER_ID, CREATED_DATE FROM TEACHER_LESSON;
     */
    public static final String GET_ALL_TEACHERS_LESSONS =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT TEACHER_ID, LESSON_ID, " +
                            "CREATED_USER_ID, CREATED_DATE FROM TEACHER_LESSON ").toString();

    /**
     * SELECT TEACHER_ID, LESSON_ID, CREATED_USER_ID, CREATED_DATE FROM TEACHER_LESSON WHERE TEACHER_ID=:teacherId;
     */
    public static final String GET_TEACHER_ALL_LESSONS =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append(GET_ALL_TEACHERS_LESSONS)
                    .append("WHERE TEACHER_ID=:teacherId ").toString();

    /**
     * SELECT TEACHER_ID, LESSON_ID, CREATED_USER_ID, CREATED_DATE FROM TEACHER_LESSON
     * WHERE TEACHER_ID=:teacherId AND LESSON_ID=:lessonId;
     */
    public static final String GET_TEACHER_LESSON =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append(GET_TEACHER_ALL_LESSONS)
                    .append("AND LESSON_ID=:lessonId").toString();

    /**
     * INSERT INTO TEACHER_LESSON (TEACHER_ID, LESSON_ID, CREATED_DATE, CREATED_USER_ID)
     * VALUES (:teacherId, :lessonId, :createdDate, :createdUserId);
     */
    public static final String SAVE_TEACHER_LESSON =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("INSERT INTO TEACHER_LESSON (TEACHER_ID, LESSON_ID, CREATED_DATE, CREATED_USER_ID) " +
                            "VALUES (:teacherId, :lessonId, :createdDate, :createdUserId)").toString();

    /**
     * DELETE FROM TEACHER_LESSON WHERE TEACHER_ID=:teacherId AND LESSON_ID=:lessonId;
     */
    public static final String DELETE_TEACHER_LESSON =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("DELETE FROM TEACHER_LESSON WHERE TEACHER_ID=:teacherId AND LESSON_ID=:lessonId").toString();

    /**
     * SELECT CASE WHEN MAX(TEACHER_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM TEACHER_LESSON WHERE TEACHER_ID=:teacherId AND LESSON_ID=:lessonId;
     */
    public static final String IS_TEACHER_LESSON_EXIST = SisSqlUtil
            .isExistByColumnTwoColumns("TEACHER_LESSON",
                    TEACHER_ID.getColumnName(),
                    TEACHER_ID.getModelName(),
                    LESSON_ID.getColumnName(),
                    LESSON_ID.getModelName());

    /**
     * SELECT TEACHER_ID FROM TEACHER_LESSON WHERE LESSON_ID=:lessonId;
     */
    public static final String GET_TEACHER_ID_BY_LESSON_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT TEACHER_ID FROM TEACHER_LESSON WHERE LESSON_ID=:lessonId").toString();
}
