package com.graduationproject.studentinformationsystem.university.student.model.dto.converter;

import com.graduationproject.studentinformationsystem.university.student.model.dto.request.StudentPersonalInfoRequest;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.university.student.model.entity.StudentPersonalInfoEntity;
import com.graduationproject.studentinformationsystem.university.student.model.enums.StudentStatus;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentPersonalInfoConverter {

    private StudentPersonalInfoConverter() {
    }

    public static StudentPersonalInfoResponse entityToResponse(StudentPersonalInfoEntity entity) {
        return new ModelMapper().map(entity, StudentPersonalInfoResponse.class);
    }

    public static List<StudentPersonalInfoResponse> entityListToResponseList(List<StudentPersonalInfoEntity> entityList) {
        List<StudentPersonalInfoResponse> dtoList = new ArrayList<>();
        entityList.forEach(entity -> dtoList.add(entityToResponse(entity)));
        return dtoList;
    }

    public static StudentPersonalInfoEntity generateSaveEntity(Long studentId, StudentPersonalInfoRequest request) {
        return StudentPersonalInfoEntity.builder()
                .studentId(studentId)
                .tcNo(request.getTcNo())
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .status(StudentStatus.ACTIVE)
//                .profilePhoto(request.getProfilePhoto()) // TODO: Set Profile Photo
//                .profilePhotoUrl(request.getProfilePhotoUrl()) // TODO: Set Profile Photo URL
                .birthday(request.getBirthday())
                .address(request.getAddress())
                .createdUserId(1L)
                .createdDate(new Date())
                .build();
    }

    public static StudentPersonalInfoEntity generateUpdateEntity(Long studentId, StudentPersonalInfoRequest request) {
        return StudentPersonalInfoEntity.builder()
                .studentId(studentId)
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

    public static StudentPersonalInfoEntity generateDeleteEntity(Long studentId) {
        return StudentPersonalInfoEntity.builder()
                .studentId(studentId)
                .status(StudentStatus.DELETED)
                .modifiedUserId(1L)
                .modifiedDate(new Date())
                .build();
    }

    public static StudentPersonalInfoEntity generatePassiveEntity(Long studentId) {
        return StudentPersonalInfoEntity.builder()
                .studentId(studentId)
                .status(StudentStatus.PASSIVE)
                .modifiedUserId(1L)
                .modifiedDate(new Date())
                .build();
    }

    public static StudentPersonalInfoEntity generateActiveEntity(Long studentId) {
        return StudentPersonalInfoEntity.builder()
                .studentId(studentId)
                .status(StudentStatus.ACTIVE)
                .modifiedUserId(1L)
                .modifiedDate(new Date())
                .build();
    }

    public static StudentPersonalInfoEntity generateGraduateEntity(Long studentId) {
        return StudentPersonalInfoEntity.builder()
                .studentId(studentId)
                .status(StudentStatus.GRADUATED)
                .modifiedUserId(1L)
                .modifiedDate(new Date())
                .build();
    }
}
