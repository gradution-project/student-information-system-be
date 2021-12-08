package com.graduationproject.studentinformationsystem.teacher.service.impl;

import com.graduationproject.studentinformationsystem.teacher.model.dto.converter.TeacherPersonalInfoConverter;
import com.graduationproject.studentinformationsystem.teacher.model.dto.request.TeacherPersonalInfoRequest;
import com.graduationproject.studentinformationsystem.teacher.model.dto.response.TeacherPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.teacher.model.entity.TeacherPersonalInfoEntity;
import com.graduationproject.studentinformationsystem.teacher.model.enums.TeacherStatus;
import com.graduationproject.studentinformationsystem.teacher.repository.TeacherPersonalInfoRepository;
import com.graduationproject.studentinformationsystem.teacher.service.TeacherPersonalInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherPersonalInfoServiceImpl implements TeacherPersonalInfoService {

    private final TeacherPersonalInfoRepository personalInfoRepository;

    @Override
    public List<TeacherPersonalInfoResponse> getAllTeacherPersonalInfosByStatus(TeacherStatus status) {
        return TeacherPersonalInfoConverter.entityListToResponseList(personalInfoRepository.getAllTeacherPersonalInfosByStatus(status));
    }

    @Override
    public TeacherPersonalInfoResponse getTeacherPersonalInfoById(Long teacherId) {
        return getTeacherPersonalInfoResponse(teacherId);
    }

    @Override
    public void saveTeacherPersonalInfo(Long teacherId, TeacherPersonalInfoRequest request) {
        TeacherPersonalInfoEntity entity = TeacherPersonalInfoConverter.generateSaveEntity(teacherId, request);
        personalInfoRepository.saveTeacherPersonalInfo(entity);
    }

    @Override
    public TeacherPersonalInfoResponse updateTeacherPersonalInfo(Long teacherId, TeacherPersonalInfoRequest request) {
        TeacherPersonalInfoEntity entity = TeacherPersonalInfoConverter.generateUpdateEntity(teacherId, request);
        personalInfoRepository.updateTeacherPersonalInfo(entity);

        return getTeacherPersonalInfoResponse(teacherId);
    }

    @Override
    public void deleteTeacherPersonalInfo(Long teacherId) {
        TeacherPersonalInfoEntity entity = TeacherPersonalInfoConverter.generateDeleteEntity(teacherId);
        personalInfoRepository.updateTeacherPersonalInfoStatus(entity);
    }

    @Override
    public void passivateTeacherPersonalInfo(Long teacherId) {
        TeacherPersonalInfoEntity entity = TeacherPersonalInfoConverter.generatePassiveEntity(teacherId);
        personalInfoRepository.updateTeacherPersonalInfoStatus(entity);
    }

    @Override
    public void activateTeacherPersonalInfo(Long teacherId) {
        TeacherPersonalInfoEntity entity = TeacherPersonalInfoConverter.generateActiveEntity(teacherId);
        personalInfoRepository.updateTeacherPersonalInfoStatus(entity);
    }

    private TeacherPersonalInfoResponse getTeacherPersonalInfoResponse(Long teacherId) {
        return TeacherPersonalInfoConverter.entityToResponse(personalInfoRepository.getTeacherPersonalInfoById(teacherId));
    }
}
