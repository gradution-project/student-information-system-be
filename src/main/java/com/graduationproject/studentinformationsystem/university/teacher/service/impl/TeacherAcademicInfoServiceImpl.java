package com.graduationproject.studentinformationsystem.university.teacher.service.impl;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.converter.TeacherAcademicInfoConverter;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.entity.TeacherAcademicInfoEntity;
import com.graduationproject.studentinformationsystem.university.teacher.model.enums.TeacherStatus;
import com.graduationproject.studentinformationsystem.university.teacher.repository.TeacherAcademicInfoRepository;
import com.graduationproject.studentinformationsystem.university.teacher.service.TeacherAcademicInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherAcademicInfoServiceImpl implements TeacherAcademicInfoService {

    private final TeacherAcademicInfoRepository academicInfoRepository;
    private final TeacherAcademicInfoConverter academicInfoConverter;

    @Override
    public List<TeacherAcademicInfoResponse> getAllTeacherAcademicInfosByStatus(final TeacherStatus status) {
        final List<TeacherAcademicInfoEntity> academicInfoEntities = academicInfoRepository.getAllTeacherAcademicInfosByStatus(status);
        return academicInfoConverter.entitiesToResponses(academicInfoEntities);
    }

    @Override
    public TeacherAcademicInfoResponse getTeacherAcademicInfoById(final Long teacherId) {
        return getTeacherAcademicInfoResponse(teacherId);
    }

    @Override
    public void saveTeacherAcademicInfo(final Long teacherId,
                                        final String teacherEmail,
                                        final TeacherAcademicInfoRequest academicInfoRequest,
                                        final SisOperationInfoRequest operationInfoRequest) {

        final TeacherAcademicInfoEntity academicInfoEntity = academicInfoConverter
                .generateSaveEntity(teacherId, teacherEmail, academicInfoRequest, operationInfoRequest);

        academicInfoRepository.saveTeacherAcademicInfo(academicInfoEntity);
    }

    @Override
    public TeacherAcademicInfoResponse updateTeacherAcademicInfo(final Long teacherId,
                                                                 final TeacherAcademicInfoUpdateRequest academicInfoUpdateRequest) {

        final TeacherAcademicInfoEntity academicInfoEntity = academicInfoConverter
                .generateUpdateEntity(teacherId, academicInfoUpdateRequest);

        academicInfoRepository.updateTeacherAcademicInfo(academicInfoEntity);

        return getTeacherAcademicInfoResponse(teacherId);
    }

    @Override
    public void deleteTeacherAcademicInfo(final TeacherDeleteRequest deleteRequest) {
        final TeacherAcademicInfoEntity academicInfoEntity = academicInfoConverter.generateDeleteEntity(deleteRequest);
        academicInfoRepository.updateTeacherAcademicInfoStatus(academicInfoEntity);
    }

    @Override
    public void passivateTeacherAcademicInfo(final TeacherPassivateRequest passivateRequest) {
        final TeacherAcademicInfoEntity academicInfoEntity = academicInfoConverter.generatePassiveEntity(passivateRequest);
        academicInfoRepository.updateTeacherAcademicInfoStatus(academicInfoEntity);
    }

    @Override
    public void activateTeacherAcademicInfo(final TeacherActivateRequest activateRequest) {
        final TeacherAcademicInfoEntity academicInfoEntity = academicInfoConverter.generateActiveEntity(activateRequest);
        academicInfoRepository.updateTeacherAcademicInfoStatus(academicInfoEntity);
    }

    @Override
    public List<Long> getAllTeacherIdsByDepartmentId(final Long departmentId) {
        return academicInfoRepository.getAllTeacherIdsByDepartmentId(departmentId);
    }

    @Override
    public boolean isTeacherExist(final Long teacherId) {
        return academicInfoRepository.isTeacherExist(teacherId);
    }

    @Override
    public boolean isTeacherDeleted(final Long teacherId) {
        return academicInfoRepository.isTeacherDeleted(teacherId);
    }

    @Override
    public boolean isTeacherPassive(final Long teacherId) {
        return academicInfoRepository.isTeacherPassive(teacherId);
    }

    @Override
    public boolean isTeacherActive(final Long teacherId) {
        return academicInfoRepository.isTeacherActive(teacherId);
    }


    private TeacherAcademicInfoResponse getTeacherAcademicInfoResponse(final Long teacherId) {
        final TeacherAcademicInfoEntity academicInfoEntity = academicInfoRepository.getTeacherAcademicInfoById(teacherId);
        return academicInfoConverter.entityToResponse(academicInfoEntity);
    }
}
