package com.graduationproject.studentinformationsystem.login.teacher.password.service.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.login.common.service.PasswordOutService;
import com.graduationproject.studentinformationsystem.login.teacher.common.service.TeacherLoginOutService;
import com.graduationproject.studentinformationsystem.login.teacher.password.model.dto.request.TeacherPasswordChangeRequest;
import com.graduationproject.studentinformationsystem.login.teacher.password.model.dto.request.TeacherPasswordForgotRequest;
import com.graduationproject.studentinformationsystem.login.teacher.password.model.dto.response.TeacherPasswordChangeResponse;
import com.graduationproject.studentinformationsystem.login.teacher.password.model.dto.response.TeacherPasswordForgotResponse;
import com.graduationproject.studentinformationsystem.login.teacher.password.repository.TeacherPasswordOperationRepository;
import com.graduationproject.studentinformationsystem.login.teacher.password.service.TeacherPasswordOperationOutService;
import com.graduationproject.studentinformationsystem.login.teacher.password.service.TeacherPasswordOperationService;
import com.graduationproject.studentinformationsystem.university.mail.service.TeacherMailService;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherInfoDetailResponse;
import com.graduationproject.studentinformationsystem.university.teacher.service.TeacherOutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherPasswordOperationServiceImpl implements TeacherPasswordOperationService {

    protected final TeacherLoginOutService teacherLoginOutService;
    protected final PasswordOutService passwordOutService;

    private final TeacherOutService teacherOutService;
    private final TeacherMailService teacherMailService;

    private final TeacherPasswordOperationOutService passwordOperationOutService;
    private final TeacherPasswordOperationRepository passwordOperationRepository;

    @Override
    public TeacherPasswordChangeResponse changePassword(final TeacherPasswordChangeRequest passwordChangeRequest) {

        final boolean operationAndPasswordsCheck = operationAndPasswordsCheck(passwordChangeRequest);

        if (operationAndPasswordsCheck) {
            final String operationId = passwordChangeRequest.getOperationId();
            final Long teacherId = passwordOperationRepository.getTeacherId(operationId);
            final String password = passwordOutService.getEncodedPassword(passwordChangeRequest.getNewPassword());

            teacherLoginOutService.saveOrUpdatePassword(teacherId, password);
            passwordOperationRepository.deletePasswordOperation(operationId);

            return TeacherPasswordChangeResponse.getSuccessResponse();
        }
        return TeacherPasswordChangeResponse.getFailResponse();
    }

    @Override
    public TeacherPasswordForgotResponse forgotPassword(TeacherPasswordForgotRequest passwordForgotRequest)
            throws SisNotExistException {

        final Long teacherId = passwordForgotRequest.getTeacherId();
        ifTeacherIsNotExistThrowNotExistException(teacherId);

        final TeacherInfoDetailResponse infoDetailResponse = teacherOutService.getTeacherInfoDetailResponse(teacherId);

        passwordOperationOutService.saveOrUpdatePasswordOperation(teacherId);
        teacherMailService.sendForgotPasswordEmail(infoDetailResponse);

        return TeacherPasswordForgotResponse.builder()
                .isForgotPasswordSuccess(true)
                .build();
    }

    @Override
    public boolean isPasswordChangeEnabled(final String operationId) {
        return passwordOperationRepository.isPasswordChangeEnabled(operationId);
    }


    private boolean operationAndPasswordsCheck(TeacherPasswordChangeRequest passwordChangeRequest) {
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

    private void ifTeacherIsNotExistThrowNotExistException(final Long teacherId) throws SisNotExistException {
        teacherOutService.ifTeacherIsNotExistThrowNotExistException(teacherId);
    }
}
