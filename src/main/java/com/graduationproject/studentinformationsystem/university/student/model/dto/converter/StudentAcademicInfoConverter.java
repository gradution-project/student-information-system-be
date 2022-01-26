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

    public static StudentAcademicInfoResponse entityToResponse(StudentAcademicInfoEntity entity) {
        return StudentAcademicInfoResponse.builder()
                .studentId(entity.getStudentId())
                .departmentId(entity.getDepartmentId())
                .degree(entity.getDegree().getName())
                .classLevel(entity.getClassLevel().getName())
                .status(entity.getStatus().getName())
                .registrationDate(SisUtil.getFormattedDate(entity.getRegistrationDate()))
                .email(entity.getEmail())
                .createdDate(SisUtil.getFormattedDate(entity.getCreatedDate()))
                .createdUserId(entity.getCreatedUserId())
                .modifiedDate(SisUtil.getFormattedDate(entity.getModifiedDate()))
                .modifiedUserId(entity.getModifiedUserId()).build();
    }

    public static List<StudentAcademicInfoResponse> entityListToResponseList(List<StudentAcademicInfoEntity> entityList) {
        List<StudentAcademicInfoResponse> dtoList = new ArrayList<>();
        entityList.forEach(entity -> dtoList.add(entityToResponse(entity)));
        return dtoList;
    }

    public static StudentAcademicInfoEntity generateSaveEntity(Long studentId,
                                                               String studentEmail,
                                                               StudentAcademicInfoRequest academicInfoRequest,
                                                               SisOperationInfoRequest operationInfoRequest) {

        return StudentAcademicInfoEntity.builder()
                .studentId(studentId)
                .departmentId(academicInfoRequest.getDepartmentId())
                .degree(academicInfoRequest.getDegree())
                .classLevel(academicInfoRequest.getClassLevel())
                .email(studentEmail)
                .status(StudentStatus.ACTIVE)
                .registrationDate(new Date())
                .createdUserId(operationInfoRequest.getOperationUserId())
                .createdDate(new Date())
                .build();
    }

    public static StudentAcademicInfoEntity generateUpdateEntity(Long studentId,
                                                                 StudentUpdateAcademicInfoRequest updateAcademicInfoRequest) {

        final StudentAcademicInfoRequest academicInfoRequest = updateAcademicInfoRequest.getAcademicInfoRequest();
        final SisOperationInfoRequest operationInfoRequest = updateAcademicInfoRequest.getOperationInfoRequest();

        return StudentAcademicInfoEntity.builder()
                .studentId(studentId)
                .departmentId(academicInfoRequest.getDepartmentId())
                .degree(academicInfoRequest.getDegree())
                .classLevel(academicInfoRequest.getClassLevel())
                .modifiedUserId(operationInfoRequest.getOperationUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static StudentAcademicInfoEntity generateDeleteEntity(StudentDeleteRequest deleteRequest) {
        return StudentAcademicInfoEntity.builder()
                .studentId(deleteRequest.getStudentId())
                .status(StudentStatus.DELETED)
                .modifiedUserId(deleteRequest.getOperationInfoRequest().getOperationUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static StudentAcademicInfoEntity generatePassiveEntity(StudentPassivateRequest passivateRequest) {
        return StudentAcademicInfoEntity.builder()
                .studentId(passivateRequest.getStudentId())
                .status(StudentStatus.PASSIVE)
                .modifiedUserId(passivateRequest.getOperationInfoRequest().getOperationUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static StudentAcademicInfoEntity generateActiveEntity(StudentActivateRequest activateRequest) {
        return StudentAcademicInfoEntity.builder()
                .studentId(activateRequest.getStudentId())
                .status(StudentStatus.ACTIVE)
                .modifiedUserId(activateRequest.getOperationInfoRequest().getOperationUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static StudentAcademicInfoEntity generateGraduateEntity(StudentGraduateRequest graduateRequest) {
        return StudentAcademicInfoEntity.builder()
                .studentId(graduateRequest.getStudentId())
                .status(StudentStatus.GRADUATED)
                .modifiedUserId(graduateRequest.getOperationInfoRequest().getOperationUserId())
                .modifiedDate(new Date())
                .build();
    }
}
