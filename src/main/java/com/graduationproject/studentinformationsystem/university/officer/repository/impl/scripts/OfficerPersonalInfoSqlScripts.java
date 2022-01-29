package com.graduationproject.studentinformationsystem.university.officer.repository.impl.scripts;

// TODO: Set Profile Photo
// TODO: Set Profile Photo URL
public class OfficerPersonalInfoSqlScripts {

    private static final StringBuilder sqlBuilder = new StringBuilder();

    private OfficerPersonalInfoSqlScripts() {
    }

    /**
     * SELECT OFFICER_ID, TC_NO, NAME, SURNAME, EMAIL, PHONE_NUMBER, STATUS, BIRTHDAY, ADDRESS,
     * CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID FROM OFFICER_PERSONAL_INFO;
     */
    private static final String GET_OFFICER_PERSONAL_INFOS =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT OFFICER_ID, TC_NO, NAME, SURNAME, EMAIL, PHONE_NUMBER, STATUS, BIRTHDAY, ADDRESS, " +
                            "CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID " +
                            "FROM OFFICER_PERSONAL_INFO ").toString();

    /**
     * SELECT OFFICER_ID, TC_NO, NAME, SURNAME, EMAIL, PHONE_NUMBER, STATUS, BIRTHDAY, ADDRESS,
     * CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID FROM OFFICER_PERSONAL_INFO
     * WHERE STATUS IN ':=status';
     */
    public static final String GET_ALL_OFFICER_PERSONAL_INFOS_BY_STATUS =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append(GET_OFFICER_PERSONAL_INFOS)
                    .append("WHERE STATUS IN ").toString();

    /**
     * SELECT OFFICER_ID, TC_NO, NAME, SURNAME, EMAIL, PHONE_NUMBER, STATUS, BIRTHDAY, ADDRESS,
     * CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID FROM OFFICER_PERSONAL_INFO
     * WHERE OFFICER_ID=:officerId;
     */
    public static final String GET_OFFICER_PERSONAL_INFO_BY_OFFICER_ID =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append(GET_OFFICER_PERSONAL_INFOS)
                    .append("WHERE OFFICER_ID=:officerId").toString();

    /**
     * INSERT INTO OFFICER_PERSONAL_INFO (OFFICER_ID, TC_NO, NAME, SURNAME, EMAIL, PHONE_NUMBER, STATUS, BIRTHDAY,
     * ADDRESS, CREATED_DATE, CREATED_USER_ID) VALUES (:officerId, :tcNo, :name,
     * :surname, :email, :phoneNumber, :status, :birthday, :address, :createdDate, :createdUserId);
     */
    public static final String SAVE_OFFICER_PERSONAL_INFO =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("INSERT INTO OFFICER_PERSONAL_INFO (OFFICER_ID, TC_NO, NAME, SURNAME, EMAIL, PHONE_NUMBER, " +
                            "STATUS, BIRTHDAY, ADDRESS, CREATED_DATE, CREATED_USER_ID) VALUES (:officerId, :tcNo, " +
                            ":name, :surname, :email, :phoneNumber, :status, :birthday, :address, " +
                            ":createdDate, :createdUserId)").toString();

    /**
     * UPDATE OFFICER_PERSONAL_INFO SET TC_NO=:tcNo, NAME=:name, SURNAME=:surname, EMAIL=:email,
     * PHONE_NUMBER=:phoneNumber, BIRTHDAY=:birthday, ADDRESS=:address,
     * MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId WHERE OFFICER_ID=:officerId;
     */
    public static final String UPDATE_OFFICER_PERSONAL_INFO =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE OFFICER_PERSONAL_INFO SET TC_NO=:tcNo, NAME=:name,  SURNAME=:surname, " +
                            "EMAIL=:email, PHONE_NUMBER=:phoneNumber, BIRTHDAY=:birthday, ADDRESS=:address, " +
                            "MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId " +
                            "WHERE OFFICER_ID=:officerId").toString();

    /**
     * UPDATE OFFICER_PERSONAL_INFO SET STATUS=:status, MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId
     * WHERE OFFICER_ID=:officerId;
     */
    public static final String UPDATE_OFFICER_PERSONAL_INFO_STATUS =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE OFFICER_PERSONAL_INFO SET STATUS=:status, " +
                            "MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId " +
                            "WHERE OFFICER_ID=:officerId").toString();
}
