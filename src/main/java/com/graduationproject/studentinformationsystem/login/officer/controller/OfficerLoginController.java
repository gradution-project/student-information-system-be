package com.graduationproject.studentinformationsystem.login.officer.controller;

import com.graduationproject.studentinformationsystem.common.util.controller.response.SisBaseApiResponse;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.login.common.controller.enpoint.LoginControllerEndpoint;
import com.graduationproject.studentinformationsystem.login.common.model.response.ForgotPasswordResponse;
import com.graduationproject.studentinformationsystem.login.common.model.response.LoginResponse;
import com.graduationproject.studentinformationsystem.login.officer.model.dto.request.OfficerForgotPasswordRequest;
import com.graduationproject.studentinformationsystem.login.officer.model.dto.request.OfficerLoginRequest;
import com.graduationproject.studentinformationsystem.login.officer.service.OfficerLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.graduationproject.studentinformationsystem.common.config.SisSwaggerConfiguration.LOGIN_API_TAG;
import static com.graduationproject.studentinformationsystem.common.util.controller.endpoint.SisControllerEndpoint.Path.LOGIN;
import static com.graduationproject.studentinformationsystem.common.util.controller.response.SisResponseUtil.successResponse;

//TODO: Officer Login Controller Return Values Must Be Updated After Login Service Created
@RestController
@RequestMapping(LOGIN)
@Api(tags = LOGIN_API_TAG)
@RequiredArgsConstructor
public class OfficerLoginController {

    private final OfficerLoginService loginService;

    @PostMapping(LoginControllerEndpoint.OFFICER)
    @ApiOperation(value = "Officer Login")
    public ResponseEntity<SisBaseApiResponse<LoginResponse>> officerLogin(
            @RequestBody @Valid final OfficerLoginRequest loginRequest) {

//        return successResponse(loginService.login(loginRequest));
        return successResponse(LoginResponse.builder().isLoginSuccess(true).build());
    }

    @PostMapping(LoginControllerEndpoint.OFFICER_FORGOT_PASSWORD)
    @ApiOperation(value = "Officer Forgot Password")
    public ResponseEntity<SisBaseApiResponse<ForgotPasswordResponse>> officerForgotPassword(
            @RequestBody @Valid final OfficerForgotPasswordRequest forgotPasswordRequest)
            throws SisNotExistException {

//        return successResponse(loginService.forgotPassword(forgotPasswordRequest));
        return successResponse(ForgotPasswordResponse.builder().isForgotPasswordSuccess(true).build());
    }
}
