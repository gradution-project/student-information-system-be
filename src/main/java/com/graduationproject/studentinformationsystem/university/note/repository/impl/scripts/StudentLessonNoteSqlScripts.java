package com.graduationproject.studentinformationsystem.university.note.repository.impl.scripts;

import com.graduationproject.studentinformationsystem.common.util.sql.SisSqlUtil;

import static com.graduationproject.studentinformationsystem.university.note.model.mapping.StudentLessonNoteMapping.ID;

public class StudentLessonNoteSqlScripts {

    private static final StringBuilder sqlBuilder = new StringBuilder();

    private StudentLessonNoteSqlScripts() {
    }

    /**
     * SELECT ID, TEACHER_ID, STUDENT_ID, LESSON_ID, MIDTERM_NOTE, FINAL_NOTE, RESIT_NOTE, MEAN_OF_NOTE, STATUS,
     * CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID FROM STUDENT_LESSON_NOTE;
     */
    private static final String GET_ALL_STUDENTS_LESSONS_NOTES =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT ID, TEACHER_ID, STUDENT_ID, LESSON_ID, MIDTERM_NOTE, FINAL_NOTE, " +
                            "RESIT_NOTE, MEAN_OF_NOTE, STATUS, " +
                            "CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID " +
                            "FROM STUDENT_LESSON_NOTE ").toString();

    /**
     * SELECT ID, TEACHER_ID, STUDENT_ID, LESSON_ID, MIDTERM_NOTE, FINAL_NOTE, RESIT_NOTE, MEAN_OF_NOTE, STATUS,
     * CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID FROM STUDENT_LESSON_NOTE
     * WHERE LESSON_ID=:lessonId;
     */
    public static final String GET_ALL_STUDENTS_LESSON_NOTES_BY_LESSON_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append(GET_ALL_STUDENTS_LESSONS_NOTES)
                    .append("WHERE LESSON_ID=:lessonId").toString();

    /**
     * SELECT ID, TEACHER_ID, STUDENT_ID, LESSON_ID, MIDTERM_NOTE, FINAL_NOTE, RESIT_NOTE, MEAN_OF_NOTE, STATUS,
     * CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID FROM STUDENT_LESSON_NOTE
     * WHERE STUDENT_ID=:studentId;
     */
    public static final String GET_ALL_STUDENT_LESSONS_NOTES_BY_STUDENT_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append(GET_ALL_STUDENTS_LESSONS_NOTES)
                    .append("WHERE STUDENT_ID=:studentId").toString();

    /**
     * SELECT ID, TEACHER_ID, STUDENT_ID, LESSON_ID, MIDTERM_NOTE, FINAL_NOTE, RESIT_NOTE, MEAN_OF_NOTE, STATUS,
     * CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID FROM STUDENT_LESSON_NOTE
     * WHERE LESSON_ID=:lessonId;
     */
    public static final String GET_STUDENT_LESSON_NOTES_BY_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append(GET_ALL_STUDENTS_LESSONS_NOTES)
                    .append("WHERE ID=:id").toString();

    /**
     * INSERT INTO STUDENT_LESSON_NOTE (ID, TEACHER_ID, STUDENT_ID, LESSON_ID, STATUS, CREATED_DATE, CREATED_USER_ID)
     * VALUES (:id, :teacherId, :studentId, :lessonId, :status, :createdDate, :createdUserId);
     */
    public static final String SAVE_STUDENT_LESSON_NOTE =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("INSERT INTO STUDENT_LESSON_NOTE (ID, TEACHER_ID, STUDENT_ID, LESSON_ID, STATUS, " +
                            "CREATED_DATE, CREATED_USER_ID) VALUES (:id, :teacherId, :studentId, :lessonId, :status, " +
                            ":createdDate, :createdUserId)").toString();

    /**
     * UPDATE STUDENT_LESSON_NOTE SET MIDTERM_NOTE=:midtermNote,
     * MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId
     * WHERE ID=:id;
     */
    public static final String UPDATE_STUDENT_LESSON_MIDTERM_NOTE =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE STUDENT_LESSON_NOTE SET MIDTERM_NOTE=:midtermNote, " +
                            "MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId " +
                            "WHERE ID=:id").toString();

    /**
     * UPDATE STUDENT_LESSON_NOTE SET FINAL_NOTE=:finalNote, MEAN_OF_NOTE=:meanOfNote, STATUS=:status,
     * MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId
     * WHERE ID=:id;
     */
    public static final String UPDATE_STUDENT_LESSON_FINAL_AND_MEAN_OF_NOTES =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE STUDENT_LESSON_NOTE SET FINAL_NOTE=:finalNote, MEAN_OF_NOTE=:meanOfNote, " +
                            "STATUS=:status, MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId " +
                            "WHERE ID=:id").toString();

    /**
     * UPDATE STUDENT_LESSON_NOTE SET RESIT_NOTE=:resitNote, MEAN_OF_NOTE=:meanOfNote, STATUS=:status,
     * MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId
     * WHERE ID=:id;
     */
    public static final String UPDATE_STUDENT_LESSON_RESIT_AND_MEAN_OF_NOTES =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE STUDENT_LESSON_NOTE SET RESIT_NOTE=:resitNote, MEAN_OF_NOTE=:meanOfNote, " +
                            "STATUS=:status, MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId " +
                            "WHERE ID=:id").toString();

    /**
     * SELECT CASE WHEN MAX(ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM STUDENT_LESSON_NOTE WHERE ID=:id;
     */
    public static final String IS_STUDENT_LESSON_NOTES_EXIST_BY_ID = SisSqlUtil
            .isExistByColumnName("STUDENT_LESSON_NOTE",
                    ID.getColumnName(),
                    ID.getModelName());

    /**
     * SELECT CASE WHEN MAX(STUDENT_ID) IS NULL THEN 'true' ELSE 'false' END IS_EXIST
     * FROM STUDENT_LESSON_NOTE WHERE STUDENT_ID=:studentId AND (STATUS='FAILED' OR STATUS='UNFINALISED');
     */
    public static final String HAS_THE_STUDENT_PASSED_ALL_LESSONS_BY_STUDENT_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT CASE WHEN MAX(STUDENT_ID) IS NULL THEN 'true' ELSE 'false' END IS_EXIST " +
                            "FROM STUDENT_LESSON_NOTE WHERE STUDENT_ID=:studentId AND (STATUS='FAILED' " +
                            "OR STATUS='UNFINALISED')").toString();

    /**
     * SELECT MIDTERM_NOTE FROM STUDENT_LESSON_NOTE WHERE ID=:id;
     */
    public static final String GET_MIDTERM_NOTE_BY_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT MIDTERM_NOTE FROM STUDENT_LESSON_NOTE WHERE ID=:id").toString();
}
