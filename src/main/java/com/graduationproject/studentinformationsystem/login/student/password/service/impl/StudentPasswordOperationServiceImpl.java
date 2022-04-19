package com.graduationproject.studentinformationsystem.login.student.password.service.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.login.common.service.PasswordOutService;
import com.graduationproject.studentinformationsystem.login.student.common.service.StudentLoginOutService;
import com.graduationproject.studentinformationsystem.login.student.password.model.dto.request.StudentPasswordChangeRequest;
import com.graduationproject.studentinformationsystem.login.student.password.model.dto.request.StudentPasswordForgotRequest;
import com.graduationproject.studentinformationsystem.login.student.password.model.dto.response.StudentPasswordChangeResponse;
import com.graduationproject.studentinformationsystem.login.student.password.model.dto.response.StudentPasswordForgotResponse;
import com.graduationproject.studentinformationsystem.login.student.password.repository.StudentPasswordOperationRepository;
import com.graduationproject.studentinformationsystem.login.student.password.service.StudentPasswordOperationOutService;
import com.graduationproject.studentinformationsystem.login.student.password.service.StudentPasswordOperationService;
import com.graduationproject.studentinformationsystem.university.mail.service.StudentMailService;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentInfoDetailResponse;
import com.graduationproject.studentinformationsystem.university.student.service.StudentOutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentPasswordOperationServiceImpl implements StudentPasswordOperationService {

    protected final StudentLoginOutService studentLoginOutService;
    protected final PasswordOutService passwordOutService;

    private final StudentOutService studentOutService;
    private final StudentMailService studentMailService;

    private final StudentPasswordOperationOutService passwordOperationOutService;
    private final StudentPasswordOperationRepository passwordOperationRepository;

    @Override
    public StudentPasswordChangeResponse changePassword(final StudentPasswordChangeRequest passwordChangeRequest) {

        final boolean operationAndPasswordsCheck = operationAndPasswordsCheck(passwordChangeRequest);

        if (operationAndPasswordsCheck) {
            final String operationId = passwordChangeRequest.getOperationId();
            final Long studentId = passwordOperationRepository.getStudentId(operationId);
            final String password = passwordOutService.getEncodedPassword(passwordChangeRequest.getNewPassword());

            studentLoginOutService.saveOrUpdatePassword(studentId, password);
            passwordOperationRepository.deletePasswordOperation(operationId);

            return StudentPasswordChangeResponse.getSuccessResponse();
        }
        return StudentPasswordChangeResponse.getFailResponse();
    }

    @Override
    public StudentPasswordForgotResponse forgotPassword(StudentPasswordForgotRequest passwordForgotRequest)
            throws SisNotExistException {

        final Long studentId = passwordForgotRequest.getStudentId();
        ifStudentIsNotExistThrowNotExistException(studentId);

        final StudentInfoDetailResponse infoDetailResponse = studentOutService.getStudentInfoDetailResponse(studentId);

        passwordOperationOutService.saveOrUpdatePasswordOperation(studentId);
        studentMailService.sendForgotPasswordEmail(infoDetailResponse);

        return StudentPasswordForgotResponse.builder()
                .isForgotPasswordSuccess(true)
                .build();
    }

    @Override
    public boolean isPasswordChangeEnabled(final String operationId) {
        return passwordOperationRepository.isPasswordChangeEnabled(operationId);
    }


    private boolean operationAndPasswordsCheck(StudentPasswordChangeRequest passwordChangeRequest) {
        final String operationId = passwordChangeRequest.getOperationId();
        final String newPassword = passwordChangeRequest.getNewPassword();
        final String newPasswordRepeat = passwordChangeRequest.getNewPasswordRepeat();

        final boolean isPasswordChangeEnabled = isPasswordChangeEnabled(operationId);
        final boolean checkPasswords = newPassword.matches(newPasswordRepeat);

        return isPasswordChangeEnabled && checkPasswords;
    }


    /**
     * Throw Exceptions
     */

    private void ifStudentIsNotExistThrowNotExistException(final Long studentId) throws SisNotExistException {
        studentOutService.ifStudentIsNotExistThrowNotExistException(studentId);
    }
}
