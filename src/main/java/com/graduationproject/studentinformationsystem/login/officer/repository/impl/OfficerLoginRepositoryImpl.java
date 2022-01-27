package com.graduationproject.studentinformationsystem.login.officer.repository.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisDatabaseException;
import com.graduationproject.studentinformationsystem.login.common.service.PasswordService;
import com.graduationproject.studentinformationsystem.login.officer.model.entity.OfficerLoginInfoEntity;
import com.graduationproject.studentinformationsystem.login.officer.repository.OfficerLoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import static com.graduationproject.studentinformationsystem.login.officer.model.mapping.OfficerLoginMapping.*;
import static com.graduationproject.studentinformationsystem.login.officer.repository.impl.scripts.OfficerLoginSqlScripts.*;

@Repository
@RequiredArgsConstructor
public class OfficerLoginRepositoryImpl implements OfficerLoginRepository {

    private final PasswordService passwordService;

    private final Sql2o sql2o;

    @Override
    public Integer getFailCounter(final Long officerId) {
        try (final Connection con = sql2o.open(); final Query query = con.createQuery(GET_OFFICER_LOGIN_FAIL_COUNTER)) {

            return query.addParameter(OFFICER_ID.getModelName(), officerId)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeScalar(Integer.class);

//            TODO: Specific Info Log Must be Added
        } catch (Exception exception) {
//            TODO: Specific Log Error Must be Added
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public String getPassword(final Long officerId) {
        try (final Connection con = sql2o.open(); final Query query = con.createQuery(GET_OFFICER_PASSWORD)) {

            return query.addParameter(OFFICER_ID.getModelName(), officerId)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeScalar(String.class);

//            TODO: Specific Info Log Must be Added
        } catch (Exception exception) {
//            TODO: Specific Error Log Must be Added
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void saveFirstPassword(final Long officerId, final String password) {
        try (final Connection con = sql2o.open(); final Query query = con.createQuery(SAVE_OFFICER_FIRST_PASSWORD)) {

            query.addParameter(OFFICER_ID.getModelName(), officerId)
                    .addParameter(PASSWORD.getModelName(), passwordService.encodePassword(password))
                    .addParameter(FAIL_COUNTER.getModelName(), 0)
                    .executeUpdate();

//            TODO: Specific Info Log Must be Added
        } catch (Exception exception) {
//            TODO: Specific Error Log Must be Added
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updatePassword(final Long officerId, final String password) {
        try (final Connection con = sql2o.open(); final Query query = con.createQuery(UPDATE_OFFICER_PASSWORD)) {

            query.addParameter(OFFICER_ID.getModelName(), officerId)
                    .addParameter(PASSWORD.getModelName(), passwordService.encodePassword(password))
                    .executeUpdate();

//            TODO: Specific Info Log Must be Added
        } catch (Exception exception) {
//            TODO: Specific Error Log Must be Added
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updateLoginInfo(final OfficerLoginInfoEntity loginInfoEntity) {
        try (final Connection con = sql2o.open(); final Query query = con.createQuery(UPDATE_OFFICER_LOGIN_INFO)) {

            query.addParameter(OFFICER_ID.getModelName(), loginInfoEntity.getOfficerId())
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
    public void updateFailCounter(final Long officerId) {
        try (final Connection con = sql2o.open(); final Query query = con.createQuery(UPDATE_OFFICER_FAIL_COUNTER)) {

            query.addParameter(OFFICER_ID.getModelName(), officerId)
                    .addParameter(FAIL_COUNTER.getModelName(), getFailCounter(officerId) + 1)
                    .executeUpdate();

//            TODO: Specific Info Log Must be Added
        } catch (Exception exception) {
//            TODO: Specific Error Log Must be Added
            throw new SisDatabaseException(exception);
        }
    }

//    TODO: Change or Update Password
//    @Override
//    public void updatePassword(final OfficerLoginInfoEntity loginInfoEntity) {
//        try (final Connection con = sql2o.open(); final Query query = con.createQuery(UPDATE_OFFICER_PASSWORD)) {
//
//            query.addParameter(OFFICER_ID.getModelName(), loginInfoEntity.getOfficerId())
//                    .addParameter(PASSWORD.getModelName(), loginInfoEntity.getPassword())
//                    .addParameter(FAIL_COUNTER.getModelName(), loginInfoEntity.getFailCounter())
//                    .addParameter(LAST_LOGIN_DATE.getModelName(), loginInfoEntity.getLastLoginDate())
//                    .executeUpdate();
//
//            TODO: Specific Info Log Must be Added
//        } catch (Exception exception) {
//            TODO: Specific Error Log Must be Added
//            throw new SisDatabaseException(exception);
//        }
//    }
}
