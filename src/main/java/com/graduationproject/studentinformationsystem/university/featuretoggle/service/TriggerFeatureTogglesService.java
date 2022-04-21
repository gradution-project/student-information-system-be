package com.graduationproject.studentinformationsystem.university.featuretoggle.service;

import com.graduationproject.studentinformationsystem.university.featuretoggle.model.dto.request.FeatureToggleRequest;

public interface TriggerFeatureTogglesService {

    void triggerEnableFeatureToggles(FeatureToggleRequest featureToggleRequest);

    void triggerDisableFeatureToggles(FeatureToggleRequest featureToggleRequest);
}
