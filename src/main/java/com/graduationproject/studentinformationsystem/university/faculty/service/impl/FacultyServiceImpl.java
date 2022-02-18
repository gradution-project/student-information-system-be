package com.graduationproject.studentinformationsystem.university.faculty.service.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.faculty.model.dto.converter.FacultyInfoConverter;
import com.graduationproject.studentinformationsystem.university.faculty.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.faculty.model.dto.response.FacultyResponse;
import com.graduationproject.studentinformationsystem.university.faculty.model.entity.FacultyEntity;
import com.graduationproject.studentinformationsystem.university.faculty.model.enums.FacultyStatus;
import com.graduationproject.studentinformationsystem.university.faculty.model.exception.FacultyException;
import com.graduationproject.studentinformationsystem.university.faculty.repository.FacultyRepository;
import com.graduationproject.studentinformationsystem.university.faculty.service.FacultyService;
import com.graduationproject.studentinformationsystem.university.faculty.util.FacultyUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;
    private final FacultyInfoConverter facultyInfoConverter;

    @Override
    public List<FacultyResponse> getAllFacultiesByStatus(final FacultyStatus status) {
        final List<FacultyEntity> facultyResponses = facultyRepository.getAllFacultiesByStatus(status);
        return facultyInfoConverter.entitiesToResponses(facultyResponses);
    }

    @Override
    public FacultyResponse getFacultyById(final Long facultyId) throws SisNotExistException {
        ifFacultyIsNotExistThrowNotExistException(facultyId);
        return getFacultyResponse(facultyId);
    }

    @Override
    public FacultyResponse saveFaculty(final FacultySaveRequest saveRequest) {

        final Long facultyId = generateFacultyId();
        final FacultyEntity facultyEntity = facultyInfoConverter.generateSaveEntity(facultyId, saveRequest);

        facultyRepository.saveFaculty(facultyEntity);

        return getFacultyResponse(facultyId);
    }

    @Override
    public FacultyResponse updateFaculty(final Long facultyId, final FacultyUpdateRequest updateRequest)
            throws SisNotExistException {

        checkBeforeUpdating(facultyId);

        final FacultyEntity facultyEntity = facultyInfoConverter.generateUpdateEntity(facultyId, updateRequest);
        facultyRepository.updateFaculty(facultyEntity);

        return getFacultyResponse(facultyId);
    }

    @Override
    public FacultyResponse deleteFaculty(final FacultyDeleteRequest deleteRequest)
            throws SisNotExistException, SisAlreadyException {

        checkBeforeDeleting(deleteRequest.getFacultyId());

        final FacultyEntity facultyEntity = facultyInfoConverter.generateDeleteEntity(deleteRequest);
        facultyRepository.updateFacultyStatus(facultyEntity);

        return getFacultyResponse(deleteRequest.getFacultyId());
    }

    @Override
    public FacultyResponse passivateFaculty(final FacultyPassivateRequest passivateRequest)
            throws SisNotExistException, SisAlreadyException {

        checkBeforePassivating(passivateRequest.getFacultyId());

        final FacultyEntity facultyEntity = facultyInfoConverter.generatePassiveEntity(passivateRequest);
        facultyRepository.updateFacultyStatus(facultyEntity);

        return getFacultyResponse(passivateRequest.getFacultyId());
    }

    @Override
    public FacultyResponse activateFaculty(final FacultyActivateRequest activateRequest)
            throws SisNotExistException, SisAlreadyException {

        checkBeforeActivating(activateRequest.getFacultyId());

        final FacultyEntity facultyEntity = facultyInfoConverter.generateActiveEntity(activateRequest);
        facultyRepository.updateFacultyStatus(facultyEntity);

        return getFacultyResponse(activateRequest.getFacultyId());
    }


    private Long generateFacultyId() {
        final List<Long> facultyIds = facultyRepository.getAllFacultyIds();
        return FacultyUtil.generateFacultyId(facultyIds);
    }

    private FacultyResponse getFacultyResponse(final Long facultyId) {
        final FacultyEntity facultyEntity = facultyRepository.getFacultyById(facultyId);
        return facultyInfoConverter.entityToResponse(facultyEntity);
    }


    /**
     * Checks Before Processing
     */

    private void checkBeforeUpdating(final Long facultyId) throws SisNotExistException {
        ifFacultyIsNotExistThrowNotExistException(facultyId);
    }

    private void checkBeforeDeleting(final Long facultyId) throws SisNotExistException, SisAlreadyException {
        ifFacultyIsNotExistThrowNotExistException(facultyId);
        ifFacultyIsAlreadyDeletedThrowAlreadyException(facultyId);
    }

    private void checkBeforePassivating(final Long facultyId) throws SisNotExistException, SisAlreadyException {
        ifFacultyIsNotExistThrowNotExistException(facultyId);
        ifFacultyIsAlreadyPassiveThrowAlreadyException(facultyId);
        ifFacultyIsAlreadyDeletedThrowAlreadyException(facultyId);
    }

    private void checkBeforeActivating(final Long facultyId) throws SisNotExistException, SisAlreadyException {
        ifFacultyIsNotExistThrowNotExistException(facultyId);
        ifFacultyIsAlreadyActiveThrowAlreadyException(facultyId);
        ifFacultyIsAlreadyDeletedThrowAlreadyException(facultyId);
    }


    /**
     * Throw Exceptions
     */

    private void ifFacultyIsNotExistThrowNotExistException(final Long facultyId) throws SisNotExistException {
        if (!facultyRepository.isFacultyExist(facultyId)) {
            FacultyException.throwNotExistException(facultyId);
        }
    }

    private void ifFacultyIsAlreadyDeletedThrowAlreadyException(final Long facultyId) throws SisAlreadyException {
        if (facultyRepository.isFacultyDeleted(facultyId)) {
            FacultyException.throwAlreadyDeletedException(facultyId);
        }
    }

    private void ifFacultyIsAlreadyPassiveThrowAlreadyException(final Long facultyId) throws SisAlreadyException {
        if (facultyRepository.isFacultyPassive(facultyId)) {
            FacultyException.throwAlreadyPassiveException(facultyId);
        }
    }

    private void ifFacultyIsAlreadyActiveThrowAlreadyException(final Long facultyId) throws SisAlreadyException {
        if (facultyRepository.isFacultyActive(facultyId)) {
            FacultyException.throwAlreadyActiveException(facultyId);
        }
    }
}
