package com.graduationproject.studentinformationsystem.university.department.repository.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisDatabaseException;
import com.graduationproject.studentinformationsystem.common.util.log.SisErrorLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisInfoLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisWarnLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.sql.SisSqlUtil;
import com.graduationproject.studentinformationsystem.university.department.model.entity.DepartmentEntity;
import com.graduationproject.studentinformationsystem.university.department.model.enums.DepartmentStatus;
import com.graduationproject.studentinformationsystem.university.department.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

import static com.graduationproject.studentinformationsystem.university.department.model.mapping.DepartmentMapping.*;
import static com.graduationproject.studentinformationsystem.university.department.repository.impl.scripts.DepartmentSqlScripts.*;

@Repository
@RequiredArgsConstructor
public class DepartmentRepositoryImpl implements DepartmentRepository {

    private static final String DEPARTMENT = "Department";
    private final SisInfoLogMessageUtil info = SisInfoLogMessageUtil.builder().apiName(DEPARTMENT).build();
    private final SisWarnLogMessageUtil warn = SisWarnLogMessageUtil.builder().apiName(DEPARTMENT).build();
    private final SisErrorLogMessageUtil error = SisErrorLogMessageUtil.builder().apiName(DEPARTMENT).build();

    private final Sql2o sql2o;

    public List<DepartmentEntity> getAllDepartmentsByStatus(final DepartmentStatus status) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_ALL_DEPARTMENTS_BY_STATUS
                .concat(SisSqlUtil.querySearchByStatus(DEPARTMENT_ID.getColumnName(), status.toString())))) {

            final List<DepartmentEntity> departmentEntities = query
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetch(DepartmentEntity.class);

            info.foundAllByStatus(status.toString());
            return departmentEntities;
        } catch (Exception exception) {
            error.errorWhenGettingAllByStatus(status.toString());
            throw new SisDatabaseException(exception);
        }
    }

    public DepartmentEntity getDepartmentById(final Long departmentId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_DEPARTMENT_BY_ID)) {

            final DepartmentEntity departmentEntity = query
                    .addParameter(DEPARTMENT_ID.getModelName(), departmentId.toString())
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetchFirst(DepartmentEntity.class);

            if (departmentEntity != null) {
                info.foundById(departmentId);
            } else {
                warn.notFoundById(departmentId);
            }
            return departmentEntity;
        } catch (Exception exception) {
            error.errorWhenGettingById(departmentId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void saveDepartment(final DepartmentEntity departmentEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(SAVE_DEPARTMENT)) {

            query.addParameter(DEPARTMENT_ID.getModelName(), departmentEntity.getDepartmentId())
                    .addParameter(FACULTY_ID.getModelName(), departmentEntity.getFacultyId())
                    .addParameter(NAME.getModelName(), departmentEntity.getName())
                    .addParameter(STATUS.getModelName(), departmentEntity.getStatus())
                    .addParameter(TOTAL_CLASS_LEVEL.getModelName(), departmentEntity.getTotalClassLevel())
                    .addParameter(IS_THERE_PREPARATORY_CLASS.getModelName(), departmentEntity.getIsTherePreparatoryClass())
                    .addParameter(CREATED_DATE.getModelName(), departmentEntity.getCreatedDate())
                    .addParameter(CREATED_USER_ID.getModelName(), departmentEntity.getCreatedUserId())
                    .executeUpdate();

            info.savedById(departmentEntity.getDepartmentId());
        } catch (Exception exception) {
            error.errorWhenSaving();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updateDepartment(final DepartmentEntity departmentEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(UPDATE_DEPARTMENT)) {

            query.addParameter(FACULTY_ID.getModelName(), departmentEntity.getFacultyId())
                    .addParameter(NAME.getModelName(), departmentEntity.getName())
                    .addParameter(TOTAL_CLASS_LEVEL.getModelName(), departmentEntity.getTotalClassLevel())
                    .addParameter(IS_THERE_PREPARATORY_CLASS.getModelName(), departmentEntity.getIsTherePreparatoryClass())
                    .addParameter(MODIFIED_DATE.getModelName(), departmentEntity.getModifiedDate())
                    .addParameter(MODIFIED_USER_ID.getModelName(), departmentEntity.getModifiedUserId())
                    .addParameter(DEPARTMENT_ID.getModelName(), departmentEntity.getDepartmentId())
                    .executeUpdate();

            info.updated();
        } catch (Exception exception) {
            error.errorWhenUpdating();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updateDepartmentStatus(final DepartmentEntity departmentEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(UPDATE_DEPARTMENT_STATUS)) {

            query.addParameter(STATUS.getModelName(), departmentEntity.getStatus())
                    .addParameter(MODIFIED_DATE.getModelName(), departmentEntity.getModifiedDate())
                    .addParameter(MODIFIED_USER_ID.getModelName(), departmentEntity.getModifiedUserId())
                    .addParameter(DEPARTMENT_ID.getModelName(), departmentEntity.getDepartmentId())
                    .executeUpdate();

            info.statusUpdated(departmentEntity.getStatus().toString());
        } catch (Exception exception) {
            error.errorWhenUpdatingStatus();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public List<Long> getAllDepartmentIdsByFacultyId(final Long facultyId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_ALL_DEPARTMENT_IDS_BY_FACULTY_ID)) {

            final List<Long> departmentIds = query.addParameter(FACULTY_ID.getModelName(), facultyId)
                    .executeScalarList(Long.class);

            if (!departmentIds.isEmpty()) {
                info.foundAllIds();
            } else {
                warn.notFoundAllIds();
            }
            return departmentIds;
        } catch (Exception exception) {
            error.errorWhenGettingAllIds();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isDepartmentExist(final Long departmentId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_DEPARTMENT_EXIST_BY_ID)) {

            final boolean isDepartmentExist = query.addParameter(DEPARTMENT_ID.getModelName(), departmentId)
                    .executeAndFetchFirst(Boolean.class);

            if (isDepartmentExist) {
                info.foundById(departmentId);
                return true;
            } else {
                warn.notFoundById(departmentId);
                return false;
            }
        } catch (Exception exception) {
            error.errorWhenGettingById(departmentId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isDepartmentDeleted(final Long departmentId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_DEPARTMENT_DELETED_BY_ID)) {

            final boolean isDepartmentExist = query.addParameter(DEPARTMENT_ID.getModelName(), departmentId)
                    .executeAndFetchFirst(Boolean.class);

            if (isDepartmentExist) {
                info.foundByIdAndStatus(departmentId, DepartmentStatus.DELETED.toString());
                return true;
            } else {
                warn.notFoundByIdAndStatus(departmentId, DepartmentStatus.DELETED.toString());
                return false;
            }
        } catch (Exception exception) {
            error.errorWhenGettingById(departmentId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isDepartmentPassive(final Long departmentId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_DEPARTMENT_PASSIVE_BY_ID)) {

            final boolean isDepartmentExist = query.addParameter(DEPARTMENT_ID.getModelName(), departmentId)
                    .executeAndFetchFirst(Boolean.class);

            if (isDepartmentExist) {
                info.foundByIdAndStatus(departmentId, DepartmentStatus.PASSIVE.toString());
                return true;
            } else {
                warn.notFoundByIdAndStatus(departmentId, DepartmentStatus.PASSIVE.toString());
                return false;
            }
        } catch (Exception exception) {
            error.errorWhenGettingById(departmentId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isDepartmentActive(final Long departmentId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_DEPARTMENT_ACTIVE_BY_ID)) {

            final boolean isDepartmentExist = query.addParameter(DEPARTMENT_ID.getModelName(), departmentId)
                    .executeAndFetchFirst(Boolean.class);

            if (isDepartmentExist) {
                info.foundByIdAndStatus(departmentId, DepartmentStatus.ACTIVE.toString());
                return true;
            } else {
                warn.notFoundByIdAndStatus(departmentId, DepartmentStatus.ACTIVE.toString());
                return false;
            }
        } catch (Exception exception) {
            error.errorWhenGettingById(departmentId);
            throw new SisDatabaseException(exception);
        }
    }
}
