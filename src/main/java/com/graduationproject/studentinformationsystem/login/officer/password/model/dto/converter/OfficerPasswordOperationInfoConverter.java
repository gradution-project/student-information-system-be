package com.graduationproject.studentinformationsystem.login.officer.password.model.dto.converter;

import com.graduationproject.studentinformationsystem.common.util.SisUtil;
import com.graduationproject.studentinformationsystem.login.officer.password.model.dto.response.OfficerPasswordOperationResponse;
import com.graduationproject.studentinformationsystem.login.officer.password.model.entity.OfficerPasswordOperationEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OfficerPasswordOperationInfoConverter {

    public OfficerPasswordOperationEntity generateEntity(final Long officerId, final String feUrl) {

        return OfficerPasswordOperationEntity.builder()
                .operationId(SisUtil.generateRandomUUID())
                .officerId(officerId)
                .expireDate(LocalDateTime.now().plusDays(1))
                .feUrl(feUrl)
                .build();
    }

    public OfficerPasswordOperationResponse entityToResponse(final OfficerPasswordOperationEntity passwordOperationEntity) {

        final String operationId = passwordOperationEntity.getOperationId();

        return OfficerPasswordOperationResponse.builder()
                .operationId(operationId)
                .officerId(passwordOperationEntity.getOfficerId())
                .expireDate(passwordOperationEntity.getExpireDate())
                .passwordChangeUrl(passwordOperationEntity.getFeUrl() + "/login/officer/change-password/" + operationId)
                .build();
    }
}
