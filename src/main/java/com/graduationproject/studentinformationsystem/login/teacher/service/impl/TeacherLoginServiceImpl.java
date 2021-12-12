package com.graduationproject.studentinformationsystem.login.teacher.service.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.login.common.model.response.ForgotPasswordResponse;
import com.graduationproject.studentinformationsystem.login.common.model.response.LoginResponse;
import com.graduationproject.studentinformationsystem.login.common.service.PasswordService;
import com.graduationproject.studentinformationsystem.login.teacher.model.dto.request.TeacherForgotPasswordRequest;
import com.graduationproject.studentinformationsystem.login.teacher.model.dto.request.TeacherLoginRequest;
import com.graduationproject.studentinformationsystem.login.teacher.model.entity.TeacherLoginInfoEntity;
import com.graduationproject.studentinformationsystem.login.teacher.repository.TeacherLoginRepository;
import com.graduationproject.studentinformationsystem.login.teacher.service.TeacherLoginService;
import com.graduationproject.studentinformationsystem.university.mail.service.TeacherMailService;
import com.graduationproject.studentinformationsystem.university.teacher.service.TeacherAcademicInfoService;
import com.graduationproject.studentinformationsystem.university.teacher.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@RequiredArgsConstructor
public class TeacherLoginServiceImpl implements TeacherLoginService {

    private final TeacherService teacherService;
    private final TeacherAcademicInfoService academicInfoService;
    private final TeacherMailService mailService;

    private final TeacherLoginRepository loginRepository;
    private final PasswordService passwordService;

    @Override
    public LoginResponse login(TeacherLoginRequest loginRequest) {
        final Long teacherId = loginRequest.getTeacherId();

        if (isTeacherActive(teacherId)) {

            if (checkPassword(loginRequest)) {
                loginRepository.updateLoginInfo(generateSuccessLoginEntity(teacherId));
                return getSuccessLoginResponse();
            }

            if (checkFailCounter(teacherId)) {
                return getFailLoginResponse();
            }
        }

        return getFailLoginResponse();
    }

    @Override
    public ForgotPasswordResponse forgotPassword(TeacherForgotPasswordRequest forgotPasswordRequest) throws SisNotExistException {
        mailService.sendForgotPasswordEmail(teacherService.getTeacherDetailById(forgotPasswordRequest.getTeacherId()));
        return ForgotPasswordResponse.builder().isForgotPasswordSuccess(true).build();
    }


    private boolean isTeacherActive(Long teacherId) {
        return academicInfoService.isTeacherActive(teacherId);
    }

    protected boolean checkPassword(TeacherLoginRequest loginRequest) {
        return passwordService.isPasswordTrue(loginRequest.getPassword(), loginRepository.getPassword(loginRequest.getTeacherId()));
    }

    private boolean checkFailCounter(Long teacherId) {
        final Integer failCounter = loginRepository.getFailCounter(teacherId);
        if (failCounter < 3) {
            loginRepository.updateFailCounter(teacherId);
            return true;
        }
        // TODO: Change Password
        return true;
    }

    private LoginResponse getSuccessLoginResponse() {
        return LoginResponse.builder().isLoginSuccess(true).build();
    }

    private LoginResponse getFailLoginResponse() {
        return LoginResponse.builder().isLoginSuccess(false).build();
    }

    private TeacherLoginInfoEntity generateSuccessLoginEntity(Long teacherId) {

        return TeacherLoginInfoEntity.builder()
                .teacherId(teacherId)
                .failCounter(0)
                .lastLoginDate(new Date())
                .build();
    }
}
