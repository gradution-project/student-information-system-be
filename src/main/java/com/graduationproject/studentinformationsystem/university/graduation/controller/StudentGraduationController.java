package com.graduationproject.studentinformationsystem.university.graduation.controller;

import com.graduationproject.studentinformationsystem.common.util.controller.response.SisBaseApiResponse;
import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.graduation.controller.endpoint.StudentGraduationControllerEndpoint;
import com.graduationproject.studentinformationsystem.university.graduation.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.graduation.model.dto.response.StudentGraduationResponse;
import com.graduationproject.studentinformationsystem.university.graduation.model.enums.StudentGraduationStatus;
import com.graduationproject.studentinformationsystem.university.graduation.service.StudentGraduationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.graduationproject.studentinformationsystem.common.config.SisSwaggerConfiguration.STUDENT_GRADUATION_API_TAG;
import static com.graduationproject.studentinformationsystem.common.util.controller.endpoint.SisControllerEndpoint.Path.STUDENT_GRADUATION;
import static com.graduationproject.studentinformationsystem.common.util.controller.response.SisResponseUtil.successResponse;

@RestController
@RequestMapping(STUDENT_GRADUATION)
@Api(tags = STUDENT_GRADUATION_API_TAG)
@RequiredArgsConstructor
public class StudentGraduationController {

    private final StudentGraduationService studentGraduationService;

    @GetMapping(StudentGraduationControllerEndpoint.GET)
    @ApiOperation(value = "Get All Student Graduations By Status")
    public ResponseEntity<SisBaseApiResponse<List<StudentGraduationResponse>>> getAllStudentGraduationsByStatus(
            @RequestParam final StudentGraduationStatus status) {

        final List<StudentGraduationResponse> graduationResponses = studentGraduationService.getAllStudentsGraduationsByStatus(status);
        return successResponse(graduationResponses);
    }

    @GetMapping(StudentGraduationControllerEndpoint.GET_BY_GRADUATION_ID)
    @ApiOperation(value = "Get Student Graduation By Registration ID")
    public ResponseEntity<SisBaseApiResponse<StudentGraduationResponse>> getStudentGraduationDetailByRegistrationId(
            @PathVariable final String graduationId) throws SisNotExistException {

        final StudentGraduationResponse graduationResponse = studentGraduationService
                .getStudentGraduationDetailByGraduationId(graduationId);
        return successResponse(graduationResponse);
    }

    @PostMapping(StudentGraduationControllerEndpoint.SAVE)
    @ApiOperation(value = "Save Student Graduation")
    public ResponseEntity<SisBaseApiResponse<StudentGraduationResponse>> saveStudentGraduation(
            @RequestBody @Valid final StudentGraduationSaveRequest saveRequest)
            throws SisAlreadyException, SisNotExistException {

        final StudentGraduationResponse graduationResponse = studentGraduationService
                .saveStudentGraduation(saveRequest);
        return successResponse(graduationResponse);
    }

    @PatchMapping(StudentGraduationControllerEndpoint.APPROVE)
    @ApiOperation(value = "Approve Student Graduation")
    public ResponseEntity<SisBaseApiResponse<StudentGraduationResponse>> approveStudentGraduation(
            @RequestBody @Valid final StudentGraduationApproveRequest approveRequest)
            throws SisNotExistException, SisAlreadyException {

        final StudentGraduationResponse graduationResponse = studentGraduationService
                .approveStudentGraduation(approveRequest);
        return successResponse(graduationResponse);
    }

    @PatchMapping(StudentGraduationControllerEndpoint.REJECT)
    @ApiOperation(value = "Reject Student Graduation")
    public ResponseEntity<SisBaseApiResponse<StudentGraduationResponse>> rejectStudentGraduation(
            @RequestBody @Valid final StudentGraduationRejectRequest rejectRequest)
            throws SisNotExistException, SisAlreadyException {

        final StudentGraduationResponse graduationResponse = studentGraduationService.rejectStudentGraduation(rejectRequest);
        return successResponse(graduationResponse);
    }

    @PatchMapping(StudentGraduationControllerEndpoint.CONFIRM)
    @ApiOperation(value = "Confirm Student Graduation")
    public ResponseEntity<SisBaseApiResponse<StudentGraduationResponse>> confirmStudentGraduation(
            @RequestBody @Valid final StudentGraduationConfirmRequest confirmRequest)
            throws SisNotExistException, SisAlreadyException {

        final StudentGraduationResponse graduationResponse = studentGraduationService.confirmStudentGraduation(confirmRequest);
        return successResponse(graduationResponse);
    }

    @PatchMapping(StudentGraduationControllerEndpoint.UNCONFIRM)
    @ApiOperation(value = "Unconfirm Student Graduation")
    public ResponseEntity<SisBaseApiResponse<StudentGraduationResponse>> unconfirmStudentGraduation(
            @RequestBody @Valid final StudentGraduationUnconfirmRequest unconfirmRequest)
            throws SisNotExistException, SisAlreadyException {

        final StudentGraduationResponse graduationResponse = studentGraduationService.unconfirmStudentGraduation(unconfirmRequest);
        return successResponse(graduationResponse);
    }
}
