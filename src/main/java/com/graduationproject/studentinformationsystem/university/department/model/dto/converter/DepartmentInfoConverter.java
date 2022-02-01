package com.graduationproject.studentinformationsystem.university.department.model.dto.converter;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.SisUtil;
import com.graduationproject.studentinformationsystem.university.department.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.department.model.dto.response.DepartmentResponse;
import com.graduationproject.studentinformationsystem.university.department.model.entity.DepartmentEntity;
import com.graduationproject.studentinformationsystem.university.department.model.enums.DepartmentStatus;
import com.graduationproject.studentinformationsystem.university.faculty.model.dto.converter.FacultyInfoConverter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DepartmentInfoConverter {

    private DepartmentInfoConverter() {
    }

    public static DepartmentEntity generateSaveEntity(final Long departmentId,
                                                      final DepartmentSaveRequest saveRequest) {

        final DepartmentInfoRequest departmentInfoRequest = saveRequest.getDepartmentInfoRequest();
        final SisOperationInfoRequest operationInfoRequest = saveRequest.getOperationInfoRequest();

        return DepartmentEntity.builder()
                .departmentId(departmentId)
                .facultyId(departmentInfoRequest.getFacultyId())
                .name(departmentInfoRequest.getName())
                .status(DepartmentStatus.ACTIVE)
                .totalClassLevel(departmentInfoRequest.getTotalClassLevel())
                .isTherePreparatoryClass(SisUtil.integerToBoolean(departmentInfoRequest.getIsTherePreparatoryClass()))
                .createdUserId(operationInfoRequest.getUserId())
                .createdDate(new Date())
                .build();
    }

    public static DepartmentEntity generateUpdateEntity(final Long departmentId,
                                                        final DepartmentUpdateRequest updateRequest) {

        final DepartmentInfoRequest departmentInfoRequest = updateRequest.getDepartmentInfoRequest();
        final SisOperationInfoRequest operationInfoRequest = updateRequest.getOperationInfoRequest();

        return DepartmentEntity.builder()
                .departmentId(departmentId)
                .facultyId(departmentInfoRequest.getFacultyId())
                .name(departmentInfoRequest.getName())
                .status(DepartmentStatus.ACTIVE)
                .totalClassLevel(departmentInfoRequest.getTotalClassLevel())
                .isTherePreparatoryClass(SisUtil.integerToBoolean(departmentInfoRequest.getIsTherePreparatoryClass()))
                .modifiedUserId(operationInfoRequest.getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static DepartmentEntity generateDeleteEntity(final DepartmentDeleteRequest deleteRequest) {
        return DepartmentEntity.builder()
                .departmentId(deleteRequest.getDepartmentId())
                .status(DepartmentStatus.DELETED)
                .modifiedUserId(deleteRequest.getOperationInfoRequest().getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static DepartmentEntity generatePassiveEntity(final DepartmentPassivateRequest passivateRequest) {
        return DepartmentEntity.builder()
                .departmentId(passivateRequest.getDepartmentId())
                .status(DepartmentStatus.PASSIVE)
                .modifiedUserId(passivateRequest.getOperationInfoRequest().getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static DepartmentEntity generateActiveEntity(final DepartmentActivateRequest activateRequest) {
        return DepartmentEntity.builder()
                .departmentId(activateRequest.getDepartmentId())
                .status(DepartmentStatus.ACTIVE)
                .modifiedUserId(activateRequest.getOperationInfoRequest().getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static DepartmentResponse entityToResponse(final DepartmentEntity departmentEntity) {
        return DepartmentResponse.builder()
                .departmentId(departmentEntity.getDepartmentId())
                .facultyResponse(FacultyInfoConverter.entityToResponse(departmentEntity.getFacultyEntity()))
                .name(departmentEntity.getName())
                .status(departmentEntity.getStatus().getName())
                .totalClassLevel(departmentEntity.getTotalClassLevel())
                .isTherePreparatoryClass(departmentEntity.getIsTherePreparatoryClass())
                .createdUserId(departmentEntity.getCreatedUserId())
                .createdDate(SisUtil.getFormattedDateTime(departmentEntity.getCreatedDate()))
                .modifiedUserId(departmentEntity.getModifiedUserId())
                .modifiedDate(SisUtil.getFormattedDateTime(departmentEntity.getModifiedDate()))
                .build();
    }

    public static List<DepartmentResponse> entitiesToResponses(final List<DepartmentEntity> departmentEntities) {
        List<DepartmentResponse> departmentResponses = new ArrayList<>();
        departmentEntities.forEach(departmentEntity -> departmentResponses.add(entityToResponse(departmentEntity)));
        return departmentResponses;
    }
}
