package com.graduationproject.studentinformationsystem.login.student.common.repository.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisDatabaseException;
import com.graduationproject.studentinformationsystem.login.student.common.model.entity.StudentLoginInfoEntity;
import com.graduationproject.studentinformationsystem.login.student.common.repository.StudentLoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import static com.graduationproject.studentinformationsystem.login.student.common.model.mapping.StudentLoginMapping.*;
import static com.graduationproject.studentinformationsystem.login.student.common.repository.impl.scripts.StudentLoginSqlScripts.*;

@Repository
@RequiredArgsConstructor
public class StudentLoginRepositoryImpl implements StudentLoginRepository {

    private final Sql2o sql2o;

    @Override
    public Integer getFailCounter(final Long studentId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_STUDENT_LOGIN_FAIL_COUNTER)) {

            return query.addParameter(STUDENT_ID.getModelName(), studentId)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeScalar(Integer.class);

//            TODO: Specific Info Log Must be Added
        } catch (Exception exception) {
//            TODO: Specific Log Error Must be Added
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public String getPassword(final Long studentId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_STUDENT_PASSWORD)) {

            return query.addParameter(STUDENT_ID.getModelName(), studentId)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeScalar(String.class);

//            TODO: Specific Info Log Must be Added
        } catch (Exception exception) {
//            TODO: Specific Error Log Must be Added
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void savePassword(final Long studentId, final String encodedPassword) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(SAVE_STUDENT_PASSWORD)) {

            query.addParameter(STUDENT_ID.getModelName(), studentId)
                    .addParameter(PASSWORD.getModelName(), encodedPassword)
                    .addParameter(FAIL_COUNTER.getModelName(), 0)
                    .executeUpdate();

//            TODO: Specific Info Log Must be Added
        } catch (Exception exception) {
//            TODO: Specific Error Log Must be Added
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updatePassword(final Long studentId, final String encodedPassword) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(UPDATE_STUDENT_PASSWORD)) {

            query.addParameter(STUDENT_ID.getModelName(), studentId)
                    .addParameter(PASSWORD.getModelName(), encodedPassword)
                    .addParameter(FAIL_COUNTER.getModelName(), 0)
                    .executeUpdate();

//            TODO: Specific Info Log Must be Added
        } catch (Exception exception) {
//            TODO: Specific Error Log Must be Added
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updateLoginInfo(final StudentLoginInfoEntity loginInfoEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(UPDATE_STUDENT_LOGIN_INFO)) {

            query.addParameter(STUDENT_ID.getModelName(), loginInfoEntity.getStudentId())
                    .addParameter(FAIL_COUNTER.getModelName(), loginInfoEntity.getFailCounter())
                    .addParameter(LAST_LOGIN_DATE.getModelName(), loginInfoEntity.getLastLoginDate())
                    .executeUpdate();

//            TODO: Specific Info Log Must be Added
        } catch (Exception exception) {
//            TODO: Specific Error Log Must be Added
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updateFailCounter(final Long studentId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(UPDATE_STUDENT_FAIL_COUNTER)) {

            query.addParameter(STUDENT_ID.getModelName(), studentId)
                    .addParameter(FAIL_COUNTER.getModelName(), getFailCounter(studentId) + 1)
                    .executeUpdate();

//            TODO: Specific Info Log Must be Added
        } catch (Exception exception) {
//            TODO: Specific Error Log Must be Added
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isPasswordExist(Long studentId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_STUDENT_PASSWORD_EXIST)) {

            return query.addParameter(STUDENT_ID.getModelName(), studentId)
                    .executeScalar(Boolean.class);

//            TODO: Specific Info Log Must be Added
        } catch (Exception exception) {
//            TODO: Specific Error Log Must be Added
            throw new SisDatabaseException(exception);
        }
    }
}
