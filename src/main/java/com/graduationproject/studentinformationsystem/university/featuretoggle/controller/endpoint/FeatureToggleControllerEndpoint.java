package com.graduationproject.studentinformationsystem.university.featuretoggle.controller.endpoint;

import com.graduationproject.studentinformationsystem.common.util.controller.endpoint.SisControllerEndpoint;

public class FeatureToggleControllerEndpoint {

    private FeatureToggleControllerEndpoint() {
    }

    public static final String ALL = SisControllerEndpoint.Path.FEATURE_TOGGLES;
    public static final String BASE = SisControllerEndpoint.Path.FEATURE_TOGGLE;
    public static final String BY_NAME = BASE + "/{name}";
    public static final String ENABLE = BASE + "/enable";
    public static final String DISABLE = BASE + "/disable";
    public static final String ENABLED_BY_NAME = BASE + "/enabled/{name}";
}
