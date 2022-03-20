package com.graduationproject.studentinformationsystem.university.featuretoggle.repository;

import com.graduationproject.studentinformationsystem.university.featuretoggle.model.entity.FeatureToggleEntity;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.enums.FeatureToggleName;

import java.util.List;

public interface FeatureToggleRepository {

    List<FeatureToggleEntity> getAllFeatureToggles();

    FeatureToggleEntity getFeatureToggleByName(FeatureToggleName name);

    void enableFeatureToggle(FeatureToggleEntity featureToggleEntity);

    void disableFeatureToggle(FeatureToggleEntity featureToggleEntity);

    boolean isFeatureToggleExist(FeatureToggleName name);

    boolean isFeatureToggleEnabled(FeatureToggleName name);
}
