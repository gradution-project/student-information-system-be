package com.graduationproject.studentinformationsystem.university.schedule.exam.service.impl;

import com.graduationproject.studentinformationsystem.common.util.SisUtil;
import com.graduationproject.studentinformationsystem.common.util.enums.SisFileType;
import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisFileTypeException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.department.model.entity.DepartmentEntity;
import com.graduationproject.studentinformationsystem.university.department.model.exception.DepartmentException;
import com.graduationproject.studentinformationsystem.university.department.repository.DepartmentRepository;
import com.graduationproject.studentinformationsystem.university.faculty.model.entity.FacultyEntity;
import com.graduationproject.studentinformationsystem.university.faculty.model.exception.FacultyException;
import com.graduationproject.studentinformationsystem.university.faculty.repository.FacultyRepository;
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

    private final FacultyRepository facultyRepository;
    private final DepartmentRepository departmentRepository;

    private final ExamScheduleFileRepository scheduleFileRepository;

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
    public ScheduleFileDetailResponse saveExamScheduleFile(final String apiUrl,
                                                           final Long facultyId,
                                                           final Long departmentId,
                                                           final Long operationUserId,
                                                           final MultipartFile document)
            throws SisNotExistException, IOException, SisAlreadyException {

        checkBeforeSaving(facultyId, departmentId, document.getContentType());

        final String fileId = SisUtil.generateRandomUUID();
        final ScheduleFileEntity scheduleFileEntity = ExamScheduleFileInfoConverter
                .generateSaveEntity(fileId, document, apiUrl, facultyId, departmentId, operationUserId);

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
        return ExamScheduleFileInfoConverter.entityToResponse(scheduleFileEntity);
    }

    private List<ScheduleFileDetailResponse> getExamScheduleFilesDetailResponses(final Long facultyId) {
        final List<ScheduleFileEntity> scheduleFileEntities = scheduleFileRepository.getExamScheduleFilesByFacultyId(facultyId);
        setDepartmentEntities(scheduleFileEntities);
        return ExamScheduleFileInfoConverter.entitiesToResponses(scheduleFileEntities);
    }

    private ScheduleFileDetailResponse getExamScheduleFileDetailResponse(final Long departmentId) {
        final ScheduleFileEntity scheduleFileEntity = scheduleFileRepository.getExamScheduleFileByDepartmentId(departmentId);
        setDepartmentEntity(scheduleFileEntity);
        return ExamScheduleFileInfoConverter.entityToDetailResponse(scheduleFileEntity);
    }

    private void setDepartmentEntities(final List<ScheduleFileEntity> scheduleFileEntities) {
        scheduleFileEntities.forEach(this::setDepartmentEntity);
    }

    private void setDepartmentEntity(final ScheduleFileEntity scheduleFileEntity) {
        final Long departmentId = scheduleFileEntity.getDepartmentId();
        final DepartmentEntity departmentEntity = departmentRepository.getDepartmentById(departmentId);
        setFacultyEntity(departmentEntity);
        scheduleFileEntity.setDepartmentEntity(departmentEntity);
    }

    private void setFacultyEntity(final DepartmentEntity departmentEntity) {
        final Long facultyId = departmentEntity.getFacultyId();
        final FacultyEntity facultyEntity = facultyRepository.getFacultyById(facultyId);
        departmentEntity.setFacultyEntity(facultyEntity);
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
        if (!departmentRepository.isDepartmentExist(departmentId)) {
            DepartmentException.throwNotExistException(departmentId);
        }
    }

    private void ifFacultyIsNotExistThrowNotExistException(final Long facultyId) throws SisNotExistException {
        if (!facultyRepository.isFacultyExist(facultyId)) {
            FacultyException.throwNotExistException(facultyId);
        }
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
