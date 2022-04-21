package com.graduationproject.studentinformationsystem.university.lesson.student.registration.repository.impl.scripts;

import com.graduationproject.studentinformationsystem.common.util.sql.SisSqlUtil;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.enums.StudentLessonRegistrationStatus;

import static com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.mapping.StudentLessonRegistrationMapping.REGISTRATION_ID;

public class StudentLessonRegistrationSqlScripts {

    private static final StringBuilder sqlBuilder = new StringBuilder();

    private StudentLessonRegistrationSqlScripts() {
    }

    /**
     * SELECT REGISTRATION_ID, STUDENT_ID, STATUS,
     * CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID FROM STUDENT_LESSON_REGISTRATION
     * WHERE STATUS IN 'status';
     */
    public static final String GET_ALL_STUDENT_LESSON_REGISTRATIONS =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT REGISTRATION_ID, STUDENT_ID, STATUS, " +
                            "CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID " +
                            "FROM STUDENT_LESSON_REGISTRATION WHERE STATUS IN ").toString();

    /**
     * SELECT REGISTRATION_ID, STUDENT_ID, LESSONS_IDS, STATUS,
     * CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID FROM STUDENT_LESSON_REGISTRATION
     * WHERE REGISTRATION_ID=:registrationId;
     */
    public static final String GET_STUDENT_LESSON_REGISTRATION =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT REGISTRATION_ID, STUDENT_ID, LESSONS_IDS, STATUS, " +
                            "CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID " +
                            "FROM STUDENT_LESSON_REGISTRATION WHERE REGISTRATION_ID=:registrationId ").toString();

    /**
     * SELECT REGISTRATION_ID, STUDENT_ID, LESSONS_IDS, STATUS,
     * CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID FROM STUDENT_LESSON_REGISTRATION
     * WHERE STUDENT_ID=:studentId AND STATUS='WAITING';
     */
    public static final String GET_WAITING_STUDENT_LESSON_REGISTRATION_BY_STUDENT_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT REGISTRATION_ID, STUDENT_ID, LESSONS_IDS, STATUS, " +
                            "CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID " +
                            "FROM STUDENT_LESSON_REGISTRATION WHERE STUDENT_ID=:studentId AND STATUS='WAITING'").toString();

    /**
     * INSERT INTO STUDENT_LESSON_REGISTRATION (REGISTRATION_ID, STUDENT_ID, LESSONS_IDS, STATUS,
     * CREATED_DATE, CREATED_USER_ID) VALUES (:registrationId, :studentId, lessonsIds, status,
     * :createdDate, :createdUserId);
     */
    public static final String SAVE_STUDENT_LESSON_REGISTRATION =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("INSERT INTO STUDENT_LESSON_REGISTRATION (REGISTRATION_ID, STUDENT_ID, LESSONS_IDS, " +
                            "STATUS, CREATED_DATE, CREATED_USER_ID) VALUES (:registrationId, :studentId, :lessonsIds, " +
                            ":status, :createdDate, :createdUserId) ").toString();

    /**
     * UPDATE STUDENT_LESSON_REGISTRATION SET STATUS=:status,
     * MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId
     * WHERE REGISTRATION_ID=:registrationId;
     */
    public static final String UPDATE_STUDENT_LESSON_REGISTRATION_STATUS =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE STUDENT_LESSON_REGISTRATION SET STATUS=:status, " +
                            "MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId " +
                            "WHERE REGISTRATION_ID=:registrationId").toString();

    /**
     * SELECT CASE WHEN MAX(REGISTRATION_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM STUDENT_LESSON_REGISTRATION WHERE REGISTRATION_ID=:registrationId;
     */
    public static final String IS_STUDENT_LESSON_REGISTRATION_EXIST_BY_REGISTRATION_ID = SisSqlUtil
            .isExistByColumnName("STUDENT_LESSON_REGISTRATION",
                    REGISTRATION_ID.getColumnName(),
                    REGISTRATION_ID.getModelName());

    /**
     * SELECT CASE WHEN MAX(REGISTRATION_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM STUDENT_LESSON_REGISTRATION WHERE REGISTRATION_ID=:registrationId AND STATUS='WAITING';
     */
    public static final String IS_STUDENT_LESSON_REGISTRATION_WAITING_BY_REGISTRATION_ID = SisSqlUtil
            .isExistByColumnNameAndStatus("STUDENT_LESSON_REGISTRATION",
                    REGISTRATION_ID.getColumnName(),
                    REGISTRATION_ID.getModelName(),
                    StudentLessonRegistrationStatus.WAITING.toString());

    /**
     * SELECT CASE WHEN MAX(REGISTRATION_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM STUDENT_LESSON_REGISTRATION WHERE REGISTRATION_ID=:registrationId AND STATUS='APPROVED';
     */
    public static final String IS_STUDENT_LESSON_REGISTRATION_APPROVED_BY_REGISTRATION_ID = SisSqlUtil
            .isExistByColumnNameAndStatus("STUDENT_LESSON_REGISTRATION",
                    REGISTRATION_ID.getColumnName(),
                    REGISTRATION_ID.getModelName(),
                    StudentLessonRegistrationStatus.APPROVED.toString());

    /**
     * SELECT CASE WHEN MAX(REGISTRATION_ID) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM STUDENT_LESSON_REGISTRATION WHERE REGISTRATION_ID=:registrationId AND STATUS='REJECTED';
     */
    public static final String IS_STUDENT_LESSON_REGISTRATION_REJECTED_BY_REGISTRATION_ID = SisSqlUtil
            .isExistByColumnNameAndStatus("STUDENT_LESSON_REGISTRATION",
                    REGISTRATION_ID.getColumnName(),
                    REGISTRATION_ID.getModelName(),
                    StudentLessonRegistrationStatus.REJECTED.toString());

    /**
     * SELECT REGISTRATION_ID FROM STUDENT_LESSON_REGISTRATION WHERE STUDENT_ID=:studentId;
     */
    public static final String GET_REGISTRATION_ID_BY_STUDENT_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT REGISTRATION_ID FROM STUDENT_LESSON_REGISTRATION " +
                            "WHERE STUDENT_ID=:studentId").toString();
}
