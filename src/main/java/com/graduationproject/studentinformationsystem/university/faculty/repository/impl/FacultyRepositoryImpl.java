package com.graduationproject.studentinformationsystem.university.faculty.repository.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisDatabaseException;
import com.graduationproject.studentinformationsystem.common.util.log.SisErrorLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisInfoLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisWarnLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.sql.SisSqlUtil;
import com.graduationproject.studentinformationsystem.university.faculty.model.entity.FacultyEntity;
import com.graduationproject.studentinformationsystem.university.faculty.model.enums.FacultyStatus;
import com.graduationproject.studentinformationsystem.university.faculty.repository.FacultyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

import static com.graduationproject.studentinformationsystem.university.faculty.model.mapping.FacultyMapping.*;
import static com.graduationproject.studentinformationsystem.university.faculty.repository.impl.scripts.FacultySqlScripts.*;
import static com.graduationproject.studentinformationsystem.university.officer.model.mapping.OfficerAcademicInfoMapping.FACULTY_ID;

@Repository
@RequiredArgsConstructor
public class FacultyRepositoryImpl implements FacultyRepository {

    private static final String FACULTY = "Faculty";
    private final SisInfoLogMessageUtil info = SisInfoLogMessageUtil.builder().apiName(FACULTY).build();
    private final SisWarnLogMessageUtil warn = SisWarnLogMessageUtil.builder().apiName(FACULTY).build();
    private final SisErrorLogMessageUtil error = SisErrorLogMessageUtil.builder().apiName(FACULTY).build();

    private final Sql2o sql2o;

    public List<FacultyEntity> getAllFacultiesByStatus(final FacultyStatus status) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_ALL_FACULTIES_BY_STATUS
                .concat(SisSqlUtil.querySearchByStatus(FACULTY_ID.getColumnName(), status.toString())))) {

            final List<FacultyEntity> facultyEntities = query
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetch(FacultyEntity.class);

            info.foundAllByStatus(status.toString());
            return facultyEntities;
        } catch (Exception exception) {
            error.errorWhenGettingAllByStatus(status.toString());
            throw new SisDatabaseException(exception);
        }
    }

    public FacultyEntity getFacultyById(final Long facultyId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_FACULTY_BY_ID)) {

            final FacultyEntity facultyEntity = query
                    .addParameter(FACULTY_ID.getModelName(), facultyId.toString())
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetchFirst(FacultyEntity.class);

            if (facultyEntity != null) {
                info.foundById(facultyId);
            } else {
                warn.notFoundById(facultyId);
            }
            return facultyEntity;
        } catch (Exception exception) {
            error.errorWhenGettingById(facultyId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void saveFaculty(final FacultyEntity facultyEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(SAVE_FACULTY)) {

            query.addParameter(FACULTY_ID.getModelName(), facultyEntity.getFacultyId())
                    .addParameter(NAME.getModelName(), facultyEntity.getName())
                    .addParameter(STATUS.getModelName(), facultyEntity.getStatus())
                    .addParameter(CREATED_DATE.getModelName(), facultyEntity.getCreatedDate())
                    .addParameter(CREATED_USER_ID.getModelName(), facultyEntity.getCreatedUserId())
                    .executeUpdate();

            info.savedById(facultyEntity.getFacultyId());
        } catch (Exception exception) {
            error.errorWhenSaving();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updateFaculty(final FacultyEntity facultyEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(UPDATE_FACULTY)) {

            query.addParameter(NAME.getModelName(), facultyEntity.getName())
                    .addParameter(MODIFIED_DATE.getModelName(), facultyEntity.getModifiedDate())
                    .addParameter(MODIFIED_USER_ID.getModelName(), facultyEntity.getModifiedUserId())
                    .addParameter(FACULTY_ID.getModelName(), facultyEntity.getFacultyId())
                    .executeUpdate();

            info.updated();
        } catch (Exception exception) {
            error.errorWhenUpdating();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updateFacultyStatus(final FacultyEntity facultyEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(UPDATE_FACULTY_STATUS)) {

            query.addParameter(STATUS.getModelName(), facultyEntity.getStatus())
                    .addParameter(MODIFIED_DATE.getModelName(), facultyEntity.getModifiedDate())
                    .addParameter(MODIFIED_USER_ID.getModelName(), facultyEntity.getModifiedUserId())
                    .addParameter(FACULTY_ID.getModelName(), facultyEntity.getFacultyId())
                    .executeUpdate();

            info.statusUpdated(facultyEntity.getStatus().toString());
        } catch (Exception exception) {
            error.errorWhenUpdatingStatus();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public List<Long> getAllFacultyIds() {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_ALL_FACULTY_IDS)) {

            final List<Long> facultyIds = query.executeScalarList(Long.class);

            if (!facultyIds.isEmpty()) {
                info.foundAllIds();
            } else {
                warn.notFoundAllIds();
            }
            return facultyIds;
        } catch (Exception exception) {
            error.errorWhenGettingAllIds();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isFacultyExist(final Long facultyId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_FACULTY_EXIST_BY_ID)) {

            final boolean isFacultyExist = query.addParameter(FACULTY_ID.getModelName(), facultyId)
                    .executeAndFetchFirst(Boolean.class);

            if (isFacultyExist) {
                info.foundById(facultyId);
                return true;
            } else {
                warn.notFoundById(facultyId);
                return false;
            }
        } catch (Exception exception) {
            error.errorWhenGettingById(facultyId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isFacultyDeleted(final Long facultyId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_FACULTY_DELETED_BY_ID)) {

            final boolean isFacultyExist = query.addParameter(FACULTY_ID.getModelName(), facultyId)
                    .executeAndFetchFirst(Boolean.class);

            if (isFacultyExist) {
                info.foundByIdAndStatus(facultyId, FacultyStatus.DELETED.toString());
                return true;
            } else {
                warn.notFoundByIdAndStatus(facultyId, FacultyStatus.DELETED.toString());
                return false;
            }
        } catch (Exception exception) {
            error.errorWhenGettingById(facultyId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isFacultyPassive(final Long facultyId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_FACULTY_PASSIVE_BY_ID)) {

            final boolean isFacultyExist = query.addParameter(FACULTY_ID.getModelName(), facultyId)
                    .executeAndFetchFirst(Boolean.class);

            if (isFacultyExist) {
                info.foundByIdAndStatus(facultyId, FacultyStatus.PASSIVE.toString());
                return true;
            } else {
                warn.notFoundByIdAndStatus(facultyId, FacultyStatus.PASSIVE.toString());
                return false;
            }
        } catch (Exception exception) {
            error.errorWhenGettingById(facultyId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isFacultyActive(final Long facultyId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_FACULTY_ACTIVE_BY_ID)) {

            final boolean isFacultyExist = query.addParameter(FACULTY_ID.getModelName(), facultyId)
                    .executeAndFetchFirst(Boolean.class);

            if (isFacultyExist) {
                info.foundByIdAndStatus(facultyId, FacultyStatus.ACTIVE.toString());
                return true;
            } else {
                warn.notFoundByIdAndStatus(facultyId, FacultyStatus.ACTIVE.toString());
                return false;
            }
        } catch (Exception exception) {
            error.errorWhenGettingById(facultyId);
            throw new SisDatabaseException(exception);
        }
    }
}
