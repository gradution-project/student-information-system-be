package com.graduationproject.studentinformationsystem.university.lesson.teacher.repository.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisDatabaseException;
import com.graduationproject.studentinformationsystem.common.util.log.SisErrorLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisInfoLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisWarnLogMessageUtil;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.entity.TeacherLessonDeleteEntity;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.entity.TeacherLessonEntity;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.entity.TeacherLessonSaveEntity;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.mapping.TeacherLessonMapping;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.repository.TeacherLessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

import static com.graduationproject.studentinformationsystem.university.lesson.teacher.model.mapping.TeacherLessonMapping.*;
import static com.graduationproject.studentinformationsystem.university.lesson.teacher.repository.impl.scripts.TeacherLessonSqlScripts.*;

@Repository
@RequiredArgsConstructor
public class TeacherLessonRepositoryImpl implements TeacherLessonRepository {

    private static final String TEACHER_LESSON = "Teacher Lesson";
    private final SisInfoLogMessageUtil info = SisInfoLogMessageUtil.builder().apiName(TEACHER_LESSON).build();
    private final SisWarnLogMessageUtil warn = SisWarnLogMessageUtil.builder().apiName(TEACHER_LESSON).build();
    private final SisErrorLogMessageUtil error = SisErrorLogMessageUtil.builder().apiName(TEACHER_LESSON).build();

    private final Sql2o sql2o;

    @Override
    public List<TeacherLessonEntity> getAllTeachersLessons() {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_ALL_TEACHERS_LESSONS)) {

            final List<TeacherLessonEntity> teacherLessonEntities = query
                    .setColumnMappings(TeacherLessonMapping.COLUMN_MAPPINGS)
                    .executeAndFetch(TeacherLessonEntity.class);

            info.foundAll();
            return teacherLessonEntities;
        } catch (Exception exception) {
            error.errorWhenGettingAll();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public List<TeacherLessonEntity> getTeacherLessonsByTeacherId(final Long teacherId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_TEACHER_ALL_LESSONS)) {

            final List<TeacherLessonEntity> teacherLessonEntities = query
                    .addParameter(TEACHER_ID.getModelName(), teacherId)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetch(TeacherLessonEntity.class);

            info.foundAll();
            return teacherLessonEntities;
        } catch (Exception exception) {
            error.errorWhenGettingAll();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public TeacherLessonEntity getTeacherLessonByTeacherIdAndLessonId(final Long teacherId, final Long lessonId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_TEACHER_LESSON)) {

            final TeacherLessonEntity teacherLessonEntity = query
                    .addParameter(TEACHER_ID.getModelName(), teacherId)
                    .addParameter(LESSON_ID.getModelName(), lessonId)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetchFirst(TeacherLessonEntity.class);

            info.foundById(lessonId);
            return teacherLessonEntity;
        } catch (Exception exception) {
            error.errorWhenGetting();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void saveTeacherLesson(final TeacherLessonSaveEntity saveEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(SAVE_TEACHER_LESSON)) {

            query.addParameter(TEACHER_ID.getModelName(), saveEntity.getTeacherId())
                    .addParameter(LESSON_ID.getModelName(), saveEntity.getLessonId())
                    .addParameter(CREATED_DATE.getModelName(), saveEntity.getCreatedDate())
                    .addParameter(CREATED_USER_ID.getModelName(), saveEntity.getCreatedUserId())
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeUpdate();

            info.savedById(saveEntity.getLessonId());
        } catch (Exception exception) {
            error.errorWhenSaving();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void deleteTeacherLesson(final TeacherLessonDeleteEntity deleteLessonEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(DELETE_TEACHER_LESSON)) {

            query.addParameter(TEACHER_ID.getModelName(), deleteLessonEntity.getTeacherId())
                    .addParameter(LESSON_ID.getModelName(), deleteLessonEntity.getLessonId())
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeUpdate();

            info.deleted();
        } catch (Exception exception) {
            error.errorWhenSaving();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isTeacherLessonExist(final Long teacherId, final Long lessonId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_TEACHER_LESSON_EXIST)) {

            final boolean isLessonExist = query
                    .addParameter(TEACHER_ID.getModelName(), teacherId)
                    .addParameter(LESSON_ID.getModelName(), lessonId)
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
    public Long getTeacherIdByLessonId(Long lessonId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_TEACHER_ID_BY_LESSON_ID)) {

            return query.addParameter(LESSON_ID.getModelName(), lessonId)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeScalar(Long.class);

        } catch (Exception exception) {
            error.errorWhenGetting();
            throw new SisDatabaseException(exception);
        }
    }
}
