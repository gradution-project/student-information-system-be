package com.graduationproject.studentinformationsystem.student.repository.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisDatabaseException;
import com.graduationproject.studentinformationsystem.common.util.log.SisErrorLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisInfoLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisWarnLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.sql.SisSqlUtil;
import com.graduationproject.studentinformationsystem.student.model.entity.StudentPersonalInfoEntity;
import com.graduationproject.studentinformationsystem.student.model.enums.StudentStatus;
import com.graduationproject.studentinformationsystem.student.repository.StudentPersonalInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

import static com.graduationproject.studentinformationsystem.student.model.mapping.StudentPersonalInfoMapping.*;
import static com.graduationproject.studentinformationsystem.student.repository.impl.scripts.StudentPersonalInfoSqlScripts.*;

@Repository
@RequiredArgsConstructor
public class StudentPersonalInfoRepositoryImpl implements StudentPersonalInfoRepository {

    private static final String STUDENT_PERSONAL_INFO = "Student Personal Info";
    private final SisInfoLogMessageUtil info = SisInfoLogMessageUtil.builder().apiName(STUDENT_PERSONAL_INFO).build();
    private final SisWarnLogMessageUtil warn = SisWarnLogMessageUtil.builder().apiName(STUDENT_PERSONAL_INFO).build();
    private final SisErrorLogMessageUtil error = SisErrorLogMessageUtil.builder().apiName(STUDENT_PERSONAL_INFO).build();

    private final Sql2o sql2o;

    public List<StudentPersonalInfoEntity> getAllStudentPersonalInfosByStatus(StudentStatus status) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(GET_ALL_STUDENT_PERSONAL_INFOS_BY_STATUS
                .concat(SisSqlUtil.querySearchByStatus(STUDENT_ID.getColumnName(), status.toString())))) {

            List<StudentPersonalInfoEntity> entities = query
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetch(StudentPersonalInfoEntity.class);

            info.foundAllByStatus(status.toString());
            return entities;
        } catch (Exception exception) {
            error.errorWhenGettingAllByStatus(status.toString());
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public StudentPersonalInfoEntity getStudentPersonalInfoById(Long studentId) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(GET_STUDENT_PERSONAL_INFO_BY_STUDENT_ID)) {

            StudentPersonalInfoEntity entity = query
                    .addParameter(STUDENT_ID.getModelName(), studentId.toString())
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetchFirst(StudentPersonalInfoEntity.class);

            if (entity != null) {
                info.foundById(studentId);
            } else {
                warn.notFoundById(studentId);
            }
            return entity;
        } catch (Exception exception) {
            error.errorWhenGetting();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void saveStudentPersonalInfo(StudentPersonalInfoEntity entity) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(SAVE_STUDENT_PERSONAL_INFO)) {

            query.addParameter(STUDENT_ID.getModelName(), entity.getStudentId())
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

            info.savedById(entity.getStudentId());
        } catch (Exception exception) {
            error.errorWhenSaving();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updateStudentPersonalInfo(StudentPersonalInfoEntity entity) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(UPDATE_STUDENT_PERSONAL_INFO)) {
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
                    .addParameter(STUDENT_ID.getModelName(), entity.getStudentId())
                    .executeUpdate();

            info.updated();
        } catch (Exception exception) {
            error.errorWhenUpdating();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updateStudentPersonalInfoStatus(StudentPersonalInfoEntity entity) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(UPDATE_STUDENT_PERSONAL_INFO_STATUS)) {

            query.addParameter(STATUS.getModelName(), entity.getStatus())
                    .addParameter(MODIFIED_DATE.getModelName(), entity.getModifiedDate())
                    .addParameter(MODIFIED_USER_ID.getModelName(), entity.getModifiedUserId())
                    .addParameter(STUDENT_ID.getModelName(), entity.getStudentId())
                    .executeUpdate();

            info.statusUpdated(entity.getStatus().toString());
        } catch (Exception exception) {
            error.errorWhenUpdatingStatus();
            throw new SisDatabaseException(exception);
        }
    }
}
