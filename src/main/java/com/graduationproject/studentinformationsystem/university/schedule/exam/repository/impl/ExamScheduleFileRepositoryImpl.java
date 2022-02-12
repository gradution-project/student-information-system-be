package com.graduationproject.studentinformationsystem.university.schedule.exam.repository.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisDatabaseException;
import com.graduationproject.studentinformationsystem.common.util.log.SisErrorLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisInfoLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisWarnLogMessageUtil;
import com.graduationproject.studentinformationsystem.university.schedule.exam.model.entity.ExamScheduleFileEntity;
import com.graduationproject.studentinformationsystem.university.schedule.exam.repository.ExamScheduleFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

import static com.graduationproject.studentinformationsystem.university.schedule.exam.model.mapping.ExamScheduleFileMapping.*;
import static com.graduationproject.studentinformationsystem.university.schedule.exam.repository.impl.scripts.ExamScheduleFileSqlScripts.*;

@Repository
@RequiredArgsConstructor
public class ExamScheduleFileRepositoryImpl implements ExamScheduleFileRepository {

    private static final String EXAM_SCHEDULE_FILE = "Exam Schedule File";
    private final SisInfoLogMessageUtil info = SisInfoLogMessageUtil.builder().apiName(EXAM_SCHEDULE_FILE).build();
    private final SisWarnLogMessageUtil warn = SisWarnLogMessageUtil.builder().apiName(EXAM_SCHEDULE_FILE).build();
    private final SisErrorLogMessageUtil error = SisErrorLogMessageUtil.builder().apiName(EXAM_SCHEDULE_FILE).build();

    private final Sql2o sql2o;

    @Override
    public List<ExamScheduleFileEntity> getExamScheduleFilesByFacultyId(Long facultyId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_EXAM_SCHEDULE_FILES_BY_FACULTY_ID)) {

            final List<ExamScheduleFileEntity> scheduleFileEntities = query
                    .addParameter(FACULTY_ID.getModelName(), facultyId)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetch(ExamScheduleFileEntity.class);

            info.foundByFacultyId(facultyId);
            return scheduleFileEntities;
        } catch (Exception exception) {
            error.errorWhenGettingByFacultyId(facultyId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public ExamScheduleFileEntity getExamScheduleFileById(String fileId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_EXAM_SCHEDULE_FILE_BY_ID)) {

            final ExamScheduleFileEntity scheduleFileEntity = query
                    .addParameter(FILE_ID.getModelName(), fileId)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetchFirst(ExamScheduleFileEntity.class);

            info.foundById(fileId);
            return scheduleFileEntity;
        } catch (Exception exception) {
            error.errorWhenGettingById(fileId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public ExamScheduleFileEntity getExamScheduleFileByDepartmentId(final Long departmentId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_EXAM_SCHEDULE_FILE_BY_DEPARTMENT_ID)) {

            final ExamScheduleFileEntity scheduleFileEntity = query
                    .addParameter(DEPARTMENT_ID.getModelName(), departmentId)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetchFirst(ExamScheduleFileEntity.class);

            info.foundByDepartmentId(departmentId);
            return scheduleFileEntity;
        } catch (Exception exception) {
            error.errorWhenGettingByDepartmentId(departmentId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void saveExamScheduleFile(final ExamScheduleFileEntity scheduleFileEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(SAVE_EXAM_SCHEDULE_FILE)) {

            query
                    .addParameter(FILE_ID.getModelName(), scheduleFileEntity.getFileId())
                    .addParameter(FACULTY_ID.getModelName(), scheduleFileEntity.getFacultyId())
                    .addParameter(DEPARTMENT_ID.getModelName(), scheduleFileEntity.getDepartmentId())
                    .addParameter(API_URL.getModelName(), scheduleFileEntity.getApiUrl())
                    .addParameter(FILE_NAME.getModelName(), scheduleFileEntity.getFileName())
                    .addParameter(FILE_TYPE.getModelName(), scheduleFileEntity.getFileType())
                    .addParameter(FILE_BYTE.getModelName(), scheduleFileEntity.getFileByte())
                    .addParameter(FILE_SIZE.getModelName(), scheduleFileEntity.getFileSize())
                    .addParameter(FILE.getModelName(), scheduleFileEntity.getFile().getBytes())
                    .addParameter(CREATED_USER_ID.getModelName(), scheduleFileEntity.getCreatedUserId())
                    .addParameter(CREATED_DATE.getModelName(), scheduleFileEntity.getCreatedDate())
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeUpdate();

            info.savedById(scheduleFileEntity.getFileId());
        } catch (Exception exception) {
            error.errorWhenSaving();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void deleteExamScheduleFile(final Long departmentId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(DELETE_EXAM_SCHEDULE_FILE)) {

            query
                    .addParameter(DEPARTMENT_ID.getModelName(), departmentId)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeUpdate();

            info.deleted();
        } catch (Exception exception) {
            error.errorWhenDeleting();
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public boolean isExamScheduleFileExist(final String fileId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_EXAM_SCHEDULE_FILE)) {

            final boolean isLessonExist = query
                    .addParameter(FILE_ID.getModelName(), fileId)
                    .executeAndFetchFirst(Boolean.class);

            if (isLessonExist) {
                info.foundById(fileId);
                return true;
            } else {
                warn.notFoundById(fileId);
                return false;
            }
        } catch (Exception exception) {
            error.errorWhenGettingById(fileId);
            throw new SisDatabaseException(exception);
        }
    }
}
