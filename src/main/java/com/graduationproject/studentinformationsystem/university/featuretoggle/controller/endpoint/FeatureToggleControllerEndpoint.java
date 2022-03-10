package com.graduationproject.studentinformationsystem.university.featuretoggle.controller.endpoint;

public class FeatureToggleControllerEndpoint {

    private FeatureToggleControllerEndpoint() {
    }

    public static final String NAME = "/{name}";
    public static final String ENABLE = "/enable";
    public static final String DISABLE = "/disable";
    public static final String ENABLED_BY_NAME = "/enabled" + NAME;
}
