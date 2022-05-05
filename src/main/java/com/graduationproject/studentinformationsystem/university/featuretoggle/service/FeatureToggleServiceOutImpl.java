package com.graduationproject.studentinformationsystem.university.featuretoggle.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.dto.converter.FeatureToggleInfoConverter;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.dto.response.FeatureToggleResponse;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.entity.FeatureToggleEntity;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.enums.FeatureToggleName;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.exception.FeatureToggleException;
import com.graduationproject.studentinformationsystem.university.featuretoggle.repository.FeatureToggleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeatureToggleServiceOutImpl implements FeatureToggleOutService {

    private final FeatureToggleRepository featureToggleRepository;
    private final FeatureToggleInfoConverter featureToggleInfoConverter;

    @Override
    public FeatureToggleResponse getFeatureToggleByName(final FeatureToggleName name) throws SisNotExistException {

        checkBeforeProcessing(name);

        final FeatureToggleEntity featureToggleEntity = featureToggleRepository.getFeatureToggleByName(name);
        return featureToggleInfoConverter.entityToResponse(featureToggleEntity);
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
