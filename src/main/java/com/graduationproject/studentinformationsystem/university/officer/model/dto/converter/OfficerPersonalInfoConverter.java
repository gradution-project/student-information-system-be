package com.graduationproject.studentinformationsystem.university.officer.model.dto.converter;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.SisUtil;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.response.OfficerPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.university.officer.model.entity.OfficerPersonalInfoEntity;
import com.graduationproject.studentinformationsystem.university.officer.model.enums.OfficerStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OfficerPersonalInfoConverter {

    private OfficerPersonalInfoConverter() {
    }

    public static OfficerPersonalInfoEntity generateSaveEntity(Long officerId,
                                                               OfficerPersonalInfoRequest personalInfoRequest,
                                                               SisOperationInfoRequest operationInfoRequest) {

        return OfficerPersonalInfoEntity.builder()
                .officerId(officerId)
                .tcNo(personalInfoRequest.getTcNo())
                .name(personalInfoRequest.getName())
                .surname(personalInfoRequest.getSurname())
                .email(personalInfoRequest.getEmail())
                .phoneNumber(personalInfoRequest.getPhoneNumber())
                .status(OfficerStatus.ACTIVE)
//                .profilePhoto(personalInfoRequest.getProfilePhoto()) // TODO: Set Profile Photo
//                .profilePhotoUrl(personalInfoRequest.getProfilePhotoUrl()) // TODO: Set Profile Photo URL
                .birthday(personalInfoRequest.getBirthday())
                .address(personalInfoRequest.getAddress())
                .createdUserId(operationInfoRequest.getUserId())
                .createdDate(new Date())
                .build();
    }

    public static OfficerPersonalInfoEntity generateUpdateEntity(final Long officerId,
                                                                 final OfficerPersonalInfoUpdateRequest personalInfoUpdateRequest) {

        final OfficerPersonalInfoRequest personalInfoRequest = personalInfoUpdateRequest.getPersonalInfoRequest();
        final SisOperationInfoRequest operationInfoRequest = personalInfoUpdateRequest.getOperationInfoRequest();

        return OfficerPersonalInfoEntity.builder()
                .officerId(officerId)
                .tcNo(personalInfoRequest.getTcNo())
                .name(personalInfoRequest.getName())
                .surname(personalInfoRequest.getSurname())
                .email(personalInfoRequest.getEmail())
                .phoneNumber(personalInfoRequest.getPhoneNumber())
//                .profilePhoto(personalInfoRequest.getProfilePhoto()) // TODO: Set Profile Photo
//                .profilePhotoUrl(personalInfoRequest.getProfilePhotoUrl()) // TODO: Set Profile Photo URL
                .birthday(personalInfoRequest.getBirthday())
                .address(personalInfoRequest.getAddress())
                .modifiedUserId(operationInfoRequest.getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static OfficerPersonalInfoEntity generateDeleteEntity(OfficerDeleteRequest deleteRequest) {
        return OfficerPersonalInfoEntity.builder()
                .officerId(deleteRequest.getOfficerId())
                .status(OfficerStatus.DELETED)
                .modifiedUserId(deleteRequest.getOperationInfoRequest().getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static OfficerPersonalInfoEntity generatePassiveEntity(OfficerPassivateRequest passivateRequest) {
        return OfficerPersonalInfoEntity.builder()
                .officerId(passivateRequest.getOfficerId())
                .status(OfficerStatus.PASSIVE)
                .modifiedUserId(passivateRequest.getOperationInfoRequest().getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static OfficerPersonalInfoEntity generateActiveEntity(OfficerActivateRequest activateRequest) {
        return OfficerPersonalInfoEntity.builder()
                .officerId(activateRequest.getOfficerId())
                .status(OfficerStatus.ACTIVE)
                .modifiedUserId(activateRequest.getOperationInfoRequest().getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static OfficerPersonalInfoResponse entityToResponse(OfficerPersonalInfoEntity personalInfoEntity) {
        return OfficerPersonalInfoResponse.builder()
                .tcNo(personalInfoEntity.getTcNo())
                .name(personalInfoEntity.getName())
                .surname(personalInfoEntity.getSurname())
                .email(personalInfoEntity.getEmail())
                .phoneNumber(SisUtil.getFormattedPhoneNumber(personalInfoEntity.getPhoneNumber()))
                .birthday(SisUtil.getFormattedDate(personalInfoEntity.getBirthday()))
                .address(personalInfoEntity.getAddress())
                .createdUserId(personalInfoEntity.getCreatedUserId())
                .createdDate(SisUtil.getFormattedDateTime(personalInfoEntity.getCreatedDate()))
                .modifiedUserId(personalInfoEntity.getModifiedUserId())
                .modifiedDate(SisUtil.getFormattedDateTime(personalInfoEntity.getCreatedDate())).build();
    }

    public static List<OfficerPersonalInfoResponse> entityListToResponseList(List<OfficerPersonalInfoEntity> personalInfoEntities) {
        List<OfficerPersonalInfoResponse> personalInfoResponses = new ArrayList<>();
        personalInfoEntities.forEach(personalInfoEntity -> personalInfoResponses.add(entityToResponse(personalInfoEntity)));
        return personalInfoResponses;
    }
}
