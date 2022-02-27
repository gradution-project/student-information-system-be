package com.graduationproject.studentinformationsystem.login.student.password.service.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.login.student.password.model.dto.converter.StudentPasswordOperationInfoConverter;
import com.graduationproject.studentinformationsystem.login.student.password.model.dto.response.StudentPasswordOperationResponse;
import com.graduationproject.studentinformationsystem.login.student.password.model.entity.StudentPasswordOperationEntity;
import com.graduationproject.studentinformationsystem.login.student.password.repository.StudentPasswordOperationRepository;
import com.graduationproject.studentinformationsystem.login.student.password.service.StudentPasswordOperationOutService;
import com.graduationproject.studentinformationsystem.university.student.service.StudentOutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentPasswordOperationOutServiceImpl implements StudentPasswordOperationOutService {

    private final StudentOutService studentOutService;

    private final StudentPasswordOperationRepository passwordOperationRepository;
    private final StudentPasswordOperationInfoConverter passwordOperationInfoConverter;

    @Override
    public String getPasswordChangeUrl(final Long studentId) {

        final StudentPasswordOperationResponse passwordOperationResponse = getPasswordOperation(studentId);

        return passwordOperationResponse.getPasswordChangeUrl();
    }

    @Override
    public void saveOrUpdatePasswordOperation(final Long studentId, final String feUrl) throws SisNotExistException {

        boolean isOperationExist = isOperationExist(studentId);

        if (!isOperationExist) {
            savePasswordOperation(studentId, feUrl);
        } else {
            updatePasswordOperation(studentId, feUrl);
        }
    }

    private StudentPasswordOperationResponse getPasswordOperation(final Long studentId) {

        final StudentPasswordOperationEntity passwordOperationEntity = passwordOperationRepository.getPasswordOperation(studentId);

        return passwordOperationInfoConverter.entityToResponse(passwordOperationEntity);
    }

    private void savePasswordOperation(final Long studentId, final String feUrl)
            throws SisNotExistException {

        checkBeforeSaving(studentId);

        final StudentPasswordOperationEntity passwordOperationEntity = passwordOperationInfoConverter
                .generateEntity(studentId, feUrl);

        passwordOperationRepository.savePasswordOperation(passwordOperationEntity);
    }

    private void updatePasswordOperation(final Long studentId, final String feUrl)
            throws SisNotExistException {

        checkBeforeUpdating(studentId);

        final StudentPasswordOperationEntity passwordOperationEntity = passwordOperationInfoConverter
                .generateEntity(studentId, feUrl);

        passwordOperationRepository.updatePasswordOperation(passwordOperationEntity);
    }

    public boolean isOperationExist(final Long studentId) {
        return passwordOperationRepository.isOperationExist(studentId);
    }


    /**
     * Checks Before Processing
     */

    private void checkBeforeSaving(final Long studentId) throws SisNotExistException {
        ifStudentIsNotExistThrowNotExistException(studentId);
    }

    private void checkBeforeUpdating(final Long studentId) throws SisNotExistException {
        ifStudentIsNotExistThrowNotExistException(studentId);
    }


    /**
     * Throw Exceptions
     */

    private void ifStudentIsNotExistThrowNotExistException(final Long studentId) throws SisNotExistException {
        studentOutService.ifStudentIsNotExistThrowNotExistException(studentId);
    }
}
