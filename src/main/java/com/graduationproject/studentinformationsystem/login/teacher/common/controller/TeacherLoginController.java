package com.graduationproject.studentinformationsystem.login.teacher.common.controller;

import com.graduationproject.studentinformationsystem.common.util.controller.response.SisBaseApiResponse;
import com.graduationproject.studentinformationsystem.login.common.model.response.LoginResponse;
import com.graduationproject.studentinformationsystem.login.teacher.common.model.dto.request.TeacherLoginRequest;
import com.graduationproject.studentinformationsystem.login.teacher.common.service.TeacherLoginService;
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
import static com.graduationproject.studentinformationsystem.common.util.controller.endpoint.SisControllerEndpoint.Path.TEACHER_LOGIN;
import static com.graduationproject.studentinformationsystem.common.util.controller.response.SisResponseUtil.successResponse;

@RestController
@RequestMapping(TEACHER_LOGIN)
@Api(tags = LOGIN_API_TAG)
@RequiredArgsConstructor
public class TeacherLoginController {

    private final TeacherLoginService loginService;

    @PostMapping
    @ApiOperation(value = "Teacher Login")
    public ResponseEntity<SisBaseApiResponse<LoginResponse>> teacherLogin(
            @RequestBody @Valid final TeacherLoginRequest loginRequest) {

        final LoginResponse loginResponse = loginService.login(loginRequest);
        return successResponse(loginResponse);
    }
}
