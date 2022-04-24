package com.graduationproject.studentinformationsystem.university.graduation.repository.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisDatabaseException;
import com.graduationproject.studentinformationsystem.common.util.log.SisErrorLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisInfoLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisWarnLogMessageUtil;
import com.graduationproject.studentinformationsystem.university.graduation.model.entity.StudentGraduationEntity;
import com.graduationproject.studentinformationsystem.university.graduation.model.enums.StudentGraduationStatus;
import com.graduationproject.studentinformationsystem.university.graduation.repository.StudentGraduationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import static com.graduationproject.studentinformationsystem.university.graduation.model.mapping.StudentGraduationMapping.*;
import static com.graduationproject.studentinformationsystem.university.graduation.repository.impl.scripts.StudentGraduationSqlScripts.*;

@Repository
@RequiredArgsConstructor
public class StudentGraduationRepositoryImpl implements StudentGraduationRepository {

    private static final String STUDENT_GRADUATION = "Student Graduation";
    private final SisInfoLogMessageUtil info = SisInfoLogMessageUtil.builder().apiName(STUDENT_GRADUATION).build();
    private final SisWarnLogMessageUtil warn = SisWarnLogMessageUtil.builder().apiName(STUDENT_GRADUATION).build();
    private final SisErrorLogMessageUtil error = SisErrorLogMessageUtil.builder().apiName(STUDENT_GRADUATION).build();

    private final Sql2o sql2o;

    @Override
    public List<StudentGraduationEntity> getAllStudentsGraduationsByStatus(final StudentGraduationStatus status) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_ALL_STUDENT_GRADUATIONS
                .concat(querySearchByStatus(CREATED_DATE.getColumnName(), status.toString())))) {

            final List<StudentGraduationEntity> registrationEntities = query
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetch(StudentGraduationEntity.class);

            if (!registrationEntities.isEmpty()) {
                info.foundAllByStatus(status.toString());
            } else {
                warn.notFoundAllIdsByStatus(status.toString());
            }
            return registrationEntities;
        } catch (Exception exception) {
            error.errorWhenGettingAllByStatus(status.toString());
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public StudentGraduationEntity getStudentGraduationDetailByGraduationId(final String graduationId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_STUDENT_GRADUATION)) {

            final StudentGraduationEntity registrationEntity = query
                    .addParameter(GRADUATION_ID.getModelName(), graduationId)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetchFirst(StudentGraduationEntity.class);

            info.foundById(graduationId);
            return registrationEntity;
        } catch (Exception exception) {
            error.errorWhenGettingById(graduationId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void saveStudentGraduation(final StudentGraduationEntity registrationEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(SAVE_STUDENT_GRADUATION)) {

            query.addParameter(GRADUATION_ID.getModelName(), registrationEntity.getGraduationId())
                    .addParameter(STUDENT_ID.getModelName(), registrationEntity.getStudentId())
                    .addParameter(STATUS.getModelName(), registrationEntity.getStatus())
                    .addParameter(CREATED_DATE.getModelName(), registrationEntity.getCreatedDate())
                    .addParameter(CREATED_USER_ID.getModelName(), registrationEntity.getCreatedUserId())
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeUpdate();

            info.savedById(registrationEntity.getStudentId());
        } catch (Exception exception) {
            error.errorWhenSaving();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updateStudentGraduationStatus(final StudentGraduationEntity registrationEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(UPDATE_STUDENT_GRADUATION_STATUS)) {

            query.addParameter(STATUS.getModelName(), registrationEntity.getStatus())
                    .addParameter(MODIFIED_DATE.getModelName(), registrationEntity.getModifiedDate())
                    .addParameter(MODIFIED_USER_ID.getModelName(), registrationEntity.getModifiedUserId())
                    .addParameter(GRADUATION_ID.getModelName(), registrationEntity.getGraduationId())
                    .executeUpdate();

            info.statusUpdated(registrationEntity.getStatus().toString());
        } catch (Exception exception) {
            error.errorWhenUpdatingStatus();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isStudentGraduationExist(final String graduationId) {
        return checkOperation(graduationId, IS_STUDENT_GRADUATION_EXIST_BY_GRADUATION_ID);
    }

    @Override
    public boolean isStudentGraduationWaiting(final String graduationId) {
        return checkOperation(graduationId, IS_STUDENT_GRADUATION_WAITING_BY_GRADUATION_ID);
    }

    @Override
    public boolean isStudentGraduationApproved(final String graduationId) {
        return checkOperation(graduationId, IS_STUDENT_GRADUATION_APPROVED_BY_GRADUATION_ID);
    }

    @Override
    public boolean isStudentGraduationRejected(final String graduationId) {
        return checkOperation(graduationId, IS_STUDENT_GRADUATION_REJECTED_BY_GRADUATION_ID);
    }

    @Override
    public boolean isStudentGraduationConfirmed(String graduationId) {
        return checkOperation(graduationId, IS_STUDENT_GRADUATION_CONFIRMED_BY_GRADUATION_ID);
    }

    @Override
    public boolean isStudentGraduationUnconfirmed(String graduationId) {
        return checkOperation(graduationId, IS_STUDENT_GRADUATION_UNCONFIRMED_BY_GRADUATION_ID);
    }

    @Override
    public String getGraduationId(final Long studentId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_GRADUATION_ID_BY_STUDENT_ID)) {

            final String graduationId = query
                    .addParameter(STUDENT_ID.getModelName(), studentId)
                    .executeScalar(String.class);

            if (graduationId != null) {
                info.foundById(studentId);
                return graduationId;
            } else {
                warn.notFoundById(studentId);
                return null;
            }
        } catch (Exception exception) {
            error.errorWhenGettingById(studentId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public Long getStudentId(final String graduationId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_STUDENT_ID_BY_GRADUATION_ID)) {

            final Long studentId = query
                    .addParameter(GRADUATION_ID.getModelName(), graduationId)
                    .executeScalar(Long.class);

            if (studentId != null) {
                info.foundById(graduationId);
                return studentId;
            } else {
                warn.notFoundById(graduationId);
                return null;
            }
        } catch (Exception exception) {
            error.errorWhenGettingById(graduationId);
            throw new SisDatabaseException(exception);
        }
    }

    private boolean checkOperation(final String graduationId, final String sql) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(sql)) {

            final boolean operationExist = query
                    .addParameter(GRADUATION_ID.getModelName(), graduationId)
                    .executeAndFetchFirst(Boolean.class);

            if (operationExist) {
                info.foundById(graduationId);
                return true;
            } else {
                warn.notFoundById(graduationId);
                return false;
            }
        } catch (Exception exception) {
            error.errorWhenGettingById(graduationId);
            throw new SisDatabaseException(exception);
        }
    }

    /**
     * ('status') ORDER BY orderByFieldName ASC; || If Status == ALL -> ('status1', 'status2', ...) ORDER BY orderByFieldName ASC;
     */
    private static String querySearchByStatus(final String orderByFieldName, final String status) {
        final String orderByIdAsc = " ORDER BY " + orderByFieldName + " DESC ";
        final StringJoiner statusForQuery = new StringJoiner(",", "(", ")");
        if (status.equalsIgnoreCase(StudentGraduationStatus.ALL.toString())) {
            Arrays.stream(StudentGraduationStatus.values())
                    .filter(s -> s != StudentGraduationStatus.ALL)
                    .forEach(s -> statusForQuery.add("'" + s + "'"));
            return statusForQuery + orderByIdAsc;
        } else
            return statusForQuery.add("'" + status + "'") + orderByIdAsc;
    }
}
