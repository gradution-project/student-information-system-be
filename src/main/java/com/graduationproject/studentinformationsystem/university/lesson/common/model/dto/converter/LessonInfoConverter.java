package com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.converter;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.SisUtil;
import com.graduationproject.studentinformationsystem.university.department.model.dto.response.DepartmentResponse;
import com.graduationproject.studentinformationsystem.university.department.service.DepartmentOutService;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.response.LessonResponse;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.entity.LessonEntity;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.enums.LessonStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LessonInfoConverter {

    private final DepartmentOutService departmentOutService;

    public LessonEntity generateSaveEntity(final Long lessonId,
                                           final LessonSaveRequest saveRequest) {

        final LessonInfoRequest lessonInfoRequest = saveRequest.getLessonInfoRequest();
        final SisOperationInfoRequest operationInfoRequest = saveRequest.getOperationInfoRequest();

        return LessonEntity.builder()
                .lessonId(lessonId)
                .departmentId(lessonInfoRequest.getDepartmentId())
                .name(lessonInfoRequest.getName())
                .semester(lessonInfoRequest.getSemester())
                .credit(lessonInfoRequest.getCredit())
                .compulsoryOrElective(lessonInfoRequest.getCompulsoryOrElective())
                .status(LessonStatus.ACTIVE)
                .createdUserId(operationInfoRequest.getUserId())
                .createdDate(new Date())
                .build();
    }

    public LessonEntity generateUpdateEntity(final Long lessonId,
                                             final LessonUpdateRequest updateRequest) {

        final LessonInfoRequest lessonInfoRequest = updateRequest.getLessonInfoRequest();
        final SisOperationInfoRequest operationInfoRequest = updateRequest.getOperationInfoRequest();

        return LessonEntity.builder()
                .lessonId(lessonId)
                .departmentId(lessonInfoRequest.getDepartmentId())
                .name(lessonInfoRequest.getName())
                .semester(lessonInfoRequest.getSemester())
                .credit(lessonInfoRequest.getCredit())
                .compulsoryOrElective(lessonInfoRequest.getCompulsoryOrElective())
                .status(LessonStatus.ACTIVE)
                .modifiedUserId(operationInfoRequest.getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public LessonEntity generateDeleteEntity(final LessonDeleteRequest deleteRequest) {
        return LessonEntity.builder()
                .lessonId(deleteRequest.getLessonId())
                .status(LessonStatus.DELETED)
                .modifiedUserId(deleteRequest.getOperationInfoRequest().getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public LessonEntity generatePassiveEntity(final LessonPassivateRequest passivateRequest) {
        return LessonEntity.builder()
                .lessonId(passivateRequest.getLessonId())
                .status(LessonStatus.PASSIVE)
                .modifiedUserId(passivateRequest.getOperationInfoRequest().getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public LessonEntity generateActiveEntity(final LessonActivateRequest activateRequest) {
        return LessonEntity.builder()
                .lessonId(activateRequest.getLessonId())
                .status(LessonStatus.ACTIVE)
                .modifiedUserId(activateRequest.getOperationInfoRequest().getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public LessonResponse entityToResponse(final LessonEntity lessonEntity) {

        final DepartmentResponse departmentResponse = departmentOutService.getDepartmentResponse(lessonEntity.getDepartmentId());

        return LessonResponse.builder()
                .lessonId(lessonEntity.getLessonId())
                .name(lessonEntity.getName())
                .status(lessonEntity.getStatus())
                .semester(lessonEntity.getSemester())
                .credit(lessonEntity.getCredit())
                .compulsoryOrElective(lessonEntity.getCompulsoryOrElective())
                .createdUserId(lessonEntity.getCreatedUserId())
                .createdDate(SisUtil.getFormattedDateTime(lessonEntity.getCreatedDate()))
                .modifiedUserId(lessonEntity.getModifiedUserId())
                .modifiedDate(SisUtil.getFormattedDateTime(lessonEntity.getModifiedDate()))
                .departmentResponse(departmentResponse)
                .build();
    }

    public List<LessonResponse> entitiesToResponses(final List<LessonEntity> lessonEntities) {
        List<LessonResponse> lessonResponses = new ArrayList<>();
        lessonEntities.forEach(lessonEntity -> lessonResponses.add(entityToResponse(lessonEntity)));
        return lessonResponses;
    }
}
