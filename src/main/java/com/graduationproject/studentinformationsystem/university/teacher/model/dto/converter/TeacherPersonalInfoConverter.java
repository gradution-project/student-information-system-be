package com.graduationproject.studentinformationsystem.university.teacher.model.dto.converter;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.SisUtil;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.entity.TeacherPersonalInfoEntity;
import com.graduationproject.studentinformationsystem.university.teacher.model.enums.TeacherStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TeacherPersonalInfoConverter {

    private TeacherPersonalInfoConverter() {
    }

    public static TeacherPersonalInfoEntity generateSaveEntity(Long teacherId,
                                                               TeacherPersonalInfoRequest personalInfoRequest,
                                                               SisOperationInfoRequest operationInfoRequest) {

        return TeacherPersonalInfoEntity.builder()
                .teacherId(teacherId)
                .tcNo(personalInfoRequest.getTcNo())
                .name(personalInfoRequest.getName())
                .surname(personalInfoRequest.getSurname())
                .email(personalInfoRequest.getEmail())
                .phoneNumber(personalInfoRequest.getPhoneNumber())
                .status(TeacherStatus.ACTIVE)
//                .profilePhoto(personalInfoRequest.getProfilePhoto()) // TODO: Set Profile Photo
//                .profilePhotoUrl(personalInfoRequest.getProfilePhotoUrl()) // TODO: Set Profile Photo URL
                .birthday(personalInfoRequest.getBirthday())
                .address(personalInfoRequest.getAddress())
                .createdUserId(operationInfoRequest.getUserId())
                .createdDate(new Date())
                .build();
    }

    public static TeacherPersonalInfoEntity generateUpdateEntity(final Long teacherId,
                                                                 final TeacherPersonalInfoUpdateRequest personalInfoUpdateRequest) {

        final TeacherPersonalInfoRequest personalInfoRequest = personalInfoUpdateRequest.getPersonalInfoRequest();
        final SisOperationInfoRequest operationInfoRequest = personalInfoUpdateRequest.getOperationInfoRequest();

        return TeacherPersonalInfoEntity.builder()
                .teacherId(teacherId)
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

    public static TeacherPersonalInfoEntity generateDeleteEntity(TeacherDeleteRequest deleteRequest) {
        return TeacherPersonalInfoEntity.builder()
                .teacherId(deleteRequest.getTeacherId())
                .status(TeacherStatus.DELETED)
                .modifiedUserId(deleteRequest.getOperationInfoRequest().getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static TeacherPersonalInfoEntity generatePassiveEntity(TeacherPassivateRequest passivateRequest) {
        return TeacherPersonalInfoEntity.builder()
                .teacherId(passivateRequest.getTeacherId())
                .status(TeacherStatus.PASSIVE)
                .modifiedUserId(passivateRequest.getOperationInfoRequest().getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static TeacherPersonalInfoEntity generateActiveEntity(TeacherActivateRequest activateRequest) {
        return TeacherPersonalInfoEntity.builder()
                .teacherId(activateRequest.getTeacherId())
                .status(TeacherStatus.ACTIVE)
                .modifiedUserId(activateRequest.getOperationInfoRequest().getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static TeacherPersonalInfoResponse entityToResponse(TeacherPersonalInfoEntity personalInfoEntity) {
        return TeacherPersonalInfoResponse.builder()
                .tcNo(personalInfoEntity.getTcNo())
                .name(personalInfoEntity.getName())
                .surname(personalInfoEntity.getSurname())
                .email(personalInfoEntity.getEmail())
                .phoneNumber(SisUtil.getFormattedPhoneNumber(personalInfoEntity.getPhoneNumber()))
                .birthday(SisUtil.getFormattedDate(personalInfoEntity.getBirthday()))
                .address(personalInfoEntity.getAddress()).build();
    }

    public static List<TeacherPersonalInfoResponse> entityListToResponseList(List<TeacherPersonalInfoEntity> personalInfoEntities) {
        List<TeacherPersonalInfoResponse> personalInfoResponses = new ArrayList<>();
        personalInfoEntities.forEach(personalInfoEntity -> personalInfoResponses.add(entityToResponse(personalInfoEntity)));
        return personalInfoResponses;
    }
}
