package com.graduationproject.studentinformationsystem.login.officer.common.controller;

import com.graduationproject.studentinformationsystem.common.util.controller.response.SisBaseApiResponse;
import com.graduationproject.studentinformationsystem.login.common.model.response.LoginResponse;
import com.graduationproject.studentinformationsystem.login.officer.common.model.dto.request.OfficerLoginRequest;
import com.graduationproject.studentinformationsystem.login.officer.common.service.OfficerLoginService;
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
import static com.graduationproject.studentinformationsystem.common.util.controller.endpoint.SisControllerEndpoint.Path.OFFICER_LOGIN;
import static com.graduationproject.studentinformationsystem.common.util.controller.response.SisResponseUtil.successResponse;

@RestController
@RequestMapping(OFFICER_LOGIN)
@Api(tags = LOGIN_API_TAG)
@RequiredArgsConstructor
public class OfficerLoginController {

    private final OfficerLoginService loginService;

    @PostMapping
    @ApiOperation(value = "Officer Login")
    public ResponseEntity<SisBaseApiResponse<LoginResponse>> officerLogin(
            @RequestBody @Valid final OfficerLoginRequest loginRequest) {

        final LoginResponse loginResponse = loginService.login(loginRequest);
        return successResponse(loginResponse);
    }
}
