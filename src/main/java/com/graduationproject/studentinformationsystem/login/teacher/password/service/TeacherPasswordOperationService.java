package com.graduationproject.studentinformationsystem.login.teacher.password.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.login.teacher.password.model.dto.request.TeacherPasswordChangeRequest;
import com.graduationproject.studentinformationsystem.login.teacher.password.model.dto.request.TeacherPasswordForgotRequest;
import com.graduationproject.studentinformationsystem.login.teacher.password.model.dto.response.TeacherPasswordChangeResponse;
import com.graduationproject.studentinformationsystem.login.teacher.password.model.dto.response.TeacherPasswordForgotResponse;

public interface TeacherPasswordOperationService {

    TeacherPasswordChangeResponse changePassword(TeacherPasswordChangeRequest passwordChangeRequest) throws SisNotExistException;

    TeacherPasswordForgotResponse forgotPassword(TeacherPasswordForgotRequest passwordForgotRequest) throws SisNotExistException;

    boolean isPasswordChangeEnabled(String operationId) throws SisNotExistException;
}
