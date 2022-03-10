package com.graduationproject.studentinformationsystem.university.featuretoggle.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.dto.request.FeatureToggleRequest;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.dto.response.FeatureToggleIsEnabledResponse;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.dto.response.FeatureToggleResponse;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.enums.FeatureToggleName;

import java.util.List;

public interface FeatureToggleService {

    List<FeatureToggleResponse> getAllFeatureToggles();

    FeatureToggleResponse getFeatureToggleByName(FeatureToggleName name) throws SisNotExistException;

    FeatureToggleResponse enableFeatureToggle(FeatureToggleRequest featureToggleRequest) throws SisNotExistException;

    FeatureToggleResponse disableFeatureToggle(FeatureToggleRequest featureToggleRequest) throws SisNotExistException;

    FeatureToggleIsEnabledResponse isFeatureToggleEnabled(FeatureToggleName name) throws SisNotExistException;
}
