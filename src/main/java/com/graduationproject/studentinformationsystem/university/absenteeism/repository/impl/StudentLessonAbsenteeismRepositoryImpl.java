package com.graduationproject.studentinformationsystem.university.absenteeism.repository.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisDatabaseException;
import com.graduationproject.studentinformationsystem.common.util.log.SisErrorLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisInfoLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisWarnLogMessageUtil;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.entity.StudentLessonAbsenteeismSaveEntity;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.entity.StudentLessonAbsenteeismUpdateEntity;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.entity.StudentLessonsAbsenteeismEntity;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.entity.StudentsLessonAbsenteeismEntity;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.enums.StudentLessonAbsenteeismStatus;
import com.graduationproject.studentinformationsystem.university.absenteeism.repository.StudentLessonAbsenteeismRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

import static com.graduationproject.studentinformationsystem.university.absenteeism.model.mapping.StudentLessonAbsenteeismMapping.*;
import static com.graduationproject.studentinformationsystem.university.absenteeism.repository.impl.scripts.StudentLessonAbsenteeismSqlScripts.*;
import static com.graduationproject.studentinformationsystem.university.note.model.mapping.StudentLessonNoteMapping.ID;

@Repository
@RequiredArgsConstructor
public class StudentLessonAbsenteeismRepositoryImpl implements StudentLessonAbsenteeismRepository {

    private static final String STUDENT_LESSON_ABSENTEEISM = "Student Lesson Absenteeism";
    private final SisInfoLogMessageUtil info = SisInfoLogMessageUtil.builder().apiName(STUDENT_LESSON_ABSENTEEISM).build();
    private final SisWarnLogMessageUtil warn = SisWarnLogMessageUtil.builder().apiName(STUDENT_LESSON_ABSENTEEISM).build();
    private final SisErrorLogMessageUtil error = SisErrorLogMessageUtil.builder().apiName(STUDENT_LESSON_ABSENTEEISM).build();

    private final Sql2o sql2o;

    @Override
    public List<StudentLessonsAbsenteeismEntity> getAllStudentLessonsAbsenteeismByStudentId(final Long studentId) {

        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_ALL_STUDENT_LESSONS_ABSENTEEISM_BY_STUDENT_ID)) {

            final List<StudentLessonsAbsenteeismEntity> absenteeismEntities = query
                    .addParameter(STUDENT_ID.getModelName(), studentId)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetch(StudentLessonsAbsenteeismEntity.class);

            if (!absenteeismEntities.isEmpty()) {
                info.foundAllByStudentId(studentId);
            } else {
                warn.notFoundAllByStudentId(studentId);
            }
            return absenteeismEntities;
        } catch (Exception exception) {
            error.errorWhenGettingAllByStudentId(studentId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public List<StudentsLessonAbsenteeismEntity> getAllStudentsLessonAbsenteeismByLessonId(final Long lessonId,
                                                                                           final Integer week) {

        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_ALL_STUDENTS_LESSON_ABSENTEEISM_BY_LESSON_ID_AND_WEEK)) {

            final List<StudentsLessonAbsenteeismEntity> absenteeismEntities = query
                    .addParameter(LESSON_ID.getModelName(), lessonId)
                    .addParameter(WEEK.getModelName(), week)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetch(StudentsLessonAbsenteeismEntity.class);

            if (!absenteeismEntities.isEmpty()) {
                info.foundAllByLessonId(lessonId);
            } else {
                warn.notFoundAllByLessonId(lessonId);
            }
            return absenteeismEntities;
        } catch (Exception exception) {
            error.errorWhenGettingAllByLessonId(lessonId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public StudentsLessonAbsenteeismEntity getStudentLessonAbsenteeismById(final String id) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_STUDENT_LESSON_ABSENTEEISM_BY_ID)) {

            final StudentsLessonAbsenteeismEntity absenteeismEntity = query
                    .addParameter(ID.getModelName(), id)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetchFirst(StudentsLessonAbsenteeismEntity.class);

            if (absenteeismEntity != null) {
                info.foundById(id);
            } else {
                warn.notFoundById(id);
            }
            return absenteeismEntity;
        } catch (Exception exception) {
            error.errorWhenGettingById(id);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void saveStudentLessonAbsenteeism(final StudentLessonAbsenteeismSaveEntity saveEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(SAVE_STUDENT_LESSON_ABSENTEEISM)) {

            query.addParameter(ID.getModelName(), saveEntity.getId())
                    .addParameter(TEACHER_ID.getModelName(), saveEntity.getTeacherId())
                    .addParameter(STUDENT_ID.getModelName(), saveEntity.getStudentId())
                    .addParameter(LESSON_ID.getModelName(), saveEntity.getLessonId())
                    .addParameter(WEEK.getModelName(), saveEntity.getWeek())
                    .addParameter(THEORETICAL_HOURS_STATE.getModelName(), saveEntity.getTheoreticalHoursState())
                    .addParameter(MAX_THEORETICAL_HOURS.getModelName(), saveEntity.getMaxTheoreticalHours())
                    .addParameter(PRACTICE_HOURS_STATE.getModelName(), saveEntity.getPracticeHoursState())
                    .addParameter(MAX_PRACTICE_HOURS.getModelName(), saveEntity.getMaxPracticeHours())
                    .addParameter(STATUS.getModelName(), saveEntity.getStatus())
                    .addParameter(CREATED_DATE.getModelName(), saveEntity.getCreatedDate())
                    .addParameter(CREATED_USER_ID.getModelName(), saveEntity.getCreatedUserId())
                    .executeUpdate();

            info.savedById(saveEntity.getId());
        } catch (Exception exception) {
            error.errorWhenSaving();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updateStudentLessonAbsenteeism(final StudentLessonAbsenteeismUpdateEntity updateEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(UPDATE_STUDENT_LESSON_ABSENTEEISM)) {

            query.addParameter(ID.getModelName(), updateEntity.getId())
                    .addParameter(THEORETICAL_HOURS_STATE.getModelName(), updateEntity.getTheoreticalHoursState())
                    .addParameter(THEORETICAL_HOURS.getModelName(), updateEntity.getTheoreticalHours())
                    .addParameter(PRACTICE_HOURS_STATE.getModelName(), updateEntity.getPracticeHoursState())
                    .addParameter(PRACTICE_HOURS.getModelName(), updateEntity.getPracticeHours())
                    .addParameter(STATUS.getModelName(), updateEntity.getStatus())
                    .addParameter(MODIFIED_DATE.getModelName(), updateEntity.getModifiedDate())
                    .addParameter(MODIFIED_USER_ID.getModelName(), updateEntity.getModifiedUserId())
                    .executeUpdate();

            info.updated();
        } catch (Exception exception) {
            error.errorWhenUpdating();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updateStudentLessonAbsenteeismStatus(String id, StudentLessonAbsenteeismStatus status) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(UPDATE_STUDENT_LESSON_ABSENTEEISM_STATUS)) {

            query.addParameter(ID.getModelName(), id)
                    .addParameter(STATUS.getModelName(), status)
                    .executeUpdate();

            info.updated();
        } catch (Exception exception) {
            error.errorWhenUpdating();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isStudentLessonAbsenteeismExist(final String id) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_STUDENT_LESSON_ABSENTEEISM_EXIST_BY_ID)) {

            final boolean isStudentLessonAbsenteeismExist = query.addParameter(ID.getModelName(), id)
                    .executeScalar(Boolean.class);

            if (isStudentLessonAbsenteeismExist) {
                info.foundById(id);
                return true;
            } else {
                warn.notFoundById(id);
                return false;
            }
        } catch (Exception exception) {
            error.errorWhenGettingById(id);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public Long getStudentIdByAbsenteeismId(final String id) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_STUDENT_ID_BY_ID)) {

            final Long studentId = query
                    .addParameter(ID.getModelName(), id)
                    .executeScalar(Long.class);

            if (studentId != null) {
                info.foundById(id);
            } else {
                warn.notFoundById(id);
            }
            return studentId;
        } catch (Exception exception) {
            error.errorWhenGettingById(id);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public Long getLessonIdByAbsenteeismId(final String id) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_LESSON_ID_BY_ID)) {

            final Long lessonId = query
                    .addParameter(ID.getModelName(), id)
                    .executeScalar(Long.class);

            if (lessonId != null) {
                info.foundById(id);
            } else {
                warn.notFoundById(id);
            }
            return lessonId;
        } catch (Exception exception) {
            error.errorWhenGettingById(id);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isStudentLessonAbsenteeismExist(final Long studentId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_STUDENT_LESSON_ABSENTEEISM_EXIST_BY_STUDENT_ID)) {

            final boolean isStudentLessonAbsenteeismExist = query
                    .addParameter(STUDENT_ID.getModelName(), studentId)
                    .executeScalar(Boolean.class);

            if (isStudentLessonAbsenteeismExist) {
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
    public Integer calculateTotalTheoreticalHours(final String id) {
        return calculateTotalHours(id, CALCULATE_TOTAL_THEORETICAL_HOURS_BY_STUDENT_ID_AND_LESSON_ID);
    }

    @Override
    public Integer calculateTotalPracticeHours(final String id) {
        return calculateTotalHours(id, CALCULATE_TOTAL_PRACTICE_HOURS_BY_STUDENT_ID_AND_LESSON_ID);
    }

    private Integer calculateTotalHours(final String id, final String sql) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(sql)) {

            final Integer totalHours = query
                    .addParameter(ID.getModelName(), id)
                    .executeScalar(Integer.class);

            if (totalHours != null) {
                info.foundById(id);
            } else {
                warn.notFoundById(id);
            }
            return totalHours;
        } catch (Exception exception) {
            error.errorWhenGettingById(id);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public Integer getMaxTheoreticalHoursById(final String id) {
        return getMaxHours(id, GET_MAX_THEORETICAL_HOURS_BY_STUDENT_ID_AND_LESSON_ID);
    }

    @Override
    public Integer getMaxPracticeHoursById(final String id) {
        return getMaxHours(id, GET_MAX_PRACTICE_HOURS_BY_STUDENT_ID_AND_LESSON_ID);
    }

    private Integer getMaxHours(final String id, final String sql) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(sql)) {

            final Integer maxHours = query
                    .addParameter(ID.getModelName(), id)
                    .executeScalar(Integer.class);

            if (maxHours != null) {
                info.foundById(id);
            } else {
                warn.notFoundById(id);
            }
            return maxHours;
        } catch (Exception exception) {
            error.errorWhenGettingById(id);
            throw new SisDatabaseException(exception);
        }
    }
}
