package com.graduationproject.studentinformationsystem.university.lesson.student.registration.repository.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisDatabaseException;
import com.graduationproject.studentinformationsystem.common.util.log.SisErrorLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisInfoLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisWarnLogMessageUtil;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.entity.StudentLessonRegistrationEntity;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.enums.StudentLessonRegistrationStatus;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.repository.StudentLessonRegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import static com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.mapping.StudentLessonRegistrationMapping.*;
import static com.graduationproject.studentinformationsystem.university.lesson.student.registration.repository.impl.scripts.StudentLessonRegistrationSqlScripts.*;

@Repository
@RequiredArgsConstructor
public class StudentLessonRegistrationRepositoryImpl implements StudentLessonRegistrationRepository {

    private static final String STUDENT_LESSON_REGISTRATION = "Student Lesson Registration";
    private final SisInfoLogMessageUtil info = SisInfoLogMessageUtil.builder().apiName(STUDENT_LESSON_REGISTRATION).build();
    private final SisWarnLogMessageUtil warn = SisWarnLogMessageUtil.builder().apiName(STUDENT_LESSON_REGISTRATION).build();
    private final SisErrorLogMessageUtil error = SisErrorLogMessageUtil.builder().apiName(STUDENT_LESSON_REGISTRATION).build();

    private final Sql2o sql2o;

    @Override
    public List<StudentLessonRegistrationEntity> getAllStudentLessonRegistrationsByStatus(final StudentLessonRegistrationStatus status) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_ALL_STUDENT_LESSON_REGISTRATIONS
                .concat(querySearchByStatus(CREATED_DATE.getColumnName(), status.toString())))) {

            final List<StudentLessonRegistrationEntity> registrationEntities = query
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetch(StudentLessonRegistrationEntity.class);

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
    public StudentLessonRegistrationEntity getStudentLessonRegistrationByRegistrationId(final String registrationId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_STUDENT_LESSON_REGISTRATION)) {

            final StudentLessonRegistrationEntity registrationEntity = query
                    .addParameter(REGISTRATION_ID.getModelName(), registrationId)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetchFirst(StudentLessonRegistrationEntity.class);

            info.foundById(registrationId);
            return registrationEntity;
        } catch (Exception exception) {
            error.errorWhenGettingById(registrationId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void saveStudentLessonRegistration(final StudentLessonRegistrationEntity registrationEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(SAVE_STUDENT_LESSON_REGISTRATION)) {

            query.addParameter(REGISTRATION_ID.getModelName(), registrationEntity.getRegistrationId())
                    .addParameter(STUDENT_ID.getModelName(), registrationEntity.getStudentId())
                    .addParameter(LESSONS_IDS.getModelName(), registrationEntity.getLessonsIds())
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
    public void updateStudentLessonRegistration(final StudentLessonRegistrationEntity registrationEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(UPDATE_STUDENT_LESSON_REGISTRATION)) {

            query.addParameter(REGISTRATION_ID.getModelName(), registrationEntity.getRegistrationId())
                    .addParameter(LESSONS_IDS.getModelName(), registrationEntity.getLessonsIds())
                    .addParameter(STATUS.getModelName(), registrationEntity.getStatus())
                    .addParameter(MODIFIED_DATE.getModelName(), registrationEntity.getModifiedDate())
                    .addParameter(MODIFIED_USER_ID.getModelName(), registrationEntity.getModifiedUserId())
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeUpdate();

            info.updated();
        } catch (Exception exception) {
            error.errorWhenUpdating();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updateStudentLessonRegistrationStatus(final StudentLessonRegistrationEntity registrationEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(UPDATE_STUDENT_LESSON_REGISTRATION_STATUS)) {

            query.addParameter(STATUS.getModelName(), registrationEntity.getStatus())
                    .addParameter(MODIFIED_DATE.getModelName(), registrationEntity.getModifiedDate())
                    .addParameter(MODIFIED_USER_ID.getModelName(), registrationEntity.getModifiedUserId())
                    .addParameter(REGISTRATION_ID.getModelName(), registrationEntity.getRegistrationId())
                    .executeUpdate();

            info.statusUpdated(registrationEntity.getStatus().toString());
        } catch (Exception exception) {
            error.errorWhenUpdatingStatus();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isStudentLessonRegistrationExist(final String registrationId) {
        return checkOperation(registrationId, IS_STUDENT_LESSON_REGISTRATION_EXIST_BY_REGISTRATION_ID);
    }

    @Override
    public boolean isStudentLessonRegistrationWaiting(final String registrationId) {
        return checkOperation(registrationId, IS_STUDENT_LESSON_REGISTRATION_WAITING_BY_REGISTRATION_ID);
    }

    @Override
    public boolean isStudentLessonRegistrationApproved(final String registrationId) {
        return checkOperation(registrationId, IS_STUDENT_LESSON_REGISTRATION_APPROVED_BY_REGISTRATION_ID);
    }

    @Override
    public boolean isStudentLessonRegistrationRejected(final String registrationId) {
        return checkOperation(registrationId, IS_STUDENT_LESSON_REGISTRATION_REJECTED_BY_REGISTRATION_ID);
    }

    @Override
    public String getRegistrationId(final Long studentId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_REGISTRATION_ID_BY_STUDENT_ID)) {

            final String registrationId = query
                    .addParameter(STUDENT_ID.getModelName(), studentId)
                    .executeScalar(String.class);

            if (registrationId != null) {
                info.foundById(studentId);
                return registrationId;
            } else {
                warn.notFoundById(studentId);
                return null;
            }
        } catch (Exception exception) {
            error.errorWhenGettingById(studentId);
            throw new SisDatabaseException(exception);
        }
    }

    private boolean checkOperation(final String registrationId, final String sql) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(sql)) {

            final boolean operationExist = query
                    .addParameter(REGISTRATION_ID.getModelName(), registrationId)
                    .executeAndFetchFirst(Boolean.class);

            if (operationExist) {
                info.foundById(registrationId);
                return true;
            } else {
                warn.notFoundById(registrationId);
                return false;
            }
        } catch (Exception exception) {
            error.errorWhenGettingById(registrationId);
            throw new SisDatabaseException(exception);
        }
    }

    /**
     * ('status') ORDER BY orderByFieldName ASC; || If Status == ALL -> ('status1', 'status2', ...) ORDER BY orderByFieldName ASC;
     */
    private static String querySearchByStatus(final String orderByFieldName, final String status) {
        final String orderByIdAsc = " ORDER BY " + orderByFieldName + " DESC ";
        final StringJoiner statusForQuery = new StringJoiner(",", "(", ")");
        if (status.equalsIgnoreCase(StudentLessonRegistrationStatus.ALL.toString())) {
            Arrays.stream(StudentLessonRegistrationStatus.values())
                    .filter(s -> s != StudentLessonRegistrationStatus.ALL)
                    .forEach(s -> statusForQuery.add("'" + s + "'"));
            return statusForQuery + orderByIdAsc;
        } else
            return statusForQuery.add("'" + status + "'") + orderByIdAsc;
    }
}
