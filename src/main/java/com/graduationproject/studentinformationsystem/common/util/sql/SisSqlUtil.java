package com.graduationproject.studentinformationsystem.common.util.sql;

import com.graduationproject.studentinformationsystem.common.model.enums.SisStatus;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.Arrays;
import java.util.StringJoiner;

public class SisSqlUtil {

    private SisSqlUtil() {
    }

    private static final StringBuilder sqlBuilder = new StringBuilder();

    /**
     * SELECT CASE WHEN MAX(columnName) IS NULL THEN 'false' ELSE 'true' END IS_EXIST FROM tableName WHERE columnName=:modelName;
     */
    public static String isExistByColumnName(String tableName, String columnName, String modelName) {
        return sqlBuilder.delete(0, sqlBuilder.length())
                .append("SELECT CASE WHEN MAX(")
                .append(columnName)
                .append(") IS NULL THEN 'false' ELSE 'true' END IS_EXIST FROM ")
                .append(tableName)
                .append(" WHERE ")
                .append(columnName)
                .append("=:")
                .append(modelName).toString();
    }

    /**
     * SELECT MAX(ID) FROM tableName;
     */
    public static Long getMaxId(Sql2o sql2o, String tableName) {
        String script = sqlBuilder.delete(0, sqlBuilder.length())
                .append("SELECT MAX(ID) FROM ")
                .append(tableName).toString();

        try (Connection con = sql2o.open(); Query query = con.createQuery(script)) {
            return query.executeAndFetchFirst(Long.class);
        } catch (Exception e) {
            // TODO: Throw Specific Method Exception
            return null;
        }
    }

    /**
     * ('status') ORDER BY orderByFieldName ASC; || If Status == ALL -> ('status1', 'status2', ...) ORDER BY orderByFieldName ASC;
     */
    public static String querySearchByStatus(String orderByFieldName, String status) {
        String orderByIdAsc = " ORDER BY " + orderByFieldName + " ASC ";
        StringJoiner statusForQuery = new StringJoiner(",", "(", ")");
        if (status.equalsIgnoreCase(SisStatus.ALL.toString())) {
            Arrays.stream(SisStatus.values())
                    .filter(s -> s != SisStatus.ALL)
                    .forEach(s -> statusForQuery.add("'" + s + "'"));
            return statusForQuery + orderByIdAsc;
        } else
            return statusForQuery.add("'" + status + "'") + orderByIdAsc;
    }
}
