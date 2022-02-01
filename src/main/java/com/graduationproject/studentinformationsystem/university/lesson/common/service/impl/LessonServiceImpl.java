package com.graduationproject.studentinformationsystem.university.lesson.common.service.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.department.model.entity.DepartmentEntity;
import com.graduationproject.studentinformationsystem.university.department.repository.DepartmentRepository;
import com.graduationproject.studentinformationsystem.university.faculty.model.entity.FacultyEntity;
import com.graduationproject.studentinformationsystem.university.faculty.repository.FacultyRepository;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.converter.LessonInfoConverter;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.response.LessonResponse;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.entity.LessonEntity;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.enums.LessonStatus;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.exception.LessonException;
import com.graduationproject.studentinformationsystem.university.lesson.common.repository.LessonRepository;
import com.graduationproject.studentinformationsystem.university.lesson.common.service.LessonService;
import com.graduationproject.studentinformationsystem.university.lesson.common.util.LessonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final FacultyRepository facultyRepository;
    private final DepartmentRepository departmentRepository;

    private final LessonRepository lessonRepository;

    @Override
    public List<LessonResponse> getAllLessonsByStatus(final LessonStatus status) {
        final List<LessonEntity> lessonEntities = lessonRepository.getAllLessonsByStatus(status);
        setDepartmentEntities(lessonEntities);
        return LessonInfoConverter.entitiesToResponses(lessonEntities);
    }

    @Override
    public LessonResponse getLessonById(final Long lessonId) throws SisNotExistException {
        ifLessonIsNotExistThrowNotExistException(lessonId);
        return getLessonResponse(lessonId);
    }

    @Override
    public LessonResponse saveLesson(final LessonSaveRequest saveRequest) throws SisNotExistException {

        final Long departmentId = saveRequest.getLessonInfoRequest().getDepartmentId();

        checkBeforeSaving(departmentId);

        final Long lessonId = generateLessonId(departmentId);
        final LessonEntity lessonEntity = LessonInfoConverter.generateSaveEntity(lessonId, saveRequest);

        lessonRepository.saveLesson(lessonEntity);

        return getLessonResponse(lessonId);
    }

    @Override
    public LessonResponse updateLesson(final Long lessonId, final LessonUpdateRequest updateRequest)
            throws SisNotExistException {

        checkBeforeUpdating(lessonId);

        final LessonEntity lessonEntity = LessonInfoConverter.generateUpdateEntity(lessonId, updateRequest);
        lessonRepository.updateLesson(lessonEntity);

        return getLessonResponse(lessonId);
    }

    @Override
    public LessonResponse deleteLesson(final LessonDeleteRequest deleteRequest)
            throws SisNotExistException, SisAlreadyException {

        checkBeforeDeleting(deleteRequest.getLessonId());

        final LessonEntity lessonEntity = LessonInfoConverter.generateDeleteEntity(deleteRequest);
        lessonRepository.updateLessonStatus(lessonEntity);

        return getLessonResponse(deleteRequest.getLessonId());
    }

    @Override
    public LessonResponse passivateLesson(final LessonPassivateRequest passivateRequest)
            throws SisNotExistException, SisAlreadyException {

        checkBeforePassivating(passivateRequest.getLessonId());

        final LessonEntity lessonEntity = LessonInfoConverter.generatePassiveEntity(passivateRequest);
        lessonRepository.updateLessonStatus(lessonEntity);

        return getLessonResponse(passivateRequest.getLessonId());
    }

    @Override
    public LessonResponse activateLesson(final LessonActivateRequest activateRequest)
            throws SisNotExistException, SisAlreadyException {

        checkBeforeActivating(activateRequest.getLessonId());

        final LessonEntity lessonEntity = LessonInfoConverter.generateActiveEntity(activateRequest);
        lessonRepository.updateLessonStatus(lessonEntity);

        return getLessonResponse(activateRequest.getLessonId());
    }


    private Long generateLessonId(final Long departmentId) {
        final List<Long> lessonIds = lessonRepository.getAllLessonIdsByDepartmentId(departmentId);
        return LessonUtil.generateLessonId(departmentId, lessonIds);
    }

    private LessonResponse getLessonResponse(final Long lessonId) {
        final LessonEntity lessonEntity = lessonRepository.getLessonById(lessonId);
        setDepartmentEntity(lessonEntity);
        return LessonInfoConverter.entityToResponse(lessonEntity);
    }

    private void setDepartmentEntity(final LessonEntity lessonEntity) {
        final Long departmentId = lessonEntity.getDepartmentId();
        final DepartmentEntity departmentEntity = departmentRepository.getDepartmentById(departmentId);
        setFacultyEntity(departmentEntity);
        lessonEntity.setDepartmentEntity(departmentEntity);
    }

    private void setDepartmentEntities(final List<LessonEntity> lessonEntities) {
        lessonEntities.forEach(this::setDepartmentEntity);
    }

    private void setFacultyEntity(final DepartmentEntity departmentEntity) {
        final Long facultyId = departmentEntity.getFacultyId();
        final FacultyEntity facultyEntity = facultyRepository.getFacultyById(facultyId);
        departmentEntity.setFacultyEntity(facultyEntity);
    }


    /**
     * Checks Before Processing
     */

    private void checkBeforeSaving(final Long departmentId) throws SisNotExistException {

        ifDepartmentIsNotExistThrowNotExistException(departmentId);
    }

    private void checkBeforeUpdating(final Long lessonId) throws SisNotExistException {

        ifLessonIsNotExistThrowNotExistException(lessonId);
    }

    private void checkBeforeDeleting(final Long lessonId) throws SisNotExistException, SisAlreadyException {
        ifLessonIsNotExistThrowNotExistException(lessonId);
        ifLessonIsAlreadyDeletedThrowAlreadyException(lessonId);
    }

    private void checkBeforePassivating(final Long lessonId) throws SisNotExistException, SisAlreadyException {
        ifLessonIsNotExistThrowNotExistException(lessonId);
        ifLessonIsAlreadyPassiveThrowAlreadyException(lessonId);
        ifLessonIsAlreadyDeletedThrowAlreadyException(lessonId);
    }

    private void checkBeforeActivating(final Long lessonId) throws SisNotExistException, SisAlreadyException {
        ifLessonIsNotExistThrowNotExistException(lessonId);
        ifLessonIsAlreadyActiveThrowAlreadyException(lessonId);
        ifLessonIsAlreadyDeletedThrowAlreadyException(lessonId);
    }


    /**
     * Throw Exceptions
     */

    private void ifDepartmentIsNotExistThrowNotExistException(final Long departmentId) throws SisNotExistException {
        if (!departmentRepository.isDepartmentExist(departmentId)) {
            LessonException.throwNotExistException(departmentId);
        }
    }

    private void ifLessonIsNotExistThrowNotExistException(final Long lessonId) throws SisNotExistException {
        if (!lessonRepository.isLessonExist(lessonId)) {
            LessonException.throwNotExistException(lessonId);
        }
    }

    private void ifLessonIsAlreadyDeletedThrowAlreadyException(final Long lessonId) throws SisAlreadyException {
        if (lessonRepository.isLessonDeleted(lessonId)) {
            LessonException.throwAlreadyDeletedException(lessonId);
        }
    }

    private void ifLessonIsAlreadyPassiveThrowAlreadyException(final Long lessonId) throws SisAlreadyException {
        if (lessonRepository.isLessonPassive(lessonId)) {
            LessonException.throwAlreadyPassiveException(lessonId);
        }
    }

    private void ifLessonIsAlreadyActiveThrowAlreadyException(final Long lessonId) throws SisAlreadyException {
        if (lessonRepository.isLessonActive(lessonId)) {
            LessonException.throwAlreadyActiveException(lessonId);
        }
    }
}
