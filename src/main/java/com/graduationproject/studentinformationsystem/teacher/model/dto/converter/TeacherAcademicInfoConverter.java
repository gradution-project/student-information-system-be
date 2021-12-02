package com.graduationproject.studentinformationsystem.teacher.model.dto.converter;

import com.graduationproject.studentinformationsystem.teacher.model.dto.request.TeacherAcademicInfoRequest;
import com.graduationproject.studentinformationsystem.teacher.model.dto.response.TeacherAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.teacher.model.entity.TeacherAcademicInfoEntity;
import com.graduationproject.studentinformationsystem.teacher.model.enums.TeacherStatus;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TeacherAcademicInfoConverter {

    private TeacherAcademicInfoConverter() {
    }

    public static TeacherAcademicInfoResponse entityToResponse(TeacherAcademicInfoEntity entity) {
        return new ModelMapper().map(entity, TeacherAcademicInfoResponse.class);
    }

    public static List<TeacherAcademicInfoResponse> entityListToResponseList(List<TeacherAcademicInfoEntity> entityList) {
        List<TeacherAcademicInfoResponse> dtoList = new ArrayList<>();
        entityList.forEach(entity -> dtoList.add(entityToResponse(entity)));
        return dtoList;
    }

    public static TeacherAcademicInfoEntity generateSaveEntity(Long teacherId, String teacherEmail, TeacherAcademicInfoRequest request) {
        return TeacherAcademicInfoEntity.builder()
                .teacherId(teacherId)
                .departmentId(request.getDepartmentId())
                .degree(request.getDegree())
                .role(request.getRole())
                .fieldOfStudy(request.getFieldOfStudy())
                .phoneNumber(request.getPhoneNumber())
                .email(teacherEmail)
                .status(TeacherStatus.ACTIVE)
                .registrationDate(new Date())
                .createdUserId(1L)
                .createdDate(new Date())
                .build();
    }

    public static TeacherAcademicInfoEntity generateUpdateEntity(Long teacherId, TeacherAcademicInfoRequest request) {
        return TeacherAcademicInfoEntity.builder()
                .teacherId(teacherId)
                .departmentId(request.getDepartmentId())
                .degree(request.getDegree())
                .role(request.getRole())
                .fieldOfStudy(request.getFieldOfStudy())
                .phoneNumber(request.getPhoneNumber())
                .modifiedUserId(1L)
                .modifiedDate(new Date())
                .build();
    }

    public static TeacherAcademicInfoEntity generateDeleteEntity(Long teacherId) {
        return TeacherAcademicInfoEntity.builder()
                .teacherId(teacherId)
                .status(TeacherStatus.DELETED)
                .modifiedUserId(1L)
                .modifiedDate(new Date())
                .build();
    }

    public static TeacherAcademicInfoEntity generatePassiveEntity(Long teacherId) {
        return TeacherAcademicInfoEntity.builder()
                .teacherId(teacherId)
                .status(TeacherStatus.PASSIVE)
                .modifiedUserId(1L)
                .modifiedDate(new Date())
                .build();
    }

    public static TeacherAcademicInfoEntity generateActiveEntity(Long teacherId) {
        return TeacherAcademicInfoEntity.builder()
                .teacherId(teacherId)
                .status(TeacherStatus.ACTIVE)
                .modifiedUserId(1L)
                .modifiedDate(new Date())
                .build();
    }
}
