package com.graduationproject.studentinformationsystem.university.officer.service.impl;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.converter.OfficerPersonalInfoConverter;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.response.OfficerPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.university.officer.model.entity.OfficerPersonalInfoEntity;
import com.graduationproject.studentinformationsystem.university.officer.model.enums.OfficerStatus;
import com.graduationproject.studentinformationsystem.university.officer.repository.OfficerPersonalInfoRepository;
import com.graduationproject.studentinformationsystem.university.officer.service.OfficerPersonalInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfficerPersonalInfoServiceImpl implements OfficerPersonalInfoService {

    private final OfficerPersonalInfoRepository personalInfoRepository;

    @Override
    public List<OfficerPersonalInfoResponse> getAllOfficerPersonalInfosByStatus(final OfficerStatus status) {
        final List<OfficerPersonalInfoEntity> personalInfoEntities = personalInfoRepository.getAllOfficerPersonalInfosByStatus(status);
        return OfficerPersonalInfoConverter.entitiesToResponses(personalInfoEntities);
    }

    @Override
    public OfficerPersonalInfoResponse getOfficerPersonalInfoById(final Long officerId) {
        return getOfficerPersonalInfoResponse(officerId);
    }

    @Override
    public void saveOfficerPersonalInfo(final Long officerId,
                                        final OfficerPersonalInfoRequest personalInfoRequest,
                                        final SisOperationInfoRequest operationInfoRequest) {

        final OfficerPersonalInfoEntity personalInfoEntity = OfficerPersonalInfoConverter
                .generateSaveEntity(officerId, personalInfoRequest, operationInfoRequest);

        personalInfoRepository.saveOfficerPersonalInfo(personalInfoEntity);
    }

    @Override
    public OfficerPersonalInfoResponse updateOfficerPersonalInfo(final Long officerId,
                                                                 final OfficerPersonalInfoUpdateRequest personalInfoUpdateRequest) {

        final OfficerPersonalInfoEntity personalInfoEntity = OfficerPersonalInfoConverter
                .generateUpdateEntity(officerId, personalInfoUpdateRequest);

        personalInfoRepository.updateOfficerPersonalInfo(personalInfoEntity);

        return getOfficerPersonalInfoResponse(officerId);
    }

    @Override
    public void deleteOfficerPersonalInfo(final OfficerDeleteRequest deleteRequest) {
        final OfficerPersonalInfoEntity personalInfoEntity = OfficerPersonalInfoConverter.generateDeleteEntity(deleteRequest);
        personalInfoRepository.updateOfficerPersonalInfoStatus(personalInfoEntity);
    }

    @Override
    public void passivateOfficerPersonalInfo(final OfficerPassivateRequest passivateRequest) {
        final OfficerPersonalInfoEntity personalInfoEntity = OfficerPersonalInfoConverter.generatePassiveEntity(passivateRequest);
        personalInfoRepository.updateOfficerPersonalInfoStatus(personalInfoEntity);
    }

    @Override
    public void activateOfficerPersonalInfo(final OfficerActivateRequest activateRequest) {
        final OfficerPersonalInfoEntity personalInfoEntity = OfficerPersonalInfoConverter.generateActiveEntity(activateRequest);
        personalInfoRepository.updateOfficerPersonalInfoStatus(personalInfoEntity);
    }


    private OfficerPersonalInfoResponse getOfficerPersonalInfoResponse(final Long officerId) {
        final OfficerPersonalInfoEntity personalInfoEntity = personalInfoRepository.getOfficerPersonalInfoById(officerId);
        return OfficerPersonalInfoConverter.entityToResponse(personalInfoEntity);
    }
}
