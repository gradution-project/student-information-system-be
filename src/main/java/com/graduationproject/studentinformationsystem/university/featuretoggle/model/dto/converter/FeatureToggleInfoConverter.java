package com.graduationproject.studentinformationsystem.university.featuretoggle.model.dto.converter;

import com.graduationproject.studentinformationsystem.common.util.SisUtil;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.dto.request.FeatureToggleRequest;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.dto.response.FeatureToggleResponse;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.entity.FeatureToggleEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class FeatureToggleInfoConverter {

    public FeatureToggleEntity generateEntity(final FeatureToggleRequest featureToggleRequest) {

        return FeatureToggleEntity.builder()
                .name(featureToggleRequest.getName())
                .modifiedUserId(featureToggleRequest.getOperationInfoRequest().getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public FeatureToggleResponse entityToResponse(final FeatureToggleEntity featureToggleEntity) {
        return FeatureToggleResponse.builder()
                .id(featureToggleEntity.getId())
                .name(featureToggleEntity.getName())
                .isEnabled(featureToggleEntity.getIsEnabled())
                .date(SisUtil.getFormattedDateTime(featureToggleEntity.getDate()))
                .createdUserId(featureToggleEntity.getCreatedUserId())
                .createdDate(SisUtil.getFormattedDateTime(featureToggleEntity.getCreatedDate()))
                .modifiedUserId(featureToggleEntity.getModifiedUserId())
                .modifiedDate(SisUtil.getFormattedDateTime(featureToggleEntity.getModifiedDate()))
                .build();
    }

    public List<FeatureToggleResponse> entitiesToResponses(final List<FeatureToggleEntity> facultyEntities) {
        List<FeatureToggleResponse> featureToggleResponses = new ArrayList<>();
        facultyEntities.forEach(facultyEntity -> featureToggleResponses.add(entityToResponse(facultyEntity)));
        return featureToggleResponses;
    }
}
