package com.graduationproject.studentinformationsystem.university.teacher.repository.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisDatabaseException;
import com.graduationproject.studentinformationsystem.common.util.log.SisErrorLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisInfoLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisWarnLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.sql.SisSqlUtil;
import com.graduationproject.studentinformationsystem.university.teacher.model.entity.TeacherAcademicInfoEntity;
import com.graduationproject.studentinformationsystem.university.teacher.model.enums.TeacherStatus;
import com.graduationproject.studentinformationsystem.university.teacher.repository.TeacherAcademicInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

import static com.graduationproject.studentinformationsystem.university.teacher.model.mapping.TeacherAcademicInfoMapping.*;
import static com.graduationproject.studentinformationsystem.university.teacher.repository.impl.scripts.TeacherAcademicInfoSqlScripts.*;

@Repository
@RequiredArgsConstructor
public class TeacherAcademicInfoRepositoryImpl implements TeacherAcademicInfoRepository {

    private static final String TEACHER_ACADEMIC_INFO = "Teacher Academic Info";
    private final SisInfoLogMessageUtil info = SisInfoLogMessageUtil.builder().apiName(TEACHER_ACADEMIC_INFO).build();
    private final SisWarnLogMessageUtil warn = SisWarnLogMessageUtil.builder().apiName(TEACHER_ACADEMIC_INFO).build();
    private final SisErrorLogMessageUtil error = SisErrorLogMessageUtil.builder().apiName(TEACHER_ACADEMIC_INFO).build();

    private final Sql2o sql2o;

    public List<TeacherAcademicInfoEntity> getAllTeacherAcademicInfosByStatus(final TeacherStatus status) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_ALL_TEACHER_ACADEMIC_INFOS_BY_STATUS
                .concat(SisSqlUtil.querySearchByStatus(TEACHER_ID.getColumnName(), status.toString())))) {

            final List<TeacherAcademicInfoEntity> academicInfoEntities = query
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetch(TeacherAcademicInfoEntity.class);

            info.foundAllByStatus(status.toString());
            return academicInfoEntities;
        } catch (Exception exception) {
            error.errorWhenGettingAllByStatus(status.toString());
            throw new SisDatabaseException(exception);
        }
    }

    public TeacherAcademicInfoEntity getTeacherAcademicInfoById(final Long teacherId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_TEACHER_ACADEMIC_INFO_BY_TEACHER_ID)) {

            final TeacherAcademicInfoEntity academicInfoEntity = query
                    .addParameter(TEACHER_ID.getModelName(), teacherId.toString())
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetchFirst(TeacherAcademicInfoEntity.class);

            if (academicInfoEntity != null) {
                info.foundById(teacherId);
            } else {
                warn.notFoundById(teacherId);
            }
            return academicInfoEntity;
        } catch (Exception exception) {
            error.errorWhenGettingById(teacherId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void saveTeacherAcademicInfo(final TeacherAcademicInfoEntity academicInfoEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(SAVE_TEACHER_ACADEMIC_INFO)) {

            query.addParameter(TEACHER_ID.getModelName(), academicInfoEntity.getTeacherId())
                    .addParameter(DEGREE.getModelName(), academicInfoEntity.getDegree())
                    .addParameter(ROLE.getModelName(), academicInfoEntity.getRole())
                    .addParameter(DEPARTMENT_ID.getModelName(), academicInfoEntity.getDepartmentId())
                    .addParameter(FIELD_OF_STUDY.getModelName(), academicInfoEntity.getFieldOfStudy())
                    .addParameter(EMAIL.getModelName(), academicInfoEntity.getEmail())
                    .addParameter(STATUS.getModelName(), academicInfoEntity.getStatus())
                    .addParameter(PHONE_NUMBER.getModelName(), academicInfoEntity.getPhoneNumber())
                    .addParameter(REGISTRATION_DATE.getModelName(), academicInfoEntity.getRegistrationDate())
                    .addParameter(CREATED_DATE.getModelName(), academicInfoEntity.getCreatedDate())
                    .addParameter(CREATED_USER_ID.getModelName(), academicInfoEntity.getCreatedUserId())
                    .executeUpdate();

            info.savedById(academicInfoEntity.getTeacherId());
        } catch (Exception exception) {
            error.errorWhenSaving();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updateTeacherAcademicInfo(final TeacherAcademicInfoEntity academicInfoEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(UPDATE_TEACHER_ACADEMIC_INFO)) {

            query.addParameter(DEGREE.getModelName(), academicInfoEntity.getDegree())
                    .addParameter(ROLE.getModelName(), academicInfoEntity.getRole())
                    .addParameter(DEPARTMENT_ID.getModelName(), academicInfoEntity.getDepartmentId())
                    .addParameter(FIELD_OF_STUDY.getModelName(), academicInfoEntity.getFieldOfStudy())
                    .addParameter(PHONE_NUMBER.getModelName(), academicInfoEntity.getPhoneNumber())
                    .addParameter(MODIFIED_DATE.getModelName(), academicInfoEntity.getModifiedDate())
                    .addParameter(MODIFIED_USER_ID.getModelName(), academicInfoEntity.getModifiedUserId())
                    .addParameter(TEACHER_ID.getModelName(), academicInfoEntity.getTeacherId())
                    .executeUpdate();

            info.updated();
        } catch (Exception exception) {
            error.errorWhenUpdating();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updateTeacherAcademicInfoStatus(final TeacherAcademicInfoEntity academicInfoEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(UPDATE_TEACHER_ACADEMIC_INFO_STATUS)) {

            query.addParameter(STATUS.getModelName(), academicInfoEntity.getStatus())
                    .addParameter(MODIFIED_DATE.getModelName(), academicInfoEntity.getModifiedDate())
                    .addParameter(MODIFIED_USER_ID.getModelName(), academicInfoEntity.getModifiedUserId())
                    .addParameter(TEACHER_ID.getModelName(), academicInfoEntity.getTeacherId())
                    .executeUpdate();

            info.statusUpdated(academicInfoEntity.getStatus().toString());
        } catch (Exception exception) {
            error.errorWhenUpdatingStatus();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public List<Long> getAllTeacherIdsByDepartmentId(final Long departmentId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_ALL_TEACHER_IDS_BY_DEPARTMENT_ID)) {

            final List<Long> teacherIds = query
                    .addParameter(DEPARTMENT_ID.getModelName(), departmentId.toString())
                    .executeScalarList(Long.class);


            if (!teacherIds.isEmpty()) {
                info.foundAllIdsByDepartmentId(departmentId);
            } else {
                warn.notFoundAllIdsByDepartmentId(departmentId);
            }
            return teacherIds;
        } catch (Exception exception) {
            error.errorWhenGettingAllIdsByDepartmentId(departmentId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isTeacherExist(final Long teacherId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_TEACHER_EXIST_BY_ID)) {

            final boolean isTeacherExist = query.addParameter(TEACHER_ID.getModelName(), teacherId)
                    .executeAndFetchFirst(Boolean.class);

            if (isTeacherExist) {
                info.foundById(teacherId);
                return true;
            } else {
                warn.notFoundById(teacherId);
                return false;
            }
        } catch (Exception exception) {
            error.errorWhenGettingById(teacherId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isTeacherDeleted(final Long teacherId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_TEACHER_DELETED_BY_ID)) {

            final boolean isTeacherExist = query.addParameter(TEACHER_ID.getModelName(), teacherId)
                    .executeAndFetchFirst(Boolean.class);

            if (isTeacherExist) {
                info.foundByIdAndStatus(teacherId, TeacherStatus.DELETED.toString());
                return true;
            } else {
                warn.notFoundByIdAndStatus(teacherId, TeacherStatus.DELETED.toString());
                return false;
            }
        } catch (Exception exception) {
            error.errorWhenGettingById(teacherId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isTeacherPassive(final Long teacherId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_TEACHER_PASSIVE_BY_ID)) {

            final boolean isTeacherExist = query.addParameter(TEACHER_ID.getModelName(), teacherId)
                    .executeAndFetchFirst(Boolean.class);

            if (isTeacherExist) {
                info.foundByIdAndStatus(teacherId, TeacherStatus.PASSIVE.toString());
                return true;
            } else {
                warn.notFoundByIdAndStatus(teacherId, TeacherStatus.PASSIVE.toString());
                return false;
            }
        } catch (Exception exception) {
            error.errorWhenGettingById(teacherId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isTeacherActive(final Long teacherId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_TEACHER_ACTIVE_BY_ID)) {

            final boolean isTeacherExist = query.addParameter(TEACHER_ID.getModelName(), teacherId)
                    .executeAndFetchFirst(Boolean.class);

            if (isTeacherExist) {
                info.foundByIdAndStatus(teacherId, TeacherStatus.ACTIVE.toString());
                return true;
            } else {
                warn.notFoundByIdAndStatus(teacherId, TeacherStatus.ACTIVE.toString());
                return false;
            }
        } catch (Exception exception) {
            error.errorWhenGettingById(teacherId);
            throw new SisDatabaseException(exception);
        }
    }
}
