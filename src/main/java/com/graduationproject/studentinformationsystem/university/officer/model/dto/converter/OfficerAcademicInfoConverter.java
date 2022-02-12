package com.graduationproject.studentinformationsystem.university.officer.model.dto.converter;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.SisUtil;
import com.graduationproject.studentinformationsystem.university.faculty.model.dto.converter.FacultyInfoConverter;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.response.OfficerAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.officer.model.entity.OfficerAcademicInfoEntity;
import com.graduationproject.studentinformationsystem.university.officer.model.enums.OfficerStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OfficerAcademicInfoConverter {

    private OfficerAcademicInfoConverter() {
    }

    public static OfficerAcademicInfoEntity generateSaveEntity(final Long officerId,
                                                               final String officerEmail,
                                                               final OfficerAcademicInfoRequest academicInfoRequest,
                                                               final SisOperationInfoRequest operationInfoRequest) {

        return OfficerAcademicInfoEntity.builder()
                .officerId(officerId)
                .facultyId(academicInfoRequest.getFacultyId())
                .phoneNumber(Long.parseLong(academicInfoRequest.getPhoneNumber()))
                .email(officerEmail)
                .status(OfficerStatus.ACTIVE)
                .registrationDate(new Date())
                .createdUserId(operationInfoRequest.getUserId())
                .createdDate(new Date())
                .build();
    }

    public static OfficerAcademicInfoEntity generateUpdateEntity(final Long officerId,
                                                                 final OfficerAcademicInfoUpdateRequest academicInfoUpdateRequest) {

        final OfficerAcademicInfoRequest academicInfoRequest = academicInfoUpdateRequest.getAcademicInfoRequest();
        final SisOperationInfoRequest operationInfoRequest = academicInfoUpdateRequest.getOperationInfoRequest();

        return OfficerAcademicInfoEntity.builder()
                .officerId(officerId)
                .facultyId(academicInfoRequest.getFacultyId())
                .phoneNumber(Long.parseLong(academicInfoRequest.getPhoneNumber()))
                .modifiedUserId(operationInfoRequest.getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static OfficerAcademicInfoEntity generateDeleteEntity(final OfficerDeleteRequest deleteRequest) {
        return OfficerAcademicInfoEntity.builder()
                .officerId(deleteRequest.getOfficerId())
                .status(OfficerStatus.DELETED)
                .modifiedUserId(deleteRequest.getOperationInfoRequest().getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static OfficerAcademicInfoEntity generatePassiveEntity(final OfficerPassivateRequest passivateRequest) {
        return OfficerAcademicInfoEntity.builder()
                .officerId(passivateRequest.getOfficerId())
                .status(OfficerStatus.PASSIVE)
                .modifiedUserId(passivateRequest.getOperationInfoRequest().getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static OfficerAcademicInfoEntity generateActiveEntity(final OfficerActivateRequest activateRequest) {
        return OfficerAcademicInfoEntity.builder()
                .officerId(activateRequest.getOfficerId())
                .status(OfficerStatus.ACTIVE)
                .modifiedUserId(activateRequest.getOperationInfoRequest().getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static OfficerAcademicInfoResponse entityToResponse(final OfficerAcademicInfoEntity academicInfoEntity) {
        return OfficerAcademicInfoResponse.builder()
                .officerId(academicInfoEntity.getOfficerId())
                .facultyResponse(FacultyInfoConverter.entityToResponse(academicInfoEntity.getFacultyEntity()))
                .phoneNumber(SisUtil.getFormattedPhoneNumber(academicInfoEntity.getPhoneNumber()))
                .email(academicInfoEntity.getEmail())
                .status(academicInfoEntity.getStatus())
                .registrationDate(SisUtil.getFormattedDate(academicInfoEntity.getRegistrationDate()))
                .createdUserId(academicInfoEntity.getCreatedUserId())
                .createdDate(SisUtil.getFormattedDateTime(academicInfoEntity.getCreatedDate()))
                .modifiedUserId(academicInfoEntity.getModifiedUserId())
                .modifiedDate(SisUtil.getFormattedDateTime(academicInfoEntity.getModifiedDate())).build();
    }

    public static List<OfficerAcademicInfoResponse> entitiesToResponses(final List<OfficerAcademicInfoEntity> academicInfoEntities) {
        List<OfficerAcademicInfoResponse> academicInfoResponses = new ArrayList<>();
        academicInfoEntities.forEach(academicInfoEntity -> academicInfoResponses.add(entityToResponse(academicInfoEntity)));
        return academicInfoResponses;
    }
}
