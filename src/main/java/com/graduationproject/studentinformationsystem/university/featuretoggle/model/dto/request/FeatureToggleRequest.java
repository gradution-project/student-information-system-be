package com.graduationproject.studentinformationsystem.university.featuretoggle.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.enums.FeatureToggleName;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class FeatureToggleRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -4460192886349312510L;

    @NotNull
    private FeatureToggleName name;

    @Valid
    private SisOperationInfoRequest operationInfoRequest;
}
