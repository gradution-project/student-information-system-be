package com.graduationproject.studentinformationsystem.university.schedule.lesson.model.dto.converter;

import com.graduationproject.studentinformationsystem.common.util.SisUtil;
import com.graduationproject.studentinformationsystem.university.department.model.dto.response.DepartmentResponse;
import com.graduationproject.studentinformationsystem.university.department.service.DepartmentOutService;
import com.graduationproject.studentinformationsystem.university.schedule.common.model.dto.response.ScheduleFileDetailResponse;
import com.graduationproject.studentinformationsystem.university.schedule.common.model.dto.response.ScheduleFileResponse;
import com.graduationproject.studentinformationsystem.university.schedule.common.model.entity.ScheduleFileEntity;
import com.graduationproject.studentinformationsystem.university.schedule.lesson.controller.endpoint.LessonScheduleFileControllerEndpoint;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LessonScheduleFileInfoConverter {

    private final DepartmentOutService departmentOutService;

    @Value("${sis.be-url}")
    private String beUrl;

    public ScheduleFileEntity generateSaveEntity(final String fileId,
                                                 final MultipartFile document,
                                                 final Long facultyId,
                                                 final Long departmentId,
                                                 final Long operationUserId) throws IOException {

        return ScheduleFileEntity.builder()
                .fileId(fileId)
                .facultyId(facultyId)
                .departmentId(departmentId)
                .fileName(document.getOriginalFilename())
                .fileType(document.getContentType())
                .file(document)
                .fileByte(document.getBytes())
                .fileSize(document.getSize())
                .createdUserId(operationUserId)
                .createdDate(new Date())
                .build();
    }

    public ScheduleFileResponse entityToResponse(final ScheduleFileEntity scheduleFileEntity) throws IOException {
        return ScheduleFileResponse.builder()
                .fileName(scheduleFileEntity.getFileName())
                .fileByte(scheduleFileEntity.getFileByte())
                .fileSize(scheduleFileEntity.getFileSize())
                .file(scheduleFileEntity.getFile())
                .build();
    }

    public ScheduleFileDetailResponse entityToDetailResponse(final ScheduleFileEntity scheduleFileEntity) {

        final DepartmentResponse departmentResponse = departmentOutService.getDepartmentResponse(scheduleFileEntity.getDepartmentId());

        return ScheduleFileDetailResponse.builder()
                .fileId(scheduleFileEntity.getFileId())
                .fileName(scheduleFileEntity.getFileName())
                .fileType(scheduleFileEntity.getFileType())
                .fileDownloadUrl(beUrl + LessonScheduleFileControllerEndpoint.Out.DOWNLOAD + scheduleFileEntity.getFileId())
                .fileViewUrl(beUrl + LessonScheduleFileControllerEndpoint.Out.VIEW + scheduleFileEntity.getFileId())
                .createdUserId(scheduleFileEntity.getCreatedUserId())
                .createdDate(SisUtil.getFormattedDateTime(scheduleFileEntity.getCreatedDate()))
                .departmentResponse(departmentResponse)
                .build();
    }

    public List<ScheduleFileDetailResponse> entitiesToResponses(final List<ScheduleFileEntity> scheduleFileEntities) {
        List<ScheduleFileDetailResponse> scheduleFileDetailResponses = new ArrayList<>();
        scheduleFileEntities.forEach(scheduleFileEntity -> scheduleFileDetailResponses.add(entityToDetailResponse(scheduleFileEntity)));
        return scheduleFileDetailResponses;
    }
}
