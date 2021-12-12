package com.graduationproject.studentinformationsystem.university.parameter.repository.impl.scripts;

public class ParameterSqlScripts {

    private static final StringBuilder sqlBuilder = new StringBuilder();

    private ParameterSqlScripts() {
    }

    /**
     * SELECT VALUE FROM STUDENT_PARAMETER WHERE NAME=:name;
     */
    public static final String GET_STUDENT_PARAMETER_BY_NAME =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT VALUE FROM STUDENT_PARAMETER WHERE NAME=:name").toString();

    /**
     * SELECT VALUE FROM TEACHER_PARAMETER WHERE NAME=:name;
     */
    public static final String GET_TEACHER_PARAMETER_BY_NAME =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT VALUE FROM TEACHER_PARAMETER WHERE NAME=:name").toString();

    /**
     * SELECT VALUE FROM OFFICER_PARAMETER WHERE NAME=:name;
     */
    public static final String GET_OFFICER_PARAMETER_BY_NAME =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT VALUE FROM OFFICER_PARAMETER WHERE NAME=:name").toString();
}
