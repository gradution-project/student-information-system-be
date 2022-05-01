package com.graduationproject.studentinformationsystem.university.lesson.student.common.repository.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisDatabaseException;
import com.graduationproject.studentinformationsystem.common.util.log.SisErrorLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisInfoLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisWarnLogMessageUtil;
import com.graduationproject.studentinformationsystem.university.lesson.student.common.model.entity.StudentLessonEntity;
import com.graduationproject.studentinformationsystem.university.lesson.student.common.model.entity.StudentLessonSaveEntity;
import com.graduationproject.studentinformationsystem.university.lesson.student.common.repository.StudentLessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

import static com.graduationproject.studentinformationsystem.university.lesson.student.common.model.mapping.StudentLessonMapping.*;
import static com.graduationproject.studentinformationsystem.university.lesson.student.common.repository.impl.scripts.StudentLessonSqlScripts.*;

@Repository
@RequiredArgsConstructor
public class StudentLessonRepositoryImpl implements StudentLessonRepository {

    private static final String STUDENT_LESSON = "Student Lesson";
    private final SisInfoLogMessageUtil info = SisInfoLogMessageUtil.builder().apiName(STUDENT_LESSON).build();
    private final SisWarnLogMessageUtil warn = SisWarnLogMessageUtil.builder().apiName(STUDENT_LESSON).build();
    private final SisErrorLogMessageUtil error = SisErrorLogMessageUtil.builder().apiName(STUDENT_LESSON).build();

    private final Sql2o sql2o;

    @Override
    public List<StudentLessonEntity> getStudentLessonsByStudentId(final Long studentId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_STUDENT_ALL_LESSONS)) {

            final List<StudentLessonEntity> studentLessonEntities = query
                    .addParameter(STUDENT_ID.getModelName(), studentId)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetch(StudentLessonEntity.class);

            info.foundAll();
            return studentLessonEntities;
        } catch (Exception exception) {
            error.errorWhenGettingAll();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void saveStudentLesson(final StudentLessonSaveEntity saveEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(SAVE_STUDENT_LESSON)) {

            query.addParameter(STUDENT_ID.getModelName(), saveEntity.getStudentId())
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
    public void deleteStudentLessons(final Long studentId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(DELETE_STUDENT_LESSONS)) {

            query.addParameter(STUDENT_ID.getModelName(), studentId)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeUpdate();

            info.deleted();
        } catch (Exception exception) {
            error.errorWhenSaving();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isStudentLessonsExist(final Long studentId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_STUDENT_LESSONS_EXIST)) {

            final boolean isLessonsExist = query
                    .addParameter(STUDENT_ID.getModelName(), studentId)
                    .executeAndFetchFirst(Boolean.class);

            if (isLessonsExist) {
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
    public boolean isStudentLessonExist(final Long studentId, final Long lessonId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_STUDENT_LESSON_EXIST)) {

            final boolean isLessonExist = query
                    .addParameter(STUDENT_ID.getModelName(), studentId)
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
