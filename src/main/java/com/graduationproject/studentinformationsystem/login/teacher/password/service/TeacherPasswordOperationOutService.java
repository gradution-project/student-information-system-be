package com.graduationproject.studentinformationsystem.login.teacher.password.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;

public interface TeacherPasswordOperationOutService {

    String getPasswordChangeUrl(Long teacherId);

    void saveOrUpdatePasswordOperation(Long teacherId, String feUrl) throws SisNotExistException;
}
