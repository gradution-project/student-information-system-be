package com.graduationproject.studentinformationsystem.university.parameter.repository.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisDatabaseException;
import com.graduationproject.studentinformationsystem.common.util.log.SisErrorLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisWarnLogMessageUtil;
import com.graduationproject.studentinformationsystem.university.parameter.repository.ParameterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import static com.graduationproject.studentinformationsystem.university.parameter.model.mapping.ParameterMapping.COLUMN_MAPPINGS;
import static com.graduationproject.studentinformationsystem.university.parameter.model.mapping.ParameterMapping.NAME;
import static com.graduationproject.studentinformationsystem.university.parameter.repository.impl.scripts.ParameterSqlScripts.*;

@Repository
@RequiredArgsConstructor
public class ParameterRepositoryImpl implements ParameterRepository {

    private static final String PARAMETER = "Parameter";
    private final SisWarnLogMessageUtil warn = SisWarnLogMessageUtil.builder().apiName(PARAMETER).build();
    private final SisErrorLogMessageUtil error = SisErrorLogMessageUtil.builder().apiName(PARAMETER).build();

    private final Sql2o sql2o;

    @Override
    public String getStudentParameterByName(final String name) {
        return getValueByNameWithScript(name, GET_STUDENT_PARAMETER_BY_NAME);
    }

    @Override
    public final String getTeacherParameterByName(final String name) {
        return getValueByNameWithScript(name, GET_TEACHER_PARAMETER_BY_NAME);
    }

    @Override
    public String getOfficerParameterByName(final String name) {
        return getValueByNameWithScript(name, GET_OFFICER_PARAMETER_BY_NAME);
    }

    private String getValueByNameWithScript(String name, String getOfficerParameterByName) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(getOfficerParameterByName)) {

            final String value = query.addParameter(NAME.getModelName(), name)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeScalar(String.class);

            if (value.isEmpty()) {
                warn.notFoundByName(name);
            }
            return value;
        } catch (Exception exception) {
            error.errorWhenGettingByName(name);
            throw new SisDatabaseException(exception);
        }
    }
}
