package com.graduationproject.studentinformationsystem.university.featuretoggle.model.dto.response;

import com.graduationproject.studentinformationsystem.common.model.dto.response.SisBaseResponse;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.enums.FeatureToggleName;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@SuperBuilder
public class FeatureToggleResponse extends SisBaseResponse {

    private Long id;
    private FeatureToggleName name;
    private Boolean isEnabled;
    private Date date;
}
