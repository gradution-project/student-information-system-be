package com.graduationproject.studentinformationsystem.university.featuretoggle.service.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.dto.converter.FeatureToggleInfoConverter;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.dto.request.FeatureToggleRequest;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.dto.response.FeatureToggleIsEnabledResponse;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.dto.response.FeatureToggleResponse;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.entity.FeatureToggleEntity;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.enums.FeatureToggleName;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.exception.FeatureToggleException;
import com.graduationproject.studentinformationsystem.university.featuretoggle.repository.FeatureToggleRepository;
import com.graduationproject.studentinformationsystem.university.featuretoggle.service.FeatureToggleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeatureToggleServiceImpl implements FeatureToggleService {

    private final FeatureToggleRepository featureToggleRepository;
    private final FeatureToggleInfoConverter featureToggleInfoConverter;

    @Override
    public List<FeatureToggleResponse> getAllFeatureToggles() {
        final List<FeatureToggleEntity> featureToggleEntities = featureToggleRepository.getAllFeatureToggles();
        return featureToggleInfoConverter.entitiesToResponses(featureToggleEntities);
    }

    @Override
    public FeatureToggleResponse getFeatureToggleByName(final FeatureToggleName name) throws SisNotExistException {

        checkBeforeProcessing(name);

        final FeatureToggleEntity featureToggleEntity = featureToggleRepository.getFeatureToggleByName(name);
        return featureToggleInfoConverter.entityToResponse(featureToggleEntity);
    }

    @Override
    public FeatureToggleResponse enableFeatureToggle(final FeatureToggleRequest featureToggleRequest) throws SisNotExistException {

        checkBeforeProcessing(featureToggleRequest.getName());

        final FeatureToggleEntity featureToggleEntity = featureToggleInfoConverter.generateEntity(featureToggleRequest);
        featureToggleRepository.enableFeatureToggle(featureToggleEntity);

        return getFeatureToggleByName(featureToggleRequest.getName());
    }

    @Override
    public FeatureToggleResponse disableFeatureToggle(final FeatureToggleRequest featureToggleRequest) throws SisNotExistException {

        checkBeforeProcessing(featureToggleRequest.getName());

        final FeatureToggleEntity featureToggleEntity = featureToggleInfoConverter.generateEntity(featureToggleRequest);
        featureToggleRepository.disableFeatureToggle(featureToggleEntity);

        return getFeatureToggleByName(featureToggleRequest.getName());
    }

    @Override
    public FeatureToggleIsEnabledResponse isFeatureToggleEnabled(final FeatureToggleName name) throws SisNotExistException {

        ifFeatureToggleIsNotExistThrowNotExistException(name);

        final boolean isFeatureToggleEnabled = featureToggleRepository.isFeatureToggleEnabled(name);
        if (isFeatureToggleEnabled) {
            return FeatureToggleIsEnabledResponse.builder()
                    .isFeatureToggleEnabled(true).build();
        }
        return FeatureToggleIsEnabledResponse.builder()
                .isFeatureToggleEnabled(false).build();
    }


    /**
     * Checks Before Processing
     */

    private void checkBeforeProcessing(final FeatureToggleName name) throws SisNotExistException {
        ifFeatureToggleIsNotExistThrowNotExistException(name);
    }

    /**
     * Throw Exceptions
     */

    private void ifFeatureToggleIsNotExistThrowNotExistException(final FeatureToggleName name) throws SisNotExistException {
        if (!featureToggleRepository.isFeatureToggleExist(name)) {
            FeatureToggleException.throwNotExistException(name);
        }
    }
}
