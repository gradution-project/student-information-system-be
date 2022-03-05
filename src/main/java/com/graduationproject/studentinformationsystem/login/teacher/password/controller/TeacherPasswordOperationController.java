package com.graduationproject.studentinformationsystem.login.teacher.password.controller;

import com.graduationproject.studentinformationsystem.common.util.controller.response.SisApiResponse;
import com.graduationproject.studentinformationsystem.common.util.controller.response.SisBaseApiResponse;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.login.teacher.password.controller.endpoint.TeacherPasswordOperationControllerEndpoint;
import com.graduationproject.studentinformationsystem.login.teacher.password.model.dto.request.TeacherPasswordChangeRequest;
import com.graduationproject.studentinformationsystem.login.teacher.password.model.dto.request.TeacherPasswordForgotRequest;
import com.graduationproject.studentinformationsystem.login.teacher.password.model.dto.response.TeacherPasswordChangeResponse;
import com.graduationproject.studentinformationsystem.login.teacher.password.model.dto.response.TeacherPasswordForgotResponse;
import com.graduationproject.studentinformationsystem.login.teacher.password.service.TeacherPasswordOperationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.graduationproject.studentinformationsystem.common.config.SisSwaggerConfiguration.TEACHER_PASSWORD_OPERATION_API_TAG;
import static com.graduationproject.studentinformationsystem.common.util.controller.endpoint.SisControllerEndpoint.Path.TEACHER_PASSWORD_OPERATION;
import static com.graduationproject.studentinformationsystem.common.util.controller.response.SisResponseUtil.successResponse;
import static com.graduationproject.studentinformationsystem.common.util.controller.response.SisResponseUtil.unauthorizedResponse;

@RestController
@RequestMapping(TEACHER_PASSWORD_OPERATION)
@Api(tags = TEACHER_PASSWORD_OPERATION_API_TAG)
@RequiredArgsConstructor
public class TeacherPasswordOperationController {

    private final TeacherPasswordOperationService passwordOperationService;

    @GetMapping
    @ApiOperation(value = "Is Teacher Password Change Operation Enabled")
    public ResponseEntity<SisApiResponse> isPasswordChangeOperationEnabled(
            @RequestParam final String operationId) throws SisNotExistException {

        final boolean isPasswordChangeEnabled = passwordOperationService.isPasswordChangeEnabled(operationId);

        if (isPasswordChangeEnabled) {
            return successResponse();
        } else {
            return unauthorizedResponse();
        }
    }

    @PostMapping(TeacherPasswordOperationControllerEndpoint.CHANGE_PASSWORD)
    @ApiOperation(value = "Change Teacher Password")
    public ResponseEntity<SisBaseApiResponse<TeacherPasswordChangeResponse>> changePassword(
            @RequestBody final TeacherPasswordChangeRequest passwordChangeRequest) throws SisNotExistException {

        final TeacherPasswordChangeResponse passwordChangeResponse = passwordOperationService.changePassword(passwordChangeRequest);
        return successResponse(passwordChangeResponse);
    }

    @PostMapping(TeacherPasswordOperationControllerEndpoint.FORGOT_PASSWORD)
    @ApiOperation(value = "Teacher Forgot Password")
    public ResponseEntity<SisBaseApiResponse<TeacherPasswordForgotResponse>> forgotPassword(
            @RequestBody @Valid final TeacherPasswordForgotRequest passwordForgotRequest)
            throws SisNotExistException {

        final TeacherPasswordForgotResponse passwordOperationResponse = passwordOperationService.forgotPassword(passwordForgotRequest);
        return successResponse(passwordOperationResponse);
    }
}
