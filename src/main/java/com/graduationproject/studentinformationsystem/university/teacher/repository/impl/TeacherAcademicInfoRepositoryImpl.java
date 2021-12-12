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

    public List<TeacherAcademicInfoEntity> getAllTeacherAcademicInfosByStatus(TeacherStatus status) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(GET_ALL_TEACHER_ACADEMIC_INFOS_BY_STATUS
                .concat(SisSqlUtil.querySearchByStatus(TEACHER_ID.getColumnName(), status.toString())))) {

            List<TeacherAcademicInfoEntity> entities = query
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetch(TeacherAcademicInfoEntity.class);

            info.foundAllByStatus(status.toString());
            return entities;
        } catch (Exception exception) {
            error.errorWhenGettingAllByStatus(status.toString());
            throw new SisDatabaseException(exception);
        }
    }

    public TeacherAcademicInfoEntity getTeacherAcademicInfoById(Long teacherId) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(GET_TEACHER_ACADEMIC_INFO_BY_TEACHER_ID)) {

            TeacherAcademicInfoEntity entity = query
                    .addParameter(TEACHER_ID.getModelName(), teacherId.toString())
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetchFirst(TeacherAcademicInfoEntity.class);

            if (entity != null) {
                info.foundById(teacherId);
            } else {
                warn.notFoundById(teacherId);
            }
            return entity;
        } catch (Exception exception) {
            error.errorWhenGettingById(teacherId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void saveTeacherAcademicInfo(TeacherAcademicInfoEntity entity) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(SAVE_TEACHER_ACADEMIC_INFO)) {

            query.addParameter(TEACHER_ID.getModelName(), entity.getTeacherId())
                    .addParameter(DEGREE.getModelName(), entity.getDegree())
                    .addParameter(ROLE.getModelName(), entity.getRole())
                    .addParameter(DEPARTMENT_ID.getModelName(), entity.getDepartmentId())
                    .addParameter(FIELD_OF_STUDY.getModelName(), entity.getFieldOfStudy())
                    .addParameter(EMAIL.getModelName(), entity.getEmail())
                    .addParameter(STATUS.getModelName(), entity.getStatus())
                    .addParameter(PHONE_NUMBER.getModelName(), entity.getPhoneNumber())
                    .addParameter(REGISTRATION_DATE.getModelName(), entity.getRegistrationDate())
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
    public void updateTeacherAcademicInfo(TeacherAcademicInfoEntity entity) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(UPDATE_TEACHER_ACADEMIC_INFO)) {

            query.addParameter(DEGREE.getModelName(), entity.getDegree())
                    .addParameter(ROLE.getModelName(), entity.getRole())
                    .addParameter(DEPARTMENT_ID.getModelName(), entity.getDepartmentId())
                    .addParameter(FIELD_OF_STUDY.getModelName(), entity.getFieldOfStudy())
                    .addParameter(PHONE_NUMBER.getModelName(), entity.getPhoneNumber())
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
    public void updateTeacherAcademicInfoStatus(TeacherAcademicInfoEntity entity) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(UPDATE_TEACHER_ACADEMIC_INFO_STATUS)) {

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

    @Override
    public List<Long> getAllTeacherIdsByDepartmentId(Long departmentId) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(GET_ALL_TEACHER_IDS_BY_DEPARTMENT_ID)) {

            List<Long> teacherIds = query
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
    public boolean isTeacherExist(Long teacherId) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(IS_TEACHER_EXIST_BY_ID)) {

            boolean isTeacherExist = query.addParameter(TEACHER_ID.getModelName(), teacherId)
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
    public boolean isTeacherDeleted(Long teacherId) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(IS_TEACHER_DELETED_BY_ID)) {

            boolean isTeacherExist = query.addParameter(TEACHER_ID.getModelName(), teacherId)
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
    public boolean isTeacherPassive(Long teacherId) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(IS_TEACHER_PASSIVE_BY_ID)) {

            boolean isTeacherExist = query.addParameter(TEACHER_ID.getModelName(), teacherId)
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
    public boolean isTeacherActive(Long teacherId) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(IS_TEACHER_ACTIVE_BY_ID)) {

            boolean isTeacherExist = query.addParameter(TEACHER_ID.getModelName(), teacherId)
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
