package com.graduationproject.studentinformationsystem.university.note.repository.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisDatabaseException;
import com.graduationproject.studentinformationsystem.common.util.log.SisErrorLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisInfoLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisWarnLogMessageUtil;
import com.graduationproject.studentinformationsystem.university.note.model.entity.*;
import com.graduationproject.studentinformationsystem.university.note.repository.StudentLessonNoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

import static com.graduationproject.studentinformationsystem.university.note.model.mapping.StudentLessonNoteMapping.*;
import static com.graduationproject.studentinformationsystem.university.note.repository.impl.scripts.StudentLessonNoteSqlScripts.*;

@Repository
@RequiredArgsConstructor
public class StudentLessonNoteRepositoryImpl implements StudentLessonNoteRepository {

    private static final String STUDENT_LESSON_NOTE = "Student Lesson Note";
    private final SisInfoLogMessageUtil info = SisInfoLogMessageUtil.builder().apiName(STUDENT_LESSON_NOTE).build();
    private final SisWarnLogMessageUtil warn = SisWarnLogMessageUtil.builder().apiName(STUDENT_LESSON_NOTE).build();
    private final SisErrorLogMessageUtil error = SisErrorLogMessageUtil.builder().apiName(STUDENT_LESSON_NOTE).build();

    private final Sql2o sql2o;

    @Override
    public List<StudentLessonNoteEntity> getAllStudentsLessonsNotesByLessonId(final Long lessonId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_ALL_STUDENTS_LESSON_NOTES_BY_LESSON_ID)) {

            final List<StudentLessonNoteEntity> noteEntities = query
                    .addParameter(LESSON_ID.getModelName(), lessonId)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetch(StudentLessonNoteEntity.class);

            if (!noteEntities.isEmpty()) {
                info.foundAllByLessonId(lessonId);
            } else {
                warn.notFoundAllByLessonId(lessonId);
            }
            return noteEntities;
        } catch (Exception exception) {
            error.errorWhenGettingAllByLessonId(lessonId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public List<StudentLessonNoteEntity> getAllStudentLessonsNotesByStudentId(final Long studentId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_ALL_STUDENT_LESSONS_NOTES_BY_STUDENT_ID)) {

            final List<StudentLessonNoteEntity> noteEntities = query
                    .addParameter(STUDENT_ID.getModelName(), studentId)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetch(StudentLessonNoteEntity.class);

            if (!noteEntities.isEmpty()) {
                info.foundAllByStudentId(studentId);
            } else {
                warn.notFoundAllByStudentId(studentId);
            }
            return noteEntities;
        } catch (Exception exception) {
            error.errorWhenGettingAllByStudentId(studentId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public StudentLessonNoteEntity getStudentLessonNotesById(final String id) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_STUDENT_LESSON_NOTES_BY_ID)) {

            final StudentLessonNoteEntity noteEntity = query
                    .addParameter(ID.getModelName(), id)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetchFirst(StudentLessonNoteEntity.class);

            if (noteEntity != null) {
                info.foundById(id);
            } else {
                warn.notFoundById(id);
            }
            return noteEntity;
        } catch (Exception exception) {
            error.errorWhenGettingById(id);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void saveStudentLessonNote(final StudentLessonNoteSaveEntity saveEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(SAVE_STUDENT_LESSON_NOTE)) {

            query.addParameter(ID.getModelName(), saveEntity.getId())
                    .addParameter(TEACHER_ID.getModelName(), saveEntity.getTeacherId())
                    .addParameter(STUDENT_ID.getModelName(), saveEntity.getStudentId())
                    .addParameter(LESSON_ID.getModelName(), saveEntity.getLessonId())
                    .addParameter(STATUS.getModelName(), saveEntity.getStatus())
                    .addParameter(CREATED_DATE.getModelName(), saveEntity.getCreatedDate())
                    .addParameter(CREATED_USER_ID.getModelName(), saveEntity.getCreatedUserId())
                    .executeUpdate();

        } catch (Exception exception) {
            error.errorWhenSaving();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updateStudentLessonMidtermNote(final StudentLessonMidtermNoteUpdateEntity updateEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(UPDATE_STUDENT_LESSON_MIDTERM_NOTE)) {

            query.addParameter(ID.getModelName(), updateEntity.getId())
                    .addParameter(MIDTERM_NOTE.getModelName(), updateEntity.getMidtermNote())
                    .addParameter(MIDTERM_NOTE_STATE.getModelName(), updateEntity.getMidtermNoteState())
                    .addParameter(MODIFIED_DATE.getModelName(), updateEntity.getModifiedDate())
                    .addParameter(MODIFIED_USER_ID.getModelName(), updateEntity.getModifiedUserId())
                    .executeUpdate();

        } catch (Exception exception) {
            error.errorWhenUpdating();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void confirmStudentLessonMidtermNote(final StudentLessonMidtermNoteConfirmEntity confirmEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(CONFIRM_STUDENT_LESSON_MIDTERM_NOTE)) {

            query.addParameter(ID.getModelName(), confirmEntity.getId())
                    .addParameter(MIDTERM_NOTE_STATE.getModelName(), confirmEntity.getNoteState())
                    .addParameter(MODIFIED_DATE.getModelName(), confirmEntity.getModifiedDate())
                    .addParameter(MODIFIED_USER_ID.getModelName(), confirmEntity.getModifiedUserId())
                    .executeUpdate();

            info.updated();
        } catch (Exception exception) {
            error.errorWhenUpdating();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updateStudentLessonFinalNote(final StudentLessonFinalNoteUpdateEntity updateEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(UPDATE_STUDENT_LESSON_FINAL_AND_MEAN_OF_NOTES)) {

            query.addParameter(ID.getModelName(), updateEntity.getId())
                    .addParameter(FINAL_NOTE.getModelName(), updateEntity.getFinalNote())
                    .addParameter(FINAL_NOTE_STATE.getModelName(), updateEntity.getFinalNoteState())
                    .addParameter(MEAN_OF_NOTE.getModelName(), updateEntity.getMeanOfNote())
                    .addParameter(STATUS.getModelName(), updateEntity.getStatus())
                    .addParameter(MODIFIED_DATE.getModelName(), updateEntity.getModifiedDate())
                    .addParameter(MODIFIED_USER_ID.getModelName(), updateEntity.getModifiedUserId())
                    .executeUpdate();

        } catch (Exception exception) {
            error.errorWhenUpdating();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void confirmStudentLessonFinalNote(final StudentLessonFinalNoteConfirmEntity confirmEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(CONFIRM_STUDENT_LESSON_FINAL_NOTE)) {

            query.addParameter(ID.getModelName(), confirmEntity.getId())
                    .addParameter(FINAL_NOTE_STATE.getModelName(), confirmEntity.getNoteState())
                    .addParameter(MODIFIED_DATE.getModelName(), confirmEntity.getModifiedDate())
                    .addParameter(MODIFIED_USER_ID.getModelName(), confirmEntity.getModifiedUserId())
                    .executeUpdate();

            info.updated();
        } catch (Exception exception) {
            error.errorWhenUpdating();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void updateStudentLessonResitNote(final StudentLessonResitNoteUpdateEntity updateEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(UPDATE_STUDENT_LESSON_RESIT_AND_MEAN_OF_NOTES)) {

            query.addParameter(ID.getModelName(), updateEntity.getId())
                    .addParameter(RESIT_NOTE.getModelName(), updateEntity.getResitNote())
                    .addParameter(RESIT_NOTE_STATE.getModelName(), updateEntity.getResitNoteState())
                    .addParameter(MEAN_OF_NOTE.getModelName(), updateEntity.getMeanOfNote())
                    .addParameter(STATUS.getModelName(), updateEntity.getStatus())
                    .addParameter(MODIFIED_DATE.getModelName(), updateEntity.getModifiedDate())
                    .addParameter(MODIFIED_USER_ID.getModelName(), updateEntity.getModifiedUserId())
                    .executeUpdate();

        } catch (Exception exception) {
            error.errorWhenUpdating();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void confirmStudentLessonResitNote(final StudentLessonResitNoteConfirmEntity confirmEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(CONFIRM_STUDENT_LESSON_RESIT_NOTE)) {

            query.addParameter(ID.getModelName(), confirmEntity.getId())
                    .addParameter(RESIT_NOTE_STATE.getModelName(), confirmEntity.getNoteState())
                    .addParameter(MODIFIED_DATE.getModelName(), confirmEntity.getModifiedDate())
                    .addParameter(MODIFIED_USER_ID.getModelName(), confirmEntity.getModifiedUserId())
                    .executeUpdate();

            info.updated();
        } catch (Exception exception) {
            error.errorWhenUpdating();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public Double getMidtermNoteById(final String id) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_MIDTERM_NOTE_BY_ID)) {

            final Double midtermNote = query
                    .addParameter(ID.getModelName(), id)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeScalar(Double.class);

            if (midtermNote != null) {
                info.foundById(id);
            } else {
                warn.notFoundById(id);
            }
            return midtermNote;
        } catch (Exception exception) {
            error.errorWhenGettingById(id);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isStudentLessonNotesExist(final String id) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_STUDENT_LESSON_NOTES_EXIST_BY_ID)) {

            final boolean isStudentLessonNotesExist = query.addParameter(ID.getModelName(), id)
                    .executeAndFetchFirst(Boolean.class);

            if (isStudentLessonNotesExist) {
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
    public boolean isStudentLessonsNotesExist(final Long studentId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_STUDENT_LESSONS_NOTES_EXIST_BY_STUDENT_ID)) {

            final boolean isStudentLessonNotesExist = query.addParameter(STUDENT_ID.getModelName(), studentId)
                    .executeAndFetchFirst(Boolean.class);

            if (isStudentLessonNotesExist) {
                info.foundAllByLessonId(studentId);
                return true;
            } else {
                warn.notFoundAllByStudentId(studentId);
                return false;
            }
        } catch (Exception exception) {
            error.errorWhenGettingAllByStudentId(studentId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean hasTheStudentPassedAllLessons(final Long studentId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(HAS_THE_STUDENT_PASSED_ALL_LESSONS_BY_STUDENT_ID)) {

            final boolean hasTheStudentPassedAllLessons = query.addParameter(STUDENT_ID.getModelName(), studentId)
                    .executeAndFetchFirst(Boolean.class);

            if (hasTheStudentPassedAllLessons) {
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
}
