package com.graduationproject.studentinformationsystem.login.student.repository.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisDatabaseException;
import com.graduationproject.studentinformationsystem.login.common.service.PasswordService;
import com.graduationproject.studentinformationsystem.login.student.model.entity.StudentLoginInfoEntity;
import com.graduationproject.studentinformationsystem.login.student.repository.StudentLoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import static com.graduationproject.studentinformationsystem.login.student.model.mapping.StudentLoginMapping.*;
import static com.graduationproject.studentinformationsystem.login.student.repository.impl.scripts.StudentLoginSqlScripts.*;

@Repository
@RequiredArgsConstructor
public class StudentLoginRepositoryImpl implements StudentLoginRepository {

    private final PasswordService passwordService;

    private final Sql2o sql2o;

    @Override
    public Integer getFailCounter(Long studentId) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(GET_STUDENT_LOGIN_FAIL_COUNTER)) {

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
    public String getPassword(Long studentId) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(GET_STUDENT_PASSWORD)) {

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
    public void saveFirstPassword(Long studentId, String password) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(SAVE_STUDENT_FIRST_PASSWORD)) {

            query.addParameter(STUDENT_ID.getModelName(), studentId)
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
    public void updatePassword(Long studentId, String password) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(UPDATE_STUDENT_PASSWORD)) {

            query.addParameter(STUDENT_ID.getModelName(), studentId)
                    .addParameter(PASSWORD.getModelName(), passwordService.encodePassword(password))
                    .executeUpdate();

//            TODO: Specific Info Log Must be Added
        } catch (Exception exception) {
//            TODO: Specific Error Log Must be Added
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updateLoginInfo(StudentLoginInfoEntity loginInfoEntity) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(UPDATE_STUDENT_LOGIN_INFO)) {

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
    public void updateFailCounter(Long studentId) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(UPDATE_STUDENT_FAIL_COUNTER)) {

            query.addParameter(STUDENT_ID.getModelName(), studentId)
                    .addParameter(FAIL_COUNTER.getModelName(), getFailCounter(studentId) + 1)
                    .executeUpdate();

//            TODO: Specific Info Log Must be Added
        } catch (Exception exception) {
//            TODO: Specific Error Log Must be Added
            throw new SisDatabaseException(exception);
        }
    }

//    TODO: Change or Update Password
//    @Override
//    public void updatePassword(StudentLoginEntity loginInfoEntity) {
//        try (Connection con = sql2o.open(); Query query = con.createQuery(UPDATE_STUDENT_PASSWORD)) {
//
//            query.addParameter(STUDENT_ID.getModelName(), loginInfoEntity.getStudentId())
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
