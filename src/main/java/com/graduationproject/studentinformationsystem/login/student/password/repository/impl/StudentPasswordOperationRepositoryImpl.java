package com.graduationproject.studentinformationsystem.login.student.password.repository.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisDatabaseException;
import com.graduationproject.studentinformationsystem.login.student.password.model.entity.StudentPasswordOperationEntity;
import com.graduationproject.studentinformationsystem.login.student.password.repository.StudentPasswordOperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import static com.graduationproject.studentinformationsystem.login.student.password.model.mapping.StudentPasswordOperationMapping.*;
import static com.graduationproject.studentinformationsystem.login.student.password.repository.impl.scripts.StudentPasswordOperationSqlScripts.*;

@Repository
@RequiredArgsConstructor
public class StudentPasswordOperationRepositoryImpl implements StudentPasswordOperationRepository {

    private final Sql2o sql2o;

    @Override
    public StudentPasswordOperationEntity getPasswordOperation(final Long studentId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_STUDENT_PASSWORD_OPERATION)) {

            return query.addParameter(STUDENT_ID.getModelName(), studentId)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetchFirst(StudentPasswordOperationEntity.class);

//            TODO: Specific Info Log Must be Added
        } catch (Exception exception) {
//            TODO: Specific Log Error Must be Added
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void savePasswordOperation(final StudentPasswordOperationEntity passwordOperationEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(SAVE_STUDENT_PASSWORD_OPERATION)) {

            query.addParameter(OPERATION_ID.getModelName(), passwordOperationEntity.getOperationId())
                    .addParameter(STUDENT_ID.getModelName(), passwordOperationEntity.getStudentId())
                    .addParameter(EXPIRE_DATE.getModelName(), passwordOperationEntity.getExpireDate())
                    .addParameter(FE_URL.getModelName(), passwordOperationEntity.getFeUrl())
                    .executeUpdate();

//            TODO: Specific Info Log Must be Added
        } catch (Exception exception) {
//            TODO: Specific Error Log Must be Added
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updatePasswordOperation(final StudentPasswordOperationEntity passwordOperationEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(UPDATE_STUDENT_PASSWORD_OPERATION)) {

            query.addParameter(OPERATION_ID.getModelName(), passwordOperationEntity.getOperationId())
                    .addParameter(STUDENT_ID.getModelName(), passwordOperationEntity.getStudentId())
                    .addParameter(EXPIRE_DATE.getModelName(), passwordOperationEntity.getExpireDate())
                    .addParameter(FE_URL.getModelName(), passwordOperationEntity.getFeUrl())
                    .executeUpdate();

//            TODO: Specific Info Log Must be Added
        } catch (Exception exception) {
//            TODO: Specific Error Log Must be Added
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void deletePasswordOperation(final String operationId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(DELETE_STUDENT_PASSWORD_OPERATION)) {

            query.addParameter(OPERATION_ID.getModelName(), operationId)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeUpdate();

//            TODO: Specific Info Log Must be Added
        } catch (Exception exception) {
//            TODO: Specific Error Log Must be Added
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isPasswordChangeEnabled(final String operationId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_PASSWORD_CHANGE_ENABLED)) {

            return query.addParameter(OPERATION_ID.getModelName(), operationId)
                    .executeScalar(Boolean.class);

//            TODO: Specific Info Log Must be Added
        } catch (Exception exception) {
//            TODO: Specific Error Log Must be Added
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isOperationExist(final Long studentId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_OPERATION_EXIST)) {

            return query.addParameter(STUDENT_ID.getModelName(), studentId)
                    .executeScalar(Boolean.class);

//            TODO: Specific Info Log Must be Added
        } catch (Exception exception) {
//            TODO: Specific Error Log Must be Added
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public Long getStudentId(final String operationId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_STUDENT_ID)) {

            return query.addParameter(OPERATION_ID.getModelName(), operationId)
                    .executeScalar(Long.class);

//            TODO: Specific Info Log Must be Added
        } catch (Exception exception) {
//            TODO: Specific Error Log Must be Added
            throw new SisDatabaseException(exception);
        }
    }
}
