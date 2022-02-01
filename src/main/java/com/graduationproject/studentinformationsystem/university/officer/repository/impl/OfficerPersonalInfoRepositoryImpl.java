package com.graduationproject.studentinformationsystem.university.officer.repository.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisDatabaseException;
import com.graduationproject.studentinformationsystem.common.util.log.SisErrorLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisInfoLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisWarnLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.sql.SisSqlUtil;
import com.graduationproject.studentinformationsystem.university.officer.model.entity.OfficerPersonalInfoEntity;
import com.graduationproject.studentinformationsystem.university.officer.model.enums.OfficerStatus;
import com.graduationproject.studentinformationsystem.university.officer.repository.OfficerPersonalInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

import static com.graduationproject.studentinformationsystem.university.officer.model.mapping.OfficerPersonalInfoMapping.*;
import static com.graduationproject.studentinformationsystem.university.officer.repository.impl.scripts.OfficerPersonalInfoSqlScripts.*;

@Repository
@RequiredArgsConstructor
public class OfficerPersonalInfoRepositoryImpl implements OfficerPersonalInfoRepository {

    private static final String OFFICER_PERSONAL_INFO = "Officer Personal Info";
    private final SisInfoLogMessageUtil info = SisInfoLogMessageUtil.builder().apiName(OFFICER_PERSONAL_INFO).build();
    private final SisWarnLogMessageUtil warn = SisWarnLogMessageUtil.builder().apiName(OFFICER_PERSONAL_INFO).build();
    private final SisErrorLogMessageUtil error = SisErrorLogMessageUtil.builder().apiName(OFFICER_PERSONAL_INFO).build();

    private final Sql2o sql2o;

    public List<OfficerPersonalInfoEntity> getAllOfficerPersonalInfosByStatus(final OfficerStatus status) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_ALL_OFFICER_PERSONAL_INFOS_BY_STATUS
                .concat(SisSqlUtil.querySearchByStatus(OFFICER_ID.getColumnName(), status.toString())))) {

            final List<OfficerPersonalInfoEntity> personalInfoEntities = query
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetch(OfficerPersonalInfoEntity.class);

            info.foundAllByStatus(status.toString());
            return personalInfoEntities;
        } catch (Exception exception) {
            error.errorWhenGettingAllByStatus(status.toString());
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public OfficerPersonalInfoEntity getOfficerPersonalInfoById(final Long officerId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_OFFICER_PERSONAL_INFO_BY_OFFICER_ID)) {

            final OfficerPersonalInfoEntity personalInfoEntity = query
                    .addParameter(OFFICER_ID.getModelName(), officerId.toString())
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetchFirst(OfficerPersonalInfoEntity.class);

            if (personalInfoEntity != null) {
                info.foundById(officerId);
            } else {
                warn.notFoundById(officerId);
            }
            return personalInfoEntity;
        } catch (Exception exception) {
            error.errorWhenGetting();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void saveOfficerPersonalInfo(final OfficerPersonalInfoEntity personalInfoEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(SAVE_OFFICER_PERSONAL_INFO)) {

            query.addParameter(OFFICER_ID.getModelName(), personalInfoEntity.getOfficerId())
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

            info.savedById(personalInfoEntity.getOfficerId());
        } catch (Exception exception) {
            error.errorWhenSaving();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updateOfficerPersonalInfo(final OfficerPersonalInfoEntity personalInfoEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(UPDATE_OFFICER_PERSONAL_INFO)) {

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
                    .addParameter(OFFICER_ID.getModelName(), personalInfoEntity.getOfficerId())
                    .executeUpdate();

            info.updated();
        } catch (Exception exception) {
            error.errorWhenUpdating();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updateOfficerPersonalInfoStatus(final OfficerPersonalInfoEntity personalInfoEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(UPDATE_OFFICER_PERSONAL_INFO_STATUS)) {

            query.addParameter(STATUS.getModelName(), personalInfoEntity.getStatus())
                    .addParameter(MODIFIED_DATE.getModelName(), personalInfoEntity.getModifiedDate())
                    .addParameter(MODIFIED_USER_ID.getModelName(), personalInfoEntity.getModifiedUserId())
                    .addParameter(OFFICER_ID.getModelName(), personalInfoEntity.getOfficerId())
                    .executeUpdate();

            info.statusUpdated(personalInfoEntity.getStatus().toString());
        } catch (Exception exception) {
            error.errorWhenUpdatingStatus();
            throw new SisDatabaseException(exception);
        }
    }
}
