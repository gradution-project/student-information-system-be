package com.graduationproject.studentinformationsystem.login.officer.password.controller;

import com.graduationproject.studentinformationsystem.common.util.controller.response.SisApiResponse;
import com.graduationproject.studentinformationsystem.common.util.controller.response.SisBaseApiResponse;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.login.officer.password.controller.endpoint.OfficerPasswordOperationControllerEndpoint;
import com.graduationproject.studentinformationsystem.login.officer.password.model.dto.request.OfficerPasswordChangeRequest;
import com.graduationproject.studentinformationsystem.login.officer.password.model.dto.request.OfficerPasswordForgotRequest;
import com.graduationproject.studentinformationsystem.login.officer.password.model.dto.response.OfficerPasswordChangeResponse;
import com.graduationproject.studentinformationsystem.login.officer.password.model.dto.response.OfficerPasswordForgotResponse;
import com.graduationproject.studentinformationsystem.login.officer.password.service.OfficerPasswordOperationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.graduationproject.studentinformationsystem.common.config.SisSwaggerConfiguration.OFFICER_PASSWORD_OPERATION_API_TAG;
import static com.graduationproject.studentinformationsystem.common.util.controller.response.SisResponseUtil.successResponse;
import static com.graduationproject.studentinformationsystem.common.util.controller.response.SisResponseUtil.unauthorizedResponse;

@RestController
@RequestMapping
@Api(tags = OFFICER_PASSWORD_OPERATION_API_TAG)
@RequiredArgsConstructor
public class OfficerPasswordOperationController {

    private final OfficerPasswordOperationService passwordOperationService;

    @GetMapping(OfficerPasswordOperationControllerEndpoint.ENABLED)
    @ApiOperation(value = "Is Officer Password Change Operation Enabled By Operation ID")
    public ResponseEntity<SisApiResponse> isPasswordChangeOperationEnabled(
            @PathVariable final String operationId) throws SisNotExistException {

        final boolean isPasswordChangeEnabled = passwordOperationService.isPasswordChangeEnabled(operationId);

        if (isPasswordChangeEnabled) {
            return successResponse();
        } else {
            return unauthorizedResponse();
        }
    }

    @PostMapping(OfficerPasswordOperationControllerEndpoint.CHANGE)
    @ApiOperation(value = "Change Officer Password")
    public ResponseEntity<SisBaseApiResponse<OfficerPasswordChangeResponse>> changePassword(
            @RequestBody final OfficerPasswordChangeRequest passwordChangeRequest) throws SisNotExistException {

        final OfficerPasswordChangeResponse passwordChangeResponse = passwordOperationService.changePassword(passwordChangeRequest);
        return successResponse(passwordChangeResponse);
    }

    @PostMapping(OfficerPasswordOperationControllerEndpoint.FORGOT)
    @ApiOperation(value = "Officer Forgot Password")
    public ResponseEntity<SisBaseApiResponse<OfficerPasswordForgotResponse>> forgotPassword(
            @RequestBody @Valid final OfficerPasswordForgotRequest passwordForgotRequest)
            throws SisNotExistException {

        final OfficerPasswordForgotResponse passwordOperationResponse = passwordOperationService.forgotPassword(passwordForgotRequest);
        return successResponse(passwordOperationResponse);
    }
}
