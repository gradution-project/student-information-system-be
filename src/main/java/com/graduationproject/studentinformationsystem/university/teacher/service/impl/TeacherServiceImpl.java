package com.graduationproject.studentinformationsystem.university.teacher.service.impl;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.login.teacher.password.service.TeacherPasswordOperationOutService;
import com.graduationproject.studentinformationsystem.university.mail.service.TeacherMailService;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.converter.TeacherInfoResponseConverter;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.converter.TeacherResponseConverter;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherInfoDetailResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherInfoResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.enums.TeacherStatus;
import com.graduationproject.studentinformationsystem.university.teacher.model.exception.TeacherException;
import com.graduationproject.studentinformationsystem.university.teacher.service.TeacherAcademicInfoService;
import com.graduationproject.studentinformationsystem.university.teacher.service.TeacherPersonalInfoService;
import com.graduationproject.studentinformationsystem.university.teacher.service.TeacherService;
import com.graduationproject.studentinformationsystem.university.teacher.util.TeacherUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherPasswordOperationOutService passwordOperationOutService;

    private final TeacherAcademicInfoService academicInfoService;
    private final TeacherPersonalInfoService personalInfoService;
    private final TeacherMailService teacherMailService;

    @Override
    public List<TeacherInfoResponse> getAllTeachersByStatus(final TeacherStatus status) {
        final List<TeacherAcademicInfoResponse> academicInfoResponses = academicInfoService.getAllTeacherAcademicInfosByStatus(status);
        final List<TeacherPersonalInfoResponse> personalInfoResponses = personalInfoService.getAllTeacherPersonalInfosByStatus(status);
        return TeacherResponseConverter.infoResponsesListToResponseList(academicInfoResponses, personalInfoResponses);
    }

    @Override
    public TeacherInfoDetailResponse getTeacherDetailById(final Long teacherId) throws SisNotExistException {
        ifTeacherIsNotExistThrowNotExistException(teacherId);
        return getTeacherInfoResponse(teacherId);
    }

    @Override
    public TeacherInfoDetailResponse saveTeacher(final TeacherSaveRequest saveRequest) throws SisNotExistException {
        checkBeforeSaving(saveRequest);

        final Long teacherId = generateTeacherId(saveRequest.getAcademicInfoRequest().getDepartmentId());
        final String teacherEmail = generateTeacherEmail(saveRequest);
        final TeacherAcademicInfoRequest academicInfoRequest = saveRequest.getAcademicInfoRequest();
        final TeacherPersonalInfoRequest personalInfoRequest = saveRequest.getPersonalInfoRequest();
        final SisOperationInfoRequest operationInfoRequest = saveRequest.getOperationInfoRequest();

        academicInfoService.saveTeacherAcademicInfo(teacherId, teacherEmail, academicInfoRequest, operationInfoRequest);
        personalInfoService.saveTeacherPersonalInfo(teacherId, personalInfoRequest, operationInfoRequest);

        final TeacherInfoDetailResponse infoDetailResponse = getTeacherInfoResponse(teacherId);

        passwordOperationOutService.saveOrUpdatePasswordOperation(teacherId, saveRequest.getOperationInfoRequest().getFeUrl());
        teacherMailService.sendSavedEmail(infoDetailResponse);
        return infoDetailResponse;
    }

    @Override
    public TeacherAcademicInfoResponse updateTeacherAcademicInfo(final Long teacherId,
                                                                 final TeacherAcademicInfoUpdateRequest academicInfoUpdateRequest)
            throws SisNotExistException {

        checkBeforeUpdatingAcademicInfo(teacherId, academicInfoUpdateRequest);
        return academicInfoService.updateTeacherAcademicInfo(teacherId, academicInfoUpdateRequest);
    }

    @Override
    public TeacherPersonalInfoResponse updateTeacherPersonalInfo(final Long teacherId,
                                                                 final TeacherPersonalInfoUpdateRequest personalInfoUpdateRequest)
            throws SisNotExistException {

        checkBeforeUpdatingPersonalInfo(teacherId);
        return personalInfoService.updateTeacherPersonalInfo(teacherId, personalInfoUpdateRequest);
    }

    @Override
    public TeacherInfoResponse deleteTeacher(final TeacherDeleteRequest deleteRequest)
            throws SisNotExistException, SisAlreadyException {

        checkBeforeDeleting(deleteRequest.getTeacherId());
        academicInfoService.deleteTeacherAcademicInfo(deleteRequest);
        personalInfoService.deleteTeacherPersonalInfo(deleteRequest);
        return getTeacherResponse(deleteRequest.getTeacherId());
    }

    @Override
    public TeacherInfoResponse passivateTeacher(final TeacherPassivateRequest passivateRequest)
            throws SisNotExistException, SisAlreadyException {

        checkBeforePassivating(passivateRequest.getTeacherId());
        academicInfoService.passivateTeacherAcademicInfo(passivateRequest);
        personalInfoService.passivateTeacherPersonalInfo(passivateRequest);
        return getTeacherResponse(passivateRequest.getTeacherId());
    }

    @Override
    public TeacherInfoResponse activateTeacher(final TeacherActivateRequest activateRequest)
            throws SisNotExistException, SisAlreadyException {

        checkBeforeActivating(activateRequest.getTeacherId());
        academicInfoService.activateTeacherAcademicInfo(activateRequest);
        personalInfoService.activateTeacherPersonalInfo(activateRequest);
        return getTeacherResponse(activateRequest.getTeacherId());
    }


    private Long generateTeacherId(final Long departmentId) {
        final List<Long> teacherIds = academicInfoService.getAllTeacherIdsByDepartmentId(departmentId);
        return TeacherUtil.generateTeacherId(departmentId, teacherIds);
    }

    private String generateTeacherEmail(final TeacherSaveRequest saveRequest) {
        final String name = saveRequest.getPersonalInfoRequest().getName();
        final String surname = saveRequest.getPersonalInfoRequest().getSurname();
        return TeacherUtil.generateTeacherEmail(name, surname);
    }

    private TeacherInfoDetailResponse getTeacherInfoResponse(final Long teacherId) {
        final TeacherAcademicInfoResponse academicInfoResponse = academicInfoService.getTeacherAcademicInfoById(teacherId);
        final TeacherPersonalInfoResponse personalInfoResponse = personalInfoService.getTeacherPersonalInfoById(teacherId);
        return TeacherInfoResponseConverter.convert(academicInfoResponse, personalInfoResponse);
    }

    private TeacherInfoResponse getTeacherResponse(final Long teacherId) {
        final TeacherAcademicInfoResponse academicInfoResponse = academicInfoService.getTeacherAcademicInfoById(teacherId);
        final TeacherPersonalInfoResponse personalInfoResponse = personalInfoService.getTeacherPersonalInfoById(teacherId);
        return TeacherResponseConverter.infoResponsesToResponse(academicInfoResponse, personalInfoResponse);
    }


    /**
     * Checks Before Processing
     */

    private void checkBeforeSaving(final TeacherSaveRequest saveRequest) {
        ifDepartmentIsNotExistThrowNotExistException(saveRequest.getAcademicInfoRequest().getDepartmentId());
    }

    private void checkBeforeUpdatingAcademicInfo(final Long teacherId, final TeacherAcademicInfoUpdateRequest academicInfoUpdateRequest)
            throws SisNotExistException {

        ifTeacherIsNotExistThrowNotExistException(teacherId);
        ifDepartmentIsNotExistThrowNotExistException(academicInfoUpdateRequest.getAcademicInfoRequest().getDepartmentId());
    }

    private void checkBeforeUpdatingPersonalInfo(final Long teacherId) throws SisNotExistException {
        ifTeacherIsNotExistThrowNotExistException(teacherId);
    }

    private void checkBeforeDeleting(final Long teacherId) throws SisNotExistException, SisAlreadyException {
        ifTeacherIsNotExistThrowNotExistException(teacherId);
        ifTeacherIsAlreadyDeletedThrowAlreadyException(teacherId);
    }

    private void checkBeforePassivating(final Long teacherId) throws SisNotExistException, SisAlreadyException {
        ifTeacherIsNotExistThrowNotExistException(teacherId);
        ifTeacherIsAlreadyPassiveThrowAlreadyException(teacherId);
        ifTeacherIsAlreadyDeletedThrowAlreadyException(teacherId);
    }

    private void checkBeforeActivating(final Long teacherId) throws SisNotExistException, SisAlreadyException {
        ifTeacherIsNotExistThrowNotExistException(teacherId);
        ifTeacherIsAlreadyActiveThrowAlreadyException(teacherId);
        ifTeacherIsAlreadyDeletedThrowAlreadyException(teacherId);
    }


    /**
     * Throw Exceptions
     */

    private void ifTeacherIsNotExistThrowNotExistException(final Long teacherId) throws SisNotExistException {
        if (!academicInfoService.isTeacherExist(teacherId)) {
            TeacherException.throwNotExistException(teacherId);
        }
    }

    private void ifDepartmentIsNotExistThrowNotExistException(final Long departmentId) {
        // TODO: ifDepartmentIsNotExistThrowNotExistException
//        if (!departmentService.isDepartmentExist(teacherId)) {
//            SisException.throwNotExistException();
//        }
    }

    private void ifTeacherIsAlreadyDeletedThrowAlreadyException(final Long teacherId) throws SisAlreadyException {
        if (academicInfoService.isTeacherDeleted(teacherId)) {
            TeacherException.throwAlreadyDeletedException(teacherId);
        }
    }

    private void ifTeacherIsAlreadyPassiveThrowAlreadyException(final Long teacherId) throws SisAlreadyException {
        if (academicInfoService.isTeacherPassive(teacherId)) {
            TeacherException.throwAlreadyPassiveException(teacherId);
        }
    }

    private void ifTeacherIsAlreadyActiveThrowAlreadyException(final Long teacherId) throws SisAlreadyException {
        if (academicInfoService.isTeacherActive(teacherId)) {
            TeacherException.throwAlreadyActiveException(teacherId);
        }
    }
}
