package com.graduationproject.studentinformationsystem.login.student.password.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.login.student.password.model.dto.request.StudentPasswordChangeRequest;
import com.graduationproject.studentinformationsystem.login.student.password.model.dto.request.StudentPasswordForgotRequest;
import com.graduationproject.studentinformationsystem.login.student.password.model.dto.response.StudentPasswordChangeResponse;
import com.graduationproject.studentinformationsystem.login.student.password.model.dto.response.StudentPasswordForgotResponse;

public interface StudentPasswordOperationService {

    StudentPasswordChangeResponse changePassword(StudentPasswordChangeRequest passwordChangeRequest) throws SisNotExistException;

    StudentPasswordForgotResponse forgotPassword(StudentPasswordForgotRequest passwordForgotRequest) throws SisNotExistException;

    boolean isPasswordChangeEnabled(String operationId) throws SisNotExistException;
}
