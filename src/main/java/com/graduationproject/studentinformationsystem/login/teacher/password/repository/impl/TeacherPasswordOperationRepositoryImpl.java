package com.graduationproject.studentinformationsystem.login.teacher.password.repository.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisDatabaseException;
import com.graduationproject.studentinformationsystem.login.teacher.password.model.entity.TeacherPasswordOperationEntity;
import com.graduationproject.studentinformationsystem.login.teacher.password.repository.TeacherPasswordOperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import static com.graduationproject.studentinformationsystem.login.teacher.password.model.mapping.TeacherPasswordOperationMapping.*;
import static com.graduationproject.studentinformationsystem.login.teacher.password.repository.impl.scripts.TeacherPasswordOperationSqlScripts.*;

@Repository
@RequiredArgsConstructor
public class TeacherPasswordOperationRepositoryImpl implements TeacherPasswordOperationRepository {

    private final Sql2o sql2o;

    @Override
    public TeacherPasswordOperationEntity getPasswordOperation(final Long teacherId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_TEACHER_PASSWORD_OPERATION)) {

            return query.addParameter(TEACHER_ID.getModelName(), teacherId)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetchFirst(TeacherPasswordOperationEntity.class);

//            TODO: Specific Info Log Must be Added
        } catch (Exception exception) {
//            TODO: Specific Log Error Must be Added
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void savePasswordOperation(final TeacherPasswordOperationEntity passwordOperationEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(SAVE_TEACHER_PASSWORD_OPERATION)) {

            query.addParameter(OPERATION_ID.getModelName(), passwordOperationEntity.getOperationId())
                    .addParameter(TEACHER_ID.getModelName(), passwordOperationEntity.getTeacherId())
                    .addParameter(EXPIRE_DATE.getModelName(), passwordOperationEntity.getExpireDate())
                    .executeUpdate();

//            TODO: Specific Info Log Must be Added
        } catch (Exception exception) {
//            TODO: Specific Error Log Must be Added
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updatePasswordOperation(final TeacherPasswordOperationEntity passwordOperationEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(UPDATE_TEACHER_PASSWORD_OPERATION)) {

            query.addParameter(OPERATION_ID.getModelName(), passwordOperationEntity.getOperationId())
                    .addParameter(TEACHER_ID.getModelName(), passwordOperationEntity.getTeacherId())
                    .addParameter(EXPIRE_DATE.getModelName(), passwordOperationEntity.getExpireDate())
                    .executeUpdate();

//            TODO: Specific Info Log Must be Added
        } catch (Exception exception) {
//            TODO: Specific Error Log Must be Added
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void deletePasswordOperation(final String operationId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(DELETE_TEACHER_PASSWORD_OPERATION)) {

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
    public boolean isOperationExist(final Long teacherId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_OPERATION_EXIST)) {

            return query.addParameter(TEACHER_ID.getModelName(), teacherId)
                    .executeScalar(Boolean.class);

//            TODO: Specific Info Log Must be Added
        } catch (Exception exception) {
//            TODO: Specific Error Log Must be Added
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public Long getTeacherId(final String operationId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_TEACHER_ID)) {

            return query.addParameter(OPERATION_ID.getModelName(), operationId)
                    .executeScalar(Long.class);

//            TODO: Specific Info Log Must be Added
        } catch (Exception exception) {
//            TODO: Specific Error Log Must be Added
            throw new SisDatabaseException(exception);
        }
    }
}
