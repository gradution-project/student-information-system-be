package com.graduationproject.studentinformationsystem.university.department.service.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.department.model.dto.converter.DepartmentInfoConverter;
import com.graduationproject.studentinformationsystem.university.department.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.department.model.dto.response.DepartmentResponse;
import com.graduationproject.studentinformationsystem.university.department.model.entity.DepartmentEntity;
import com.graduationproject.studentinformationsystem.university.department.model.enums.DepartmentStatus;
import com.graduationproject.studentinformationsystem.university.department.model.exception.DepartmentException;
import com.graduationproject.studentinformationsystem.university.department.repository.DepartmentRepository;
import com.graduationproject.studentinformationsystem.university.department.service.DepartmentService;
import com.graduationproject.studentinformationsystem.university.department.util.DepartmentUtil;
import com.graduationproject.studentinformationsystem.university.faculty.repository.FacultyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final FacultyRepository facultyRepository;

    @Override
    public List<DepartmentResponse> getAllDepartmentsByStatus(final DepartmentStatus status) {
        final List<DepartmentEntity> departmentResponses = departmentRepository.getAllDepartmentsByStatus(status);
        return DepartmentInfoConverter.entitiesToResponses(departmentResponses);
    }

    @Override
    public DepartmentResponse getDepartmentById(final Long departmentId) throws SisNotExistException {
        ifDepartmentIsNotExistThrowNotExistException(departmentId);
        return getDepartmentResponse(departmentId);
    }

    @Override
    public DepartmentResponse saveDepartment(final DepartmentSaveRequest saveRequest) throws SisNotExistException {

        final Long facultyId = saveRequest.getDepartmentInfoRequest().getFacultyId();

        checkBeforeSaving(facultyId);

        final Long departmentId = generateDepartmentId(facultyId);
        final DepartmentEntity departmentEntity = DepartmentInfoConverter.generateSaveEntity(departmentId, saveRequest);

        departmentRepository.saveDepartment(departmentEntity);

        return getDepartmentResponse(departmentId);
    }

    @Override
    public DepartmentResponse updateDepartment(final Long departmentId, final DepartmentUpdateRequest updateRequest)
            throws SisNotExistException {

        checkBeforeUpdating(departmentId);

        final DepartmentEntity departmentEntity = DepartmentInfoConverter.generateUpdateEntity(departmentId, updateRequest);
        departmentRepository.updateDepartment(departmentEntity);

        return getDepartmentResponse(departmentId);
    }

    @Override
    public DepartmentResponse deleteDepartment(final DepartmentDeleteRequest deleteRequest)
            throws SisNotExistException, SisAlreadyException {

        checkBeforeDeleting(deleteRequest.getDepartmentId());

        final DepartmentEntity departmentEntity = DepartmentInfoConverter.generateDeleteEntity(deleteRequest);
        departmentRepository.updateDepartmentStatus(departmentEntity);

        return getDepartmentResponse(deleteRequest.getDepartmentId());
    }

    @Override
    public DepartmentResponse passivateDepartment(final DepartmentPassivateRequest passivateRequest)
            throws SisNotExistException, SisAlreadyException {

        checkBeforePassivating(passivateRequest.getDepartmentId());

        final DepartmentEntity departmentEntity = DepartmentInfoConverter.generatePassiveEntity(passivateRequest);
        departmentRepository.updateDepartmentStatus(departmentEntity);

        return getDepartmentResponse(passivateRequest.getDepartmentId());
    }

    @Override
    public DepartmentResponse activateDepartment(final DepartmentActivateRequest activateRequest)
            throws SisNotExistException, SisAlreadyException {

        checkBeforeActivating(activateRequest.getDepartmentId());

        final DepartmentEntity departmentEntity = DepartmentInfoConverter.generateActiveEntity(activateRequest);
        departmentRepository.updateDepartmentStatus(departmentEntity);

        return getDepartmentResponse(activateRequest.getDepartmentId());
    }


    private Long generateDepartmentId(final Long facultyId) {
        final List<Long> departmentIds = departmentRepository.getAllDepartmentIdsByFacultyId(facultyId);
        return DepartmentUtil.generateDepartmentId(facultyId, departmentIds);
    }

    private DepartmentResponse getDepartmentResponse(final Long departmentId) {
        final DepartmentEntity departmentEntity = departmentRepository.getDepartmentById(departmentId);
        return DepartmentInfoConverter.entityToResponse(departmentEntity);
    }


    /**
     * Checks Before Processing
     */

    private void checkBeforeSaving(final Long facultyId) throws SisNotExistException {

        ifFacultyIsNotExistThrowNotExistException(facultyId);
    }

    private void checkBeforeUpdating(final Long departmentId) throws SisNotExistException {

        ifDepartmentIsNotExistThrowNotExistException(departmentId);
    }

    private void checkBeforeDeleting(final Long departmentId) throws SisNotExistException, SisAlreadyException {
        ifDepartmentIsNotExistThrowNotExistException(departmentId);
        ifDepartmentIsAlreadyDeletedThrowAlreadyException(departmentId);
    }

    private void checkBeforePassivating(final Long departmentId) throws SisNotExistException, SisAlreadyException {
        ifDepartmentIsNotExistThrowNotExistException(departmentId);
        ifDepartmentIsAlreadyPassiveThrowAlreadyException(departmentId);
        ifDepartmentIsAlreadyDeletedThrowAlreadyException(departmentId);
    }

    private void checkBeforeActivating(final Long departmentId) throws SisNotExistException, SisAlreadyException {
        ifDepartmentIsNotExistThrowNotExistException(departmentId);
        ifDepartmentIsAlreadyActiveThrowAlreadyException(departmentId);
        ifDepartmentIsAlreadyDeletedThrowAlreadyException(departmentId);
    }


    /**
     * Throw Exceptions
     */

    private void ifFacultyIsNotExistThrowNotExistException(final Long facultyId) throws SisNotExistException {
        if (!facultyRepository.isFacultyExist(facultyId)) {
            DepartmentException.throwNotExistException(facultyId);
        }
    }

    private void ifDepartmentIsNotExistThrowNotExistException(final Long departmentId) throws SisNotExistException {
        if (!departmentRepository.isDepartmentExist(departmentId)) {
            DepartmentException.throwNotExistException(departmentId);
        }
    }

    private void ifDepartmentIsAlreadyDeletedThrowAlreadyException(final Long departmentId) throws SisAlreadyException {
        if (departmentRepository.isDepartmentDeleted(departmentId)) {
            DepartmentException.throwAlreadyDeletedException(departmentId);
        }
    }

    private void ifDepartmentIsAlreadyPassiveThrowAlreadyException(final Long departmentId) throws SisAlreadyException {
        if (departmentRepository.isDepartmentPassive(departmentId)) {
            DepartmentException.throwAlreadyPassiveException(departmentId);
        }
    }

    private void ifDepartmentIsAlreadyActiveThrowAlreadyException(final Long departmentId) throws SisAlreadyException {
        if (departmentRepository.isDepartmentActive(departmentId)) {
            DepartmentException.throwAlreadyActiveException(departmentId);
        }
    }
}
