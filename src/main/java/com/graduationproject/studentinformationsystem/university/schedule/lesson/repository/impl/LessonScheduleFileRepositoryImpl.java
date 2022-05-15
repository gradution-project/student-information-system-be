package com.graduationproject.studentinformationsystem.university.schedule.lesson.repository.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisDatabaseException;
import com.graduationproject.studentinformationsystem.common.util.log.SisErrorLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisInfoLogMessageUtil;
import com.graduationproject.studentinformationsystem.common.util.log.SisWarnLogMessageUtil;
import com.graduationproject.studentinformationsystem.university.schedule.common.model.entity.ScheduleFileEntity;
import com.graduationproject.studentinformationsystem.university.schedule.lesson.repository.LessonScheduleFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

import static com.graduationproject.studentinformationsystem.university.schedule.common.model.mapping.ScheduleFileMapping.*;
import static com.graduationproject.studentinformationsystem.university.schedule.lesson.repository.impl.scripts.LessonScheduleFileSqlScripts.*;

@Repository
@RequiredArgsConstructor
public class LessonScheduleFileRepositoryImpl implements LessonScheduleFileRepository {

    private static final String LESSON_SCHEDULE_FILE = "Lesson Schedule File";
    private final SisInfoLogMessageUtil info = SisInfoLogMessageUtil.builder().apiName(LESSON_SCHEDULE_FILE).build();
    private final SisWarnLogMessageUtil warn = SisWarnLogMessageUtil.builder().apiName(LESSON_SCHEDULE_FILE).build();
    private final SisErrorLogMessageUtil error = SisErrorLogMessageUtil.builder().apiName(LESSON_SCHEDULE_FILE).build();

    private final Sql2o sql2o;

    @Override
    public List<ScheduleFileEntity> getLessonScheduleFilesByFacultyId(Long facultyId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_LESSON_SCHEDULE_FILES_BY_FACULTY_ID)) {

            final List<ScheduleFileEntity> scheduleFileEntities = query
                    .addParameter(FACULTY_ID.getModelName(), facultyId)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetch(ScheduleFileEntity.class);

            info.foundByFacultyId(facultyId);
            return scheduleFileEntities;
        } catch (Exception exception) {
            error.errorWhenGettingByFacultyId(facultyId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public ScheduleFileEntity getLessonScheduleFileById(String fileId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_LESSON_SCHEDULE_FILE_BY_ID)) {

            final ScheduleFileEntity scheduleFileEntity = query
                    .addParameter(FILE_ID.getModelName(), fileId)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetchFirst(ScheduleFileEntity.class);

            info.foundById(fileId);
            return scheduleFileEntity;
        } catch (Exception exception) {
            error.errorWhenGettingById(fileId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public ScheduleFileEntity getLessonScheduleFileByDepartmentId(final Long departmentId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(GET_LESSON_SCHEDULE_FILE_BY_DEPARTMENT_ID)) {

            final ScheduleFileEntity scheduleFileEntity = query
                    .addParameter(DEPARTMENT_ID.getModelName(), departmentId)
                    .setColumnMappings(COLUMN_MAPPINGS)
                    .executeAndFetchFirst(ScheduleFileEntity.class);

            info.foundByDepartmentId(departmentId);
            return scheduleFileEntity;
        } catch (Exception exception) {
            error.errorWhenGettingByDepartmentId(departmentId);
            throw new SisDatabaseException(exception);
        }
    }

    @Override
    public void saveLessonScheduleFile(final ScheduleFileEntity scheduleFileEntity) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(SAVE_LESSON_SCHEDULE_FILE)) {

            query
                    .addParameter(FILE_ID.getModelName(), scheduleFileEntity.getFileId())
                    .addParameter(FACULTY_ID.getModelName(), scheduleFileEntity.getFacultyId())
                    .addParameter(DEPARTMENT_ID.getModelName(), scheduleFileEntity.getDepartmentId())
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
    public void deleteLessonScheduleFile(final Long departmentId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(DELETE_LESSON_SCHEDULE_FILE)) {

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
    public boolean isLessonScheduleFileExist(final String fileId) {
        try (final Connection connection = sql2o.open(); final Query query = connection.createQuery(IS_LESSON_SCHEDULE_FILE)) {

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
