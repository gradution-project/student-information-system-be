package com.graduationproject.studentinformationsystem.university.lesson.teacher.repository.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisDatabaseException;
import com.graduationproject.studentinformationsystem.common.util.log.SisErrorLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisInfoLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisWarnLogMessageUtil;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.entity.TeacherDeleteLessonEntity;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.entity.TeacherLessonEntity;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.entity.TeacherSaveLessonEntity;
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
        try (Connection con = sql2o.open(); Query query = con.createQuery(GET_ALL_TEACHERS_LESSONS)) {

            List<TeacherLessonEntity> entities = query
                    .setColumnMappings(TeacherLessonMapping.COLUMN_MAPPINGS)
                    .executeAndFetch(TeacherLessonEntity.class);

            info.foundAll();
            return entities;
        } catch (Exception exception) {
            error.errorWhenGettingAll();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public List<TeacherLessonEntity> getTeacherLessonsByTeacherId(Long teacherId) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(GET_TEACHER_ALL_LESSONS)) {

            List<TeacherLessonEntity> entities = query
                    .addParameter(TEACHER_ID.getModelName(), teacherId)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetch(TeacherLessonEntity.class);

            info.foundAll();
            return entities;
        } catch (Exception exception) {
            error.errorWhenGettingAll();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public TeacherLessonEntity getTeacherLessonByTeacherIdAndLessonId(Long teacherId, Long lessonId) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(GET_TEACHER_LESSON)) {

            TeacherLessonEntity entity = query
                    .addParameter(TEACHER_ID.getModelName(), teacherId)
                    .addParameter(LESSON_ID.getModelName(), lessonId)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetchFirst(TeacherLessonEntity.class);

            info.foundById(lessonId);
            return entity;
        } catch (Exception exception) {
            error.errorWhenGetting();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void saveTeacherLesson(TeacherSaveLessonEntity saveLessonEntity) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(SAVE_TEACHER_LESSON)) {

            query.addParameter(TEACHER_ID.getModelName(), saveLessonEntity.getTeacherId())
                    .addParameter(LESSON_ID.getModelName(), saveLessonEntity.getLessonId())
                    .addParameter(CREATED_DATE.getModelName(), saveLessonEntity.getCreatedDate())
                    .addParameter(CREATED_USER_ID.getModelName(), saveLessonEntity.getCreatedUserId())
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeUpdate();

            info.savedById(saveLessonEntity.getLessonId());
        } catch (Exception exception) {
            error.errorWhenSaving();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void deleteTeacherLesson(TeacherDeleteLessonEntity deleteLessonEntity) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(DELETE_TEACHER_LESSON)) {

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
    public boolean isTeacherLessonExist(Long teacherId, Long lessonId) {
        try (Connection con = sql2o.open(); Query query = con.createQuery(IS_TEACHER_LESSON_EXIST)) {

            boolean isLessonExist = query
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
}
