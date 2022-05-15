package com.graduationproject.studentinformationsystem.university.featuretoggle.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.dto.response.FeatureToggleResponse;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.enums.FeatureToggleName;

public interface FeatureToggleOutService {

    FeatureToggleResponse getFeatureToggleByName(FeatureToggleName name) throws SisNotExistException;
}
