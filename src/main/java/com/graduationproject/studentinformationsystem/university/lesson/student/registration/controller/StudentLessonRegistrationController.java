package com.graduationproject.studentinformationsystem.university.lesson.student.registration.controller;

import com.graduationproject.studentinformationsystem.common.util.controller.response.SisBaseApiResponse;
import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.common.util.validation.id.StudentID;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.controller.endpoint.StudentLessonRegistrationControllerEndpoint;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.dto.request.StudentLessonRegistrationApproveRequest;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.dto.request.StudentLessonRegistrationRejectRequest;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.dto.request.StudentLessonRegistrationSaveRequest;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.dto.response.StudentLessonRegistrationDetailResponse;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.dto.response.StudentLessonRegistrationResponse;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.enums.StudentLessonRegistrationStatus;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.service.StudentLessonRegistrationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.graduationproject.studentinformationsystem.common.config.SisSwaggerConfiguration.STUDENT_LESSON_REGISTRATION_API_TAG;
import static com.graduationproject.studentinformationsystem.common.util.controller.endpoint.SisControllerEndpoint.Path.STUDENT_LESSON_REGISTRATION;
import static com.graduationproject.studentinformationsystem.common.util.controller.response.SisResponseUtil.failResponse;
import static com.graduationproject.studentinformationsystem.common.util.controller.response.SisResponseUtil.successResponse;

@RestController
@RequestMapping(STUDENT_LESSON_REGISTRATION)
@Api(tags = STUDENT_LESSON_REGISTRATION_API_TAG)
@RequiredArgsConstructor
public class StudentLessonRegistrationController {

    private final StudentLessonRegistrationService studentLessonRegistrationService;

    @GetMapping
    @ApiOperation(value = "Get All Student Lesson Registrations By Status")
    public ResponseEntity<SisBaseApiResponse<List<StudentLessonRegistrationResponse>>> getAllStudentLessonRegistrationsByStatus(
            @RequestParam final StudentLessonRegistrationStatus status) {

        final List<StudentLessonRegistrationResponse> registrationResponses = studentLessonRegistrationService
                .getAllStudentLessonRegistrationsByStatus(status);
        return successResponse(registrationResponses);
    }

    @GetMapping(StudentLessonRegistrationControllerEndpoint.BY_REGISTRATION_ID)
    @ApiOperation(value = "Get Student Lesson Registration By Registration ID")
    public ResponseEntity<SisBaseApiResponse<StudentLessonRegistrationDetailResponse>> getStudentLessonRegistrationDetailByRegistrationId(
            @PathVariable final String registrationId) throws SisNotExistException {

        final StudentLessonRegistrationDetailResponse registrationDetailResponse = studentLessonRegistrationService
                .getStudentLessonRegistrationDetailByRegistrationId(registrationId);
        return successResponse(registrationDetailResponse);
    }

    @GetMapping(StudentLessonRegistrationControllerEndpoint.BY_STUDENT_ID)
    @ApiOperation(value = "Get Student Lesson Registrations Detail By Student ID")
    public ResponseEntity<SisBaseApiResponse<Object>> getStudentLessonRegistrationDetailByStudentId(
            @PathVariable @StudentID final Long studentId)
            throws SisNotExistException {

        final StudentLessonRegistrationDetailResponse registrationDetailResponse = studentLessonRegistrationService
                .getStudentLessonRegistrationDetailByStudentId(studentId);
        return successResponse(registrationDetailResponse);
    }

    @PostMapping(StudentLessonRegistrationControllerEndpoint.SAVE)
    @ApiOperation(value = "Save Student Lesson Registration")
    public ResponseEntity<SisBaseApiResponse<StudentLessonRegistrationDetailResponse>> saveStudentLessonRegistration(
            @RequestBody @Valid final StudentLessonRegistrationSaveRequest saveRequest)
            throws SisAlreadyException, SisNotExistException {

        final StudentLessonRegistrationDetailResponse registrationDetailResponse = studentLessonRegistrationService
                .saveStudentLessonRegistration(saveRequest);
        return successResponse(registrationDetailResponse);
    }

    @PatchMapping(StudentLessonRegistrationControllerEndpoint.APPROVE)
    @ApiOperation(value = "Approve Student Lesson Registration")
    public ResponseEntity<SisBaseApiResponse<StudentLessonRegistrationDetailResponse>> approveStudentLessonRegistration(
            @RequestBody @Valid final StudentLessonRegistrationApproveRequest approveRequest)
            throws SisNotExistException, SisAlreadyException {

        final StudentLessonRegistrationDetailResponse registrationDetailResponse = studentLessonRegistrationService
                .approveStudentLessonRegistration(approveRequest);
        return successResponse(registrationDetailResponse);
    }

    @PatchMapping(StudentLessonRegistrationControllerEndpoint.REJECT)
    @ApiOperation(value = "Reject Student Lesson Registration")
    public ResponseEntity<SisBaseApiResponse<StudentLessonRegistrationDetailResponse>> activateStudent(
            @RequestBody @Valid final StudentLessonRegistrationRejectRequest rejectRequest)
            throws SisNotExistException, SisAlreadyException {

        final StudentLessonRegistrationDetailResponse registrationDetailResponse = studentLessonRegistrationService
                .rejectStudentLessonRegistration(rejectRequest);
        return successResponse(registrationDetailResponse);
    }
}
