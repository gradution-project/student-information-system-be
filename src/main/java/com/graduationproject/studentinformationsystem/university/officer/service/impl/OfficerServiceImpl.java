package com.graduationproject.studentinformationsystem.university.officer.service.impl;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.mail.service.OfficerMailService;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.converter.OfficerInfoResponseConverter;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.converter.OfficerResponseConverter;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.response.OfficerAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.response.OfficerInfoDetailResponse;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.response.OfficerInfoResponse;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.response.OfficerPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.university.officer.model.enums.OfficerStatus;
import com.graduationproject.studentinformationsystem.university.officer.model.exception.OfficerException;
import com.graduationproject.studentinformationsystem.university.officer.service.OfficerAcademicInfoService;
import com.graduationproject.studentinformationsystem.university.officer.service.OfficerPersonalInfoService;
import com.graduationproject.studentinformationsystem.university.officer.service.OfficerService;
import com.graduationproject.studentinformationsystem.university.officer.util.OfficerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfficerServiceImpl implements OfficerService {

    private final OfficerAcademicInfoService academicInfoService;
    private final OfficerPersonalInfoService personalInfoService;
    private final OfficerMailService officerMailService;

    @Override
    public List<OfficerInfoResponse> getAllOfficersByStatus(final OfficerStatus status) {
        final List<OfficerAcademicInfoResponse> academicInfoResponses = academicInfoService.getAllOfficerAcademicInfosByStatus(status);
        final List<OfficerPersonalInfoResponse> personalInfoResponses = personalInfoService.getAllOfficerPersonalInfosByStatus(status);
        return OfficerResponseConverter.infoResponsesListToResponseList(academicInfoResponses, personalInfoResponses);
    }

    @Override
    public OfficerInfoDetailResponse getOfficerDetailById(final Long officerId) throws SisNotExistException {
        ifOfficerIsNotExistThrowNotExistException(officerId);
        return getOfficerInfoResponse(officerId);
    }

    @Override
    public OfficerInfoDetailResponse saveOfficer(final OfficerSaveRequest saveRequest) {
        checkBeforeSaving(saveRequest);

        final Long officerId = generateOfficerId(saveRequest.getAcademicInfoRequest().getFacultyId());
        final String officerEmail = generateOfficerEmail(saveRequest);
        final OfficerAcademicInfoRequest academicInfoRequest = saveRequest.getAcademicInfoRequest();
        final OfficerPersonalInfoRequest personalInfoRequest = saveRequest.getPersonalInfoRequest();
        final SisOperationInfoRequest operationInfoRequest = saveRequest.getOperationInfoRequest();

        academicInfoService.saveOfficerAcademicInfo(officerId, officerEmail, academicInfoRequest, operationInfoRequest);
        personalInfoService.saveOfficerPersonalInfo(officerId, personalInfoRequest, operationInfoRequest);

        final OfficerInfoDetailResponse infoDetailResponse = getOfficerInfoResponse(officerId);
        officerMailService.sendFirstPasswordEmail(infoDetailResponse);
        return infoDetailResponse;
    }

    @Override
    public OfficerAcademicInfoResponse updateOfficerAcademicInfo(final Long officerId,
                                                                 final OfficerAcademicInfoUpdateRequest academicInfoUpdateRequest)
            throws SisNotExistException {

        checkBeforeUpdatingAcademicInfo(officerId, academicInfoUpdateRequest);
        return academicInfoService.updateOfficerAcademicInfo(officerId, academicInfoUpdateRequest);
    }

    @Override
    public OfficerPersonalInfoResponse updateOfficerPersonalInfo(final Long officerId,
                                                                 final OfficerPersonalInfoUpdateRequest personalInfoUpdateRequest)
            throws SisNotExistException {

        checkBeforeUpdatingPersonalInfo(officerId);
        return personalInfoService.updateOfficerPersonalInfo(officerId, personalInfoUpdateRequest);
    }

    @Override
    public OfficerInfoResponse deleteOfficer(final OfficerDeleteRequest deleteRequest)
            throws SisNotExistException, SisAlreadyException {

        checkBeforeDeleting(deleteRequest.getOfficerId());
        academicInfoService.deleteOfficerAcademicInfo(deleteRequest);
        personalInfoService.deleteOfficerPersonalInfo(deleteRequest);
        return getOfficerResponse(deleteRequest.getOfficerId());
    }

    @Override
    public OfficerInfoResponse passivateOfficer(final OfficerPassivateRequest passivateRequest)
            throws SisNotExistException, SisAlreadyException {

        checkBeforePassivating(passivateRequest.getOfficerId());
        academicInfoService.passivateOfficerAcademicInfo(passivateRequest);
        personalInfoService.passivateOfficerPersonalInfo(passivateRequest);
        return getOfficerResponse(passivateRequest.getOfficerId());
    }

    @Override
    public OfficerInfoResponse activateOfficer(final OfficerActivateRequest activateRequest)
            throws SisNotExistException, SisAlreadyException {

        checkBeforeActivating(activateRequest.getOfficerId());
        academicInfoService.activateOfficerAcademicInfo(activateRequest);
        personalInfoService.activateOfficerPersonalInfo(activateRequest);
        return getOfficerResponse(activateRequest.getOfficerId());
    }


    private Long generateOfficerId(final Long facultyId) {
        final List<Long> officerIds = academicInfoService.getAllOfficerIdsByFacultyId(facultyId);
        return OfficerUtil.generateOfficerId(facultyId, officerIds);
    }

    private String generateOfficerEmail(final OfficerSaveRequest saveRequest) {
        final String name = saveRequest.getPersonalInfoRequest().getName();
        final String surname = saveRequest.getPersonalInfoRequest().getSurname();
        return OfficerUtil.generateOfficerEmail(name, surname);
    }

    private OfficerInfoDetailResponse getOfficerInfoResponse(final Long officerId) {
        final OfficerAcademicInfoResponse academicInfoResponse = academicInfoService.getOfficerAcademicInfoById(officerId);
        final OfficerPersonalInfoResponse personalInfoResponse = personalInfoService.getOfficerPersonalInfoById(officerId);
        return OfficerInfoResponseConverter.convert(academicInfoResponse, personalInfoResponse);
    }

    private OfficerInfoResponse getOfficerResponse(final Long officerId) {
        final OfficerAcademicInfoResponse academicInfoResponse = academicInfoService.getOfficerAcademicInfoById(officerId);
        final OfficerPersonalInfoResponse personalInfoResponse = personalInfoService.getOfficerPersonalInfoById(officerId);
        return OfficerResponseConverter.infoResponsesToResponse(academicInfoResponse, personalInfoResponse);
    }


    /**
     * Checks Before Processing
     */

    private void checkBeforeSaving(final OfficerSaveRequest saveRequest) {
        ifFacultyIsNotExistThrowNotExistException(saveRequest.getAcademicInfoRequest().getFacultyId());
    }

    private void checkBeforeUpdatingAcademicInfo(final Long officerId, final OfficerAcademicInfoUpdateRequest academicInfoUpdateRequest)
            throws SisNotExistException {

        ifOfficerIsNotExistThrowNotExistException(officerId);
        ifFacultyIsNotExistThrowNotExistException(academicInfoUpdateRequest.getAcademicInfoRequest().getFacultyId());
    }

    private void checkBeforeUpdatingPersonalInfo(final Long officerId) throws SisNotExistException {
        ifOfficerIsNotExistThrowNotExistException(officerId);
    }

    private void checkBeforeDeleting(final Long officerId) throws SisNotExistException, SisAlreadyException {
        ifOfficerIsNotExistThrowNotExistException(officerId);
        ifOfficerIsAlreadyDeletedThrowAlreadyException(officerId);
    }

    private void checkBeforePassivating(final Long officerId) throws SisNotExistException, SisAlreadyException {
        ifOfficerIsNotExistThrowNotExistException(officerId);
        ifOfficerIsAlreadyPassiveThrowAlreadyException(officerId);
        ifOfficerIsAlreadyDeletedThrowAlreadyException(officerId);
    }

    private void checkBeforeActivating(final Long officerId) throws SisNotExistException, SisAlreadyException {
        ifOfficerIsNotExistThrowNotExistException(officerId);
        ifOfficerIsAlreadyActiveThrowAlreadyException(officerId);
        ifOfficerIsAlreadyDeletedThrowAlreadyException(officerId);
    }


    /**
     * Throw Exceptions
     */

    private void ifOfficerIsNotExistThrowNotExistException(final Long officerId) throws SisNotExistException {
        if (!academicInfoService.isOfficerExist(officerId)) {
            OfficerException.throwNotExistException(officerId);
        }
    }

    private void ifFacultyIsNotExistThrowNotExistException(final Long facultyId) {
        // TODO: ifFacultyIsNotExistThrowNotExistException
//        if (!facultyService.isFacultyExist(facultyId)) {
//            SisException.throwNotExistException();
//        }
    }

    private void ifOfficerIsAlreadyDeletedThrowAlreadyException(final Long officerId) throws SisAlreadyException {
        if (academicInfoService.isOfficerDeleted(officerId)) {
            OfficerException.throwAlreadyDeletedException(officerId);
        }
    }

    private void ifOfficerIsAlreadyPassiveThrowAlreadyException(final Long officerId) throws SisAlreadyException {
        if (academicInfoService.isOfficerPassive(officerId)) {
            OfficerException.throwAlreadyPassiveException(officerId);
        }
    }

    private void ifOfficerIsAlreadyActiveThrowAlreadyException(final Long officerId) throws SisAlreadyException {
        if (academicInfoService.isOfficerActive(officerId)) {
            OfficerException.throwAlreadyActiveException(officerId);
        }
    }
}
