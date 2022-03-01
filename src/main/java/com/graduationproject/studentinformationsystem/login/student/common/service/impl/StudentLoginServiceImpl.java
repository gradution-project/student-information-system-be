package com.graduationproject.studentinformationsystem.login.student.common.service.impl;

import com.graduationproject.studentinformationsystem.login.common.model.response.LoginResponse;
import com.graduationproject.studentinformationsystem.login.common.service.PasswordService;
import com.graduationproject.studentinformationsystem.login.student.common.model.dto.request.StudentLoginRequest;
import com.graduationproject.studentinformationsystem.login.student.common.model.entity.StudentLoginInfoEntity;
import com.graduationproject.studentinformationsystem.login.student.common.repository.StudentLoginRepository;
import com.graduationproject.studentinformationsystem.login.student.common.service.StudentLoginService;
import com.graduationproject.studentinformationsystem.university.student.service.StudentAcademicInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class StudentLoginServiceImpl implements StudentLoginService {

    private final StudentAcademicInfoService academicInfoService;

    private final StudentLoginRepository loginRepository;
    private final PasswordService passwordService;

    @Override
    public LoginResponse login(StudentLoginRequest loginRequest) {
        final Long studentId = loginRequest.getStudentId();

        if (isStudentActive(studentId)) {

            if (checkPassword(loginRequest)) {
                loginRepository.updateLoginInfo(generateSuccessLoginEntity(studentId));
                return getSuccessLoginResponse();
            }

            if (checkFailCounter(studentId)) {
                return getFailLoginResponse();
            }
        }

        return getFailLoginResponse();
    }


    private boolean isStudentActive(Long studentId) {
        return academicInfoService.isStudentActive(studentId);
    }

    protected boolean checkPassword(StudentLoginRequest loginRequest) {
        return passwordService.isPasswordTrue(loginRequest.getPassword(), loginRepository.getPassword(loginRequest.getStudentId()));
    }

    private boolean checkFailCounter(Long studentId) {
        final Integer failCounter = loginRepository.getFailCounter(studentId);
        if (failCounter < 3) {
            loginRepository.updateFailCounter(studentId);
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

    private StudentLoginInfoEntity generateSuccessLoginEntity(Long studentId) {

        return StudentLoginInfoEntity.builder()
                .studentId(studentId)
                .failCounter(0)
                .lastLoginDate(new Date())
                .build();
    }
}
