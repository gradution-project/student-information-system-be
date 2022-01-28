package com.graduationproject.studentinformationsystem.login.officer.service.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.login.common.model.response.ForgotPasswordResponse;
import com.graduationproject.studentinformationsystem.login.common.model.response.LoginResponse;
import com.graduationproject.studentinformationsystem.login.officer.model.dto.request.OfficerForgotPasswordRequest;
import com.graduationproject.studentinformationsystem.login.officer.model.dto.request.OfficerLoginRequest;
import com.graduationproject.studentinformationsystem.login.officer.service.OfficerLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//TODO: Officer Login Service Must Be Add
@Service
@RequiredArgsConstructor
public class OfficerLoginServiceImpl implements OfficerLoginService {

    @Override
    public LoginResponse login(final OfficerLoginRequest loginRequest) {
        return null;
    }

    @Override
    public ForgotPasswordResponse forgotPassword(final OfficerForgotPasswordRequest forgotPasswordRequest) throws SisNotExistException {
        return null;
    }

//    private final OfficerService studentService;
//    private final OfficerAcademicInfoService academicInfoService;
//    private final OfficerMailService mailService;
//
//    private final OfficerLoginRepository loginRepository;
//    private final PasswordService passwordService;
//
//    @Override
//    public LoginResponse login(OfficerLoginRequest loginRequest) {
//        final Long officerId = loginRequest.getofficerId();
//
//        if (isOfficerActive(officerId)) {
//
//            if (checkPassword(loginRequest)) {
//                loginRepository.updateLoginInfo(generateSuccessLoginEntity(officerId));
//                return getSuccessLoginResponse();
//            }
//
//            if (checkFailCounter(officerId)) {
//                return getFailLoginResponse();
//            }
//        }
//
//        return getFailLoginResponse();
//    }
//
//    @Override
//    public ForgotPasswordResponse forgotPassword(OfficerForgotPasswordRequest forgotPasswordRequest) throws SisNotExistException {
//        mailService.sendForgotPasswordEmail(studentService.getOfficerDetailById(forgotPasswordRequest.getofficerId()));
//        return ForgotPasswordResponse.builder().isForgotPasswordSuccess(true).build();
//    }
//
//
//    private boolean isOfficerActive(Long officerId) {
//        return academicInfoService.isOfficerActive(officerId);
//    }
//
//    protected boolean checkPassword(OfficerLoginRequest loginRequest) {
//        return passwordService.isPasswordTrue(loginRequest.getPassword(), loginRepository.getPassword(loginRequest.getofficerId()));
//    }
//
//    private boolean checkFailCounter(Long officerId) {
//        final Integer failCounter = loginRepository.getFailCounter(officerId);
//        if (failCounter < 3) {
//            loginRepository.updateFailCounter(officerId);
//            return true;
//        }
//        // TODO: Change Password
//        return true;
//    }
//
//    private LoginResponse getSuccessLoginResponse() {
//        return LoginResponse.builder().isLoginSuccess(true).build();
//    }
//
//    private LoginResponse getFailLoginResponse() {
//        return LoginResponse.builder().isLoginSuccess(false).build();
//    }
//
//    private OfficerLoginInfoEntity generateSuccessLoginEntity(Long officerId) {
//
//        return OfficerLoginInfoEntity.builder()
//                .officerId(officerId)
//                .failCounter(0)
//                .lastLoginDate(new Date())
//                .build();
//    }
}
