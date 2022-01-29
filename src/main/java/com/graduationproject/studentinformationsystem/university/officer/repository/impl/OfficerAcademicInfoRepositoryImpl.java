package com.graduationproject.studentinformationsystem.university.officer.repository.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisDatabaseException;
import com.graduationproject.studentinformationsystem.common.util.log.SisErrorLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisInfoLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisWarnLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.sql.SisSqlUtil;
import com.graduationproject.studentinformationsystem.university.officer.model.entity.OfficerAcademicInfoEntity;
import com.graduationproject.studentinformationsystem.university.officer.model.enums.OfficerStatus;
import com.graduationproject.studentinformationsystem.university.officer.repository.OfficerAcademicInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

import static com.graduationproject.studentinformationsystem.university.officer.model.mapping.OfficerAcademicInfoMapping.*;
import static com.graduationproject.studentinformationsystem.university.officer.repository.impl.scripts.OfficerAcademicInfoSqlScripts.*;

@Repository
@RequiredArgsConstructor
public class OfficerAcademicInfoRepositoryImpl implements OfficerAcademicInfoRepository {

    private static final String OFFICER_ACADEMIC_INFO = "Officer Academic Info";
    private final SisInfoLogMessageUtil info = SisInfoLogMessageUtil.builder().apiName(OFFICER_ACADEMIC_INFO).build();
    private final SisWarnLogMessageUtil warn = SisWarnLogMessageUtil.builder().apiName(OFFICER_ACADEMIC_INFO).build();
    private final SisErrorLogMessageUtil error = SisErrorLogMessageUtil.builder().apiName(OFFICER_ACADEMIC_INFO).build();

    private final Sql2o sql2o;

    public List<OfficerAcademicInfoEntity> getAllOfficerAcademicInfosByStatus(final OfficerStatus status) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_ALL_OFFICER_ACADEMIC_INFOS_BY_STATUS
                .concat(SisSqlUtil.querySearchByStatus(OFFICER_ID.getColumnName(), status.toString())))) {

            final List<OfficerAcademicInfoEntity> academicInfoEntities = query
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetch(OfficerAcademicInfoEntity.class);

            info.foundAllByStatus(status.toString());
            return academicInfoEntities;
        } catch (Exception exception) {
            error.errorWhenGettingAllByStatus(status.toString());
            throw new SisDatabaseException(exception);
        }
    }

    public OfficerAcademicInfoEntity getOfficerAcademicInfoById(final Long officerId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_OFFICER_ACADEMIC_INFO_BY_OFFICER_ID)) {

            final OfficerAcademicInfoEntity academicInfoEntity = query
                    .addParameter(OFFICER_ID.getModelName(), officerId.toString())
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetchFirst(OfficerAcademicInfoEntity.class);

            if (academicInfoEntity != null) {
                info.foundById(officerId);
            } else {
                warn.notFoundById(officerId);
            }
            return academicInfoEntity;
        } catch (Exception exception) {
            error.errorWhenGettingById(officerId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void saveOfficerAcademicInfo(final OfficerAcademicInfoEntity academicInfoEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(SAVE_OFFICER_ACADEMIC_INFO)) {

            query.addParameter(OFFICER_ID.getModelName(), academicInfoEntity.getOfficerId())
                    .addParameter(FACULTY_ID.getModelName(), academicInfoEntity.getFacultyId())
                    .addParameter(EMAIL.getModelName(), academicInfoEntity.getEmail())
                    .addParameter(STATUS.getModelName(), academicInfoEntity.getStatus())
                    .addParameter(PHONE_NUMBER.getModelName(), academicInfoEntity.getPhoneNumber())
                    .addParameter(REGISTRATION_DATE.getModelName(), academicInfoEntity.getRegistrationDate())
                    .addParameter(CREATED_DATE.getModelName(), academicInfoEntity.getCreatedDate())
                    .addParameter(CREATED_USER_ID.getModelName(), academicInfoEntity.getCreatedUserId())
                    .executeUpdate();

            info.savedById(academicInfoEntity.getOfficerId());
        } catch (Exception exception) {
            error.errorWhenSaving();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updateOfficerAcademicInfo(final OfficerAcademicInfoEntity academicInfoEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(UPDATE_OFFICER_ACADEMIC_INFO)) {

            query.addParameter(FACULTY_ID.getModelName(), academicInfoEntity.getFacultyId())
                    .addParameter(PHONE_NUMBER.getModelName(), academicInfoEntity.getPhoneNumber())
                    .addParameter(MODIFIED_DATE.getModelName(), academicInfoEntity.getModifiedDate())
                    .addParameter(MODIFIED_USER_ID.getModelName(), academicInfoEntity.getModifiedUserId())
                    .addParameter(OFFICER_ID.getModelName(), academicInfoEntity.getOfficerId())
                    .executeUpdate();

            info.updated();
        } catch (Exception exception) {
            error.errorWhenUpdating();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updateOfficerAcademicInfoStatus(final OfficerAcademicInfoEntity academicInfoEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(UPDATE_OFFICER_ACADEMIC_INFO_STATUS)) {

            query.addParameter(STATUS.getModelName(), academicInfoEntity.getStatus())
                    .addParameter(MODIFIED_DATE.getModelName(), academicInfoEntity.getModifiedDate())
                    .addParameter(MODIFIED_USER_ID.getModelName(), academicInfoEntity.getModifiedUserId())
                    .addParameter(OFFICER_ID.getModelName(), academicInfoEntity.getOfficerId())
                    .executeUpdate();

            info.statusUpdated(academicInfoEntity.getStatus().toString());
        } catch (Exception exception) {
            error.errorWhenUpdatingStatus();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public List<Long> getAllOfficerIdsByFacultyId(final Long departmentId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_ALL_OFFICER_IDS_BY_FACULTY_ID)) {

            final List<Long> officerIds = query
                    .addParameter(FACULTY_ID.getModelName(), departmentId.toString())
                    .executeScalarList(Long.class);


            if (!officerIds.isEmpty()) {
                info.foundAllIdsByFacultyId(departmentId);
            } else {
                warn.notFoundAllIdsByFacultyId(departmentId);
            }
            return officerIds;
        } catch (Exception exception) {
            error.errorWhenGettingAllIdsByFacultyId(departmentId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isOfficerExist(final Long officerId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_OFFICER_EXIST_BY_ID)) {

            final boolean isOfficerExist = query.addParameter(OFFICER_ID.getModelName(), officerId)
                    .executeAndFetchFirst(Boolean.class);

            if (isOfficerExist) {
                info.foundById(officerId);
                return true;
            } else {
                warn.notFoundById(officerId);
                return false;
            }
        } catch (Exception exception) {
            error.errorWhenGettingById(officerId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isOfficerDeleted(final Long officerId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_OFFICER_DELETED_BY_ID)) {

            final boolean isOfficerExist = query.addParameter(OFFICER_ID.getModelName(), officerId)
                    .executeAndFetchFirst(Boolean.class);

            if (isOfficerExist) {
                info.foundByIdAndStatus(officerId, OfficerStatus.DELETED.toString());
                return true;
            } else {
                warn.notFoundByIdAndStatus(officerId, OfficerStatus.DELETED.toString());
                return false;
            }
        } catch (Exception exception) {
            error.errorWhenGettingById(officerId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isOfficerPassive(final Long officerId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_OFFICER_PASSIVE_BY_ID)) {

            final boolean isOfficerExist = query.addParameter(OFFICER_ID.getModelName(), officerId)
                    .executeAndFetchFirst(Boolean.class);

            if (isOfficerExist) {
                info.foundByIdAndStatus(officerId, OfficerStatus.PASSIVE.toString());
                return true;
            } else {
                warn.notFoundByIdAndStatus(officerId, OfficerStatus.PASSIVE.toString());
                return false;
            }
        } catch (Exception exception) {
            error.errorWhenGettingById(officerId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isOfficerActive(final Long officerId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_OFFICER_ACTIVE_BY_ID)) {

            final boolean isOfficerExist = query.addParameter(OFFICER_ID.getModelName(), officerId)
                    .executeAndFetchFirst(Boolean.class);

            if (isOfficerExist) {
                info.foundByIdAndStatus(officerId, OfficerStatus.ACTIVE.toString());
                return true;
            } else {
                warn.notFoundByIdAndStatus(officerId, OfficerStatus.ACTIVE.toString());
                return false;
            }
        } catch (Exception exception) {
            error.errorWhenGettingById(officerId);
            throw new SisDatabaseException(exception);
        }
    }
}
