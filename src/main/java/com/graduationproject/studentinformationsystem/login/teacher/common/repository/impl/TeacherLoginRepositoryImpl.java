package com.graduationproject.studentinformationsystem.login.teacher.common.repository.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisDatabaseException;
import com.graduationproject.studentinformationsystem.login.common.service.PasswordService;
import com.graduationproject.studentinformationsystem.login.teacher.common.model.entity.TeacherLoginInfoEntity;
import com.graduationproject.studentinformationsystem.login.teacher.common.repository.TeacherLoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import static com.graduationproject.studentinformationsystem.login.teacher.common.model.mapping.TeacherLoginMapping.*;
import static com.graduationproject.studentinformationsystem.login.teacher.common.repository.impl.scripts.TeacherLoginSqlScripts.*;

@Repository
@RequiredArgsConstructor
public class TeacherLoginRepositoryImpl implements TeacherLoginRepository {

    private final PasswordService passwordService;

    private final Sql2o sql2o;

    @Override
    public Integer getFailCounter(final Long teacherId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_TEACHER_LOGIN_FAIL_COUNTER)) {

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
    public String getPassword(final Long teacherId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_TEACHER_PASSWORD)) {

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
    public void savePassword(final Long teacherId, final String encodedPassword) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(SAVE_TEACHER_PASSWORD)) {

            query.addParameter(TEACHER_ID.getModelName(), teacherId)
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
    public void updatePassword(final Long teacherId, final String encodedPassword) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(UPDATE_TEACHER_PASSWORD)) {

            query.addParameter(TEACHER_ID.getModelName(), teacherId)
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
    public void updateLoginInfo(final TeacherLoginInfoEntity loginInfoEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(UPDATE_TEACHER_LOGIN_INFO)) {

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
    public void updateFailCounter(final Long teacherId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(UPDATE_TEACHER_FAIL_COUNTER)) {

            query.addParameter(TEACHER_ID.getModelName(), teacherId)
                    .addParameter(FAIL_COUNTER.getModelName(), getFailCounter(teacherId) + 1)
                    .executeUpdate();

//            TODO: Specific Info Log Must be Added
        } catch (Exception exception) {
//            TODO: Specific Error Log Must be Added
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isPasswordExist(final Long teacherId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_TEACHER_PASSWORD_EXIST)) {

            return query.addParameter(TEACHER_ID.getModelName(), teacherId)
                    .executeScalar(Boolean.class);

//            TODO: Specific Info Log Must be Added
        } catch (Exception exception) {
//            TODO: Specific Error Log Must be Added
            throw new SisDatabaseException(exception);
        }
    }
}
