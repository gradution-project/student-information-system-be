package com.graduationproject.studentinformationsystem.login.officer.password.model.dto.converter;

import com.graduationproject.studentinformationsystem.common.util.SisUtil;
import com.graduationproject.studentinformationsystem.login.officer.password.model.dto.response.OfficerPasswordOperationResponse;
import com.graduationproject.studentinformationsystem.login.officer.password.model.entity.OfficerPasswordOperationEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OfficerPasswordOperationInfoConverter {

    @Value("${sis.fe-url}")
    private String feUrl;

    public OfficerPasswordOperationEntity generateEntity(final Long officerId) {

        return OfficerPasswordOperationEntity.builder()
                .operationId(SisUtil.generateRandomUUID())
                .officerId(officerId)
                .expireDate(LocalDateTime.now().plusDays(1))
                .build();
    }

    public OfficerPasswordOperationResponse entityToResponse(final OfficerPasswordOperationEntity passwordOperationEntity) {

        final String operationId = passwordOperationEntity.getOperationId();

        return OfficerPasswordOperationResponse.builder()
                .operationId(operationId)
                .officerId(passwordOperationEntity.getOfficerId())
                .expireDate(passwordOperationEntity.getExpireDate())
                .passwordChangeUrl(feUrl + "/login/officer/change-password/" + operationId)
                .build();
    }
}
