package com.graduationproject.studentinformationsystem.university.graduation.service.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.graduation.model.dto.converter.StudentGraduationInfoConverter;
import com.graduationproject.studentinformationsystem.university.graduation.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.graduation.model.dto.response.StudentGraduationResponse;
import com.graduationproject.studentinformationsystem.university.graduation.model.entity.StudentGraduationEntity;
import com.graduationproject.studentinformationsystem.university.graduation.model.enums.StudentGraduationStatus;
import com.graduationproject.studentinformationsystem.university.graduation.model.exception.StudentGraduationException;
import com.graduationproject.studentinformationsystem.university.graduation.repository.StudentGraduationRepository;
import com.graduationproject.studentinformationsystem.university.graduation.service.StudentGraduationService;
import com.graduationproject.studentinformationsystem.university.note.service.StudentLessonNoteOutService;
import com.graduationproject.studentinformationsystem.university.student.service.StudentOutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentGraduationServiceImpl implements StudentGraduationService {

    private final StudentOutService studentOutService;
    private final StudentLessonNoteOutService studentLessonNoteOutService;

    private final StudentGraduationRepository studentGraduationRepository;
    private final StudentGraduationInfoConverter studentGraduationInfoConverter;

    @Override
    public List<StudentGraduationResponse> getAllStudentsGraduationsByStatus(final StudentGraduationStatus status) {

        final List<StudentGraduationEntity> graduationEntities = studentGraduationRepository
                .getAllStudentsGraduationsByStatus(status);

        return studentGraduationInfoConverter.entitiesToResponses(graduationEntities);
    }

    @Override
    public StudentGraduationResponse getStudentGraduationDetailByGraduationId(final String graduationId)
            throws SisNotExistException {

        ifStudentGraduationIsNotExistThrowNotExistException(graduationId);

        final StudentGraduationEntity graduationEntity = studentGraduationRepository
                .getStudentGraduationDetailByGraduationId(graduationId);

        return studentGraduationInfoConverter.entityToResponse(graduationEntity);
    }

    @Override
    public StudentGraduationResponse saveStudentGraduation(final StudentGraduationSaveRequest saveRequest)
            throws SisAlreadyException, SisNotExistException {

        final Long studentId = saveRequest.getStudentId();
        ifStudentIsNotExistThrowNotExistException(studentId);

        final String graduationId = studentGraduationRepository.getGraduationId(studentId);
        checkBeforeSaving(graduationId, studentId);

        final StudentGraduationEntity graduationEntity = studentGraduationInfoConverter.generateSaveEntity(saveRequest);
        studentGraduationRepository.saveStudentGraduation(graduationEntity);

        return getStudentGraduationDetailByGraduationId(graduationEntity.getGraduationId());
    }

    @Override
    public StudentGraduationResponse approveStudentGraduation(final StudentGraduationApproveRequest approveRequest)
            throws SisNotExistException, SisAlreadyException {

        final String graduationId = approveRequest.getGraduationId();
        checkBeforeApproved(graduationId);

        final StudentGraduationEntity graduationEntity = studentGraduationInfoConverter.generateApprovedEntity(approveRequest);
        studentGraduationRepository.updateStudentGraduationStatus(graduationEntity);

        return getStudentGraduationDetailByGraduationId(graduationId);
    }

    @Override
    public StudentGraduationResponse rejectStudentGraduation(final StudentGraduationRejectRequest rejectRequest)
            throws SisNotExistException, SisAlreadyException {

        final String graduationId = rejectRequest.getGraduationId();
        checkBeforeRejected(graduationId);

        final StudentGraduationEntity graduationEntity = studentGraduationInfoConverter.generateRejectedEntity(rejectRequest);
        studentGraduationRepository.updateStudentGraduationStatus(graduationEntity);

        return getStudentGraduationDetailByGraduationId(graduationId);
    }

    @Override
    public StudentGraduationResponse confirmStudentGraduation(final StudentGraduationConfirmRequest confirmRequest)
            throws SisNotExistException, SisAlreadyException {

        final String graduationId = confirmRequest.getGraduationId();
        checkBeforeConfirmed(graduationId);

        confirmStudentGraduationOperation(confirmRequest);
        graduateStudent(graduationId, confirmRequest);

        return getStudentGraduationDetailByGraduationId(graduationId);
    }

    private void confirmStudentGraduationOperation(final StudentGraduationConfirmRequest confirmRequest) {
        final StudentGraduationEntity graduationEntity = studentGraduationInfoConverter
                .generateConfirmedEntity(confirmRequest);

        studentGraduationRepository.updateStudentGraduationStatus(graduationEntity);
    }

    private void graduateStudent(final String graduationId, final StudentGraduationConfirmRequest confirmRequest)
            throws SisNotExistException, SisAlreadyException {

        final Long studentId = studentGraduationRepository.getStudentId(graduationId);
        studentOutService.graduateStudent(studentId, confirmRequest.getOperationInfoRequest());
    }

    @Override
    public StudentGraduationResponse unconfirmStudentGraduation(StudentGraduationUnconfirmRequest unconfirmRequest)
            throws SisAlreadyException, SisNotExistException {

        final String graduationId = unconfirmRequest.getGraduationId();
        checkBeforeUnconfirmed(graduationId);

        final StudentGraduationEntity graduationEntity = studentGraduationInfoConverter.generateUnconfirmedEntity(unconfirmRequest);
        studentGraduationRepository.updateStudentGraduationStatus(graduationEntity);

        return getStudentGraduationDetailByGraduationId(graduationId);
    }

    @Override
    public boolean isStudentGraduationEnabled(final Long studentId) throws SisAlreadyException, SisNotExistException {

        ifStudentIsNotExistThrowNotExistException(studentId);

        return studentLessonNoteOutService.isStudentGraduationEnabled(studentId);
    }


    /**
     * Checks Before Processing
     */

    private void checkBeforeSaving(final String graduationId, final Long studentId) throws SisAlreadyException {

        ifStudentIsNotPassedAllLessonsThrowUnfinalisedOrFailedLessonNoteExistException(studentId);

        if (graduationId != null) {
            ifStudentGraduationIsWaitingThrowAlreadyWaitingException(graduationId);
            ifStudentGraduationIsApprovedThrowAlreadyApprovedException(graduationId);
            ifStudentGraduationIsConfirmedThrowAlreadyConfirmedException(graduationId);
        }
    }

    private void checkBeforeApproved(final String graduationId) throws SisAlreadyException, SisNotExistException {

        ifStudentGraduationIsNotExistThrowNotExistException(graduationId);
        ifStudentGraduationIsApprovedThrowAlreadyApprovedException(graduationId);
        ifStudentGraduationIsRejectedThrowAlreadyRejectedException(graduationId);
        ifStudentGraduationIsConfirmedThrowAlreadyConfirmedException(graduationId);
        ifStudentGraduationIsUnconfirmedThrowAlreadyUnconfirmedException(graduationId);
    }

    private void checkBeforeRejected(final String graduationId) throws SisNotExistException, SisAlreadyException {

        ifStudentGraduationIsNotExistThrowNotExistException(graduationId);
        ifStudentGraduationIsApprovedThrowAlreadyApprovedException(graduationId);
        ifStudentGraduationIsRejectedThrowAlreadyRejectedException(graduationId);
        ifStudentGraduationIsConfirmedThrowAlreadyConfirmedException(graduationId);
        ifStudentGraduationIsUnconfirmedThrowAlreadyUnconfirmedException(graduationId);
    }

    private void checkBeforeConfirmed(final String graduationId) throws SisNotExistException, SisAlreadyException {

        ifStudentGraduationIsNotExistThrowNotExistException(graduationId);
        ifStudentGraduationIsWaitingThrowAlreadyWaitingException(graduationId);
        ifStudentGraduationIsRejectedThrowAlreadyRejectedException(graduationId);
        ifStudentGraduationIsConfirmedThrowAlreadyConfirmedException(graduationId);
        ifStudentGraduationIsUnconfirmedThrowAlreadyUnconfirmedException(graduationId);
    }

    private void checkBeforeUnconfirmed(final String graduationId) throws SisNotExistException, SisAlreadyException {

        ifStudentGraduationIsNotExistThrowNotExistException(graduationId);
        ifStudentGraduationIsWaitingThrowAlreadyWaitingException(graduationId);
        ifStudentGraduationIsRejectedThrowAlreadyRejectedException(graduationId);
        ifStudentGraduationIsConfirmedThrowAlreadyConfirmedException(graduationId);
        ifStudentGraduationIsUnconfirmedThrowAlreadyUnconfirmedException(graduationId);
    }


    /**
     * Throw Exceptions
     */

    private void ifStudentIsNotExistThrowNotExistException(final Long studentId) throws SisNotExistException {
        studentOutService.ifStudentIsNotExistThrowNotExistException(studentId);
    }

    private void ifStudentIsNotPassedAllLessonsThrowUnfinalisedOrFailedLessonNoteExistException(final Long studentId) throws SisAlreadyException {
        studentLessonNoteOutService.hasTheStudentPassedAllLessons(studentId);
    }

    private void ifStudentGraduationIsNotExistThrowNotExistException(final String graduationId)
            throws SisNotExistException {

        if (!studentGraduationRepository.isStudentGraduationExist(graduationId)) {
            StudentGraduationException.throwNotExistException(graduationId);
        }
    }

    private void ifStudentGraduationIsWaitingThrowAlreadyWaitingException(final String graduationId)
            throws SisAlreadyException {

        if (studentGraduationRepository.isStudentGraduationWaiting(graduationId)) {
            StudentGraduationException.throwAlreadyWaitingException(graduationId);
        }
    }

    private void ifStudentGraduationIsApprovedThrowAlreadyApprovedException(final String graduationId)
            throws SisAlreadyException {

        if (studentGraduationRepository.isStudentGraduationApproved(graduationId)) {
            StudentGraduationException.throwAlreadyApprovedException(graduationId);
        }
    }

    private void ifStudentGraduationIsRejectedThrowAlreadyRejectedException(final String graduationId)
            throws SisAlreadyException {

        if (studentGraduationRepository.isStudentGraduationRejected(graduationId)) {
            StudentGraduationException.throwAlreadyRejectedException(graduationId);
        }
    }

    private void ifStudentGraduationIsConfirmedThrowAlreadyConfirmedException(final String graduationId)
            throws SisAlreadyException {

        if (studentGraduationRepository.isStudentGraduationConfirmed(graduationId)) {
            StudentGraduationException.throwAlreadyConfirmedException(graduationId);
        }
    }

    private void ifStudentGraduationIsUnconfirmedThrowAlreadyUnconfirmedException(final String graduationId)
            throws SisAlreadyException {

        if (studentGraduationRepository.isStudentGraduationUnconfirmed(graduationId)) {
            StudentGraduationException.throwAlreadyUnconfirmedException(graduationId);
        }
    }
}
