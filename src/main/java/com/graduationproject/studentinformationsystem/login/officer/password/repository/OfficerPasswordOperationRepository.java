package com.graduationproject.studentinformationsystem.login.officer.password.repository;

import com.graduationproject.studentinformationsystem.login.officer.password.model.entity.OfficerPasswordOperationEntity;

public interface OfficerPasswordOperationRepository {

    OfficerPasswordOperationEntity getPasswordOperation(Long officerId);

    void savePasswordOperation(OfficerPasswordOperationEntity passwordOperationEntity);

    void updatePasswordOperation(OfficerPasswordOperationEntity passwordOperationEntity);

    void deletePasswordOperation(String operationId);

    boolean isPasswordChangeEnabled(String operationId);

    boolean isOperationExist(Long officerId);

    Long getOfficerId(String operationId);
}
