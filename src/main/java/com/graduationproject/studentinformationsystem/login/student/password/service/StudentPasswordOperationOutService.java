package com.graduationproject.studentinformationsystem.login.student.password.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;

public interface StudentPasswordOperationOutService {

    String getPasswordChangeUrl(Long studentId);

    void saveOrUpdatePasswordOperation(Long studentId, String feUrl) throws SisNotExistException;
}
