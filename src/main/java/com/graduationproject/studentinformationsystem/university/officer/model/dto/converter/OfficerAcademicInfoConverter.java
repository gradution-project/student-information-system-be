package com.graduationproject.studentinformationsystem.university.officer.model.dto.converter;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.SisUtil;
import com.graduationproject.studentinformationsystem.university.faculty.model.dto.response.FacultyResponse;
import com.graduationproject.studentinformationsystem.university.faculty.service.FacultyOutService;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.response.OfficerAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.officer.model.entity.OfficerAcademicInfoEntity;
import com.graduationproject.studentinformationsystem.university.officer.model.enums.OfficerStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OfficerAcademicInfoConverter {

    private final FacultyOutService facultyOutService;

    public OfficerAcademicInfoEntity generateSaveEntity(final Long officerId,
                                                        final String officerEmail,
                                                        final OfficerAcademicInfoRequest academicInfoRequest,
                                                        final SisOperationInfoRequest operationInfoRequest) {

        return OfficerAcademicInfoEntity.builder()
                .officerId(officerId)
                .facultyId(academicInfoRequest.getFacultyId())
                .phoneNumber(SisUtil.getUnformattedPhoneNumber(academicInfoRequest.getPhoneNumber()))
                .email(officerEmail)
                .status(OfficerStatus.ACTIVE)
                .registrationDate(new Date())
                .createdUserId(operationInfoRequest.getUserId())
                .createdDate(new Date())
                .build();
    }

    public OfficerAcademicInfoEntity generateUpdateEntity(final Long officerId,
                                                          final OfficerAcademicInfoUpdateRequest academicInfoUpdateRequest) {

        final OfficerAcademicInfoRequest academicInfoRequest = academicInfoUpdateRequest.getAcademicInfoRequest();
        final SisOperationInfoRequest operationInfoRequest = academicInfoUpdateRequest.getOperationInfoRequest();

        return OfficerAcademicInfoEntity.builder()
                .officerId(officerId)
                .facultyId(academicInfoRequest.getFacultyId())
                .phoneNumber(SisUtil.getUnformattedPhoneNumber(academicInfoRequest.getPhoneNumber()))
                .modifiedUserId(operationInfoRequest.getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public OfficerAcademicInfoEntity generateDeleteEntity(final OfficerDeleteRequest deleteRequest) {
        return OfficerAcademicInfoEntity.builder()
                .officerId(deleteRequest.getOfficerId())
                .status(OfficerStatus.DELETED)
                .modifiedUserId(deleteRequest.getOperationInfoRequest().getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public OfficerAcademicInfoEntity generatePassiveEntity(final OfficerPassivateRequest passivateRequest) {
        return OfficerAcademicInfoEntity.builder()
                .officerId(passivateRequest.getOfficerId())
                .status(OfficerStatus.PASSIVE)
                .modifiedUserId(passivateRequest.getOperationInfoRequest().getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public OfficerAcademicInfoEntity generateActiveEntity(final OfficerActivateRequest activateRequest) {
        return OfficerAcademicInfoEntity.builder()
                .officerId(activateRequest.getOfficerId())
                .status(OfficerStatus.ACTIVE)
                .modifiedUserId(activateRequest.getOperationInfoRequest().getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public OfficerAcademicInfoResponse entityToResponse(final OfficerAcademicInfoEntity academicInfoEntity) {

        final FacultyResponse facultyResponse = facultyOutService.getFacultyResponse(academicInfoEntity.getFacultyId());

        return OfficerAcademicInfoResponse.builder()
                .officerId(academicInfoEntity.getOfficerId())
                .phoneNumber(SisUtil.getFormattedPhoneNumber(academicInfoEntity.getPhoneNumber()))
                .email(academicInfoEntity.getEmail())
                .status(academicInfoEntity.getStatus())
                .registrationDate(SisUtil.getFormattedDate(academicInfoEntity.getRegistrationDate()))
                .createdUserId(academicInfoEntity.getCreatedUserId())
                .createdDate(SisUtil.getFormattedDateTime(academicInfoEntity.getCreatedDate()))
                .modifiedUserId(academicInfoEntity.getModifiedUserId())
                .modifiedDate(SisUtil.getFormattedDateTime(academicInfoEntity.getModifiedDate()))
                .facultyResponse(facultyResponse)
                .build();
    }

    public List<OfficerAcademicInfoResponse> entitiesToResponses(final List<OfficerAcademicInfoEntity> academicInfoEntities) {
        List<OfficerAcademicInfoResponse> academicInfoResponses = new ArrayList<>();
        academicInfoEntities.forEach(academicInfoEntity -> academicInfoResponses.add(entityToResponse(academicInfoEntity)));
        return academicInfoResponses;
    }
}
