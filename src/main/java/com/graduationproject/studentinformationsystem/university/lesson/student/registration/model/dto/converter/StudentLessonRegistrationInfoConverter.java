package com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.dto.converter;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.SisUtil;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.response.LessonResponse;
import com.graduationproject.studentinformationsystem.university.lesson.common.service.LessonOutService;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.dto.request.StudentLessonRegistrationApproveRequest;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.dto.request.StudentLessonRegistrationInfoRequest;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.dto.request.StudentLessonRegistrationRejectRequest;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.dto.request.StudentLessonRegistrationSaveRequest;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.dto.response.StudentLessonRegistrationDetailResponse;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.dto.response.StudentLessonRegistrationResponse;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.entity.StudentLessonRegistrationEntity;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.enums.StudentLessonRegistrationStatus;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentInfoResponse;
import com.graduationproject.studentinformationsystem.university.student.service.StudentOutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentLessonRegistrationInfoConverter {

    private final StudentOutService studentOutService;
    private final LessonOutService lessonOutService;

    public StudentLessonRegistrationEntity generateSaveEntity(final StudentLessonRegistrationSaveRequest saveRequest) {

        final StudentLessonRegistrationInfoRequest studentLessonRegistrationInfoRequest = saveRequest.getStudentLessonRegistrationInfoRequest();
        final SisOperationInfoRequest operationInfoRequest = saveRequest.getOperationInfoRequest();

        return StudentLessonRegistrationEntity.builder()
                .registrationId(SisUtil.generateRandomUUID())
                .studentId(studentLessonRegistrationInfoRequest.getStudentId())
                .lessonsIds(SisUtil.longListToString(studentLessonRegistrationInfoRequest.getLessonsIds()))
                .status(StudentLessonRegistrationStatus.WAITING)
                .createdUserId(operationInfoRequest.getUserId())
                .createdDate(new Date()).build();
    }

    public StudentLessonRegistrationEntity generateApprovedEntity(final StudentLessonRegistrationApproveRequest approveRequest) {

        final SisOperationInfoRequest operationInfoRequest = approveRequest.getOperationInfoRequest();

        return StudentLessonRegistrationEntity.builder()
                .registrationId(approveRequest.getRegistrationId())
                .status(StudentLessonRegistrationStatus.APPROVED)
                .modifiedUserId(operationInfoRequest.getUserId())
                .modifiedDate(new Date()).build();
    }

    public StudentLessonRegistrationEntity generateRejectedEntity(final StudentLessonRegistrationRejectRequest rejectRequest) {

        final SisOperationInfoRequest operationInfoRequest = rejectRequest.getOperationInfoRequest();

        return StudentLessonRegistrationEntity.builder()
                .registrationId(rejectRequest.getRegistrationId())
                .status(StudentLessonRegistrationStatus.REJECTED)
                .modifiedUserId(operationInfoRequest.getUserId())
                .modifiedDate(new Date()).build();
    }

    public StudentLessonRegistrationDetailResponse entityToResponse(final StudentLessonRegistrationEntity registrationEntity) {

        final StudentInfoResponse studentInfoResponse = getStudentInfoResponse(registrationEntity.getStudentId());
        final List<Long> lessonIds = SisUtil.longArrayToLongList(registrationEntity.getLessonsIds());
        final List<LessonResponse> lessonResponses = new ArrayList<>();

        lessonIds.forEach(lessonId -> lessonResponses.add(getLessonResponse(lessonId)));

        return StudentLessonRegistrationDetailResponse.builder()
                .registrationId(registrationEntity.getRegistrationId())
                .status(registrationEntity.getStatus())
                .createdUserId(registrationEntity.getCreatedUserId())
                .createdDate(SisUtil.getFormattedDateTime(registrationEntity.getCreatedDate()))
                .modifiedUserId(registrationEntity.getModifiedUserId())
                .modifiedDate(SisUtil.getFormattedDateTime(registrationEntity.getModifiedDate()))
                .studentInfoResponse(studentInfoResponse)
                .lessonResponses(lessonResponses).build();
    }

    public List<StudentLessonRegistrationResponse> entitiesToResponses(final List<StudentLessonRegistrationEntity> registrationEntities) {

        final List<StudentLessonRegistrationResponse> registrationResponses = new ArrayList<>();

        registrationEntities.forEach(registrationEntity -> {
            final StudentInfoResponse studentInfoResponse = getStudentInfoResponse(registrationEntity.getStudentId());

            final StudentLessonRegistrationResponse registrationResponse = StudentLessonRegistrationResponse.builder()
                    .registrationId(registrationEntity.getRegistrationId())
                    .status(registrationEntity.getStatus())
                    .createdUserId(registrationEntity.getCreatedUserId())
                    .createdDate(SisUtil.getFormattedDateTime(registrationEntity.getCreatedDate()))
                    .modifiedUserId(registrationEntity.getModifiedUserId())
                    .modifiedDate(SisUtil.getFormattedDateTime(registrationEntity.getModifiedDate()))
                    .studentInfoResponse(studentInfoResponse).build();

            registrationResponses.add(registrationResponse);
        });

        return registrationResponses;
    }

    private StudentInfoResponse getStudentInfoResponse(final Long studentId) {
        return studentOutService.getStudentInfoResponse(studentId);
    }

    private LessonResponse getLessonResponse(final Long lessonId) {
        return lessonOutService.getLessonResponse(lessonId);
    }
}
