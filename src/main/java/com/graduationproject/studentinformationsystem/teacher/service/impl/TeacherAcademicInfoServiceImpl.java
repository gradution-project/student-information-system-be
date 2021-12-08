package com.graduationproject.studentinformationsystem.teacher.service.impl;

import com.graduationproject.studentinformationsystem.teacher.model.dto.converter.TeacherAcademicInfoConverter;
import com.graduationproject.studentinformationsystem.teacher.model.dto.request.TeacherAcademicInfoRequest;
import com.graduationproject.studentinformationsystem.teacher.model.dto.response.TeacherAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.teacher.model.entity.TeacherAcademicInfoEntity;
import com.graduationproject.studentinformationsystem.teacher.model.enums.TeacherStatus;
import com.graduationproject.studentinformationsystem.teacher.repository.TeacherAcademicInfoRepository;
import com.graduationproject.studentinformationsystem.teacher.service.TeacherAcademicInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherAcademicInfoServiceImpl implements TeacherAcademicInfoService {

    private final TeacherAcademicInfoRepository academicInfoRepository;

    @Override
    public List<TeacherAcademicInfoResponse> getAllTeacherAcademicInfosByStatus(TeacherStatus status) {
        return TeacherAcademicInfoConverter.entityListToResponseList(academicInfoRepository.getAllTeacherAcademicInfosByStatus(status));
    }

    @Override
    public TeacherAcademicInfoResponse getTeacherAcademicInfoById(Long teacherId) {
        return getTeacherAcademicInfoResponse(teacherId);
    }

    @Override
    public void saveTeacherAcademicInfo(Long teacherId, String teacherEmail, TeacherAcademicInfoRequest request) {
        TeacherAcademicInfoEntity academicInfoEntity = TeacherAcademicInfoConverter.generateSaveEntity(teacherId, teacherEmail, request);
        academicInfoRepository.saveTeacherAcademicInfo(academicInfoEntity);
    }

    @Override
    public TeacherAcademicInfoResponse updateTeacherAcademicInfo(Long teacherId, TeacherAcademicInfoRequest request) {
        TeacherAcademicInfoEntity entity = TeacherAcademicInfoConverter.generateUpdateEntity(teacherId, request);
        academicInfoRepository.updateTeacherAcademicInfo(entity);

        return getTeacherAcademicInfoResponse(teacherId);
    }

    @Override
    public void deleteTeacherAcademicInfo(Long teacherId) {
        TeacherAcademicInfoEntity entity = TeacherAcademicInfoConverter.generateDeleteEntity(teacherId);
        academicInfoRepository.updateTeacherAcademicInfoStatus(entity);
    }

    @Override
    public void passivateTeacherAcademicInfo(Long teacherId) {
        TeacherAcademicInfoEntity entity = TeacherAcademicInfoConverter.generatePassiveEntity(teacherId);
        academicInfoRepository.updateTeacherAcademicInfoStatus(entity);
    }

    @Override
    public void activateTeacherAcademicInfo(Long teacherId) {
        TeacherAcademicInfoEntity entity = TeacherAcademicInfoConverter.generateActiveEntity(teacherId);
        academicInfoRepository.updateTeacherAcademicInfoStatus(entity);
    }

    @Override
    public List<Long> getAllTeacherIdsByDepartmentId(Long departmentId) {
        return academicInfoRepository.getAllTeacherIdsByDepartmentId(departmentId);
    }

    @Override
    public boolean isTeacherExist(Long teacherId) {
        return academicInfoRepository.isTeacherExist(teacherId);
    }

    @Override
    public boolean isTeacherDeleted(Long teacherId) {
        return academicInfoRepository.isTeacherDeleted(teacherId);
    }

    @Override
    public boolean isTeacherPassive(Long teacherId) {
        return academicInfoRepository.isTeacherPassive(teacherId);
    }

    @Override
    public boolean isTeacherActive(Long teacherId) {
        return academicInfoRepository.isTeacherActive(teacherId);
    }

    private TeacherAcademicInfoResponse getTeacherAcademicInfoResponse(Long teacherId) {
        return TeacherAcademicInfoConverter.entityToResponse(academicInfoRepository.getTeacherAcademicInfoById(teacherId));
    }
}
