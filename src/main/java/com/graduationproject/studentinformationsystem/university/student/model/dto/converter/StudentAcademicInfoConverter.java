package com.graduationproject.studentinformationsystem.university.student.model.dto.converter;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.SisUtil;
import com.graduationproject.studentinformationsystem.university.student.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.student.model.entity.StudentAcademicInfoEntity;
import com.graduationproject.studentinformationsystem.university.student.model.enums.StudentStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentAcademicInfoConverter {

    private StudentAcademicInfoConverter() {
    }

    public static StudentAcademicInfoEntity generateSaveEntity(final Long studentId,
                                                               final String studentEmail,
                                                               final StudentAcademicInfoRequest academicInfoRequest,
                                                               final SisOperationInfoRequest operationInfoRequest) {

        return StudentAcademicInfoEntity.builder()
                .studentId(studentId)
                .departmentId(academicInfoRequest.getDepartmentId())
                .degree(academicInfoRequest.getDegree())
                .classLevel(academicInfoRequest.getClassLevel())
                .email(studentEmail)
                .status(StudentStatus.ACTIVE)
                .registrationDate(new Date())
                .createdUserId(operationInfoRequest.getUserId())
                .createdDate(new Date())
                .build();
    }

    public static StudentAcademicInfoEntity generateUpdateEntity(final Long studentId,
                                                                 final StudentAcademicInfoUpdateRequest academicInfoUpdateRequest) {

        final StudentAcademicInfoRequest academicInfoRequest = academicInfoUpdateRequest.getAcademicInfoRequest();
        final SisOperationInfoRequest operationInfoRequest = academicInfoUpdateRequest.getOperationInfoRequest();

        return StudentAcademicInfoEntity.builder()
                .studentId(studentId)
                .departmentId(academicInfoRequest.getDepartmentId())
                .degree(academicInfoRequest.getDegree())
                .classLevel(academicInfoRequest.getClassLevel())
                .modifiedUserId(operationInfoRequest.getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static StudentAcademicInfoEntity generateDeleteEntity(final StudentDeleteRequest deleteRequest) {
        return StudentAcademicInfoEntity.builder()
                .studentId(deleteRequest.getStudentId())
                .status(StudentStatus.DELETED)
                .modifiedUserId(deleteRequest.getOperationInfoRequest().getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static StudentAcademicInfoEntity generatePassiveEntity(final StudentPassivateRequest passivateRequest) {
        return StudentAcademicInfoEntity.builder()
                .studentId(passivateRequest.getStudentId())
                .status(StudentStatus.PASSIVE)
                .modifiedUserId(passivateRequest.getOperationInfoRequest().getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static StudentAcademicInfoEntity generateActiveEntity(final StudentActivateRequest activateRequest) {
        return StudentAcademicInfoEntity.builder()
                .studentId(activateRequest.getStudentId())
                .status(StudentStatus.ACTIVE)
                .modifiedUserId(activateRequest.getOperationInfoRequest().getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static StudentAcademicInfoEntity generateGraduateEntity(final StudentGraduateRequest graduateRequest) {
        return StudentAcademicInfoEntity.builder()
                .studentId(graduateRequest.getStudentId())
                .status(StudentStatus.GRADUATED)
                .modifiedUserId(graduateRequest.getOperationInfoRequest().getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static StudentAcademicInfoResponse entityToResponse(final StudentAcademicInfoEntity academicInfoEntity) {
        return StudentAcademicInfoResponse.builder()
                .studentId(academicInfoEntity.getStudentId())
                .departmentId(academicInfoEntity.getDepartmentId())
                .degree(academicInfoEntity.getDegree().getName())
                .classLevel(academicInfoEntity.getClassLevel().getName())
                .status(academicInfoEntity.getStatus().getName())
                .registrationDate(SisUtil.getFormattedDate(academicInfoEntity.getRegistrationDate()))
                .email(academicInfoEntity.getEmail())
                .createdDate(SisUtil.getFormattedDateTime(academicInfoEntity.getCreatedDate()))
                .createdUserId(academicInfoEntity.getCreatedUserId())
                .modifiedDate(SisUtil.getFormattedDateTime(academicInfoEntity.getModifiedDate()))
                .modifiedUserId(academicInfoEntity.getModifiedUserId()).build();
    }

    public static List<StudentAcademicInfoResponse> entityListToResponseList(final List<StudentAcademicInfoEntity> academicInfoEntities) {
        List<StudentAcademicInfoResponse> academicInfoResponses = new ArrayList<>();
        academicInfoEntities.forEach(academicInfoEntity -> academicInfoResponses.add(entityToResponse(academicInfoEntity)));
        return academicInfoResponses;
    }
}
