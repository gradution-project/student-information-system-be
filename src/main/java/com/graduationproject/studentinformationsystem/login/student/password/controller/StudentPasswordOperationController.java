package com.graduationproject.studentinformationsystem.login.student.password.controller;

import com.graduationproject.studentinformationsystem.common.util.controller.response.SisApiResponse;
import com.graduationproject.studentinformationsystem.common.util.controller.response.SisBaseApiResponse;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.login.student.password.controller.endpoint.StudentPasswordOperationControllerEndpoint;
import com.graduationproject.studentinformationsystem.login.student.password.model.dto.request.StudentPasswordChangeRequest;
import com.graduationproject.studentinformationsystem.login.student.password.model.dto.request.StudentPasswordForgotRequest;
import com.graduationproject.studentinformationsystem.login.student.password.model.dto.response.StudentPasswordChangeResponse;
import com.graduationproject.studentinformationsystem.login.student.password.model.dto.response.StudentPasswordForgotResponse;
import com.graduationproject.studentinformationsystem.login.student.password.service.StudentPasswordOperationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.graduationproject.studentinformationsystem.common.config.SisSwaggerConfiguration.STUDENT_PASSWORD_OPERATION_API_TAG;
import static com.graduationproject.studentinformationsystem.common.util.controller.response.SisResponseUtil.successResponse;
import static com.graduationproject.studentinformationsystem.common.util.controller.response.SisResponseUtil.unauthorizedResponse;

@RestController
@RequestMapping
@Api(tags = STUDENT_PASSWORD_OPERATION_API_TAG)
@RequiredArgsConstructor
public class StudentPasswordOperationController {

    private final StudentPasswordOperationService passwordOperationService;

    @GetMapping(StudentPasswordOperationControllerEndpoint.ENABLED)
    @ApiOperation(value = "Is Student Password Change Operation Enabled By Operation ID")
    public ResponseEntity<SisApiResponse> isPasswordChangeOperationEnabled(
            @PathVariable final String operationId) throws SisNotExistException {

        final boolean isPasswordChangeEnabled = passwordOperationService.isPasswordChangeEnabled(operationId);

        if (isPasswordChangeEnabled) {
            return successResponse();
        } else {
            return unauthorizedResponse();
        }
    }

    @PostMapping(StudentPasswordOperationControllerEndpoint.CHANGE)
    @ApiOperation(value = "Change Student Password")
    public ResponseEntity<SisBaseApiResponse<StudentPasswordChangeResponse>> changePassword(
            @RequestBody final StudentPasswordChangeRequest passwordChangeRequest) throws SisNotExistException {

        final StudentPasswordChangeResponse passwordChangeResponse = passwordOperationService.changePassword(passwordChangeRequest);
        return successResponse(passwordChangeResponse);
    }

    @PostMapping(StudentPasswordOperationControllerEndpoint.FORGOT)
    @ApiOperation(value = "Student Forgot Password")
    public ResponseEntity<SisBaseApiResponse<StudentPasswordForgotResponse>> forgotPassword(
            @RequestBody @Valid final StudentPasswordForgotRequest passwordForgotRequest)
            throws SisNotExistException {

        final StudentPasswordForgotResponse passwordOperationResponse = passwordOperationService.forgotPassword(passwordForgotRequest);
        return successResponse(passwordOperationResponse);
    }
}
