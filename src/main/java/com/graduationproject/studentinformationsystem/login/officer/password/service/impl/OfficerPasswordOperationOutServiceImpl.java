package com.graduationproject.studentinformationsystem.login.officer.password.service.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.login.officer.password.model.dto.converter.OfficerPasswordOperationInfoConverter;
import com.graduationproject.studentinformationsystem.login.officer.password.model.dto.response.OfficerPasswordOperationResponse;
import com.graduationproject.studentinformationsystem.login.officer.password.model.entity.OfficerPasswordOperationEntity;
import com.graduationproject.studentinformationsystem.login.officer.password.repository.OfficerPasswordOperationRepository;
import com.graduationproject.studentinformationsystem.login.officer.password.service.OfficerPasswordOperationOutService;
import com.graduationproject.studentinformationsystem.university.officer.service.OfficerOutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OfficerPasswordOperationOutServiceImpl implements OfficerPasswordOperationOutService {

    private final OfficerOutService officerOutService;

    private final OfficerPasswordOperationRepository passwordOperationRepository;
    private final OfficerPasswordOperationInfoConverter passwordOperationInfoConverter;

    @Override
    public String getPasswordChangeUrl(final Long officerId) {

        final OfficerPasswordOperationResponse passwordOperationResponse = getPasswordOperation(officerId);
        return passwordOperationResponse.getPasswordChangeUrl();
    }

    @Override
    public void saveOrUpdatePasswordOperation(final Long officerId) throws SisNotExistException {

        boolean isOperationExist = isOperationExist(officerId);

        if (!isOperationExist) {
            savePasswordOperation(officerId);
        } else {
            updatePasswordOperation(officerId);
        }
    }

    private OfficerPasswordOperationResponse getPasswordOperation(final Long officerId) {

        final OfficerPasswordOperationEntity passwordOperationEntity = passwordOperationRepository.getPasswordOperation(officerId);

        return passwordOperationInfoConverter.entityToResponse(passwordOperationEntity);
    }

    private void savePasswordOperation(final Long officerId)
            throws SisNotExistException {

        checkBeforeSaving(officerId);

        final OfficerPasswordOperationEntity passwordOperationEntity = passwordOperationInfoConverter
                .generateEntity(officerId);

        passwordOperationRepository.savePasswordOperation(passwordOperationEntity);
    }

    private void updatePasswordOperation(final Long officerId)
            throws SisNotExistException {

        checkBeforeUpdating(officerId);

        final OfficerPasswordOperationEntity passwordOperationEntity = passwordOperationInfoConverter
                .generateEntity(officerId);

        passwordOperationRepository.updatePasswordOperation(passwordOperationEntity);
    }

    public boolean isOperationExist(final Long officerId) {
        return passwordOperationRepository.isOperationExist(officerId);
    }


    /**
     * Checks Before Processing
     */

    private void checkBeforeSaving(final Long officerId) throws SisNotExistException {
        ifOfficerIsNotExistThrowNotExistException(officerId);
    }

    private void checkBeforeUpdating(final Long officerId) throws SisNotExistException {
        ifOfficerIsNotExistThrowNotExistException(officerId);
    }


    /**
     * Throw Exceptions
     */

    private void ifOfficerIsNotExistThrowNotExistException(final Long officerId) throws SisNotExistException {
        officerOutService.ifOfficerIsNotExistThrowNotExistException(officerId);
    }
}
