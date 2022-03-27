package com.graduationproject.studentinformationsystem.university.featuretoggle.model.entity;

import com.graduationproject.studentinformationsystem.common.model.entity.SisBaseEntity;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.enums.FeatureToggleName;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@SuperBuilder
public class FeatureToggleEntity extends SisBaseEntity {

    private Long id;
    private FeatureToggleName name;
    private Boolean isEnabled;
    private Date startDate;
    private Date endDate;
}
