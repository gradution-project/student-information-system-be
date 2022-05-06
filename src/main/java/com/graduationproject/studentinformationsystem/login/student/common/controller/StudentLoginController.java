package com.graduationproject.studentinformationsystem.login.student.common.controller;

import com.graduationproject.studentinformationsystem.common.util.controller.response.SisBaseApiResponse;
import com.graduationproject.studentinformationsystem.login.common.model.response.LoginResponse;
import com.graduationproject.studentinformationsystem.login.student.common.model.dto.request.StudentLoginRequest;
import com.graduationproject.studentinformationsystem.login.student.common.service.StudentLoginService;
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
import static com.graduationproject.studentinformationsystem.common.util.controller.endpoint.SisControllerEndpoint.Path.STUDENT_LOGIN;
import static com.graduationproject.studentinformationsystem.common.util.controller.response.SisResponseUtil.successResponse;

@RestController
@RequestMapping(STUDENT_LOGIN)
@Api(tags = LOGIN_API_TAG)
@RequiredArgsConstructor
public class StudentLoginController {

    private final StudentLoginService loginService;

    @PostMapping
    @ApiOperation(value = "Student Login")
    public ResponseEntity<SisBaseApiResponse<LoginResponse>> studentLogin(
            @RequestBody @Valid final StudentLoginRequest loginRequest) {

        final LoginResponse loginResponse = loginService.login(loginRequest);
        return successResponse(loginResponse);
    }
}
