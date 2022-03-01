package com.graduationproject.studentinformationsystem.login.officer.password.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;

public interface OfficerPasswordOperationOutService {

    String getPasswordChangeUrl(Long officerId);

    void saveOrUpdatePasswordOperation(Long officerId, String feUrl) throws SisNotExistException;
}
