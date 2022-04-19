package com.graduationproject.studentinformationsystem.login.officer.password.service.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.login.common.service.PasswordOutService;
import com.graduationproject.studentinformationsystem.login.officer.common.service.OfficerLoginOutService;
import com.graduationproject.studentinformationsystem.login.officer.password.model.dto.request.OfficerPasswordChangeRequest;
import com.graduationproject.studentinformationsystem.login.officer.password.model.dto.request.OfficerPasswordForgotRequest;
import com.graduationproject.studentinformationsystem.login.officer.password.model.dto.response.OfficerPasswordChangeResponse;
import com.graduationproject.studentinformationsystem.login.officer.password.model.dto.response.OfficerPasswordForgotResponse;
import com.graduationproject.studentinformationsystem.login.officer.password.repository.OfficerPasswordOperationRepository;
import com.graduationproject.studentinformationsystem.login.officer.password.service.OfficerPasswordOperationOutService;
import com.graduationproject.studentinformationsystem.login.officer.password.service.OfficerPasswordOperationService;
import com.graduationproject.studentinformationsystem.university.mail.service.OfficerMailService;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.response.OfficerInfoDetailResponse;
import com.graduationproject.studentinformationsystem.university.officer.service.OfficerOutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OfficerPasswordOperationServiceImpl implements OfficerPasswordOperationService {

    protected final OfficerLoginOutService officerLoginOutService;
    protected final PasswordOutService passwordOutService;

    private final OfficerOutService officerOutService;
    private final OfficerMailService officerMailService;

    private final OfficerPasswordOperationOutService passwordOperationOutService;
    private final OfficerPasswordOperationRepository passwordOperationRepository;

    @Override
    public OfficerPasswordChangeResponse changePassword(final OfficerPasswordChangeRequest passwordChangeRequest) {

        final boolean operationAndPasswordsCheck = operationAndPasswordsCheck(passwordChangeRequest);

        if (operationAndPasswordsCheck) {
            final String operationId = passwordChangeRequest.getOperationId();
            final Long officerId = passwordOperationRepository.getOfficerId(operationId);
            final String password = passwordOutService.getEncodedPassword(passwordChangeRequest.getNewPassword());

            officerLoginOutService.saveOrUpdatePassword(officerId, password);
            passwordOperationRepository.deletePasswordOperation(operationId);

            return OfficerPasswordChangeResponse.getSuccessResponse();
        }
        return OfficerPasswordChangeResponse.getFailResponse();
    }

    @Override
    public OfficerPasswordForgotResponse forgotPassword(OfficerPasswordForgotRequest passwordForgotRequest)
            throws SisNotExistException {

        final Long officerId = passwordForgotRequest.getOfficerId();
        ifOfficerIsNotExistThrowNotExistException(officerId);

        final OfficerInfoDetailResponse infoDetailResponse = officerOutService.getOfficerInfoDetailResponse(officerId);

        passwordOperationOutService.saveOrUpdatePasswordOperation(officerId);
        officerMailService.sendForgotPasswordEmail(infoDetailResponse);

        return OfficerPasswordForgotResponse.builder()
                .isForgotPasswordSuccess(true)
                .build();
    }

    @Override
    public boolean isPasswordChangeEnabled(final String operationId) {
        return passwordOperationRepository.isPasswordChangeEnabled(operationId);
    }


    private boolean operationAndPasswordsCheck(OfficerPasswordChangeRequest passwordChangeRequest) {
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

    private void ifOfficerIsNotExistThrowNotExistException(final Long officerId) throws SisNotExistException {
        officerOutService.ifOfficerIsNotExistThrowNotExistException(officerId);
    }
}
