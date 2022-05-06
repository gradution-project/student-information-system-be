package com.graduationproject.studentinformationsystem.university.schedule.exam.controller;


import com.graduationproject.studentinformationsystem.common.util.controller.response.SisApiResponse;
import com.graduationproject.studentinformationsystem.common.util.controller.response.SisBaseApiResponse;
import com.graduationproject.studentinformationsystem.common.util.enums.SisFileType;
import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.common.util.validation.id.DepartmentID;
import com.graduationproject.studentinformationsystem.common.util.validation.id.FacultyID;
import com.graduationproject.studentinformationsystem.common.util.validation.id.OperationUserID;
import com.graduationproject.studentinformationsystem.university.schedule.common.model.dto.response.ScheduleFileDetailResponse;
import com.graduationproject.studentinformationsystem.university.schedule.common.model.dto.response.ScheduleFileResponse;
import com.graduationproject.studentinformationsystem.university.schedule.exam.controller.endpoint.ExamScheduleFileControllerEndpoint;
import com.graduationproject.studentinformationsystem.university.schedule.exam.service.ExamScheduleFileService;
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

import static com.graduationproject.studentinformationsystem.common.config.SisSwaggerConfiguration.EXAM_SCHEDULE_FILE_API_TAG;
import static com.graduationproject.studentinformationsystem.common.util.controller.response.SisResponseUtil.successResponse;

@RestController
@RequestMapping
@Api(tags = EXAM_SCHEDULE_FILE_API_TAG)
@RequiredArgsConstructor
public class ExamScheduleFileController {

    private final ExamScheduleFileService examScheduleFileService;

    @GetMapping(ExamScheduleFileControllerEndpoint.ALL_BY_FACULTY_ID)
    @ApiOperation(value = "Get Exam Schedule Files Detail By Faculty ID")
    public ResponseEntity<SisBaseApiResponse<List<ScheduleFileDetailResponse>>> getExamScheduleFilesByFacultyId(
            @PathVariable final Long facultyId)
            throws SisNotExistException {

        final List<ScheduleFileDetailResponse> scheduleFileDetailResponses = examScheduleFileService
                .getExamScheduleFilesByFacultyId(facultyId);
        return successResponse(scheduleFileDetailResponses);
    }

    @GetMapping(ExamScheduleFileControllerEndpoint.VIEW_BY_FILE_ID)
    @ApiOperation(value = "View Exam Schedule File By File ID")
    public ResponseEntity<byte[]> viewExamScheduleFileById(
            @PathVariable String fileId)
            throws SisNotExistException, IOException {

        final ScheduleFileResponse scheduleFileResponse = examScheduleFileService.getExamScheduleFileById(fileId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; fileName=\"" + scheduleFileResponse.getFileName() + "\"")
                .contentLength(scheduleFileResponse.getFileSize())
                .contentType(MediaType.parseMediaType(SisFileType.PDF.getName()))
                .body(scheduleFileResponse.getFileByte());
    }

    @GetMapping(ExamScheduleFileControllerEndpoint.DOWNLOAD_BY_FILE_ID)
    @ApiOperation(value = "Download Exam Schedule File By File ID")
    public ResponseEntity<MultipartFile> downloadExamScheduleFileById(
            @PathVariable String fileId)
            throws SisNotExistException, IOException {

        final ScheduleFileResponse scheduleFileResponse = examScheduleFileService.getExamScheduleFileById(fileId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + scheduleFileResponse.getFileName() + "\"")
                .body(scheduleFileResponse.getFile());
    }

    @GetMapping(ExamScheduleFileControllerEndpoint.BY_DEPARTMENT_ID)
    @ApiOperation(value = "Get Exam Schedule File Detail By Department ID")
    public ResponseEntity<SisBaseApiResponse<ScheduleFileDetailResponse>> getExamScheduleFileByDepartmentId(
            @PathVariable final Long departmentId)
            throws SisNotExistException {

        final ScheduleFileDetailResponse scheduleFileDetailResponse = examScheduleFileService
                .getExamScheduleFileByDepartmentId(departmentId);
        return successResponse(scheduleFileDetailResponse);
    }

    @PostMapping(
            value = ExamScheduleFileControllerEndpoint.BASE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @ApiOperation(value = "Save Exam Schedule File")
    public ResponseEntity<SisBaseApiResponse<ScheduleFileDetailResponse>> saveExamScheduleFile(
            @RequestParam @FacultyID Long facultyId,
            @RequestParam @DepartmentID Long departmentId,
            @RequestParam @OperationUserID Long operationUserId,
            @RequestPart(value = "document") MultipartFile document)
            throws SisNotExistException, IOException, SisAlreadyException {

        final ScheduleFileDetailResponse scheduleFileDetailResponse = examScheduleFileService
                .saveExamScheduleFile(facultyId, departmentId, operationUserId, document);
        return successResponse(scheduleFileDetailResponse);
    }

    @DeleteMapping(ExamScheduleFileControllerEndpoint.BY_DEPARTMENT_ID)
    @ApiOperation(value = "Delete Exam Schedule File")
    public ResponseEntity<SisApiResponse> deleteExamScheduleFile(
            @PathVariable final Long departmentId)
            throws SisNotExistException, SisAlreadyException {

        examScheduleFileService.deleteExamScheduleFileByDepartmentId(departmentId);
        return successResponse();
    }
}
