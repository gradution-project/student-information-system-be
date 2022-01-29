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

    public static StudentPersonalInfoEntity generateSaveEntity(final Long studentId,
                                                               final StudentPersonalInfoRequest personalInfoRequest,
                                                               final SisOperationInfoRequest operationInfoRequest) {

        return StudentPersonalInfoEntity.builder()
                .studentId(studentId)
                .tcNo(personalInfoRequest.getTcNo())
                .name(personalInfoRequest.getName())
                .surname(personalInfoRequest.getSurname())
                .email(personalInfoRequest.getEmail())
                .phoneNumber(personalInfoRequest.getPhoneNumber())
                .status(StudentStatus.ACTIVE)
//                .profilePhoto(personalInfoRequest.getProfilePhoto()) // TODO: Set Profile Photo
//                .profilePhotoUrl(personalInfoRequest.getProfilePhotoUrl()) // TODO: Set Profile Photo URL
                .birthday(personalInfoRequest.getBirthday())
                .address(personalInfoRequest.getAddress())
                .createdUserId(operationInfoRequest.getUserId())
                .createdDate(new Date())
                .build();
    }

    public static StudentPersonalInfoEntity generateUpdateEntity(final Long studentId,
                                                                 final StudentPersonalInfoUpdateRequest personalInfoUpdateRequest) {

        final StudentPersonalInfoRequest personalInfoRequest = personalInfoUpdateRequest.getPersonalInfoRequest();
        final SisOperationInfoRequest operationInfoRequest = personalInfoUpdateRequest.getOperationInfoRequest();

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
                .createdUserId(operationInfoRequest.getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static StudentPersonalInfoEntity generateDeleteEntity(final StudentDeleteRequest deleteRequest) {
        return StudentPersonalInfoEntity.builder()
                .studentId(deleteRequest.getStudentId())
                .status(StudentStatus.DELETED)
                .modifiedUserId(deleteRequest.getOperationInfoRequest().getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static StudentPersonalInfoEntity generatePassiveEntity(final StudentPassivateRequest passivateRequest) {
        return StudentPersonalInfoEntity.builder()
                .studentId(passivateRequest.getStudentId())
                .status(StudentStatus.PASSIVE)
                .modifiedUserId(passivateRequest.getOperationInfoRequest().getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static StudentPersonalInfoEntity generateActiveEntity(final StudentActivateRequest activateRequest) {
        return StudentPersonalInfoEntity.builder()
                .studentId(activateRequest.getStudentId())
                .status(StudentStatus.ACTIVE)
                .modifiedUserId(activateRequest.getOperationInfoRequest().getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static StudentPersonalInfoEntity generateGraduateEntity(final StudentGraduateRequest graduateRequest) {
        return StudentPersonalInfoEntity.builder()
                .studentId(graduateRequest.getStudentId())
                .status(StudentStatus.GRADUATED)
                .modifiedUserId(graduateRequest.getOperationInfoRequest().getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public static StudentPersonalInfoResponse entityToResponse(final StudentPersonalInfoEntity personalInfoEntity) {
        return StudentPersonalInfoResponse.builder()
                .tcNo(personalInfoEntity.getTcNo())
                .name(personalInfoEntity.getName())
                .surname(personalInfoEntity.getSurname())
                .email(personalInfoEntity.getEmail())
                .phoneNumber(SisUtil.getFormattedPhoneNumber(personalInfoEntity.getPhoneNumber()))
                .birthday(SisUtil.getFormattedDate(personalInfoEntity.getBirthday()))
                .address(personalInfoEntity.getAddress())
                .createdDate(SisUtil.getFormattedDateTime(personalInfoEntity.getCreatedDate()))
                .createdUserId(personalInfoEntity.getCreatedUserId())
                .modifiedDate(SisUtil.getFormattedDateTime(personalInfoEntity.getModifiedDate()))
                .modifiedUserId(personalInfoEntity.getModifiedUserId()).build();
    }

    public static List<StudentPersonalInfoResponse> entityListToResponseList(final List<StudentPersonalInfoEntity> personalInfoEntities) {
        List<StudentPersonalInfoResponse> personalInfoResponses = new ArrayList<>();
        personalInfoEntities.forEach(personalInfoEntity -> personalInfoResponses.add(entityToResponse(personalInfoEntity)));
        return personalInfoResponses;
    }
}
