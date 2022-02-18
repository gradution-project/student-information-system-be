package com.graduationproject.studentinformationsystem.university.officer.service.impl;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.converter.OfficerAcademicInfoConverter;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.response.OfficerAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.officer.model.entity.OfficerAcademicInfoEntity;
import com.graduationproject.studentinformationsystem.university.officer.model.enums.OfficerStatus;
import com.graduationproject.studentinformationsystem.university.officer.repository.OfficerAcademicInfoRepository;
import com.graduationproject.studentinformationsystem.university.officer.service.OfficerAcademicInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfficerAcademicInfoServiceImpl implements OfficerAcademicInfoService {

    private final OfficerAcademicInfoRepository academicInfoRepository;
    private final OfficerAcademicInfoConverter academicInfoConverter;

    @Override
    public List<OfficerAcademicInfoResponse> getAllOfficerAcademicInfosByStatus(final OfficerStatus status) {
        final List<OfficerAcademicInfoEntity> academicInfoEntities = academicInfoRepository.getAllOfficerAcademicInfosByStatus(status);
        return academicInfoConverter.entitiesToResponses(academicInfoEntities);
    }

    @Override
    public OfficerAcademicInfoResponse getOfficerAcademicInfoById(final Long officerId) {
        return getOfficerAcademicInfoResponse(officerId);
    }

    @Override
    public void saveOfficerAcademicInfo(final Long officerId,
                                        final String officerEmail,
                                        final OfficerAcademicInfoRequest academicInfoRequest,
                                        final SisOperationInfoRequest operationInfoRequest) {

        final OfficerAcademicInfoEntity academicInfoEntity = academicInfoConverter
                .generateSaveEntity(officerId, officerEmail, academicInfoRequest, operationInfoRequest);

        academicInfoRepository.saveOfficerAcademicInfo(academicInfoEntity);
    }

    @Override
    public OfficerAcademicInfoResponse updateOfficerAcademicInfo(final Long officerId,
                                                                 final OfficerAcademicInfoUpdateRequest academicInfoUpdateRequest) {

        final OfficerAcademicInfoEntity academicInfoEntity = academicInfoConverter
                .generateUpdateEntity(officerId, academicInfoUpdateRequest);

        academicInfoRepository.updateOfficerAcademicInfo(academicInfoEntity);

        return getOfficerAcademicInfoResponse(officerId);
    }

    @Override
    public void deleteOfficerAcademicInfo(final OfficerDeleteRequest deleteRequest) {
        final OfficerAcademicInfoEntity academicInfoEntity = academicInfoConverter.generateDeleteEntity(deleteRequest);
        academicInfoRepository.updateOfficerAcademicInfoStatus(academicInfoEntity);
    }

    @Override
    public void passivateOfficerAcademicInfo(final OfficerPassivateRequest passivateRequest) {
        final OfficerAcademicInfoEntity academicInfoEntity = academicInfoConverter.generatePassiveEntity(passivateRequest);
        academicInfoRepository.updateOfficerAcademicInfoStatus(academicInfoEntity);
    }

    @Override
    public void activateOfficerAcademicInfo(final OfficerActivateRequest activateRequest) {
        final OfficerAcademicInfoEntity academicInfoEntity = academicInfoConverter.generateActiveEntity(activateRequest);
        academicInfoRepository.updateOfficerAcademicInfoStatus(academicInfoEntity);
    }

    @Override
    public List<Long> getAllOfficerIdsByFacultyId(final Long facultyId) {
        return academicInfoRepository.getAllOfficerIdsByFacultyId(facultyId);
    }

    @Override
    public boolean isOfficerExist(final Long officerId) {
        return academicInfoRepository.isOfficerExist(officerId);
    }

    @Override
    public boolean isOfficerDeleted(final Long officerId) {
        return academicInfoRepository.isOfficerDeleted(officerId);
    }

    @Override
    public boolean isOfficerPassive(final Long officerId) {
        return academicInfoRepository.isOfficerPassive(officerId);
    }

    @Override
    public boolean isOfficerActive(final Long officerId) {
        return academicInfoRepository.isOfficerActive(officerId);
    }


    private OfficerAcademicInfoResponse getOfficerAcademicInfoResponse(final Long officerId) {
        final OfficerAcademicInfoEntity academicInfoEntity = academicInfoRepository.getOfficerAcademicInfoById(officerId);
        return academicInfoConverter.entityToResponse(academicInfoEntity);
    }
}
