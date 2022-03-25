package com.graduationproject.studentinformationsystem.university.graduation.model.dto.converter;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.SisUtil;
import com.graduationproject.studentinformationsystem.university.graduation.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.graduation.model.dto.response.StudentGraduationResponse;
import com.graduationproject.studentinformationsystem.university.graduation.model.entity.StudentGraduationEntity;
import com.graduationproject.studentinformationsystem.university.graduation.model.enums.StudentGraduationStatus;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentInfoResponse;
import com.graduationproject.studentinformationsystem.university.student.service.StudentOutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentGraduationInfoConverter {

    private final StudentOutService studentOutService;

    public StudentGraduationEntity generateSaveEntity(final StudentGraduationSaveRequest saveRequest) {

        final SisOperationInfoRequest operationInfoRequest = saveRequest.getOperationInfoRequest();

        return StudentGraduationEntity.builder()
                .graduationId(SisUtil.generateRandomUUID())
                .studentId(saveRequest.getStudentId())
                .status(StudentGraduationStatus.WAITING)
                .createdUserId(operationInfoRequest.getUserId())
                .createdDate(new Date()).build();
    }

    public StudentGraduationEntity generateApprovedEntity(final StudentGraduationApproveRequest approveRequest) {

        final SisOperationInfoRequest operationInfoRequest = approveRequest.getOperationInfoRequest();

        return StudentGraduationEntity.builder()
                .graduationId(approveRequest.getGraduationId())
                .status(StudentGraduationStatus.APPROVED)
                .modifiedUserId(operationInfoRequest.getUserId())
                .modifiedDate(new Date()).build();
    }

    public StudentGraduationEntity generateRejectedEntity(final StudentGraduationRejectRequest rejectRequest) {

        final SisOperationInfoRequest operationInfoRequest = rejectRequest.getOperationInfoRequest();

        return StudentGraduationEntity.builder()
                .graduationId(rejectRequest.getGraduationId())
                .status(StudentGraduationStatus.REJECTED)
                .modifiedUserId(operationInfoRequest.getUserId())
                .modifiedDate(new Date()).build();
    }

    public StudentGraduationEntity generateConfirmedEntity(final StudentGraduationConfirmRequest confirmRequest) {

        final SisOperationInfoRequest operationInfoRequest = confirmRequest.getOperationInfoRequest();

        return StudentGraduationEntity.builder()
                .graduationId(confirmRequest.getGraduationId())
                .status(StudentGraduationStatus.CONFIRMED)
                .modifiedUserId(operationInfoRequest.getUserId())
                .modifiedDate(new Date()).build();
    }

    public StudentGraduationEntity generateUnconfirmedEntity(final StudentGraduationUnconfirmRequest unconfirmRequest) {

        final SisOperationInfoRequest operationInfoRequest = unconfirmRequest.getOperationInfoRequest();

        return StudentGraduationEntity.builder()
                .graduationId(unconfirmRequest.getGraduationId())
                .status(StudentGraduationStatus.UNCONFIRMED)
                .modifiedUserId(operationInfoRequest.getUserId())
                .modifiedDate(new Date()).build();
    }

    public StudentGraduationResponse entityToResponse(final StudentGraduationEntity graduationEntity) {

        final StudentInfoResponse studentInfoResponse = getStudentInfoResponse(graduationEntity.getStudentId());

        return StudentGraduationResponse.builder()
                .graduationId(graduationEntity.getGraduationId())
                .status(graduationEntity.getStatus())
                .createdUserId(graduationEntity.getCreatedUserId())
                .createdDate(SisUtil.getFormattedDateTime(graduationEntity.getCreatedDate()))
                .modifiedUserId(graduationEntity.getModifiedUserId())
                .modifiedDate(SisUtil.getFormattedDateTime(graduationEntity.getModifiedDate()))
                .studentInfoResponse(studentInfoResponse).build();
    }

    public List<StudentGraduationResponse> entitiesToResponses(final List<StudentGraduationEntity> graduationEntities) {
        final List<StudentGraduationResponse> graduationsResponses = new ArrayList<>();
        graduationEntities.forEach(graduationEntity -> graduationsResponses.add(entityToResponse(graduationEntity)));
        return graduationsResponses;
    }

    private StudentInfoResponse getStudentInfoResponse(final Long studentId) {
        return studentOutService.getStudentInfoResponse(studentId);
    }
}
