package com.graduationproject.studentinformationsystem.teacher.repository.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisDatabaseException;
import com.graduationproject.studentinformationsystem.common.util.log.SisErrorLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisInfoLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisWarnLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.sql.SisSqlUtil;
import com.graduationproject.studentinformationsystem.teacher.model.entity.TeacherPersonalInfoEntity;
import com.graduationproject.studentinformationsystem.teacher.model.enums.TeacherStatus;
import com.graduationproject.studentinformationsystem.teacher.repository.TeacherPersonalInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

import static com.graduationproject.studentinformationsystem.teacher.model.mapping.TeacherPersonalInfoMapping.*;
import static com.graduationproject.studentinformationsystem.teacher.repository.impl.scripts.TeacherPersonalInfoSqlScripts.*;

@Repository
@RequiredArgsConstructor
public class TeacherPersonalInfoRepositoryImpl implements TeacherPersonalInfoRepository {

    private static final String TEACHER_PERSONAL_INFO = "Teacher Personal Info";
    private final SisInfoLogMessageUtil info = SisInfoLogMessageUtil.builder().apiName(TEACHER_PERSONAL_INFO).build();
    private final SisWarnLogMessageUtil warn = SisWarnLogMessageUtil.builder().apiName(TEACHER_PERSONAL_INFO).build();
    private final SisErrorLogMessageUtil error = SisErrorLogMessageUtil.builder().apiName(TEACHER_PERSONAL_INFO).build();

    private final Sql2o sql2o;

    public List<TeacherPersonalInfoEntity> getAllTeacherPersonalInfosByStatus(TeacherStatus status) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(GET_ALL_TEACHER_PERSONAL_INFOS_BY_STATUS
                .concat(SisSqlUtil.querySearchByStatus(TEACHER_ID.getColumnName(), status.toString())))) {

            List<TeacherPersonalInfoEntity> entities = query
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetch(TeacherPersonalInfoEntity.class);

            info.foundAllByStatus(status.toString());
            return entities;
        } catch (Exception exception) {
            error.errorWhenGettingAllByStatus(status.toString());
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public TeacherPersonalInfoEntity getTeacherPersonalInfoById(Long teacherId) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(GET_TEACHER_PERSONAL_INFO_BY_TEACHER_ID)) {

            TeacherPersonalInfoEntity entity = query
                    .addParameter(TEACHER_ID.getModelName(), teacherId.toString())
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetchFirst(TeacherPersonalInfoEntity.class);

            if (entity != null) {
                info.foundById(teacherId);
            } else {
                warn.notFoundById(teacherId);
            }
            return entity;
        } catch (Exception exception) {
            error.errorWhenGetting();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void saveTeacherPersonalInfo(TeacherPersonalInfoEntity entity) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(SAVE_TEACHER_PERSONAL_INFO)) {

            query.addParameter(TEACHER_ID.getModelName(), entity.getTeacherId())
                    .addParameter(TC_NO.getModelName(), entity.getTcNo())
                    .addParameter(NAME.getModelName(), entity.getName())
                    .addParameter(SURNAME.getModelName(), entity.getSurname())
                    .addParameter(EMAIL.getModelName(), entity.getEmail())
                    .addParameter(PHONE_NUMBER.getModelName(), entity.getPhoneNumber())
                    .addParameter(STATUS.getModelName(), entity.getStatus())
//                    .addParameter(PROFILE_PHOTO.getModelName(), entity.getProfilePhoto()) // TODO: Add Profile Photo Parameter
//                    .addParameter(PROFILE_PHOTO_URL.getModelName(), entity.getProfilePhotoUrl()) // TODO: Add Profile Photo URL Parameter
                    .addParameter(BIRTHDAY.getModelName(), entity.getBirthday())
                    .addParameter(ADDRESS.getModelName(), entity.getAddress())
                    .addParameter(CREATED_DATE.getModelName(), entity.getCreatedDate())
                    .addParameter(CREATED_USER_ID.getModelName(), entity.getCreatedUserId())
                    .executeUpdate();

            info.savedById(entity.getTeacherId());
        } catch (Exception exception) {
            error.errorWhenSaving();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updateTeacherPersonalInfo(TeacherPersonalInfoEntity entity) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(UPDATE_TEACHER_PERSONAL_INFO)) {
            query.addParameter(TC_NO.getModelName(), entity.getTcNo())
                    .addParameter(NAME.getModelName(), entity.getName())
                    .addParameter(SURNAME.getModelName(), entity.getSurname())
                    .addParameter(EMAIL.getModelName(), entity.getEmail())
                    .addParameter(PHONE_NUMBER.getModelName(), entity.getPhoneNumber())
//                    .addParameter(PROFILE_PHOTO.getModelName(), entity.getProfilePhoto()) // TODO: Add Profile Photo Parameter
//                    .addParameter(PROFILE_PHOTO_URL.getModelName(), entity.getProfilePhotoUrl()) // TODO: Add Profile Photo URL Parameter
                    .addParameter(BIRTHDAY.getModelName(), entity.getBirthday())
                    .addParameter(ADDRESS.getModelName(), entity.getAddress())
                    .addParameter(MODIFIED_DATE.getModelName(), entity.getModifiedDate())
                    .addParameter(MODIFIED_USER_ID.getModelName(), entity.getModifiedUserId())
                    .addParameter(TEACHER_ID.getModelName(), entity.getTeacherId())
                    .executeUpdate();

            info.updated();
        } catch (Exception exception) {
            error.errorWhenUpdating();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updateTeacherPersonalInfoStatus(TeacherPersonalInfoEntity entity) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(UPDATE_TEACHER_PERSONAL_INFO_STATUS)) {

            query.addParameter(STATUS.getModelName(), entity.getStatus())
                    .addParameter(MODIFIED_DATE.getModelName(), entity.getModifiedDate())
                    .addParameter(MODIFIED_USER_ID.getModelName(), entity.getModifiedUserId())
                    .addParameter(TEACHER_ID.getModelName(), entity.getTeacherId())
                    .executeUpdate();

            info.statusUpdated(entity.getStatus().toString());
        } catch (Exception exception) {
            error.errorWhenUpdatingStatus();
            throw new SisDatabaseException(exception);
        }
    }
}
