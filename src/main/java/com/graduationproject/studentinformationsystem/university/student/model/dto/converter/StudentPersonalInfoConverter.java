package com.graduationproject.studentinformationsystem.university.student.model.dto.converter;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.SisUtil;
import com.graduationproject.studentinformationsystem.university.student.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.university.student.model.entity.StudentPersonalInfoEntity;
import com.graduationproject.studentinformationsystem.university.student.model.enums.StudentStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentPersonalInfoConverter {

    private StudentPersonalInfoConverter() {
    }

    public static StudentPersonalInfoResponse entityToResponse(StudentPersonalInfoEntity entity) {
        return StudentPersonalInfoResponse.builder()
                .tcNo(entity.getTcNo())
                .name(entity.getName())
                .surname(entity.getSurname())
                .email(entity.getEmail())
                .phoneNumber(SisUtil.getFormattedPhoneNumber(entity.getPhoneNumber()))
                .birthday(SisUtil.getFormattedDate(entity.getBirthday()))
                .address(entity.getAddress())
                .createdDate(SisUtil.getFormattedDate(entity.getCreatedDate()))
                .createdUserId(entity.getCreatedUserId())
                .modifiedDate(SisUtil.getFormattedDate(entity.getModifiedDate()))
                .modifiedUserId(entity.getModifiedUserId()).build();
    }

    public static List<StudentPersonalInfoResponse> entityListToResponseList(List<StudentPersonalInfoEntity> entityList) {
        List<StudentPersonalInfoResponse> dtoList = new ArrayList<>();
        entityList.forEach(entity -> dtoList.add(entityToResponse(entity)));
        return dtoList;
    }

    public static StudentPersonalInfoEntity generateSaveEntity(Long studentId,
                                                               StudentPersonalInfoRequest personalInfoRequest,
                                                               SisOperationInfoRequest operationInfoRequest) {
        return StudentPersonalInfoEntity.builder()
                .studentId(studentId)
                .tcNo(personalInfoRequest.getTcNo())
                .name(personalInfoRequest.getName())
                .surname(personalInfoRequest.getSurname())
                .email(personalInfoRequest.getEmail())
                .phoneNumber(personalInfoRequest.getPhoneNumber())
                .status(StudentStatus.ACTIVE)
//                .profilePhoto(request.getProfilePhoto()) // TODO: Set Profile Photo
//                .profilePhotoUrl(request.getProfilePhotoUrl()) // TODO: Set Profile Photo URL
                .birthday(personalInfoRequest.getBirthday())
                .address(personalInfoRequest.getAddress())
                .createdUserId(operationInfoRequest.getOperationUserId())
                .createdDate(new Date())
                .build();
    }

    public static StudentPersonalInfoEntity generateUpdateEntity(Long studentId,
                                                                 StudentUpdatePersonalInfoRequest updatePersonalInfoRequest) {

        final StudentPersonalInfoRequest personalInfoRequest = updatePersonalInfoRequest.getPersonalInfoRequest();
        final SisOperationInfoRequest operationInfoRequest = updatePersonalInfoRequest.getOperationInfoRequest();

        return StudentPersonalInfoEntity.builder()
                .studentId(studentId)
                .tcNo(personalInfoRequest.getTcNo())
                .name(personalInfoRequest.getName())
                .surname(personalInfoRequest.getSurname())
                .email(personalInfoRequest.getEmail())
                .phoneNumber(personalInfoRequest.getPhoneNumber())
//                .profilePhoto(personalInfoRequest.getProfilePhoto()) // TODO: Set Profile Photo
//                .profilePhotoUrl(personalInfoRequest.getProfilePhotoUrl()) // TODO: Set Profile Photo URL
                .birthday(personalInfoRequest.getBirthday())
                .address(personalInfoRequest.getAddress())
                .createdUserId(operationInfoRequest.getOperationUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static StudentPersonalInfoEntity generateDeleteEntity(StudentDeleteRequest deleteRequest) {
        return StudentPersonalInfoEntity.builder()
                .studentId(deleteRequest.getStudentId())
                .status(StudentStatus.DELETED)
                .modifiedUserId(deleteRequest.getOperationInfoRequest().getOperationUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static StudentPersonalInfoEntity generatePassiveEntity(StudentPassivateRequest passivateRequest) {
        return StudentPersonalInfoEntity.builder()
                .studentId(passivateRequest.getStudentId())
                .status(StudentStatus.PASSIVE)
                .modifiedUserId(passivateRequest.getOperationInfoRequest().getOperationUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static StudentPersonalInfoEntity generateActiveEntity(StudentActivateRequest activateRequest) {
        return StudentPersonalInfoEntity.builder()
                .studentId(activateRequest.getStudentId())
                .status(StudentStatus.ACTIVE)
                .modifiedUserId(activateRequest.getOperationInfoRequest().getOperationUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static StudentPersonalInfoEntity generateGraduateEntity(StudentGraduateRequest graduateRequest) {
        return StudentPersonalInfoEntity.builder()
                .studentId(graduateRequest.getStudentId())
                .status(StudentStatus.GRADUATED)
                .modifiedUserId(graduateRequest.getOperationInfoRequest().getOperationUserId())
                .modifiedDate(new Date())
                .build();
    }
}
