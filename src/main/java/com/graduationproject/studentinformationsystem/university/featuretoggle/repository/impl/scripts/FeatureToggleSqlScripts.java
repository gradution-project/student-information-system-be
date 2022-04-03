package com.graduationproject.studentinformationsystem.university.featuretoggle.repository.impl.scripts;

import com.graduationproject.studentinformationsystem.common.util.sql.SisSqlUtil;

import static com.graduationproject.studentinformationsystem.university.featuretoggle.model.mapping.FeatureToggleMapping.NAME;

public class FeatureToggleSqlScripts {

    private static final StringBuilder sqlBuilder = new StringBuilder();

    private FeatureToggleSqlScripts() {
    }

    /**
     * SELECT ID, NAME, IS_ENABLED, START_DATE, END_DATE,
     * CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID FROM UNIV_FEATURE_TOGGLE;
     */
    public static final String GET_ALL_FEATURE_TOGGLES =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT ID, NAME, IS_ENABLED, START_DATE, END_DATE, " +
                            "CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID " +
                            "FROM UNIV_FEATURE_TOGGLE ").toString();

    /**
     * SELECT ID, NAME, IS_ENABLED, START_DATE, END_DATE,
     * CREATED_DATE, CREATED_USER_ID, MODIFIED_DATE, MODIFIED_USER_ID FROM UNIV_FEATURE_TOGGLE
     * WHERE NAME=:name;
     */
    public static final String GET_FEATURE_TOGGLE_BY_NAME =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append(GET_ALL_FEATURE_TOGGLES)
                    .append("WHERE NAME=:name").toString();

    /**
     * UPDATE UNIV_FEATURE_TOGGLE SET IS_ENABLED=1,
     * MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId
     * WHERE NAME=:name;
     */
    public static final String ENABLE_FEATURE_TOGGLE =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE UNIV_FEATURE_TOGGLE SET IS_ENABLED=1, " +
                            "MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId " +
                            "WHERE NAME=:name").toString();

    /**
     * UPDATE UNIV_FEATURE_TOGGLE SET IS_ENABLED=0,
     * MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId
     * WHERE NAME=:name;
     */
    public static final String DISABLE_FEATURE_TOGGLE =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("UPDATE UNIV_FEATURE_TOGGLE SET IS_ENABLED=0, " +
                            "MODIFIED_DATE=:modifiedDate, MODIFIED_USER_ID=:modifiedUserId " +
                            "WHERE NAME=:name").toString();

    /**
     * SELECT CASE WHEN MAX(NAME) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM UNIV_FEATURE_TOGGLE WHERE NAME=:name;
     */
    public static final String IS_FEATURE_TOGGLE_EXIST_BY_NAME = SisSqlUtil
            .isExistByColumnName("UNIV_FEATURE_TOGGLE",
                    NAME.getColumnName(),
                    NAME.getModelName());

    /**
     * SELECT CASE WHEN MAX(NAME) IS NULL THEN 'false' ELSE 'true' END IS_EXIST
     * FROM UNIV_FEATURE_TOGGLE WHERE NAME=:name AND IS_ENABLED=1 AND
     * NOW() > START_DATE AND NOW() < END_DATE;
     */
    public static final String IS_FEATURE_TOGGLE_ENABLED =
            sqlBuilder.delete(0, sqlBuilder.length())
                    .append("SELECT CASE WHEN MAX(NAME) IS NULL " +
                            "THEN 'false' ELSE 'true' END IS_EXIST FROM UNIV_FEATURE_TOGGLE " +
                            "WHERE NAME=:name AND IS_ENABLED='1' AND " +
                            "NOW() > START_DATE AND NOW() < END_DATE").toString();
}
