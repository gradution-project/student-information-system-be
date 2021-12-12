package com.graduationproject.studentinformationsystem.login.teacher.controller;

import com.graduationproject.studentinformationsystem.common.util.controller.response.SisBaseApiResponse;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.login.common.controller.enpoint.LoginControllerEndpoint;
import com.graduationproject.studentinformationsystem.login.common.model.response.ForgotPasswordResponse;
import com.graduationproject.studentinformationsystem.login.common.model.response.LoginResponse;
import com.graduationproject.studentinformationsystem.login.teacher.model.dto.request.TeacherForgotPasswordRequest;
import com.graduationproject.studentinformationsystem.login.teacher.model.dto.request.TeacherLoginRequest;
import com.graduationproject.studentinformationsystem.login.teacher.service.TeacherLoginService;
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
public class TeacherLoginController {

    private final TeacherLoginService loginService;

    @PostMapping(LoginControllerEndpoint.TEACHER)
    @ApiOperation(value = "Teacher Login")
    public ResponseEntity<SisBaseApiResponse<LoginResponse>> teacherLogin(
            @RequestBody @Valid TeacherLoginRequest loginRequest) {

        return successResponse(loginService.login(loginRequest));
    }

    @PostMapping(LoginControllerEndpoint.TEACHER_FORGOT_PASSWORD)
    @ApiOperation(value = "Teacher Forgot Password")
    public ResponseEntity<SisBaseApiResponse<ForgotPasswordResponse>> teacherForgotPassword(
            @RequestBody @Valid TeacherForgotPasswordRequest forgotPasswordRequest)
            throws SisNotExistException {

        return successResponse(loginService.forgotPassword(forgotPasswordRequest));
    }
}
