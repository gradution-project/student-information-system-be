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
import static com.graduationproject.studentinformationsystem.common.util.controller.endpoint.SisControllerEndpoint.Path.STUDENT_PASSWORD_OPERATION;
import static com.graduationproject.studentinformationsystem.common.util.controller.response.SisResponseUtil.successResponse;
import static com.graduationproject.studentinformationsystem.common.util.controller.response.SisResponseUtil.unauthorizedResponse;

@RestController
@RequestMapping(STUDENT_PASSWORD_OPERATION)
@Api(tags = STUDENT_PASSWORD_OPERATION_API_TAG)
@RequiredArgsConstructor
public class StudentPasswordOperationController {

    private final StudentPasswordOperationService passwordOperationService;

    @GetMapping
    @ApiOperation(value = "Is Student Password Change Operation Enabled")
    public ResponseEntity<SisApiResponse> isPasswordChangeOperationEnabled(
            @RequestParam final String operationId) throws SisNotExistException {

        final boolean isPasswordChangeEnabled = passwordOperationService.isPasswordChangeEnabled(operationId);

        if (isPasswordChangeEnabled) {
            return successResponse();
        } else {
            return unauthorizedResponse();
        }
    }

    @PostMapping(StudentPasswordOperationControllerEndpoint.CHANGE_PASSWORD)
    @ApiOperation(value = "Change Student Password")
    public ResponseEntity<SisBaseApiResponse<StudentPasswordChangeResponse>> changePassword(
            @RequestBody final StudentPasswordChangeRequest passwordChangeRequest) throws SisNotExistException {

        final StudentPasswordChangeResponse passwordChangeResponse = passwordOperationService.changePassword(passwordChangeRequest);
        return successResponse(passwordChangeResponse);
    }

    @PostMapping(StudentPasswordOperationControllerEndpoint.FORGOT_PASSWORD)
    @ApiOperation(value = "Student Forgot Password")
    public ResponseEntity<SisBaseApiResponse<StudentPasswordForgotResponse>> forgotPassword(
            @RequestBody @Valid final StudentPasswordForgotRequest passwordForgotRequest)
            throws SisNotExistException {

        final StudentPasswordForgotResponse passwordOperationResponse = passwordOperationService.forgotPassword(passwordForgotRequest);
        return successResponse(passwordOperationResponse);
    }
}
