package com.graduationproject.studentinformationsystem.teacher.model.dto.converter;

import com.graduationproject.studentinformationsystem.teacher.model.dto.request.TeacherPersonalInfoRequest;
import com.graduationproject.studentinformationsystem.teacher.model.dto.response.TeacherPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.teacher.model.entity.TeacherPersonalInfoEntity;
import com.graduationproject.studentinformationsystem.teacher.model.enums.TeacherStatus;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TeacherPersonalInfoConverter {

    private TeacherPersonalInfoConverter() {
    }

    public static TeacherPersonalInfoResponse entityToResponse(TeacherPersonalInfoEntity entity) {
        return new ModelMapper().map(entity, TeacherPersonalInfoResponse.class);
    }

    public static List<TeacherPersonalInfoResponse> entityListToResponseList(List<TeacherPersonalInfoEntity> entities) {
        List<TeacherPersonalInfoResponse> dtoList = new ArrayList<>();
        entities.forEach(entity -> dtoList.add(entityToResponse(entity)));
        return dtoList;
    }

    public static TeacherPersonalInfoEntity generateSaveEntity(Long teacherId, TeacherPersonalInfoRequest request) {
        return TeacherPersonalInfoEntity.builder()
                .teacherId(teacherId)
                .tcNo(request.getTcNo())
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .status(TeacherStatus.ACTIVE)
//                .profilePhoto(request.getProfilePhoto()) // TODO: Set Profile Photo
//                .profilePhotoUrl(request.getProfilePhotoUrl()) // TODO: Set Profile Photo URL
                .birthday(request.getBirthday())
                .address(request.getAddress())
                .createdUserId(1L)
                .createdDate(new Date())
                .build();
    }

    public static TeacherPersonalInfoEntity generateUpdateEntity(Long teacherId, TeacherPersonalInfoRequest request) {
        return TeacherPersonalInfoEntity.builder()
                .teacherId(teacherId)
                .tcNo(request.getTcNo())
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
//                .profilePhoto(request.getProfilePhoto()) // TODO: Set Profile Photo
//                .profilePhotoUrl(request.getProfilePhotoUrl()) // TODO: Set Profile Photo URL
                .birthday(request.getBirthday())
                .address(request.getAddress())
                .modifiedUserId(1L)
                .modifiedDate(new Date())
                .build();
    }

    public static TeacherPersonalInfoEntity generateDeleteEntity(Long teacherId) {
        return TeacherPersonalInfoEntity.builder()
                .teacherId(teacherId)
                .status(TeacherStatus.DELETED)
                .modifiedUserId(1L)
                .modifiedDate(new Date())
                .build();
    }

    public static TeacherPersonalInfoEntity generatePassiveEntity(Long teacherId) {
        return TeacherPersonalInfoEntity.builder()
                .teacherId(teacherId)
                .status(TeacherStatus.PASSIVE)
                .modifiedUserId(1L)
                .modifiedDate(new Date())
                .build();
    }

    public static TeacherPersonalInfoEntity generateActiveEntity(Long teacherId) {
        return TeacherPersonalInfoEntity.builder()
                .teacherId(teacherId)
                .status(TeacherStatus.ACTIVE)
                .modifiedUserId(1L)
                .modifiedDate(new Date())
                .build();
    }
}
