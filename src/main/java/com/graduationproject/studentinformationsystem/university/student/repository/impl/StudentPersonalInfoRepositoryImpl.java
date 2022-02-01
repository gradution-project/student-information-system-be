package com.graduationproject.studentinformationsystem.university.student.repository.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisDatabaseException;
import com.graduationproject.studentinformationsystem.common.util.log.SisErrorLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisInfoLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisWarnLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.sql.SisSqlUtil;
import com.graduationproject.studentinformationsystem.university.student.model.entity.StudentPersonalInfoEntity;
import com.graduationproject.studentinformationsystem.university.student.model.enums.StudentStatus;
import com.graduationproject.studentinformationsystem.university.student.repository.StudentPersonalInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

import static com.graduationproject.studentinformationsystem.university.student.model.mapping.StudentPersonalInfoMapping.*;
import static com.graduationproject.studentinformationsystem.university.student.repository.impl.scripts.StudentPersonalInfoSqlScripts.*;

@Repository
@RequiredArgsConstructor
public class StudentPersonalInfoRepositoryImpl implements StudentPersonalInfoRepository {

    private static final String STUDENT_PERSONAL_INFO = "Student Personal Info";
    private final SisInfoLogMessageUtil info = SisInfoLogMessageUtil.builder().apiName(STUDENT_PERSONAL_INFO).build();
    private final SisWarnLogMessageUtil warn = SisWarnLogMessageUtil.builder().apiName(STUDENT_PERSONAL_INFO).build();
    private final SisErrorLogMessageUtil error = SisErrorLogMessageUtil.builder().apiName(STUDENT_PERSONAL_INFO).build();

    private final Sql2o sql2o;

    public List<StudentPersonalInfoEntity> getAllStudentPersonalInfosByStatus(final StudentStatus status) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_ALL_STUDENT_PERSONAL_INFOS_BY_STATUS
                .concat(SisSqlUtil.querySearchByStatus(STUDENT_ID.getColumnName(), status.toString())))) {

            final List<StudentPersonalInfoEntity> personalInfoEntities = query
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetch(StudentPersonalInfoEntity.class);

            if (!personalInfoEntities.isEmpty()) {
                info.foundAllByStatus(status.toString());
            } else {
                warn.notFoundAllIdsByStatus(status.getName());
            }
            return personalInfoEntities;
        } catch (Exception exception) {
            error.errorWhenGettingAllByStatus(status.toString());
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public StudentPersonalInfoEntity getStudentPersonalInfoById(final Long studentId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_STUDENT_PERSONAL_INFO_BY_STUDENT_ID)) {

            final StudentPersonalInfoEntity personalInfoEntity = query
                    .addParameter(STUDENT_ID.getModelName(), studentId.toString())
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetchFirst(StudentPersonalInfoEntity.class);

            if (personalInfoEntity != null) {
                info.foundById(studentId);
            } else {
                warn.notFoundById(studentId);
            }
            return personalInfoEntity;
        } catch (Exception exception) {
            error.errorWhenGetting();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void saveStudentPersonalInfo(final StudentPersonalInfoEntity personalInfoEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(SAVE_STUDENT_PERSONAL_INFO)) {

            query.addParameter(STUDENT_ID.getModelName(), personalInfoEntity.getStudentId())
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

            info.savedById(personalInfoEntity.getStudentId());
        } catch (Exception exception) {
            error.errorWhenSaving();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updateStudentPersonalInfo(StudentPersonalInfoEntity personalInfoEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(UPDATE_STUDENT_PERSONAL_INFO)) {

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
                    .addParameter(STUDENT_ID.getModelName(), personalInfoEntity.getStudentId())
                    .executeUpdate();

            info.updated();
        } catch (Exception exception) {
            error.errorWhenUpdating();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updateStudentPersonalInfoStatus(final StudentPersonalInfoEntity personalInfoEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(UPDATE_STUDENT_PERSONAL_INFO_STATUS)) {

            query.addParameter(STATUS.getModelName(), personalInfoEntity.getStatus())
                    .addParameter(MODIFIED_DATE.getModelName(), personalInfoEntity.getModifiedDate())
                    .addParameter(MODIFIED_USER_ID.getModelName(), personalInfoEntity.getModifiedUserId())
                    .addParameter(STUDENT_ID.getModelName(), personalInfoEntity.getStudentId())
                    .executeUpdate();

            info.statusUpdated(personalInfoEntity.getStatus().toString());
        } catch (Exception exception) {
            error.errorWhenUpdatingStatus();
            throw new SisDatabaseException(exception);
        }
    }
}
