package com.graduationproject.studentinformationsystem.university.schedule.exam.service.impl;

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
import com.graduationproject.studentinformationsystem.university.schedule.exam.model.dto.converter.ExamScheduleFileInfoConverter;
import com.graduationproject.studentinformationsystem.university.schedule.exam.model.exception.ExamScheduleFileException;
import com.graduationproject.studentinformationsystem.university.schedule.exam.repository.ExamScheduleFileRepository;
import com.graduationproject.studentinformationsystem.university.schedule.exam.service.ExamScheduleFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamScheduleFileServiceImpl implements ExamScheduleFileService {

    private final FacultyOutService facultyOutService;
    private final DepartmentOutService departmentOutService;

    private final ExamScheduleFileRepository scheduleFileRepository;
    private final ExamScheduleFileInfoConverter scheduleFileInfoConverter;

    @Override
    public List<ScheduleFileDetailResponse> getExamScheduleFilesByFacultyId(final Long facultyId)
            throws SisNotExistException {

        checkBeforeGetFacultyId(facultyId);
        return getExamScheduleFilesDetailResponses(facultyId);
    }

    @Override
    public ScheduleFileResponse getExamScheduleFileById(String fileId)
            throws SisNotExistException, IOException {

        ifExamScheduleFileIsNotExistThrowNotExistException(fileId);
        return getExamScheduleFileResponse(fileId);
    }

    @Override
    public ScheduleFileDetailResponse getExamScheduleFileByDepartmentId(final Long departmentId)
            throws SisNotExistException {

        checkBeforeGetByDepartmentId(departmentId);
        return getExamScheduleFileDetailResponse(departmentId);
    }

    @Override
    public ScheduleFileDetailResponse saveExamScheduleFile(final Long facultyId,
                                                           final Long departmentId,
                                                           final Long operationUserId,
                                                           final MultipartFile document)
            throws SisNotExistException, IOException, SisAlreadyException {

        checkBeforeSaving(facultyId, departmentId, document.getContentType());

        final String fileId = SisUtil.generateRandomUUID();
        final ScheduleFileEntity scheduleFileEntity = scheduleFileInfoConverter
                .generateSaveEntity(fileId, document, facultyId, departmentId, operationUserId);

        scheduleFileRepository.saveExamScheduleFile(scheduleFileEntity);
        return getExamScheduleFileDetailResponse(departmentId);
    }

    @Override
    public void deleteExamScheduleFileByDepartmentId(final Long departmentId) throws SisNotExistException {
        checkBeforeDeleting(departmentId);
        scheduleFileRepository.deleteExamScheduleFile(departmentId);
    }


    private ScheduleFileResponse getExamScheduleFileResponse(final String fileId) throws IOException {
        final ScheduleFileEntity scheduleFileEntity = scheduleFileRepository.getExamScheduleFileById(fileId);
        return scheduleFileInfoConverter.entityToResponse(scheduleFileEntity);
    }

    private List<ScheduleFileDetailResponse> getExamScheduleFilesDetailResponses(final Long facultyId) {
        final List<ScheduleFileEntity> scheduleFileEntities = scheduleFileRepository.getExamScheduleFilesByFacultyId(facultyId);
        return scheduleFileInfoConverter.entitiesToResponses(scheduleFileEntities);
    }

    private ScheduleFileDetailResponse getExamScheduleFileDetailResponse(final Long departmentId) {
        final ScheduleFileEntity scheduleFileEntity = scheduleFileRepository.getExamScheduleFileByDepartmentId(departmentId);
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
        ifExamScheduleFileIsNotExistThrowNotExistException(departmentId);
    }

    private void checkBeforeGetByDepartmentId(final Long departmentId) throws SisNotExistException {
        ifDepartmentIsNotExistThrowNotExistException(departmentId);
        ifExamScheduleFileIsNotExistThrowNotExistException(departmentId);
    }

    private void checkBeforeGetFacultyId(final Long facultyId) throws SisNotExistException {
        ifFacultyIsNotExistThrowNotExistException(facultyId);
    }


    /**
     * Throw Exceptions
     */

    private void ifDepartmentIsNotExistThrowNotExistException(final Long departmentId) throws SisNotExistException {
        departmentOutService.ifDepartmentIsNotExistThrowNotExistException(departmentId);
    }

    private void ifFacultyIsNotExistThrowNotExistException(final Long facultyId) throws SisNotExistException {
        facultyOutService.ifFacultyIsNotExistThrowNotExistException(facultyId);
    }

    private void ifFileIsExistThrowFileAlreadyExistException(final Long departmentId) throws SisAlreadyException {
        final ScheduleFileEntity scheduleFileEntity = scheduleFileRepository.getExamScheduleFileByDepartmentId(departmentId);
        if (scheduleFileEntity != null) {
            if (scheduleFileRepository.isExamScheduleFileExist(scheduleFileEntity.getFileId())) {
                ExamScheduleFileException.throwAlreadyExistException(scheduleFileEntity.getFileId());
            }
        }
    }

    private void ifExamScheduleFileIsNotExistThrowNotExistException(final Long departmentId) throws SisNotExistException {
        final ScheduleFileEntity scheduleFileEntity = scheduleFileRepository.getExamScheduleFileByDepartmentId(departmentId);
        if (scheduleFileEntity == null) {
            ExamScheduleFileException.throwNotExistException(departmentId);
        }
    }

    private void ifExamScheduleFileIsNotExistThrowNotExistException(final String fileId) throws SisNotExistException {
        if (!scheduleFileRepository.isExamScheduleFileExist(fileId)) {
            ExamScheduleFileException.throwNotExistException(fileId);
        }
    }

    private void ifFileTypeIsNotPdfThrowFileTypeNotPdfException(final String fileType) throws SisFileTypeException {
        if (!SisFileType.PDF.getName().equals(fileType)) {
            ExamScheduleFileException.throwFileTypeNotPdfException(fileType);
        }
    }
}
