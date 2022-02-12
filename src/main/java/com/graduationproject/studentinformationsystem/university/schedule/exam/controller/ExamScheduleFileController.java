package com.graduationproject.studentinformationsystem.university.schedule.exam.controller;


import com.graduationproject.studentinformationsystem.common.util.controller.response.SisApiResponse;
import com.graduationproject.studentinformationsystem.common.util.controller.response.SisBaseApiResponse;
import com.graduationproject.studentinformationsystem.common.util.enums.SisFileType;
import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.common.util.validation.id.DepartmentID;
import com.graduationproject.studentinformationsystem.common.util.validation.id.FacultyID;
import com.graduationproject.studentinformationsystem.common.util.validation.id.OperationUserID;
import com.graduationproject.studentinformationsystem.university.schedule.exam.controller.enpoint.ExamScheduleFileControllerEndpoint;
import com.graduationproject.studentinformationsystem.university.schedule.exam.model.dto.response.ExamScheduleFileDetailResponse;
import com.graduationproject.studentinformationsystem.university.schedule.exam.model.dto.response.ExamScheduleFileResponse;
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
import static com.graduationproject.studentinformationsystem.common.util.controller.endpoint.SisControllerEndpoint.Path.EXAM_SCHEDULE_FILE;
import static com.graduationproject.studentinformationsystem.common.util.controller.response.SisResponseUtil.successResponse;

@RestController
@RequestMapping(EXAM_SCHEDULE_FILE)
@Api(tags = EXAM_SCHEDULE_FILE_API_TAG)
@RequiredArgsConstructor
public class ExamScheduleFileController {

    private final ExamScheduleFileService examScheduleFileService;

    @GetMapping(ExamScheduleFileControllerEndpoint.FACULTY_ID)
    @ApiOperation(value = "Get Exam Schedule Files Detail By Faculty ID")
    public ResponseEntity<SisBaseApiResponse<List<ExamScheduleFileDetailResponse>>> getExamScheduleFilesByFacultyId(
            @PathVariable final Long facultyId)
            throws SisNotExistException {

        final List<ExamScheduleFileDetailResponse> scheduleFileDetailResponses = examScheduleFileService
                .getExamScheduleFilesByFacultyId(facultyId);
        return successResponse(scheduleFileDetailResponses);
    }

    @GetMapping(ExamScheduleFileControllerEndpoint.VIEW)
    @ApiOperation(value = "View Exam Schedule File By ID")
    public ResponseEntity<byte[]> viewExamScheduleFileById(
            @PathVariable String fileId)
            throws SisNotExistException, IOException {

        final ExamScheduleFileResponse scheduleFileResponse = examScheduleFileService.getExamScheduleFileById(fileId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; fileName=\"" + scheduleFileResponse.getFileName() + "\"")
                .contentLength(scheduleFileResponse.getFileSize())
                .contentType(MediaType.parseMediaType(SisFileType.PDF.getName()))
                .body(scheduleFileResponse.getFileByte());
    }

    @GetMapping(ExamScheduleFileControllerEndpoint.DOWNLOAD)
    @ApiOperation(value = "Download Exam Schedule File By ID")
    public ResponseEntity<MultipartFile> downloadExamScheduleFileById(
            @PathVariable String fileId)
            throws SisNotExistException, IOException {

        final ExamScheduleFileResponse scheduleFileResponse = examScheduleFileService.getExamScheduleFileById(fileId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + scheduleFileResponse.getFileName() + "\"")
                .body(scheduleFileResponse.getFile());
    }

    @GetMapping(ExamScheduleFileControllerEndpoint.DEPARTMENT_ID)
    @ApiOperation(value = "Get Exam Schedule File Detail By Department ID")
    public ResponseEntity<SisBaseApiResponse<ExamScheduleFileDetailResponse>> getExamScheduleFileByDepartmentId(
            @PathVariable final Long departmentId)
            throws SisNotExistException {

        final ExamScheduleFileDetailResponse scheduleFileDetailResponse = examScheduleFileService
                .getExamScheduleFileByDepartmentId(departmentId);
        return successResponse(scheduleFileDetailResponse);
    }

    @PostMapping(
            value = ExamScheduleFileControllerEndpoint.SAVE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @ApiOperation(value = "Save Exam Schedule File")
    public ResponseEntity<SisBaseApiResponse<ExamScheduleFileDetailResponse>> saveExamScheduleFile(
            @RequestParam String apiUrl,
            @RequestParam @FacultyID Long facultyId,
            @RequestParam @DepartmentID Long departmentId,
            @RequestParam @OperationUserID Long operationUserId,
            @RequestPart(value = "document") MultipartFile document)
            throws SisNotExistException, IOException, SisAlreadyException {

        final ExamScheduleFileDetailResponse scheduleFileDetailResponse = examScheduleFileService
                .saveExamScheduleFile(apiUrl, facultyId, departmentId, operationUserId, document);
        return successResponse(scheduleFileDetailResponse);
    }

    @DeleteMapping(ExamScheduleFileControllerEndpoint.DELETE)
    @ApiOperation(value = "Delete Exam Schedule File")
    public ResponseEntity<SisApiResponse> deleteExamScheduleFile(
            @PathVariable final Long departmentId)
            throws SisNotExistException, SisAlreadyException {

        examScheduleFileService.deleteExamScheduleFileByDepartmentId(departmentId);
        return successResponse();
    }
}
