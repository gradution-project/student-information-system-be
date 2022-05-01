package com.graduationproject.studentinformationsystem.login.officer.password.repository.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisDatabaseException;
import com.graduationproject.studentinformationsystem.login.officer.password.model.entity.OfficerPasswordOperationEntity;
import com.graduationproject.studentinformationsystem.login.officer.password.repository.OfficerPasswordOperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import static com.graduationproject.studentinformationsystem.login.officer.password.model.mapping.OfficerPasswordOperationMapping.*;
import static com.graduationproject.studentinformationsystem.login.officer.password.repository.impl.scripts.OfficerPasswordOperationSqlScripts.*;

@Repository
@RequiredArgsConstructor
public class OfficerPasswordOperationRepositoryImpl implements OfficerPasswordOperationRepository {

    private final Sql2o sql2o;

    @Override
    public OfficerPasswordOperationEntity getPasswordOperation(final Long officerId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_OFFICER_PASSWORD_OPERATION)) {

            return query.addParameter(OFFICER_ID.getModelName(), officerId)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetchFirst(OfficerPasswordOperationEntity.class);

//            TODO: Specific Info Log Must be Added
        } catch (Exception exception) {
//            TODO: Specific Log Error Must be Added
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void savePasswordOperation(final OfficerPasswordOperationEntity passwordOperationEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(SAVE_OFFICER_PASSWORD_OPERATION)) {

            query.addParameter(OPERATION_ID.getModelName(), passwordOperationEntity.getOperationId())
                    .addParameter(OFFICER_ID.getModelName(), passwordOperationEntity.getOfficerId())
                    .addParameter(EXPIRE_DATE.getModelName(), passwordOperationEntity.getExpireDate())
                    .executeUpdate();

//            TODO: Specific Info Log Must be Added
        } catch (Exception exception) {
//            TODO: Specific Error Log Must be Added
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updatePasswordOperation(final OfficerPasswordOperationEntity passwordOperationEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(UPDATE_OFFICER_PASSWORD_OPERATION)) {

            query.addParameter(OPERATION_ID.getModelName(), passwordOperationEntity.getOperationId())
                    .addParameter(OFFICER_ID.getModelName(), passwordOperationEntity.getOfficerId())
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
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(DELETE_OFFICER_PASSWORD_OPERATION)) {

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
    public boolean isOperationExist(final Long officerId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_OPERATION_EXIST)) {

            return query.addParameter(OFFICER_ID.getModelName(), officerId)
                    .executeScalar(Boolean.class);

//            TODO: Specific Info Log Must be Added
        } catch (Exception exception) {
//            TODO: Specific Error Log Must be Added
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public Long getOfficerId(final String operationId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_OFFICER_ID)) {

            return query.addParameter(OPERATION_ID.getModelName(), operationId)
                    .executeScalar(Long.class);

//            TODO: Specific Info Log Must be Added
        } catch (Exception exception) {
//            TODO: Specific Error Log Must be Added
            throw new SisDatabaseException(exception);
        }
    }
}
