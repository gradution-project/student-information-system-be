package com.graduationproject.studentinformationsystem.student.repository.impl.scripts;

// TODO: Set Profile Photo
// TODO: Set Profile Photo URL
public class StudentPersonalInfoSqlScripts {

    private static final StringBuilder sqlBuilder = new StringBuilder();

    private StudentPersonalInfoSqlScripts() {
    }

    /**
     * SELECT TC_NO, NAME, SURNAME, EMAIL, PHONE_NUMBER, STATUS, BIRTHDAY, ADDRESS,
     * CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID FROM STUDENT_PERSONAL_INFO;
     */
    public static final String GET_STUDENT_PERSONAL_INFOS =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT TC_NO, NAME, SURNAME, EMAIL, PHONE_NUMBER, STATUS, BIRTHDAY, ADDRESS, " +
                            "CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID " +
                            "FROM STUDENT_PERSONAL_INFO ").toString();

    /**
     * SELECT TC_NO, NAME, SURNAME, EMAIL, PHONE_NUMBER, STATUS, BIRTHDAY, ADDRESS, CREATED_DATE, CREATED_USER_ID,
     * MODIFIED_DATE, MODIFIED_USER_ID FROM STUDENT_PERSONAL_INFO WHERE STATUS IN ':=status';
     */
    public static final String GET_ALL_STUDENT_PERSONAL_INFOS_BY_STATUS =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append(GET_STUDENT_PERSONAL_INFOS)
                    .append("WHERE STATUS IN ").toString();

    /**
     * SELECT TC_NO, NAME, SURNAME, EMAIL, PHONE_NUMBER, STATUS, BIRTHDAY, ADDRESS, CREATED_DATE, CREATED_USER_ID,
     * MODIFIED_DATE, MODIFIED_USER_ID FROM STUDENT_PERSONAL_INFO WHERE STUDENT_ID=:studentId;
     */
    public static final String GET_STUDENT_PERSONAL_INFO_BY_STUDENT_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append(GET_STUDENT_PERSONAL_INFOS)
                    .append("WHERE STUDENT_ID=:studentId").toString();

    /**
     * INSERT INTO STUDENT_PERSONAL_INFO (STUDENT_ID, TC_NO, NAME, SURNAME, EMAIL, STATUS, PHONE_NUMBER, BIRTHDAY,
     * ADDRESS, CREATED_DATE, CREATED_USER_ID) VALUES (:studentId, :tcNo, :name, :surname, :email, :status,
     * :phoneNumber, :birthday, :address, :createdDate, :createdUserId);
     */
    public static final String SAVE_STUDENT_PERSONAL_INFO =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("INSERT INTO STUDENT_PERSONAL_INFO (STUDENT_ID, TC_NO, NAME, SURNAME, EMAIL, STATUS, " +
                            "PHONE_NUMBER, BIRTHDAY, ADDRESS, CREATED_DATE, CREATED_USER_ID) VALUES (:studentId, " +
                            ":tcNo, :name, :surname, :email, :status, :phoneNumber, :birthday, :address, " +
                            ":createdDate, :createdUserId)").toString();

    /**
     * UPDATE STUDENT_PERSONAL_INFO SET TC_NO =:tcNo, NAME=:name, SURNAME=:surname, EMAIL=:email,
     * PHONE_NUMBER=:phoneNumber, BIRTHDAY=:birthday, ADDRESS=:address,
     * MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId WHERE STUDENT_ID=:studentId;
     */
    public static final String UPDATE_STUDENT_PERSONAL_INFO =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE STUDENT_PERSONAL_INFO SET TC_NO=:tcNo, NAME=:name,  SURNAME=:surname, " +
                            "EMAIL=:email, PHONE_NUMBER=:phoneNumber, BIRTHDAY=:birthday, ADDRESS=:address, " +
                            "MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId " +
                            "WHERE STUDENT_ID=:studentId").toString();

    /**
     * UPDATE STUDENT_PERSONAL_INFO SET STATUS=:status, MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId
     * WHERE STUDENT_ID=:studentId;
     */
    public static final String UPDATE_STUDENT_PERSONAL_INFO_STATUS =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE STUDENT_PERSONAL_INFO SET STATUS=:status, " +
                            "MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId " +
                            "WHERE STUDENT_ID=:studentId").toString();
}
