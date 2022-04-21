package com.graduationproject.studentinformationsystem.university.featuretoggle.service.impl;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.dto.request.FeatureToggleRequest;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.entity.FeatureToggleEntity;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.enums.FeatureToggleName;
import com.graduationproject.studentinformationsystem.university.featuretoggle.repository.FeatureToggleRepository;
import com.graduationproject.studentinformationsystem.university.featuretoggle.service.TriggerFeatureTogglesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class TriggerFeatureTogglesServiceImpl implements TriggerFeatureTogglesService {

    private final FeatureToggleRepository featureToggleRepository;

    @Override
    public void triggerEnableFeatureToggles(final FeatureToggleRequest featureToggleRequest) {

        final SisOperationInfoRequest operationInfoRequest = featureToggleRequest.getOperationInfoRequest();

        switch (featureToggleRequest.getName()) {
            case MIDTERM_NOTE_OPERATIONS -> {
                enableNoteOperationsFeatureToggle(operationInfoRequest);
                disableFinalNoteOperationsFeatureToggle(operationInfoRequest);
                disableResitNoteOperationsFeatureToggle(operationInfoRequest);
            }
            case FINAL_NOTE_OPERATIONS -> {
                enableNoteOperationsFeatureToggle(operationInfoRequest);
                disableMidtermNoteOperationsFeatureToggle(operationInfoRequest);
                disableResitNoteOperationsFeatureToggle(operationInfoRequest);
            }
            case RESIT_NOTE_OPERATIONS -> {
                enableNoteOperationsFeatureToggle(operationInfoRequest);
                disableMidtermNoteOperationsFeatureToggle(operationInfoRequest);
                disableFinalNoteOperationsFeatureToggle(operationInfoRequest);
            }
        }
    }

    @Override
    public void triggerDisableFeatureToggles(FeatureToggleRequest featureToggleRequest) {

        final SisOperationInfoRequest operationInfoRequest = featureToggleRequest.getOperationInfoRequest();

        switch (featureToggleRequest.getName()) {
            case NOTE_OPERATIONS -> {
                disableMidtermNoteOperationsFeatureToggle(operationInfoRequest);
                disableFinalNoteOperationsFeatureToggle(operationInfoRequest);
                disableResitNoteOperationsFeatureToggle(operationInfoRequest);
            }
        }
    }

    private void enableNoteOperationsFeatureToggle(final SisOperationInfoRequest operationInfoRequest) {
        final FeatureToggleEntity featureToggleEntity = FeatureToggleEntity.builder()
                .name(FeatureToggleName.NOTE_OPERATIONS)
                .modifiedUserId(operationInfoRequest.getUserId())
                .modifiedDate(new Date())
                .build();
        featureToggleRepository.enableFeatureToggle(featureToggleEntity);
    }

    private void disableMidtermNoteOperationsFeatureToggle(final SisOperationInfoRequest operationInfoRequest) {
        final FeatureToggleEntity featureToggleEntity = FeatureToggleEntity.builder()
                .name(FeatureToggleName.MIDTERM_NOTE_OPERATIONS)
                .modifiedUserId(operationInfoRequest.getUserId())
                .modifiedDate(new Date())
                .build();
        featureToggleRepository.disableFeatureToggle(featureToggleEntity);
    }

    private void disableFinalNoteOperationsFeatureToggle(final SisOperationInfoRequest operationInfoRequest) {
        final FeatureToggleEntity featureToggleEntity = FeatureToggleEntity.builder()
                .name(FeatureToggleName.FINAL_NOTE_OPERATIONS)
                .modifiedUserId(operationInfoRequest.getUserId())
                .modifiedDate(new Date())
                .build();
        featureToggleRepository.disableFeatureToggle(featureToggleEntity);
    }

    private void disableResitNoteOperationsFeatureToggle(final SisOperationInfoRequest operationInfoRequest) {
        final FeatureToggleEntity featureToggleEntity = FeatureToggleEntity.builder()
                .name(FeatureToggleName.RESIT_NOTE_OPERATIONS)
                .modifiedUserId(operationInfoRequest.getUserId())
                .modifiedDate(new Date())
                .build();
        featureToggleRepository.disableFeatureToggle(featureToggleEntity);
    }
}
