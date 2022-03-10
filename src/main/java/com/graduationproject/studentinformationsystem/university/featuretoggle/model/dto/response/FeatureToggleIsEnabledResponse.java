package com.graduationproject.studentinformationsystem.university.featuretoggle.model.dto.response;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class FeatureToggleIsEnabledResponse {

    private Boolean isFeatureToggleEnabled;
}
