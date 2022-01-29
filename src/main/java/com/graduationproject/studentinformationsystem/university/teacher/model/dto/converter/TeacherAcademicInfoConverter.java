package com.graduationproject.studentinformationsystem.university.teacher.model.dto.converter;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.SisUtil;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.entity.TeacherAcademicInfoEntity;
import com.graduationproject.studentinformationsystem.university.teacher.model.enums.TeacherStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TeacherAcademicInfoConverter {

    private TeacherAcademicInfoConverter() {
    }

    public static TeacherAcademicInfoEntity generateSaveEntity(final Long teacherId,
                                                               final String teacherEmail,
                                                               final TeacherAcademicInfoRequest academicInfoRequest,
                                                               final SisOperationInfoRequest operationInfoRequest) {

        return TeacherAcademicInfoEntity.builder()
                .teacherId(teacherId)
                .departmentId(academicInfoRequest.getDepartmentId())
                .degree(academicInfoRequest.getDegree())
                .role(academicInfoRequest.getRole())
                .fieldOfStudy(academicInfoRequest.getFieldOfStudy())
                .phoneNumber(academicInfoRequest.getPhoneNumber())
                .email(teacherEmail)
                .status(TeacherStatus.ACTIVE)
                .registrationDate(new Date())
                .createdUserId(operationInfoRequest.getUserId())
                .createdDate(new Date())
                .build();
    }

    public static TeacherAcademicInfoEntity generateUpdateEntity(final Long teacherId,
                                                                 final TeacherAcademicInfoUpdateRequest academicInfoUpdateRequest) {

        final TeacherAcademicInfoRequest academicInfoRequest = academicInfoUpdateRequest.getAcademicInfoRequest();
        final SisOperationInfoRequest operationInfoRequest = academicInfoUpdateRequest.getOperationInfoRequest();

        return TeacherAcademicInfoEntity.builder()
                .teacherId(teacherId)
                .departmentId(academicInfoRequest.getDepartmentId())
                .degree(academicInfoRequest.getDegree())
                .role(academicInfoRequest.getRole())
                .fieldOfStudy(academicInfoRequest.getFieldOfStudy())
                .phoneNumber(academicInfoRequest.getPhoneNumber())
                .modifiedUserId(operationInfoRequest.getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static TeacherAcademicInfoEntity generateDeleteEntity(final TeacherDeleteRequest deleteRequest) {
        return TeacherAcademicInfoEntity.builder()
                .teacherId(deleteRequest.getTeacherId())
                .status(TeacherStatus.DELETED)
                .modifiedUserId(deleteRequest.getOperationInfoRequest().getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static TeacherAcademicInfoEntity generatePassiveEntity(final TeacherPassivateRequest passivateRequest) {
        return TeacherAcademicInfoEntity.builder()
                .teacherId(passivateRequest.getTeacherId())
                .status(TeacherStatus.PASSIVE)
                .modifiedUserId(passivateRequest.getOperationInfoRequest().getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static TeacherAcademicInfoEntity generateActiveEntity(final TeacherActivateRequest activateRequest) {
        return TeacherAcademicInfoEntity.builder()
                .teacherId(activateRequest.getTeacherId())
                .status(TeacherStatus.ACTIVE)
                .modifiedUserId(activateRequest.getOperationInfoRequest().getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static TeacherAcademicInfoResponse entityToResponse(final TeacherAcademicInfoEntity academicInfoEntity) {
        return TeacherAcademicInfoResponse.builder()
                .teacherId(academicInfoEntity.getTeacherId())
                .departmentId(academicInfoEntity.getDepartmentId())
                .degree(academicInfoEntity.getDegree().getName())
                .role(academicInfoEntity.getRole().getName())
                .fieldOfStudy(academicInfoEntity.getFieldOfStudy())
                .phoneNumber(SisUtil.getFormattedPhoneNumber(academicInfoEntity.getPhoneNumber()))
                .email(academicInfoEntity.getEmail())
                .status(academicInfoEntity.getStatus().getName())
                .registrationDate(SisUtil.getFormattedDate(academicInfoEntity.getRegistrationDate()))
                .createdUserId(academicInfoEntity.getCreatedUserId())
                .createdDate(SisUtil.getFormattedDateTime(academicInfoEntity.getCreatedDate()))
                .modifiedUserId(academicInfoEntity.getModifiedUserId())
                .modifiedDate(SisUtil.getFormattedDateTime(academicInfoEntity.getModifiedDate())).build();
    }

    public static List<TeacherAcademicInfoResponse> entityListToResponseList(final List<TeacherAcademicInfoEntity> academicInfoEntities) {
        List<TeacherAcademicInfoResponse> academicInfoResponses = new ArrayList<>();
        academicInfoEntities.forEach(academicInfoEntity -> academicInfoResponses.add(entityToResponse(academicInfoEntity)));
        return academicInfoResponses;
    }
}
