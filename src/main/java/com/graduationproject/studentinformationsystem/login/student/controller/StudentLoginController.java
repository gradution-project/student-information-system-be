package com.graduationproject.studentinformationsystem.login.student.controller;

import com.graduationproject.studentinformationsystem.common.util.controller.response.SisBaseApiResponse;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.login.common.controller.enpoint.LoginControllerEndpoint;
import com.graduationproject.studentinformationsystem.login.common.model.response.ForgotPasswordResponse;
import com.graduationproject.studentinformationsystem.login.common.model.response.LoginResponse;
import com.graduationproject.studentinformationsystem.login.student.model.dto.request.StudentForgotPasswordRequest;
import com.graduationproject.studentinformationsystem.login.student.model.dto.request.StudentLoginRequest;
import com.graduationproject.studentinformationsystem.login.student.service.StudentLoginService;
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

@RestController
@RequestMapping(LOGIN)
@Api(tags = LOGIN_API_TAG)
@RequiredArgsConstructor
public class StudentLoginController {

    private final StudentLoginService loginService;

    @PostMapping(LoginControllerEndpoint.STUDENT)
    @ApiOperation(value = "Student Login")
    public ResponseEntity<SisBaseApiResponse<LoginResponse>> studentLogin(
            @RequestBody @Valid StudentLoginRequest loginRequest) {

        return successResponse(loginService.login(loginRequest));
    }

    @PostMapping(LoginControllerEndpoint.STUDENT_FORGOT_PASSWORD)
    @ApiOperation(value = "Student Forgot Password")
    public ResponseEntity<SisBaseApiResponse<ForgotPasswordResponse>> studentForgotPassword(
            @RequestBody @Valid StudentForgotPasswordRequest forgotPasswordRequest)
            throws SisNotExistException {

        return successResponse(loginService.forgotPassword(forgotPasswordRequest));
    }
}
