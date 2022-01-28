package com.graduationproject.studentinformationsystem.login.teacher.repository.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisDatabaseException;
import com.graduationproject.studentinformationsystem.login.common.service.PasswordService;
import com.graduationproject.studentinformationsystem.login.teacher.model.entity.TeacherLoginInfoEntity;
import com.graduationproject.studentinformationsystem.login.teacher.repository.TeacherLoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import static com.graduationproject.studentinformationsystem.login.teacher.model.mapping.TeacherLoginMapping.*;
import static com.graduationproject.studentinformationsystem.login.teacher.repository.impl.scripts.TeacherLoginSqlScripts.*;

@Repository
@RequiredArgsConstructor
public class TeacherLoginRepositoryImpl implements TeacherLoginRepository {

    private final PasswordService passwordService;

    private final Sql2o sql2o;

    @Override
    public Integer getFailCounter(Long teacherId) {
        try (Connection connection = sql2o.open(); Query query = connection.createQuery(GET_TEACHER_LOGIN_FAIL_COUNTER)) {

            return query.addParameter(TEACHER_ID.getModelName(), teacherId)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeScalar(Integer.class);

//            TODO: Specific Info Log Must be Added
        } catch (Exception exception) {
//            TODO: Specific Log Error Must be Added
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public String getPassword(Long teacherId) {
        try (Connection connection = sql2o.open(); Query query = connection.createQuery(GET_TEACHER_PASSWORD)) {

            return query.addParameter(TEACHER_ID.getModelName(), teacherId)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeScalar(String.class);

//            TODO: Specific Info Log Must be Added
        } catch (Exception exception) {
//            TODO: Specific Error Log Must be Added
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void saveFirstPassword(Long teacherId, String password) {
        try (Connection connection = sql2o.open(); Query query = connection.createQuery(SAVE_TEACHER_FIRST_PASSWORD)) {

            query.addParameter(TEACHER_ID.getModelName(), teacherId)
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
    public void updatePassword(Long teacherId, String password) {
        try (Connection connection = sql2o.open(); Query query = connection.createQuery(UPDATE_TEACHER_PASSWORD)) {

            query.addParameter(TEACHER_ID.getModelName(), teacherId)
                    .addParameter(PASSWORD.getModelName(), passwordService.encodePassword(password))
                    .executeUpdate();

//            TODO: Specific Info Log Must be Added
        } catch (Exception exception) {
//            TODO: Specific Error Log Must be Added
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updateLoginInfo(TeacherLoginInfoEntity loginInfoEntity) {
        try (Connection connection = sql2o.open(); Query query = connection.createQuery(UPDATE_TEACHER_LOGIN_INFO)) {

            query.addParameter(TEACHER_ID.getModelName(), loginInfoEntity.getTeacherId())
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
    public void updateFailCounter(Long teacherId) {
        try (Connection connection = sql2o.open(); Query query = connection.createQuery(UPDATE_TEACHER_FAIL_COUNTER)) {

            query.addParameter(TEACHER_ID.getModelName(), teacherId)
                    .addParameter(FAIL_COUNTER.getModelName(), getFailCounter(teacherId) + 1)
                    .executeUpdate();

//            TODO: Specific Info Log Must be Added
        } catch (Exception exception) {
//            TODO: Specific Error Log Must be Added
            throw new SisDatabaseException(exception);
        }
    }

//    TODO: Change or Update Password
//    @Override
//    public void updatePassword(TeacherLoginInfoEntity loginInfoEntity) {
//        try (Connection connection = sql2o.open(); Query query = connection.createQuery(UPDATE_TEACHER_PASSWORD)) {
//
//            query.addParameter(TEACHER_ID.getModelName(), loginInfoEntity.getTeacherId())
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
