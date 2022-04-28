package com.graduationproject.studentinformationsystem.university.lesson.student.registration.service.impl;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.response.LessonResponse;
import com.graduationproject.studentinformationsystem.university.lesson.common.service.LessonOutService;
import com.graduationproject.studentinformationsystem.university.lesson.student.common.service.StudentLessonOutService;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.dto.converter.StudentLessonRegistrationInfoConverter;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.dto.request.StudentLessonRegistrationApproveRequest;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.dto.request.StudentLessonRegistrationRejectRequest;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.dto.request.StudentLessonRegistrationSaveRequest;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.dto.response.StudentLessonRegistrationDetailResponse;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.dto.response.StudentLessonRegistrationResponse;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.entity.StudentLessonRegistrationEntity;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.enums.StudentLessonRegistrationStatus;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.exception.StudentLessonRegistrationException;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.repository.StudentLessonRegistrationRepository;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.service.StudentLessonRegistrationService;
import com.graduationproject.studentinformationsystem.university.note.service.StudentLessonNoteOutService;
import com.graduationproject.studentinformationsystem.university.student.service.StudentOutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentLessonRegistrationServiceImpl implements StudentLessonRegistrationService {

    private final StudentOutService studentOutService;
    private final LessonOutService lessonOutService;
    private final StudentLessonOutService studentLessonOutService;
    private final StudentLessonNoteOutService studentLessonNoteOutService;

    private final StudentLessonRegistrationRepository studentLessonRegistrationRepository;
    private final StudentLessonRegistrationInfoConverter studentLessonRegistrationInfoConverter;

    @Override
    public List<StudentLessonRegistrationResponse> getAllStudentLessonRegistrationsByStatus(final StudentLessonRegistrationStatus status) {

        final List<StudentLessonRegistrationEntity> registrationEntities = studentLessonRegistrationRepository
                .getAllStudentLessonRegistrationsByStatus(status);

        return studentLessonRegistrationInfoConverter.entitiesToResponses(registrationEntities);
    }

    @Override
    public StudentLessonRegistrationDetailResponse getStudentLessonRegistrationDetailByRegistrationId(final String registrationId)
            throws SisNotExistException {

        ifStudentLessonRegistrationIsNotExistThrowNotExistException(registrationId);

        final StudentLessonRegistrationEntity registrationEntity = studentLessonRegistrationRepository
                .getStudentLessonRegistrationByRegistrationId(registrationId);

        return studentLessonRegistrationInfoConverter.entityToResponse(registrationEntity);
    }

    @Override
    public String getStudentLessonRegistrationIdByStudentId(final Long studentId) throws SisNotExistException {

        ifStudentIsNotExistThrowNotExistException(studentId);

        final String registrationId = studentLessonRegistrationRepository.getRegistrationId(studentId);

        ifStudentLessonRegistrationIsNotExistThrowNotExistException(registrationId);

        return registrationId;
    }

    @Override
    public StudentLessonRegistrationDetailResponse saveStudentLessonRegistration(final StudentLessonRegistrationSaveRequest saveRequest)
            throws SisAlreadyException, SisNotExistException {

        final Long studentId = saveRequest.getStudentLessonRegistrationInfoRequest().getStudentId();
        final List<Long> lessonsIds = saveRequest.getStudentLessonRegistrationInfoRequest().getLessonsIds();
        final String registrationId = studentLessonRegistrationRepository.getRegistrationId(studentId);

        checkBeforeSaving(registrationId, studentId, lessonsIds);

        final StudentLessonRegistrationEntity registrationEntity = studentLessonRegistrationInfoConverter
                .generateSaveEntity(saveRequest);

        studentLessonRegistrationRepository.saveStudentLessonRegistration(registrationEntity);
        return getStudentLessonRegistrationDetailByRegistrationId(registrationEntity.getRegistrationId());
    }

    @Override
    public StudentLessonRegistrationDetailResponse approveStudentLessonRegistration(final StudentLessonRegistrationApproveRequest approveRequest)
            throws SisNotExistException, SisAlreadyException {

        final String registrationId = approveRequest.getRegistrationId();

        checkBeforeApproved(registrationId);

        final StudentLessonRegistrationEntity registrationEntity = studentLessonRegistrationInfoConverter
                .generateApprovedEntity(approveRequest);

        studentLessonRegistrationRepository.updateStudentLessonRegistrationStatus(registrationEntity);

        final StudentLessonRegistrationDetailResponse registrationDetailResponse = getStudentLessonRegistrationDetailByRegistrationId(registrationId);

        if (StudentLessonRegistrationStatus.APPROVED.equals(registrationDetailResponse.getStatus())) {
            studentLessonOutService.saveStudentLessons(registrationDetailResponse);

            final Long studentId = registrationDetailResponse.getStudentInfoResponse().getStudentId();
            final List<LessonResponse> lessonResponses = registrationDetailResponse.getLessonResponses();
            final SisOperationInfoRequest operationInfoRequest = approveRequest.getOperationInfoRequest();
            studentLessonNoteOutService.saveStudentLessonsNotesRegistrations(studentId, lessonResponses, operationInfoRequest);
        }

        return registrationDetailResponse;
    }

    @Override
    public StudentLessonRegistrationDetailResponse rejectStudentLessonRegistration(final StudentLessonRegistrationRejectRequest rejectRequest)
            throws SisNotExistException, SisAlreadyException {

        final String registrationId = rejectRequest.getRegistrationId();

        checkBeforeRejected(registrationId);

        final StudentLessonRegistrationEntity registrationEntity = studentLessonRegistrationInfoConverter
                .generateRejectedEntity(rejectRequest);

        studentLessonRegistrationRepository.updateStudentLessonRegistrationStatus(registrationEntity);
        return getStudentLessonRegistrationDetailByRegistrationId(registrationId);
    }


    /**
     * Checks Before Processing
     */

    private void checkBeforeSaving(final String registrationId, final Long studentId, final List<Long> lessonsIds)
            throws SisNotExistException, SisAlreadyException {

        if (registrationId != null) {
            ifStudentLessonRegistrationIsWaitingThrowAlreadyWaitingException(registrationId);
            ifStudentLessonRegistrationIsApprovedThrowAlreadyApprovedException(registrationId);
        }
        ifStudentIsNotExistThrowNotExistException(studentId);
        for (Long lessonId : lessonsIds) {
            ifLessonIsNotExistThrowNotExistException(lessonId);
        }
    }

    private void checkBeforeApproved(final String registrationId)
            throws SisNotExistException, SisAlreadyException {

        ifStudentLessonRegistrationIsNotExistThrowNotExistException(registrationId);
        ifStudentLessonRegistrationIsApprovedThrowAlreadyApprovedException(registrationId);
        ifStudentLessonRegistrationIsRejectedThrowAlreadyRejectedException(registrationId);
    }

    private void checkBeforeRejected(final String registrationId)
            throws SisNotExistException, SisAlreadyException {

        ifStudentLessonRegistrationIsNotExistThrowNotExistException(registrationId);
        ifStudentLessonRegistrationIsApprovedThrowAlreadyApprovedException(registrationId);
        ifStudentLessonRegistrationIsRejectedThrowAlreadyRejectedException(registrationId);
    }

    /**
     * Throw Exceptions
     */

    private void ifStudentIsNotExistThrowNotExistException(final Long studentId) throws SisNotExistException {
        studentOutService.ifStudentIsNotExistThrowNotExistException(studentId);
    }

    private void ifLessonIsNotExistThrowNotExistException(final Long lessonId) throws SisNotExistException {
        lessonOutService.ifLessonIsNotExistThrowNotExistException(lessonId);
    }

    private void ifStudentLessonRegistrationIsNotExistThrowNotExistException(final String registrationId)
            throws SisNotExistException {

        if (!studentLessonRegistrationRepository.isStudentLessonRegistrationExist(registrationId)) {
            StudentLessonRegistrationException.throwNotExistException(registrationId);
        }
    }

    private void ifStudentLessonRegistrationIsWaitingThrowAlreadyWaitingException(final String registrationId)
            throws SisAlreadyException {

        if (studentLessonRegistrationRepository.isStudentLessonRegistrationWaiting(registrationId)) {
            StudentLessonRegistrationException.throwAlreadyWaitingException(registrationId);
        }
    }

    private void ifStudentLessonRegistrationIsApprovedThrowAlreadyApprovedException(final String registrationId)
            throws SisAlreadyException {

        if (studentLessonRegistrationRepository.isStudentLessonRegistrationApproved(registrationId)) {
            StudentLessonRegistrationException.throwAlreadyApprovedException(registrationId);
        }
    }

    private void ifStudentLessonRegistrationIsRejectedThrowAlreadyRejectedException(final String registrationId)
            throws SisAlreadyException {

        if (studentLessonRegistrationRepository.isStudentLessonRegistrationRejected(registrationId)) {
            StudentLessonRegistrationException.throwAlreadyRejectedException(registrationId);
        }
    }
}
