package com.graduationproject.studentinformationsystem.university.lesson.common.repository.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisDatabaseException;
import com.graduationproject.studentinformationsystem.common.util.log.SisErrorLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisInfoLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisWarnLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.sql.SisSqlUtil;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.entity.LessonEntity;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.enums.LessonStatus;
import com.graduationproject.studentinformationsystem.university.lesson.common.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

import static com.graduationproject.studentinformationsystem.university.lesson.common.model.mapping.LessonMapping.*;
import static com.graduationproject.studentinformationsystem.university.lesson.common.repository.impl.scripts.LessonSqlScripts.*;

@Repository
@RequiredArgsConstructor
public class LessonRepositoryImpl implements LessonRepository {

    private static final String LESSON = "Lesson";
    private final SisInfoLogMessageUtil info = SisInfoLogMessageUtil.builder().apiName(LESSON).build();
    private final SisWarnLogMessageUtil warn = SisWarnLogMessageUtil.builder().apiName(LESSON).build();
    private final SisErrorLogMessageUtil error = SisErrorLogMessageUtil.builder().apiName(LESSON).build();

    private final Sql2o sql2o;

    public List<LessonEntity> getAllLessonsByStatus(final LessonStatus status) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_ALL_LESSONS_BY_STATUS
                .concat(SisSqlUtil.querySearchByStatus(LESSON_ID.getColumnName(), status.toString())))) {

            final List<LessonEntity> lessonEntities = query
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetch(LessonEntity.class);

            info.foundAllByStatus(status.toString());
            return lessonEntities;
        } catch (Exception exception) {
            error.errorWhenGettingAllByStatus(status.toString());
            throw new SisDatabaseException(exception);
        }
    }

    public LessonEntity getLessonById(final Long lessonId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_LESSON_BY_ID)) {

            final LessonEntity lessonEntity = query
                    .addParameter(LESSON_ID.getModelName(), lessonId.toString())
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetchFirst(LessonEntity.class);

            if (lessonEntity != null) {
                info.foundById(lessonId);
            } else {
                warn.notFoundById(lessonId);
            }
            return lessonEntity;
        } catch (Exception exception) {
            error.errorWhenGettingById(lessonId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void saveLesson(final LessonEntity lessonEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(SAVE_LESSON)) {

            query.addParameter(LESSON_ID.getModelName(), lessonEntity.getLessonId())
                    .addParameter(DEPARTMENT_ID.getModelName(), lessonEntity.getDepartmentId())
                    .addParameter(NAME.getModelName(), lessonEntity.getName())
                    .addParameter(STATUS.getModelName(), lessonEntity.getStatus())
                    .addParameter(SEMESTER.getModelName(), lessonEntity.getSemester())
                    .addParameter(CREDIT.getModelName(), lessonEntity.getCredit())
                    .addParameter(COMPULSORY_OR_ELECTIVE.getModelName(), lessonEntity.getCompulsoryOrElective())
                    .addParameter(CREATED_DATE.getModelName(), lessonEntity.getCreatedDate())
                    .addParameter(CREATED_USER_ID.getModelName(), lessonEntity.getCreatedUserId())
                    .executeUpdate();

            info.savedById(lessonEntity.getLessonId());
        } catch (Exception exception) {
            error.errorWhenSaving();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updateLesson(final LessonEntity lessonEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(UPDATE_LESSON)) {

            query.addParameter(LESSON_ID.getModelName(), lessonEntity.getLessonId())
                    .addParameter(DEPARTMENT_ID.getModelName(), lessonEntity.getDepartmentId())
                    .addParameter(NAME.getModelName(), lessonEntity.getName())
                    .addParameter(SEMESTER.getModelName(), lessonEntity.getSemester())
                    .addParameter(CREDIT.getModelName(), lessonEntity.getCredit())
                    .addParameter(COMPULSORY_OR_ELECTIVE.getModelName(), lessonEntity.getCompulsoryOrElective())
                    .addParameter(MODIFIED_DATE.getModelName(), lessonEntity.getModifiedDate())
                    .addParameter(MODIFIED_USER_ID.getModelName(), lessonEntity.getModifiedUserId())
                    .addParameter(LESSON_ID.getModelName(), lessonEntity.getLessonId())
                    .executeUpdate();

            info.updated();
        } catch (Exception exception) {
            error.errorWhenUpdating();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updateLessonStatus(final LessonEntity lessonEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(UPDATE_LESSON_STATUS)) {

            query.addParameter(STATUS.getModelName(), lessonEntity.getStatus())
                    .addParameter(MODIFIED_DATE.getModelName(), lessonEntity.getModifiedDate())
                    .addParameter(MODIFIED_USER_ID.getModelName(), lessonEntity.getModifiedUserId())
                    .addParameter(LESSON_ID.getModelName(), lessonEntity.getLessonId())
                    .executeUpdate();

            info.statusUpdated(lessonEntity.getStatus().toString());
        } catch (Exception exception) {
            error.errorWhenUpdatingStatus();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public List<Long> getAllLessonIdsByDepartmentId(final Long departmentId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_ALL_LESSON_IDS_BY_DEPARTMENT_ID)) {

            final List<Long> lessonIds = query.addParameter(DEPARTMENT_ID.getModelName(), departmentId)
                    .executeScalarList(Long.class);

            if (!lessonIds.isEmpty()) {
                info.foundAllIds();
            } else {
                warn.notFoundAllIds();
            }
            return lessonIds;
        } catch (Exception exception) {
            error.errorWhenGettingAllIds();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isLessonExist(final Long lessonId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_LESSON_EXIST_BY_ID)) {

            final boolean isLessonExist = query.addParameter(LESSON_ID.getModelName(), lessonId)
                    .executeAndFetchFirst(Boolean.class);

            if (isLessonExist) {
                info.foundById(lessonId);
                return true;
            } else {
                warn.notFoundById(lessonId);
                return false;
            }
        } catch (Exception exception) {
            error.errorWhenGettingById(lessonId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isLessonDeleted(final Long lessonId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_LESSON_DELETED_BY_ID)) {

            final boolean isLessonExist = query.addParameter(LESSON_ID.getModelName(), lessonId)
                    .executeAndFetchFirst(Boolean.class);

            if (isLessonExist) {
                info.foundByIdAndStatus(lessonId, LessonStatus.DELETED.toString());
                return true;
            } else {
                warn.notFoundByIdAndStatus(lessonId, LessonStatus.DELETED.toString());
                return false;
            }
        } catch (Exception exception) {
            error.errorWhenGettingById(lessonId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isLessonPassive(final Long lessonId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_LESSON_PASSIVE_BY_ID)) {

            final boolean isLessonExist = query.addParameter(LESSON_ID.getModelName(), lessonId)
                    .executeAndFetchFirst(Boolean.class);

            if (isLessonExist) {
                info.foundByIdAndStatus(lessonId, LessonStatus.PASSIVE.toString());
                return true;
            } else {
                warn.notFoundByIdAndStatus(lessonId, LessonStatus.PASSIVE.toString());
                return false;
            }
        } catch (Exception exception) {
            error.errorWhenGettingById(lessonId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isLessonActive(final Long lessonId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_LESSON_ACTIVE_BY_ID)) {

            final boolean isLessonExist = query.addParameter(LESSON_ID.getModelName(), lessonId)
                    .executeAndFetchFirst(Boolean.class);

            if (isLessonExist) {
                info.foundByIdAndStatus(lessonId, LessonStatus.ACTIVE.toString());
                return true;
            } else {
                warn.notFoundByIdAndStatus(lessonId, LessonStatus.ACTIVE.toString());
                return false;
            }
        } catch (Exception exception) {
            error.errorWhenGettingById(lessonId);
            throw new SisDatabaseException(exception);
        }
    }
}
