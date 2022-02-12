package com.graduationproject.studentinformationsystem.university.schedule.exam.model.dto.converter;

import com.graduationproject.studentinformationsystem.common.util.SisUtil;
import com.graduationproject.studentinformationsystem.university.department.model.dto.converter.DepartmentInfoConverter;
import com.graduationproject.studentinformationsystem.university.schedule.common.model.dto.response.ScheduleFileDetailResponse;
import com.graduationproject.studentinformationsystem.university.schedule.common.model.dto.response.ScheduleFileResponse;
import com.graduationproject.studentinformationsystem.university.schedule.common.model.entity.ScheduleFileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExamScheduleFileInfoConverter {

    private ExamScheduleFileInfoConverter() {
    }

    public static ScheduleFileEntity generateSaveEntity(final String fileId,
                                                        final MultipartFile document,
                                                        final String apiUrl,
                                                        final Long facultyId,
                                                        final Long departmentId,
                                                        final Long operationUserId) throws IOException {

        return ScheduleFileEntity.builder()
                .fileId(fileId)
                .facultyId(facultyId)
                .departmentId(departmentId)
                .apiUrl(apiUrl)
                .fileName(document.getOriginalFilename())
                .fileType(document.getContentType())
                .file(document)
                .fileByte(document.getBytes())
                .fileSize(document.getSize())
                .createdUserId(operationUserId)
                .createdDate(new Date())
                .build();
    }

    public static ScheduleFileResponse entityToResponse(final ScheduleFileEntity scheduleFileEntity) throws IOException {
        return ScheduleFileResponse.builder()
                .fileName(scheduleFileEntity.getFileName())
                .fileByte(scheduleFileEntity.getFileByte())
                .fileSize(scheduleFileEntity.getFileSize())
                .file(scheduleFileEntity.getFile())
                .build();
    }

    public static ScheduleFileDetailResponse entityToDetailResponse(final ScheduleFileEntity scheduleFileEntity) {
        return ScheduleFileDetailResponse.builder()
                .fileId(scheduleFileEntity.getFileId())
                .departmentResponse(DepartmentInfoConverter.entityToResponse(scheduleFileEntity.getDepartmentEntity()))
                .fileName(scheduleFileEntity.getFileName())
                .fileType(scheduleFileEntity.getFileType())
                .fileDownloadUrl(scheduleFileEntity.getApiUrl() + "/exam-schedule-file/download/" + scheduleFileEntity.getFileId())
                .fileViewUrl(scheduleFileEntity.getApiUrl() + "/exam-schedule-file/view/" + scheduleFileEntity.getFileId())
                .createdUserId(scheduleFileEntity.getCreatedUserId())
                .createdDate(SisUtil.getFormattedDateTime(scheduleFileEntity.getCreatedDate()))
                .build();
    }

    public static List<ScheduleFileDetailResponse> entitiesToResponses(final List<ScheduleFileEntity> scheduleFileEntities) {
        List<ScheduleFileDetailResponse> scheduleFileDetailResponses = new ArrayList<>();
        scheduleFileEntities.forEach(scheduleFileEntity -> scheduleFileDetailResponses.add(entityToDetailResponse(scheduleFileEntity)));
        return scheduleFileDetailResponses;
    }
}
