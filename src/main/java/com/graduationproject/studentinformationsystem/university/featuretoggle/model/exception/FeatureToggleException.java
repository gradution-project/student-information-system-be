package com.graduationproject.studentinformationsystem.university.featuretoggle.model.exception;

import com.graduationproject.studentinformationsystem.common.util.exception.SisException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.enums.FeatureToggleName;

public class FeatureToggleException extends SisException {

    private FeatureToggleException() {
    }

    public static void throwNotExistException(final FeatureToggleName name) throws SisNotExistException {
        SisException.throwNotExistException("FEATURE TOGGLE IS NOT EXIST! name:" + name);
    }
}
