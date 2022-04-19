package com.graduationproject.studentinformationsystem.login.teacher.password.service.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.login.teacher.password.model.dto.converter.TeacherPasswordOperationInfoConverter;
import com.graduationproject.studentinformationsystem.login.teacher.password.model.dto.response.TeacherPasswordOperationResponse;
import com.graduationproject.studentinformationsystem.login.teacher.password.model.entity.TeacherPasswordOperationEntity;
import com.graduationproject.studentinformationsystem.login.teacher.password.repository.TeacherPasswordOperationRepository;
import com.graduationproject.studentinformationsystem.login.teacher.password.service.TeacherPasswordOperationOutService;
import com.graduationproject.studentinformationsystem.university.teacher.service.TeacherOutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherPasswordOperationOutServiceImpl implements TeacherPasswordOperationOutService {

    private final TeacherOutService teacherOutService;

    private final TeacherPasswordOperationRepository passwordOperationRepository;
    private final TeacherPasswordOperationInfoConverter passwordOperationInfoConverter;

    @Override
    public String getPasswordChangeUrl(final Long teacherId) {

        final TeacherPasswordOperationResponse passwordOperationResponse = getPasswordOperation(teacherId);
        return passwordOperationResponse.getPasswordChangeUrl();
    }

    @Override
    public void saveOrUpdatePasswordOperation(final Long teacherId) throws SisNotExistException {

        boolean isOperationExist = isOperationExist(teacherId);

        if (!isOperationExist) {
            savePasswordOperation(teacherId);
        } else {
            updatePasswordOperation(teacherId);
        }
    }

    private TeacherPasswordOperationResponse getPasswordOperation(final Long teacherId) {

        final TeacherPasswordOperationEntity passwordOperationEntity = passwordOperationRepository.getPasswordOperation(teacherId);
        return passwordOperationInfoConverter.entityToResponse(passwordOperationEntity);
    }

    private void savePasswordOperation(final Long teacherId)
            throws SisNotExistException {

        checkBeforeSaving(teacherId);

        final TeacherPasswordOperationEntity passwordOperationEntity = passwordOperationInfoConverter.generateEntity(teacherId);
        passwordOperationRepository.savePasswordOperation(passwordOperationEntity);
    }

    private void updatePasswordOperation(final Long teacherId)
            throws SisNotExistException {

        checkBeforeUpdating(teacherId);

        final TeacherPasswordOperationEntity passwordOperationEntity = passwordOperationInfoConverter.generateEntity(teacherId);
        passwordOperationRepository.updatePasswordOperation(passwordOperationEntity);
    }

    public boolean isOperationExist(final Long teacherId) {
        return passwordOperationRepository.isOperationExist(teacherId);
    }


    /**
     * Checks Before Processing
     */

    private void checkBeforeSaving(final Long teacherId) throws SisNotExistException {
        ifTeacherIsNotExistThrowNotExistException(teacherId);
    }

    private void checkBeforeUpdating(final Long teacherId) throws SisNotExistException {
        ifTeacherIsNotExistThrowNotExistException(teacherId);
    }


    /**
     * Throw Exceptions
     */

    private void ifTeacherIsNotExistThrowNotExistException(final Long teacherId) throws SisNotExistException {
        teacherOutService.ifTeacherIsNotExistThrowNotExistException(teacherId);
    }
}
