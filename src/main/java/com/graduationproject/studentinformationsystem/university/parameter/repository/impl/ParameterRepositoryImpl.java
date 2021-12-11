package com.graduationproject.studentinformationsystem.university.parameter.repository.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisDatabaseException;
import com.graduationproject.studentinformationsystem.common.util.log.SisErrorLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisInfoLogMessageUtil;
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
    private final SisInfoLogMessageUtil info = SisInfoLogMessageUtil.builder().apiName(PARAMETER).build();
    private final SisWarnLogMessageUtil warn = SisWarnLogMessageUtil.builder().apiName(PARAMETER).build();
    private final SisErrorLogMessageUtil error = SisErrorLogMessageUtil.builder().apiName(PARAMETER).build();

    private final Sql2o sql2o;

    @Override
    public String getStudentParameterByName(String name) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(GET_STUDENT_PARAMETER_BY_NAME)) {

            String value = query
                    .addParameter(NAME.getModelName(), name)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeScalar(String.class);

            if ("".equals(value)) {
                warn.notFoundByName(name);
            }
            return value;
        } catch (Exception exception) {
            error.errorWhenGettingByName(name);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public String getTeacherParameterByName(String name) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(GET_TEACHER_PARAMETER_BY_NAME)) {

            String value = query
                    .addParameter(NAME.getModelName(), name)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeScalar(String.class);

            if ("".equals(value)) {
                warn.notFoundByName(name);
            }
            return value;
        } catch (Exception exception) {
            error.errorWhenGettingByName(name);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public String getOfficerParameterByName(String name) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(GET_OFFICER_PARAMETER_BY_NAME)) {

            String value = query
                    .addParameter(NAME.getModelName(), name)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeScalar(String.class);

            if ("".equals(value)) {
                warn.notFoundByName(name);
            }
            return value;
        } catch (Exception exception) {
            error.errorWhenGettingByName(name);
            throw new SisDatabaseException(exception);
        }
    }
}
