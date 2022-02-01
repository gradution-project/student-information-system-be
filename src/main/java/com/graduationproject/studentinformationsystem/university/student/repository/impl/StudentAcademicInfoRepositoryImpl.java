package com.graduationproject.studentinformationsystem.university.student.repository.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisDatabaseException;
import com.graduationproject.studentinformationsystem.common.util.log.SisErrorLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisInfoLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisWarnLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.sql.SisSqlUtil;
import com.graduationproject.studentinformationsystem.university.student.model.entity.StudentAcademicInfoEntity;
import com.graduationproject.studentinformationsystem.university.student.model.enums.StudentStatus;
import com.graduationproject.studentinformationsystem.university.student.repository.StudentAcademicInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

import static com.graduationproject.studentinformationsystem.university.student.model.mapping.StudentAcademicInfoMapping.*;
import static com.graduationproject.studentinformationsystem.university.student.repository.impl.scripts.StudentAcademicInfoSqlScripts.*;

@Repository
@RequiredArgsConstructor
public class StudentAcademicInfoRepositoryImpl implements StudentAcademicInfoRepository {

    private static final String STUDENT_ACADEMIC_INFO = "Student Academic Info";
    private final SisInfoLogMessageUtil info = SisInfoLogMessageUtil.builder().apiName(STUDENT_ACADEMIC_INFO).build();
    private final SisWarnLogMessageUtil warn = SisWarnLogMessageUtil.builder().apiName(STUDENT_ACADEMIC_INFO).build();
    private final SisErrorLogMessageUtil error = SisErrorLogMessageUtil.builder().apiName(STUDENT_ACADEMIC_INFO).build();

    private final Sql2o sql2o;

    public List<StudentAcademicInfoEntity> getAllStudentAcademicInfosByStatus(final StudentStatus status) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_ALL_STUDENT_ACADEMIC_INFOS_BY_STATUS
                .concat(SisSqlUtil.querySearchByStatus(STUDENT_ID.getColumnName(), status.toString())))) {

            final List<StudentAcademicInfoEntity> academicInfoEntities = query
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetch(StudentAcademicInfoEntity.class);

            info.foundAllByStatus(status.toString());
            return academicInfoEntities;
        } catch (Exception exception) {
            error.errorWhenGettingAllByStatus(status.toString());
            throw new SisDatabaseException(exception);
        }
    }

    public StudentAcademicInfoEntity getStudentAcademicInfoById(final Long studentId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_STUDENT_ACADEMIC_INFO_BY_STUDENT_ID)) {

            final StudentAcademicInfoEntity academicInfoEntity = query
                    .addParameter(STUDENT_ID.getModelName(), studentId.toString())
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetchFirst(StudentAcademicInfoEntity.class);

            if (academicInfoEntity != null) {
                info.foundById(studentId);
            } else {
                warn.notFoundById(studentId);
            }
            return academicInfoEntity;
        } catch (Exception exception) {
            error.errorWhenGettingById(studentId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void saveStudentAcademicInfo(StudentAcademicInfoEntity academicInfoEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(SAVE_STUDENT_ACADEMIC_INFO)) {

            query.addParameter(STUDENT_ID.getModelName(), academicInfoEntity.getStudentId())
                    .addParameter(DEPARTMENT_ID.getModelName(), academicInfoEntity.getDepartmentId())
                    .addParameter(DEGREE.getModelName(), academicInfoEntity.getDegree())
                    .addParameter(CLASS_LEVEL.getModelName(), academicInfoEntity.getClassLevel())
                    .addParameter(EMAIL.getModelName(), academicInfoEntity.getEmail())
                    .addParameter(STATUS.getModelName(), academicInfoEntity.getStatus())
                    .addParameter(REGISTRATION_DATE.getModelName(), academicInfoEntity.getRegistrationDate())
                    .addParameter(CREATED_DATE.getModelName(), academicInfoEntity.getCreatedDate())
                    .addParameter(CREATED_USER_ID.getModelName(), academicInfoEntity.getCreatedUserId())
                    .executeUpdate();

            info.savedById(academicInfoEntity.getStudentId());
        } catch (Exception exception) {
            error.errorWhenSaving();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updateStudentAcademicInfo(final StudentAcademicInfoEntity entity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(UPDATE_STUDENT_ACADEMIC_INFO)) {

            query.addParameter(DEPARTMENT_ID.getModelName(), entity.getDepartmentId())
                    .addParameter(DEGREE.getModelName(), entity.getDegree())
                    .addParameter(CLASS_LEVEL.getModelName(), entity.getClassLevel())
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
    public void updateStudentAcademicInfoStatus(final StudentAcademicInfoEntity academicInfoEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(UPDATE_STUDENT_ACADEMIC_INFO_STATUS)) {

            query.addParameter(STATUS.getModelName(), academicInfoEntity.getStatus())
                    .addParameter(MODIFIED_DATE.getModelName(), academicInfoEntity.getModifiedDate())
                    .addParameter(MODIFIED_USER_ID.getModelName(), academicInfoEntity.getModifiedUserId())
                    .addParameter(STUDENT_ID.getModelName(), academicInfoEntity.getStudentId())
                    .executeUpdate();

            info.statusUpdated(academicInfoEntity.getStatus().toString());
        } catch (Exception exception) {
            error.errorWhenUpdatingStatus();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public List<Long> getAllStudentIdsByDepartmentId(final Long departmentId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_ALL_STUDENT_IDS_BY_DEPARTMENT_ID)) {

            final List<Long> studentIds = query
                    .addParameter(DEPARTMENT_ID.getModelName(), departmentId.toString())
                    .executeScalarList(Long.class);

            info.foundAllIdsByDepartmentId(departmentId);
            return studentIds;
        } catch (Exception exception) {
            error.errorWhenGettingAllIdsByDepartmentId(departmentId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isStudentExist(final Long studentId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_STUDENT_EXIST_BY_ID)) {

            final boolean isStudentExist = query.addParameter(STUDENT_ID.getModelName(), studentId)
                    .executeAndFetchFirst(Boolean.class);

            if (isStudentExist) {
                info.foundById(studentId);
                return true;
            } else {
                warn.notFoundById(studentId);
                return false;
            }
        } catch (Exception exception) {
            error.errorWhenGettingById(studentId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isStudentDeleted(final Long studentId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_STUDENT_DELETED_BY_ID)) {

            final boolean isStudentExist = query.addParameter(STUDENT_ID.getModelName(), studentId)
                    .executeAndFetchFirst(Boolean.class);

            if (isStudentExist) {
                info.foundByIdAndStatus(studentId, StudentStatus.DELETED.toString());
                return true;
            } else {
                warn.notFoundByIdAndStatus(studentId, StudentStatus.DELETED.toString());
                return false;
            }
        } catch (Exception exception) {
            error.errorWhenGettingById(studentId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isStudentPassive(final Long studentId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_STUDENT_PASSIVE_BY_ID)) {

            final boolean isStudentExist = query.addParameter(STUDENT_ID.getModelName(), studentId)
                    .executeAndFetchFirst(Boolean.class);

            if (isStudentExist) {
                info.foundByIdAndStatus(studentId, StudentStatus.PASSIVE.toString());
                return true;
            } else {
                warn.notFoundByIdAndStatus(studentId, StudentStatus.PASSIVE.toString());
                return false;
            }
        } catch (Exception exception) {
            error.errorWhenGettingById(studentId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isStudentActive(final Long studentId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_STUDENT_ACTIVE_BY_ID)) {

            final boolean isStudentExist = query.addParameter(STUDENT_ID.getModelName(), studentId)
                    .executeAndFetchFirst(Boolean.class);

            if (isStudentExist) {
                info.foundByIdAndStatus(studentId, StudentStatus.ACTIVE.toString());
                return true;
            } else {
                warn.notFoundByIdAndStatus(studentId, StudentStatus.ACTIVE.toString());
                return false;
            }
        } catch (Exception exception) {
            error.errorWhenGettingById(studentId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isStudentGraduated(final Long studentId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_STUDENT_GRADUATED_BY_ID)) {

            final boolean isStudentExist = query.addParameter(STUDENT_ID.getModelName(), studentId)
                    .executeAndFetchFirst(Boolean.class);

            if (isStudentExist) {
                info.foundByIdAndStatus(studentId, StudentStatus.GRADUATED.toString());
                return true;
            } else {
                warn.notFoundByIdAndStatus(studentId, StudentStatus.GRADUATED.toString());
                return false;
            }
        } catch (Exception exception) {
            error.errorWhenGettingById(studentId);
            throw new SisDatabaseException(exception);
        }
    }
}
