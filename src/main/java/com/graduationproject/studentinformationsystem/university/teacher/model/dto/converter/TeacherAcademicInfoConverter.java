package com.graduationproject.studentinformationsystem.university.teacher.model.dto.converter;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.SisUtil;
import com.graduationproject.studentinformationsystem.university.department.model.dto.response.DepartmentResponse;
import com.graduationproject.studentinformationsystem.university.department.service.DepartmentOutService;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.entity.TeacherAcademicInfoEntity;
import com.graduationproject.studentinformationsystem.university.teacher.model.enums.TeacherStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TeacherAcademicInfoConverter {

    private final DepartmentOutService departmentOutService;

    public TeacherAcademicInfoEntity generateSaveEntity(final Long teacherId,
                                                        final String teacherEmail,
                                                        final TeacherAcademicInfoRequest academicInfoRequest,
                                                        final SisOperationInfoRequest operationInfoRequest) {

        return TeacherAcademicInfoEntity.builder()
                .teacherId(teacherId)
                .departmentId(academicInfoRequest.getDepartmentId())
                .degree(academicInfoRequest.getDegree())
                .role(academicInfoRequest.getRole())
                .fieldOfStudy(academicInfoRequest.getFieldOfStudy())
                .phoneNumber(SisUtil.getUnformattedPhoneNumber(academicInfoRequest.getPhoneNumber()))
                .email(teacherEmail)
                .status(TeacherStatus.ACTIVE)
                .registrationDate(new Date())
                .createdUserId(operationInfoRequest.getUserId())
                .createdDate(new Date())
                .build();
    }

    public TeacherAcademicInfoEntity generateUpdateEntity(final Long teacherId,
                                                          final TeacherAcademicInfoUpdateRequest academicInfoUpdateRequest) {

        final TeacherAcademicInfoRequest academicInfoRequest = academicInfoUpdateRequest.getAcademicInfoRequest();
        final SisOperationInfoRequest operationInfoRequest = academicInfoUpdateRequest.getOperationInfoRequest();

        return TeacherAcademicInfoEntity.builder()
                .teacherId(teacherId)
                .departmentId(academicInfoRequest.getDepartmentId())
                .degree(academicInfoRequest.getDegree())
                .role(academicInfoRequest.getRole())
                .fieldOfStudy(academicInfoRequest.getFieldOfStudy())
                .phoneNumber(SisUtil.getUnformattedPhoneNumber(academicInfoRequest.getPhoneNumber()))
                .modifiedUserId(operationInfoRequest.getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public TeacherAcademicInfoEntity generateDeleteEntity(final TeacherDeleteRequest deleteRequest) {
        return TeacherAcademicInfoEntity.builder()
                .teacherId(deleteRequest.getTeacherId())
                .status(TeacherStatus.DELETED)
                .modifiedUserId(deleteRequest.getOperationInfoRequest().getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public TeacherAcademicInfoEntity generatePassiveEntity(final TeacherPassivateRequest passivateRequest) {
        return TeacherAcademicInfoEntity.builder()
                .teacherId(passivateRequest.getTeacherId())
                .status(TeacherStatus.PASSIVE)
                .modifiedUserId(passivateRequest.getOperationInfoRequest().getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public TeacherAcademicInfoEntity generateActiveEntity(final TeacherActivateRequest activateRequest) {
        return TeacherAcademicInfoEntity.builder()
                .teacherId(activateRequest.getTeacherId())
                .status(TeacherStatus.ACTIVE)
                .modifiedUserId(activateRequest.getOperationInfoRequest().getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public TeacherAcademicInfoResponse entityToResponse(final TeacherAcademicInfoEntity academicInfoEntity) {

        final DepartmentResponse departmentResponse = departmentOutService.getDepartmentResponse(academicInfoEntity.getDepartmentId());

        return TeacherAcademicInfoResponse.builder()
                .teacherId(academicInfoEntity.getTeacherId())
                .degree(academicInfoEntity.getDegree())
                .role(academicInfoEntity.getRole())
                .fieldOfStudy(academicInfoEntity.getFieldOfStudy())
                .phoneNumber(SisUtil.getFormattedPhoneNumber(academicInfoEntity.getPhoneNumber()))
                .email(academicInfoEntity.getEmail())
                .status(academicInfoEntity.getStatus())
                .registrationDate(SisUtil.getFormattedDate(academicInfoEntity.getRegistrationDate()))
                .createdUserId(academicInfoEntity.getCreatedUserId())
                .createdDate(SisUtil.getFormattedDateTime(academicInfoEntity.getCreatedDate()))
                .modifiedUserId(academicInfoEntity.getModifiedUserId())
                .modifiedDate(SisUtil.getFormattedDateTime(academicInfoEntity.getModifiedDate()))
                .departmentResponse(departmentResponse)
                .build();
    }

    public List<TeacherAcademicInfoResponse> entitiesToResponses(final List<TeacherAcademicInfoEntity> academicInfoEntities) {
        List<TeacherAcademicInfoResponse> academicInfoResponses = new ArrayList<>();
        academicInfoEntities.forEach(academicInfoEntity -> academicInfoResponses.add(entityToResponse(academicInfoEntity)));
        return academicInfoResponses;
    }
}
