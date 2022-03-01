package com.graduationproject.studentinformationsystem.login.officer.password.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.login.officer.password.model.dto.request.OfficerPasswordChangeRequest;
import com.graduationproject.studentinformationsystem.login.officer.password.model.dto.request.OfficerPasswordForgotRequest;
import com.graduationproject.studentinformationsystem.login.officer.password.model.dto.response.OfficerPasswordChangeResponse;
import com.graduationproject.studentinformationsystem.login.officer.password.model.dto.response.OfficerPasswordForgotResponse;

public interface OfficerPasswordOperationService {

    OfficerPasswordChangeResponse changePassword(OfficerPasswordChangeRequest passwordChangeRequest) throws SisNotExistException;

    OfficerPasswordForgotResponse forgotPassword(OfficerPasswordForgotRequest passwordForgotRequest) throws SisNotExistException;

    boolean isPasswordChangeEnabled(String operationId) throws SisNotExistException;
}
