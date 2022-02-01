package com.graduationproject.studentinformationsystem.login.officer.service.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.login.common.model.response.ForgotPasswordResponse;
import com.graduationproject.studentinformationsystem.login.common.model.response.LoginResponse;
import com.graduationproject.studentinformationsystem.login.common.service.PasswordService;
import com.graduationproject.studentinformationsystem.login.officer.model.dto.request.OfficerForgotPasswordRequest;
import com.graduationproject.studentinformationsystem.login.officer.model.dto.request.OfficerLoginRequest;
import com.graduationproject.studentinformationsystem.login.officer.model.entity.OfficerLoginInfoEntity;
import com.graduationproject.studentinformationsystem.login.officer.repository.OfficerLoginRepository;
import com.graduationproject.studentinformationsystem.login.officer.service.OfficerLoginService;
import com.graduationproject.studentinformationsystem.university.mail.service.OfficerMailService;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.response.OfficerInfoDetailResponse;
import com.graduationproject.studentinformationsystem.university.officer.service.OfficerAcademicInfoService;
import com.graduationproject.studentinformationsystem.university.officer.service.OfficerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class OfficerLoginServiceImpl implements OfficerLoginService {

    private final OfficerService studentService;
    private final OfficerAcademicInfoService academicInfoService;
    private final OfficerMailService mailService;

    private final OfficerLoginRepository loginRepository;
    private final PasswordService passwordService;

    @Override
    public LoginResponse login(final OfficerLoginRequest loginRequest) {
        final Long officerId = loginRequest.getOfficerId();

        if (isOfficerActive(officerId)) {

            if (checkPassword(loginRequest)) {
                loginRepository.updateLoginInfo(generateSuccessLoginEntity(officerId));
                return getSuccessLoginResponse();
            }

            if (checkFailCounter(officerId)) {
                return getFailLoginResponse();
            }
        }

        return getFailLoginResponse();
    }

    @Override
    public ForgotPasswordResponse forgotPassword(final OfficerForgotPasswordRequest forgotPasswordRequest) throws SisNotExistException {
        final OfficerInfoDetailResponse infoDetailResponse = studentService.getOfficerDetailById(forgotPasswordRequest.getOfficerId());

        mailService.sendForgotPasswordEmail(infoDetailResponse);

        return ForgotPasswordResponse.builder()
                .isForgotPasswordSuccess(true).build();
    }


    private boolean isOfficerActive(final Long officerId) {
        return academicInfoService.isOfficerActive(officerId);
    }

    protected boolean checkPassword(final OfficerLoginRequest loginRequest) {
        final String password = loginRequest.getPassword();
        final String passwordFromDb = loginRepository.getPassword(loginRequest.getOfficerId());

        return passwordService.isPasswordTrue(password, passwordFromDb);
    }

    private boolean checkFailCounter(final Long officerId) {
        final Integer failCounter = loginRepository.getFailCounter(officerId);
        if (failCounter < 3) {
            loginRepository.updateFailCounter(officerId);
            return true;
        }
        // TODO: Change Password
        return true;
    }

    private LoginResponse getSuccessLoginResponse() {
        return LoginResponse.builder()
                .isLoginSuccess(true).build();
    }

    private LoginResponse getFailLoginResponse() {
        return LoginResponse.builder()
                .isLoginSuccess(false).build();
    }

    private OfficerLoginInfoEntity generateSuccessLoginEntity(final Long officerId) {
        return OfficerLoginInfoEntity.builder()
                .officerId(officerId)
                .failCounter(0)
                .lastLoginDate(new Date())
                .build();
    }
}
