package com.graduationproject.studentinformationsystem.university.schedule.lesson.controller;


import com.graduationproject.studentinformationsystem.common.util.controller.response.SisApiResponse;
import com.graduationproject.studentinformationsystem.common.util.controller.response.SisBaseApiResponse;
import com.graduationproject.studentinformationsystem.common.util.enums.SisFileType;
import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.common.util.validation.id.DepartmentID;
import com.graduationproject.studentinformationsystem.common.util.validation.id.FacultyID;
import com.graduationproject.studentinformationsystem.common.util.validation.id.OperationUserID;
import com.graduationproject.studentinformationsystem.university.schedule.common.controller.endpoint.ScheduleFileControllerEndpoint;
import com.graduationproject.studentinformationsystem.university.schedule.common.model.dto.response.ScheduleFileDetailResponse;
import com.graduationproject.studentinformationsystem.university.schedule.common.model.dto.response.ScheduleFileResponse;
import com.graduationproject.studentinformationsystem.university.schedule.lesson.service.LessonScheduleFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.graduationproject.studentinformationsystem.common.config.SisSwaggerConfiguration.LESSON_SCHEDULE_FILE_API_TAG;
import static com.graduationproject.studentinformationsystem.common.util.controller.endpoint.SisControllerEndpoint.Path.LESSON_SCHEDULE_FILE;
import static com.graduationproject.studentinformationsystem.common.util.controller.response.SisResponseUtil.successResponse;

@RestController
@RequestMapping(LESSON_SCHEDULE_FILE)
@Api(tags = LESSON_SCHEDULE_FILE_API_TAG)
@RequiredArgsConstructor
public class LessonScheduleFileController {

    private final LessonScheduleFileService lessonScheduleFileService;

    @GetMapping(ScheduleFileControllerEndpoint.FACULTY_ID)
    @ApiOperation(value = "Get Lesson Schedule Files Detail By Faculty ID")
    public ResponseEntity<SisBaseApiResponse<List<ScheduleFileDetailResponse>>> getLessonScheduleFilesByFacultyId(
            @PathVariable final Long facultyId)
            throws SisNotExistException {

        final List<ScheduleFileDetailResponse> scheduleFileDetailResponses = lessonScheduleFileService
                .getLessonScheduleFilesByFacultyId(facultyId);
        return successResponse(scheduleFileDetailResponses);
    }

    @GetMapping(ScheduleFileControllerEndpoint.VIEW)
    @ApiOperation(value = "View Lesson Schedule File By ID")
    public ResponseEntity<byte[]> viewLessonScheduleFileById(
            @PathVariable String fileId)
            throws SisNotExistException, IOException {

        final ScheduleFileResponse scheduleFileResponse = lessonScheduleFileService.getLessonScheduleFileById(fileId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; fileName=\"" + scheduleFileResponse.getFileName() + "\"")
                .contentLength(scheduleFileResponse.getFileSize())
                .contentType(MediaType.parseMediaType(SisFileType.PDF.getName()))
                .body(scheduleFileResponse.getFileByte());
    }

    @GetMapping(ScheduleFileControllerEndpoint.DOWNLOAD)
    @ApiOperation(value = "Download Lesson Schedule File By ID")
    public ResponseEntity<MultipartFile> downloadLessonScheduleFileById(
            @PathVariable String fileId)
            throws SisNotExistException, IOException {

        final ScheduleFileResponse scheduleFileResponse = lessonScheduleFileService.getLessonScheduleFileById(fileId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + scheduleFileResponse.getFileName() + "\"")
                .body(scheduleFileResponse.getFile());
    }

    @GetMapping(ScheduleFileControllerEndpoint.DEPARTMENT_ID)
    @ApiOperation(value = "Get Lesson Schedule File Detail By Department ID")
    public ResponseEntity<SisBaseApiResponse<ScheduleFileDetailResponse>> getLessonScheduleFileByDepartmentId(
            @PathVariable final Long departmentId)
            throws SisNotExistException {

        final ScheduleFileDetailResponse scheduleFileDetailResponse = lessonScheduleFileService
                .getLessonScheduleFileByDepartmentId(departmentId);
        return successResponse(scheduleFileDetailResponse);
    }

    @PostMapping(
            value = ScheduleFileControllerEndpoint.SAVE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @ApiOperation(value = "Save Lesson Schedule File")
    public ResponseEntity<SisBaseApiResponse<ScheduleFileDetailResponse>> saveLessonScheduleFile(
            @RequestParam String apiUrl,
            @RequestParam @FacultyID Long facultyId,
            @RequestParam @DepartmentID Long departmentId,
            @RequestParam @OperationUserID Long operationUserId,
            @RequestPart(value = "document") MultipartFile document)
            throws SisNotExistException, IOException, SisAlreadyException {

        final ScheduleFileDetailResponse scheduleFileDetailResponse = lessonScheduleFileService
                .saveLessonScheduleFile(apiUrl, facultyId, departmentId, operationUserId, document);
        return successResponse(scheduleFileDetailResponse);
    }

    @DeleteMapping(ScheduleFileControllerEndpoint.DELETE)
    @ApiOperation(value = "Delete Lesson Schedule File")
    public ResponseEntity<SisApiResponse> deleteLessonScheduleFile(
            @PathVariable final Long departmentId)
            throws SisNotExistException, SisAlreadyException {

        lessonScheduleFileService.deleteLessonScheduleFileByDepartmentId(departmentId);
        return successResponse();
    }
}
