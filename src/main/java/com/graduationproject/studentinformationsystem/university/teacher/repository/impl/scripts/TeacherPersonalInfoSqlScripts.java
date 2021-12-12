package com.graduationproject.studentinformationsystem.university.teacher.repository.impl.scripts;

// TODO: Set Profile Photo
// TODO: Set Profile Photo URL
public class TeacherPersonalInfoSqlScripts {

    private static final StringBuilder sqlBuilder = new StringBuilder();

    private TeacherPersonalInfoSqlScripts() {
    }

    /**
     * SELECT TEACHER_ID, TC_NO, NAME, SURNAME, EMAIL, PHONE_NUMBER, STATUS, BIRTHDAY, ADDRESS,
     * CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID FROM TEACHER_PERSONAL_INFO;
     */
    private static final String GET_TEACHER_PERSONAL_INFOS =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT TEACHER_ID, TC_NO, NAME, SURNAME, EMAIL, PHONE_NUMBER, STATUS, BIRTHDAY, ADDRESS, " +
                            "CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID " +
                            "FROM TEACHER_PERSONAL_INFO ").toString();

    /**
     * SELECT TEACHER_ID, TC_NO, NAME, SURNAME, EMAIL, PHONE_NUMBER, STATUS, BIRTHDAY, ADDRESS,
     * CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID FROM TEACHER_PERSONAL_INFO
     * WHERE STATUS IN ':=status';
     */
    public static final String GET_ALL_TEACHER_PERSONAL_INFOS_BY_STATUS =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append(GET_TEACHER_PERSONAL_INFOS)
                    .append("WHERE STATUS IN ").toString();

    /**
     * SELECT TEACHER_ID, TC_NO, NAME, SURNAME, EMAIL, PHONE_NUMBER, STATUS, BIRTHDAY, ADDRESS,
     * CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID FROM TEACHER_PERSONAL_INFO
     * WHERE TEACHER_ID=:teacherId;
     */
    public static final String GET_TEACHER_PERSONAL_INFO_BY_TEACHER_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append(GET_TEACHER_PERSONAL_INFOS)
                    .append("WHERE TEACHER_ID=:teacherId").toString();

    /**
     * INSERT INTO TEACHER_PERSONAL_INFO (TEACHER_ID, TC_NO, NAME, SURNAME, EMAIL, PHONE_NUMBER, STATUS, BIRTHDAY,
     * ADDRESS, CREATED_DATE, CREATED_USER_ID) VALUES (:teacherId, :tcNo, :name,
     * :surname, :email, :phoneNumber, :status, :birthday, :address, :createdDate, :createdUserId);
     */
    public static final String SAVE_TEACHER_PERSONAL_INFO =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("INSERT INTO TEACHER_PERSONAL_INFO (TEACHER_ID, TC_NO, NAME, SURNAME, EMAIL, PHONE_NUMBER, " +
                            "STATUS, BIRTHDAY, ADDRESS, CREATED_DATE, CREATED_USER_ID) VALUES (:teacherId, :tcNo, " +
                            ":name, :surname, :email, :phoneNumber, :status, :birthday, :address, " +
                            ":createdDate, :createdUserId)").toString();

    /**
     * UPDATE TEACHER_PERSONAL_INFO SET TC_NO=:tcNo, NAME=:name, SURNAME=:surname, EMAIL=:email,
     * PHONE_NUMBER=:phoneNumber, BIRTHDAY=:birthday, ADDRESS=:address,
     * MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId WHERE TEACHER_ID=:teacherId;
     */
    public static final String UPDATE_TEACHER_PERSONAL_INFO =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE TEACHER_PERSONAL_INFO SET TC_NO=:tcNo, NAME=:name,  SURNAME=:surname, " +
                            "EMAIL=:email, PHONE_NUMBER=:phoneNumber, BIRTHDAY=:birthday, ADDRESS=:address, " +
                            "MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId " +
                            "WHERE TEACHER_ID=:teacherId").toString();

    /**
     * UPDATE TEACHER_PERSONAL_INFO SET STATUS=:status, MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId
     * WHERE TEACHER_ID=:teacherId;
     */
    public static final String UPDATE_TEACHER_PERSONAL_INFO_STATUS =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE TEACHER_PERSONAL_INFO SET STATUS=:status, " +
                            "MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId " +
                            "WHERE TEACHER_ID=:teacherId").toString();
}
