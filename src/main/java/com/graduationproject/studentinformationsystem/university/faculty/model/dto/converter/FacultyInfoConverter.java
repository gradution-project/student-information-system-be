package com.graduationproject.studentinformationsystem.university.faculty.model.dto.converter;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.SisUtil;
import com.graduationproject.studentinformationsystem.university.faculty.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.faculty.model.dto.response.FacultyResponse;
import com.graduationproject.studentinformationsystem.university.faculty.model.entity.FacultyEntity;
import com.graduationproject.studentinformationsystem.university.faculty.model.enums.FacultyStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FacultyInfoConverter {

    private FacultyInfoConverter() {
    }

    public static FacultyEntity generateSaveEntity(final Long facultyId,
                                                   final FacultySaveRequest saveRequest) {

        final FacultyInfoRequest facultyInfoRequest = saveRequest.getFacultyInfoRequest();
        final SisOperationInfoRequest operationInfoRequest = saveRequest.getOperationInfoRequest();

        return FacultyEntity.builder()
                .facultyId(facultyId)
                .name(facultyInfoRequest.getName())
                .status(FacultyStatus.ACTIVE)
                .createdUserId(operationInfoRequest.getUserId())
                .createdDate(new Date())
                .build();
    }

    public static FacultyEntity generateUpdateEntity(final Long facultyId,
                                                     final FacultyUpdateRequest updateRequest) {

        final FacultyInfoRequest facultyInfoRequest = updateRequest.getFacultyInfoRequest();
        final SisOperationInfoRequest operationInfoRequest = updateRequest.getOperationInfoRequest();

        return FacultyEntity.builder()
                .facultyId(facultyId)
                .name(facultyInfoRequest.getName())
                .status(FacultyStatus.ACTIVE)
                .modifiedUserId(operationInfoRequest.getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static FacultyEntity generateDeleteEntity(final FacultyDeleteRequest deleteRequest) {
        return FacultyEntity.builder()
                .facultyId(deleteRequest.getFacultyId())
                .status(FacultyStatus.DELETED)
                .modifiedUserId(deleteRequest.getOperationInfoRequest().getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static FacultyEntity generatePassiveEntity(final FacultyPassivateRequest passivateRequest) {
        return FacultyEntity.builder()
                .facultyId(passivateRequest.getFacultyId())
                .status(FacultyStatus.PASSIVE)
                .modifiedUserId(passivateRequest.getOperationInfoRequest().getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static FacultyEntity generateActiveEntity(final FacultyActivateRequest activateRequest) {
        return FacultyEntity.builder()
                .facultyId(activateRequest.getFacultyId())
                .status(FacultyStatus.ACTIVE)
                .modifiedUserId(activateRequest.getOperationInfoRequest().getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static FacultyResponse entityToResponse(final FacultyEntity facultyEntity) {
        return FacultyResponse.builder()
                .facultyId(facultyEntity.getFacultyId())
                .name(facultyEntity.getName())
                .status(facultyEntity.getStatus().getName())
                .createdUserId(facultyEntity.getCreatedUserId())
                .createdDate(SisUtil.getFormattedDateTime(facultyEntity.getCreatedDate()))
                .modifiedUserId(facultyEntity.getModifiedUserId())
                .modifiedDate(SisUtil.getFormattedDateTime(facultyEntity.getModifiedDate()))
                .build();
    }

    public static List<FacultyResponse> entitiesToResponses(final List<FacultyEntity> facultyEntities) {
        List<FacultyResponse> facultyResponses = new ArrayList<>();
        facultyEntities.forEach(facultyEntity -> facultyResponses.add(entityToResponse(facultyEntity)));
        return facultyResponses;
    }
}
