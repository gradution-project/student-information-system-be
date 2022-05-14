package com.graduationproject.studentinformationsystem.university.transcript.controller;

import com.graduationproject.studentinformationsystem.common.util.controller.response.SisBaseApiResponse;
import com.graduationproject.studentinformationsystem.common.util.enums.SisFileType;
import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisProcessException;
import com.graduationproject.studentinformationsystem.common.util.validation.id.StudentID;
import com.graduationproject.studentinformationsystem.university.transcript.controller.endpoint.StudentTranscriptControllerEndpoint;
import com.graduationproject.studentinformationsystem.university.transcript.model.dto.response.StudentTranscriptResponse;
import com.graduationproject.studentinformationsystem.university.transcript.service.StudentTranscriptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.graduationproject.studentinformationsystem.common.config.SisSwaggerConfiguration.STUDENT_TRANSCRIPT_API_TAG;
import static com.graduationproject.studentinformationsystem.common.util.controller.response.SisResponseUtil.successResponse;

@RestController
@RequestMapping
@Api(tags = STUDENT_TRANSCRIPT_API_TAG)
@RequiredArgsConstructor
public class StudentTranscriptController {

    private final StudentTranscriptService studentTranscriptService;

    @GetMapping(StudentTranscriptControllerEndpoint.BY_STUDENT_ID)
    @ApiOperation(value = "Get Student Transcript Detail By Student ID")
    public ResponseEntity<SisBaseApiResponse<StudentTranscriptResponse>> getStudentTranscriptDetailByStudentId(
            @PathVariable @StudentID final Long studentId)
            throws SisNotExistException, SisAlreadyException {

        final StudentTranscriptResponse transcriptResponse = studentTranscriptService.getStudentTranscriptDetailByStudentId(studentId);
        return successResponse(transcriptResponse);
    }

    @GetMapping(StudentTranscriptControllerEndpoint.DOWNLOAD_BY_STUDENT_ID)
    @ApiOperation(value = "Download Student Transcript File By Student ID")
    public void downloadStudentTranscriptFileByStudentId(
            @PathVariable @StudentID final Long studentId,
            final HttpServletResponse httpServletResponse)
            throws SisNotExistException, SisAlreadyException, IOException, SisProcessException {

        httpServletResponse.setContentType(SisFileType.PDF.getName());
        String fileName = createFileName(studentId);
        httpServletResponse.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        studentTranscriptService.exportStudentTranscriptFileByStudentId(studentId, httpServletResponse);
    }

    private String createFileName(final Long studentId) {
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy_HH.mm");
        final String dateTime = dateTimeFormatter.format(LocalDateTime.now());
        return studentId + "_sis_transcript_" + dateTime + ".pdf";
    }
}
