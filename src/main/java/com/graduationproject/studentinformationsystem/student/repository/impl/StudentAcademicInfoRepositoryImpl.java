package com.graduationproject.studentinformationsystem.student.repository.impl;

import com.graduationproject.studentinformationsystem.common.util.log.SisErrorLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisInfoLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisWarnLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.sql.SisSqlUtil;
import com.graduationproject.studentinformationsystem.student.model.entity.StudentAcademicInfoEntity;
import com.graduationproject.studentinformationsystem.student.model.enums.StudentStatus;
import com.graduationproject.studentinformationsystem.student.repository.StudentAcademicInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

import static com.graduationproject.studentinformationsystem.student.model.mapping.StudentAcademicInfoMapping.*;
import static com.graduationproject.studentinformationsystem.student.repository.impl.scripts.StudentAcademicInfoSqlScripts.*;

@Repository
@RequiredArgsConstructor
public class StudentAcademicInfoRepositoryImpl implements StudentAcademicInfoRepository {

    private static final String STUDENT_ACADEMIC_INFO = "Student Academic Info";
    private final SisInfoLogMessageUtil info = SisInfoLogMessageUtil.builder().apiName(STUDENT_ACADEMIC_INFO).build();
    private final SisWarnLogMessageUtil warn = SisWarnLogMessageUtil.builder().apiName(STUDENT_ACADEMIC_INFO).build();
    private final SisErrorLogMessageUtil error = SisErrorLogMessageUtil.builder().apiName(STUDENT_ACADEMIC_INFO).build();

    private final Sql2o sql2o;

    public List<StudentAcademicInfoEntity> getAllStudentAcademicInfosByStatus(StudentStatus status) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(
                GET_ALL_STUDENT_ACADEMIC_INFOS_BY_STATUS
                        .concat(SisSqlUtil.querySearchByStatus(STUDENT_ID.getColumnName(), status.toString())))) {

            List<StudentAcademicInfoEntity> entities = query
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetch(StudentAcademicInfoEntity.class);

            info.foundAllByStatus(status.toString());
            return entities;
        } catch (Exception e) {
            error.errorWhenGettingAllByStatus(status.toString());
            // TODO: Throw Specific Method Exception
            return null;
        }
    }

    public StudentAcademicInfoEntity getStudentAcademicInfoById(Long studentId) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(GET_STUDENT_ACADEMIC_INFO_BY_STUDENT_ID)) {

            StudentAcademicInfoEntity entity = query
                    .addParameter(STUDENT_ID.getModelName(), studentId.toString())
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetchFirst(StudentAcademicInfoEntity.class);

            if (entity != null) {
                info.foundById(studentId);
            } else {
                warn.notFoundById(studentId);
            }
            return entity;
        } catch (Exception e) {
            error.errorWhenGettingById(studentId);
            // TODO: Throw Specific Method Exception
            return null;
        }
    }

    @Override
    public void saveStudentAcademicInfo(StudentAcademicInfoEntity entity) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(SAVE_STUDENT_ACADEMIC_INFO)) {

            query.addParameter(STUDENT_ID.getModelName(), entity.getStudentId())
                    .addParameter(DEPARTMENT_ID.getModelName(), entity.getDepartmentId())
                    .addParameter(DEGREE.getModelName(), entity.getDegree())
                    .addParameter(CLASS_LEVEL.getModelName(), entity.getClassLevel())
                    .addParameter(EMAIL.getModelName(), entity.getEmail())
                    .addParameter(STATUS.getModelName(), entity.getStatus())
                    .addParameter(REGISTRATION_DATE.getModelName(), entity.getRegistrationDate())
                    .addParameter(CREATED_DATE.getModelName(), entity.getCreatedDate())
                    .addParameter(CREATED_USER_ID.getModelName(), entity.getCreatedUserId())
                    .executeUpdate();

            info.savedById(entity.getStudentId());
        } catch (Exception e) {
            error.errorWhenSaving();
            // TODO: Throw Specific Method Exception
        }
    }

    @Override
    public void updateStudentAcademicInfo(StudentAcademicInfoEntity entity) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(UPDATE_STUDENT_ACADEMIC_INFO)) {

            query.addParameter(DEPARTMENT_ID.getModelName(), entity.getDepartmentId())
                    .addParameter(DEGREE.getModelName(), entity.getDegree())
                    .addParameter(CLASS_LEVEL.getModelName(), entity.getClassLevel())
                    .addParameter(MODIFIED_DATE.getModelName(), entity.getModifiedDate())
                    .addParameter(MODIFIED_USER_ID.getModelName(), entity.getModifiedUserId())
                    .addParameter(STUDENT_ID.getModelName(), entity.getStudentId())
                    .executeUpdate();

            info.updated();
        } catch (Exception e) {
            error.errorWhenUpdating();
            // TODO: Throw Specific Method Exception
        }
    }

    @Override
    public void updateStudentAcademicInfoStatus(StudentAcademicInfoEntity entity) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(UPDATE_STUDENT_ACADEMIC_INFO_STATUS)) {

            query.addParameter(STATUS.getModelName(), entity.getStatus())
                    .addParameter(MODIFIED_DATE.getModelName(), entity.getModifiedDate())
                    .addParameter(MODIFIED_USER_ID.getModelName(), entity.getModifiedUserId())
                    .addParameter(STUDENT_ID.getModelName(), entity.getStudentId())
                    .executeUpdate();

            info.statusUpdated(entity.getStatus().toString());
        } catch (Exception e) {
            error.errorWhenUpdatingStatus();
            // TODO: Throw Specific Method Exception
        }
    }

    @Override
    public List<Long> getAllStudentIdsByDepartmentId(Long departmentId) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(GET_ALL_STUDENT_IDS_BY_DEPARTMENT_ID)) {

            List<Long> studentIdList = query
                    .addParameter(DEPARTMENT_ID.getModelName(), departmentId.toString())
                    .executeScalarList(Long.class);

            info.foundAllIdsByDepartmentId(departmentId);
            return studentIdList;
        } catch (Exception e) {
            error.errorWhenGettingAllIdsByDepartmentId(departmentId);
            // TODO: Throw Specific Method Exception
            return null;
        }
    }

    @Override
    public boolean isStudentExist(Long studentId) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(IS_STUDENT_EXIST_BY_ID)) {

            boolean isStudentExist = query.addParameter(STUDENT_ID.getModelName(), studentId)
                    .executeAndFetchFirst(Boolean.class);

            info.foundById(studentId);
            return isStudentExist;
        } catch (Exception e) {
            error.errorWhenGettingById(studentId);
            // TODO: Throw Specific Method Exception
            return false;
        }
    }
}
