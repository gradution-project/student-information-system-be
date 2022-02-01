package com.graduationproject.studentinformationsystem.university.teacher.service.impl;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.converter.TeacherPersonalInfoConverter;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.entity.TeacherPersonalInfoEntity;
import com.graduationproject.studentinformationsystem.university.teacher.model.enums.TeacherStatus;
import com.graduationproject.studentinformationsystem.university.teacher.repository.TeacherPersonalInfoRepository;
import com.graduationproject.studentinformationsystem.university.teacher.service.TeacherPersonalInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherPersonalInfoServiceImpl implements TeacherPersonalInfoService {

    private final TeacherPersonalInfoRepository personalInfoRepository;

    @Override
    public List<TeacherPersonalInfoResponse> getAllTeacherPersonalInfosByStatus(final TeacherStatus status) {
        final List<TeacherPersonalInfoEntity> personalInfoEntities = personalInfoRepository.getAllTeacherPersonalInfosByStatus(status);
        return TeacherPersonalInfoConverter.entitiesToResponses(personalInfoEntities);
    }

    @Override
    public TeacherPersonalInfoResponse getTeacherPersonalInfoById(final Long teacherId) {
        return getTeacherPersonalInfoResponse(teacherId);
    }

    @Override
    public void saveTeacherPersonalInfo(final Long teacherId,
                                        final TeacherPersonalInfoRequest personalInfoRequest,
                                        final SisOperationInfoRequest operationInfoRequest) {

        final TeacherPersonalInfoEntity personalInfoEntity = TeacherPersonalInfoConverter
                .generateSaveEntity(teacherId, personalInfoRequest, operationInfoRequest);

        personalInfoRepository.saveTeacherPersonalInfo(personalInfoEntity);
    }

    @Override
    public TeacherPersonalInfoResponse updateTeacherPersonalInfo(final Long teacherId,
                                                                 final TeacherPersonalInfoUpdateRequest personalInfoUpdateRequest) {

        final TeacherPersonalInfoEntity personalInfoEntity = TeacherPersonalInfoConverter
                .generateUpdateEntity(teacherId, personalInfoUpdateRequest);

        personalInfoRepository.updateTeacherPersonalInfo(personalInfoEntity);

        return getTeacherPersonalInfoResponse(teacherId);
    }

    @Override
    public void deleteTeacherPersonalInfo(final TeacherDeleteRequest deleteRequest) {
        final TeacherPersonalInfoEntity personalInfoEntity = TeacherPersonalInfoConverter.generateDeleteEntity(deleteRequest);
        personalInfoRepository.updateTeacherPersonalInfoStatus(personalInfoEntity);
    }

    @Override
    public void passivateTeacherPersonalInfo(final TeacherPassivateRequest passivateRequest) {
        final TeacherPersonalInfoEntity personalInfoEntity = TeacherPersonalInfoConverter.generatePassiveEntity(passivateRequest);
        personalInfoRepository.updateTeacherPersonalInfoStatus(personalInfoEntity);
    }

    @Override
    public void activateTeacherPersonalInfo(final TeacherActivateRequest activateRequest) {
        final TeacherPersonalInfoEntity personalInfoEntity = TeacherPersonalInfoConverter.generateActiveEntity(activateRequest);
        personalInfoRepository.updateTeacherPersonalInfoStatus(personalInfoEntity);
    }


    private TeacherPersonalInfoResponse getTeacherPersonalInfoResponse(final Long teacherId) {
        final TeacherPersonalInfoEntity personalInfoEntity = personalInfoRepository.getTeacherPersonalInfoById(teacherId);
        return TeacherPersonalInfoConverter.entityToResponse(personalInfoEntity);
    }
}
