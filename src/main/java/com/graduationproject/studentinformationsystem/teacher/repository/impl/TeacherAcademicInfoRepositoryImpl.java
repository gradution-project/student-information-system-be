package com.graduationproject.studentinformationsystem.teacher.repository.impl;

import com.graduationproject.studentinformationsystem.common.util.log.SisErrorLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisInfoLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisWarnLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.sql.SisSqlUtil;
import com.graduationproject.studentinformationsystem.teacher.model.entity.TeacherAcademicInfoEntity;
import com.graduationproject.studentinformationsystem.teacher.model.enums.TeacherStatus;
import com.graduationproject.studentinformationsystem.teacher.repository.TeacherAcademicInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

import static com.graduationproject.studentinformationsystem.teacher.model.mapping.TeacherAcademicInfoMapping.*;
import static com.graduationproject.studentinformationsystem.teacher.repository.impl.scripts.TeacherAcademicInfoSqlScripts.*;

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
        } catch (Exception e) {
            error.errorWhenGettingAllByStatus(status.toString());
            // TODO: Throw Specific Method Exception
            return null;
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
        } catch (Exception e) {
            error.errorWhenGettingById(teacherId);
            // TODO: Throw Specific Method Exception
            return null;
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
        } catch (Exception e) {
            error.errorWhenSaving();
            // TODO: Throw Specific Method Exception
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
        } catch (Exception e) {
            error.errorWhenUpdating();
            // TODO: Throw Specific Method Exception
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
        } catch (Exception e) {
            error.errorWhenUpdatingStatus();
            // TODO: Throw Specific Method Exception
        }
    }

    @Override
    public List<Long> getAllTeacherIdsByDepartmentId(Long departmentId) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(GET_ALL_TEACHER_IDS_BY_DEPARTMENT_ID)) {

            List<Long> teacherIds = query
                    .addParameter(DEPARTMENT_ID.getModelName(), departmentId.toString())
                    .executeScalarList(Long.class);

            info.foundAllIdsByDepartmentId(departmentId);
            return teacherIds;
        } catch (Exception e) {
            error.errorWhenGettingAllIdsByDepartmentId(departmentId);
            // TODO: Throw Specific Method Exception
            return null;
        }
    }

    @Override
    public boolean isTeacherExist(Long teacherId) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(IS_TEACHER_EXIST_BY_ID)) {

            boolean isTeacherExist = query.addParameter(TEACHER_ID.getModelName(), teacherId)
                    .executeAndFetchFirst(Boolean.class);

            info.foundById(teacherId);
            return isTeacherExist;
        } catch (Exception e) {
            error.errorWhenGettingById(teacherId);
            // TODO: Throw Specific Method Exception
            return false;
        }
    }
}
