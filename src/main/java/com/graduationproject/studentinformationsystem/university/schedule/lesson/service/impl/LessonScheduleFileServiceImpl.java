package com.graduationproject.studentinformationsystem.university.schedule.lesson.service.impl;

import com.graduationproject.studentinformationsystem.common.util.SisUtil;
import com.graduationproject.studentinformationsystem.common.util.enums.SisFileType;
import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisFileTypeException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.department.service.DepartmentOutService;
import com.graduationproject.studentinformationsystem.university.faculty.service.FacultyOutService;
import com.graduationproject.studentinformationsystem.university.schedule.common.model.dto.response.ScheduleFileDetailResponse;
import com.graduationproject.studentinformationsystem.university.schedule.common.model.dto.response.ScheduleFileResponse;
import com.graduationproject.studentinformationsystem.university.schedule.common.model.entity.ScheduleFileEntity;
import com.graduationproject.studentinformationsystem.university.schedule.lesson.model.dto.converter.LessonScheduleFileInfoConverter;
import com.graduationproject.studentinformationsystem.university.schedule.lesson.model.exception.LessonScheduleFileException;
import com.graduationproject.studentinformationsystem.university.schedule.lesson.repository.LessonScheduleFileRepository;
import com.graduationproject.studentinformationsystem.university.schedule.lesson.service.LessonScheduleFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonScheduleFileServiceImpl implements LessonScheduleFileService {

    private final FacultyOutService facultyOutService;
    private final DepartmentOutService departmentOutService;

    private final LessonScheduleFileRepository scheduleFileRepository;
    private final LessonScheduleFileInfoConverter scheduleFileInfoConverter;

    @Override
    public List<ScheduleFileDetailResponse> getLessonScheduleFilesByFacultyId(final Long facultyId)
            throws SisNotExistException {

        checkBeforeGetFacultyId(facultyId);
        return getLessonScheduleFilesDetailResponses(facultyId);
    }

    @Override
    public ScheduleFileResponse getLessonScheduleFileById(String fileId)
            throws SisNotExistException, IOException {

        ifLessonScheduleFileIsNotExistThrowNotExistException(fileId);
        return getLessonScheduleFileResponse(fileId);
    }

    @Override
    public ScheduleFileDetailResponse getLessonScheduleFileByDepartmentId(final Long departmentId)
            throws SisNotExistException {

        checkBeforeGetByDepartmentId(departmentId);
        return getLessonScheduleFileDetailResponse(departmentId);
    }

    @Override
    public ScheduleFileDetailResponse saveLessonScheduleFile(final String apiUrl,
                                                             final Long facultyId,
                                                             final Long departmentId,
                                                             final Long operationUserId,
                                                             final MultipartFile document)
            throws SisNotExistException, IOException, SisAlreadyException {

        checkBeforeSaving(facultyId, departmentId, document.getContentType());

        final String fileId = SisUtil.generateRandomUUID();
        final ScheduleFileEntity scheduleFileEntity = scheduleFileInfoConverter
                .generateSaveEntity(fileId, document, apiUrl, facultyId, departmentId, operationUserId);

        scheduleFileRepository.saveLessonScheduleFile(scheduleFileEntity);
        return getLessonScheduleFileDetailResponse(departmentId);
    }

    @Override
    public void deleteLessonScheduleFileByDepartmentId(final Long departmentId) throws SisNotExistException {
        checkBeforeDeleting(departmentId);
        scheduleFileRepository.deleteLessonScheduleFile(departmentId);
    }


    private ScheduleFileResponse getLessonScheduleFileResponse(final String fileId) throws IOException {
        final ScheduleFileEntity scheduleFileEntity = scheduleFileRepository.getLessonScheduleFileById(fileId);
        return scheduleFileInfoConverter.entityToResponse(scheduleFileEntity);
    }

    private List<ScheduleFileDetailResponse> getLessonScheduleFilesDetailResponses(final Long facultyId) {
        final List<ScheduleFileEntity> scheduleFileEntities = scheduleFileRepository.getLessonScheduleFilesByFacultyId(facultyId);
        return scheduleFileInfoConverter.entitiesToResponses(scheduleFileEntities);
    }

    private ScheduleFileDetailResponse getLessonScheduleFileDetailResponse(final Long departmentId) {
        final ScheduleFileEntity scheduleFileEntity = scheduleFileRepository.getLessonScheduleFileByDepartmentId(departmentId);
        return scheduleFileInfoConverter.entityToDetailResponse(scheduleFileEntity);
    }


    /**
     * Checks Before Processing
     */

    private void checkBeforeSaving(final Long facultyId, final Long departmentId, final String fileType)
            throws SisNotExistException, SisFileTypeException, SisAlreadyException {

        ifFacultyIsNotExistThrowNotExistException(facultyId);
        ifDepartmentIsNotExistThrowNotExistException(departmentId);
        ifFileIsExistThrowFileAlreadyExistException(departmentId);
        ifFileTypeIsNotPdfThrowFileTypeNotPdfException(fileType);
    }

    private void checkBeforeDeleting(final Long departmentId) throws SisNotExistException {
        ifDepartmentIsNotExistThrowNotExistException(departmentId);
        ifLessonScheduleFileIsNotExistThrowNotExistException(departmentId);
    }

    private void checkBeforeGetByDepartmentId(final Long departmentId) throws SisNotExistException {
        ifDepartmentIsNotExistThrowNotExistException(departmentId);
        ifLessonScheduleFileIsNotExistThrowNotExistException(departmentId);
    }

    private void checkBeforeGetFacultyId(final Long facultyId) throws SisNotExistException {
        ifFacultyIsNotExistThrowNotExistException(facultyId);
    }


    /**
     * Throw Exceptions
     */

    private void ifFacultyIsNotExistThrowNotExistException(final Long facultyId) throws SisNotExistException {
        facultyOutService.ifFacultyIsNotExistThrowNotExistException(facultyId);
    }

    private void ifDepartmentIsNotExistThrowNotExistException(final Long departmentId) throws SisNotExistException {
        departmentOutService.ifDepartmentIsNotExistThrowNotExistException(departmentId);
    }

    private void ifFileIsExistThrowFileAlreadyExistException(final Long departmentId) throws SisAlreadyException {
        final ScheduleFileEntity scheduleFileEntity = scheduleFileRepository.getLessonScheduleFileByDepartmentId(departmentId);
        if (scheduleFileEntity != null) {
            if (scheduleFileRepository.isLessonScheduleFileExist(scheduleFileEntity.getFileId())) {
                LessonScheduleFileException.throwAlreadyExistException(scheduleFileEntity.getFileId());
            }
        }
    }

    private void ifLessonScheduleFileIsNotExistThrowNotExistException(final Long departmentId) throws SisNotExistException {
        final ScheduleFileEntity scheduleFileEntity = scheduleFileRepository.getLessonScheduleFileByDepartmentId(departmentId);
        if (scheduleFileEntity == null) {
            LessonScheduleFileException.throwNotExistException(departmentId);
        }
    }

    private void ifLessonScheduleFileIsNotExistThrowNotExistException(final String fileId) throws SisNotExistException {
        if (!scheduleFileRepository.isLessonScheduleFileExist(fileId)) {
            LessonScheduleFileException.throwNotExistException(fileId);
        }
    }

    private void ifFileTypeIsNotPdfThrowFileTypeNotPdfException(final String fileType) throws SisFileTypeException {
        if (!SisFileType.PDF.getName().equals(fileType)) {
            LessonScheduleFileException.throwFileTypeNotPdfException(fileType);
        }
    }
}
