package com.graduationproject.studentinformationsystem.teacher.controller;

import com.graduationproject.studentinformationsystem.common.util.group.OnCreate;
import com.graduationproject.studentinformationsystem.common.util.group.OnUpdate;
import com.graduationproject.studentinformationsystem.common.util.response.SisBaseResponse;
import com.graduationproject.studentinformationsystem.teacher.controller.endpoint.TeacherControllerEndpoint;
import com.graduationproject.studentinformationsystem.teacher.model.dto.request.TeacherAcademicInfoRequest;
import com.graduationproject.studentinformationsystem.teacher.model.dto.request.TeacherInfoRequest;
import com.graduationproject.studentinformationsystem.teacher.model.dto.request.TeacherPersonalInfoRequest;
import com.graduationproject.studentinformationsystem.teacher.model.dto.response.TeacherAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.teacher.model.dto.response.TeacherInfoDetailResponse;
import com.graduationproject.studentinformationsystem.teacher.model.dto.response.TeacherPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.teacher.model.dto.response.TeacherResponse;
import com.graduationproject.studentinformationsystem.teacher.model.enums.TeacherStatus;
import com.graduationproject.studentinformationsystem.teacher.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.graduationproject.studentinformationsystem.common.config.SisSwaggerConfiguration.TEACHER_API_TAG;
import static com.graduationproject.studentinformationsystem.common.util.controller.endpoint.SisControllerEndpoint.Path.TEACHER;
import static com.graduationproject.studentinformationsystem.common.util.response.SisBaseResponseUtil.successResponse;

@RestController
@RequestMapping(TEACHER)
@Api(tags = TEACHER_API_TAG)
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    @ApiOperation(value = "Get All Teacher By Status")
    public ResponseEntity<SisBaseResponse<List<TeacherResponse>>> getAllTeachersByStatus(TeacherStatus status) {

        List<TeacherResponse> teacherResponses = teacherService.getAllTeachersByStatus(status);
        return successResponse(teacherResponses);
    }

    @GetMapping(TeacherControllerEndpoint.TEACHER_ID)
    @ApiOperation(value = "Get Teacher Detail By Teacher ID")
    public ResponseEntity<SisBaseResponse<TeacherInfoDetailResponse>> getTeacherDetailById(@PathVariable Long teacherId) {

        TeacherInfoDetailResponse teacherInfoDetailResponse = teacherService.getTeacherDetailById(teacherId);
        return successResponse(teacherInfoDetailResponse);
    }

    @PostMapping(TeacherControllerEndpoint.SAVE)
    @ApiOperation(value = "Save Teacher")
    public ResponseEntity<SisBaseResponse<TeacherInfoDetailResponse>> saveTeacher(
            @RequestBody @Validated(OnCreate.class) TeacherInfoRequest teacherInfoRequest) {

        TeacherInfoDetailResponse teacherInfoDetailResponse = teacherService.saveTeacher(teacherInfoRequest);
        return successResponse(teacherInfoDetailResponse);
    }

    @PutMapping(TeacherControllerEndpoint.UPDATE_ACADEMIC_INFO_BY_TEACHER_ID)
    @ApiOperation(value = "Update Teacher Academic Info")
    public ResponseEntity<SisBaseResponse<TeacherAcademicInfoResponse>> updateTeacherAcademicInfo(
            @PathVariable Long teacherId,
            @RequestBody @Validated(OnUpdate.class) TeacherAcademicInfoRequest academicInfoRequest) {

        return successResponse(teacherService.updateTeacherAcademicInfo(teacherId, academicInfoRequest));
    }

    @PutMapping(TeacherControllerEndpoint.UPDATE_PERSONAL_INFO_BY_TEACHER_ID)
    @ApiOperation(value = "Update Teacher Personal Info")
    public ResponseEntity<SisBaseResponse<TeacherPersonalInfoResponse>> updateTeacherPersonalInfo(
            @PathVariable Long teacherId,
            @RequestBody @Validated(OnUpdate.class) TeacherPersonalInfoRequest personalInfoRequest) {

        return successResponse(teacherService.updateTeacherPersonalInfo(teacherId, personalInfoRequest));
    }

    @DeleteMapping(TeacherControllerEndpoint.DELETE_BY_TEACHER_ID)
    @ApiOperation(value = "Delete Teacher")
    public ResponseEntity<SisBaseResponse<TeacherResponse>> deleteTeacher(@PathVariable Long teacherId) {
        return successResponse(teacherService.deleteTeacher(teacherId));
    }

    @PatchMapping(TeacherControllerEndpoint.PASSIVATE_BY_TEACHER_ID)
    @ApiOperation(value = "Passivate Teacher")
    public ResponseEntity<SisBaseResponse<TeacherResponse>> passivateTeacher(@PathVariable Long teacherId) {
        return successResponse(teacherService.passivateTeacher(teacherId));
    }

    @PatchMapping(TeacherControllerEndpoint.ACTIVATE_BY_TEACHER_ID)
    @ApiOperation(value = "Activate Teacher")
    public ResponseEntity<SisBaseResponse<TeacherResponse>> activateTeacher(@PathVariable Long teacherId) {
        return successResponse(teacherService.activateTeacher(teacherId));
    }
}
