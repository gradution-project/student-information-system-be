package com.graduationproject.studentinformationsystem.university.teacher.repository.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisDatabaseException;
import com.graduationproject.studentinformationsystem.common.util.log.SisErrorLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisInfoLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisWarnLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.sql.SisSqlUtil;
import com.graduationproject.studentinformationsystem.university.teacher.model.entity.TeacherPersonalInfoEntity;
import com.graduationproject.studentinformationsystem.university.teacher.model.enums.TeacherStatus;
import com.graduationproject.studentinformationsystem.university.teacher.repository.TeacherPersonalInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

import static com.graduationproject.studentinformationsystem.university.teacher.model.mapping.TeacherPersonalInfoMapping.*;
import static com.graduationproject.studentinformationsystem.university.teacher.repository.impl.scripts.TeacherPersonalInfoSqlScripts.*;

@Repository
@RequiredArgsConstructor
public class TeacherPersonalInfoRepositoryImpl implements TeacherPersonalInfoRepository {

    private static final String TEACHER_PERSONAL_INFO = "Teacher Personal Info";
    private final SisInfoLogMessageUtil info = SisInfoLogMessageUtil.builder().apiName(TEACHER_PERSONAL_INFO).build();
    private final SisWarnLogMessageUtil warn = SisWarnLogMessageUtil.builder().apiName(TEACHER_PERSONAL_INFO).build();
    private final SisErrorLogMessageUtil error = SisErrorLogMessageUtil.builder().apiName(TEACHER_PERSONAL_INFO).build();

    private final Sql2o sql2o;

    public List<TeacherPersonalInfoEntity> getAllTeacherPersonalInfosByStatus(final TeacherStatus status) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_ALL_TEACHER_PERSONAL_INFOS_BY_STATUS
                .concat(SisSqlUtil.querySearchByStatus(TEACHER_ID.getColumnName(), status.toString())))) {

            final List<TeacherPersonalInfoEntity> personalInfoEntities = query
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetch(TeacherPersonalInfoEntity.class);

            info.foundAllByStatus(status.toString());
            return personalInfoEntities;
        } catch (Exception exception) {
            error.errorWhenGettingAllByStatus(status.toString());
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public TeacherPersonalInfoEntity getTeacherPersonalInfoById(final Long teacherId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_TEACHER_PERSONAL_INFO_BY_TEACHER_ID)) {

            final TeacherPersonalInfoEntity personalInfoEntity = query
                    .addParameter(TEACHER_ID.getModelName(), teacherId.toString())
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetchFirst(TeacherPersonalInfoEntity.class);

            if (personalInfoEntity != null) {
                info.foundById(teacherId);
            } else {
                warn.notFoundById(teacherId);
            }
            return personalInfoEntity;
        } catch (Exception exception) {
            error.errorWhenGetting();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void saveTeacherPersonalInfo(final TeacherPersonalInfoEntity personalInfoEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(SAVE_TEACHER_PERSONAL_INFO)) {

            query.addParameter(TEACHER_ID.getModelName(), personalInfoEntity.getTeacherId())
                    .addParameter(TC_NO.getModelName(), personalInfoEntity.getTcNo())
                    .addParameter(NAME.getModelName(), personalInfoEntity.getName())
                    .addParameter(SURNAME.getModelName(), personalInfoEntity.getSurname())
                    .addParameter(EMAIL.getModelName(), personalInfoEntity.getEmail())
                    .addParameter(PHONE_NUMBER.getModelName(), personalInfoEntity.getPhoneNumber())
                    .addParameter(STATUS.getModelName(), personalInfoEntity.getStatus())
//                    .addParameter(PROFILE_PHOTO.getModelName(), personalInfoEntity.getProfilePhoto()) // TODO: Add Profile Photo Parameter
//                    .addParameter(PROFILE_PHOTO_URL.getModelName(), personalInfoEntity.getProfilePhotoUrl()) // TODO: Add Profile Photo URL Parameter
                    .addParameter(BIRTHDAY.getModelName(), personalInfoEntity.getBirthday())
                    .addParameter(ADDRESS.getModelName(), personalInfoEntity.getAddress())
                    .addParameter(CREATED_DATE.getModelName(), personalInfoEntity.getCreatedDate())
                    .addParameter(CREATED_USER_ID.getModelName(), personalInfoEntity.getCreatedUserId())
                    .executeUpdate();

            info.savedById(personalInfoEntity.getTeacherId());
        } catch (Exception exception) {
            error.errorWhenSaving();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updateTeacherPersonalInfo(final TeacherPersonalInfoEntity personalInfoEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(UPDATE_TEACHER_PERSONAL_INFO)) {

            query.addParameter(TC_NO.getModelName(), personalInfoEntity.getTcNo())
                    .addParameter(NAME.getModelName(), personalInfoEntity.getName())
                    .addParameter(SURNAME.getModelName(), personalInfoEntity.getSurname())
                    .addParameter(EMAIL.getModelName(), personalInfoEntity.getEmail())
                    .addParameter(PHONE_NUMBER.getModelName(), personalInfoEntity.getPhoneNumber())
//                    .addParameter(PROFILE_PHOTO.getModelName(), personalInfoEntity.getProfilePhoto()) // TODO: Add Profile Photo Parameter
//                    .addParameter(PROFILE_PHOTO_URL.getModelName(), personalInfoEntity.getProfilePhotoUrl()) // TODO: Add Profile Photo URL Parameter
                    .addParameter(BIRTHDAY.getModelName(), personalInfoEntity.getBirthday())
                    .addParameter(ADDRESS.getModelName(), personalInfoEntity.getAddress())
                    .addParameter(MODIFIED_DATE.getModelName(), personalInfoEntity.getModifiedDate())
                    .addParameter(MODIFIED_USER_ID.getModelName(), personalInfoEntity.getModifiedUserId())
                    .addParameter(TEACHER_ID.getModelName(), personalInfoEntity.getTeacherId())
                    .executeUpdate();

            info.updated();
        } catch (Exception exception) {
            error.errorWhenUpdating();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updateTeacherPersonalInfoStatus(final TeacherPersonalInfoEntity personalInfoEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(UPDATE_TEACHER_PERSONAL_INFO_STATUS)) {

            query.addParameter(STATUS.getModelName(), personalInfoEntity.getStatus())
                    .addParameter(MODIFIED_DATE.getModelName(), personalInfoEntity.getModifiedDate())
                    .addParameter(MODIFIED_USER_ID.getModelName(), personalInfoEntity.getModifiedUserId())
                    .addParameter(TEACHER_ID.getModelName(), personalInfoEntity.getTeacherId())
                    .executeUpdate();

            info.statusUpdated(personalInfoEntity.getStatus().toString());
        } catch (Exception exception) {
            error.errorWhenUpdatingStatus();
            throw new SisDatabaseException(exception);
        }
    }
}
