package com.graduationproject.studentinformationsystem.university.teacher.controller;

import com.graduationproject.studentinformationsystem.common.util.controller.response.SisBaseApiResponse;
import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.teacher.controller.endpoint.TeacherControllerEndpoint;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherInfoDetailResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherInfoResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.enums.TeacherStatus;
import com.graduationproject.studentinformationsystem.university.teacher.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.graduationproject.studentinformationsystem.common.config.SisSwaggerConfiguration.TEACHER_API_TAG;
import static com.graduationproject.studentinformationsystem.common.util.controller.response.SisResponseUtil.successResponse;

@RestController
@RequestMapping
@Api(tags = TEACHER_API_TAG)
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping(TeacherControllerEndpoint.ALL)
    @ApiOperation(value = "Get All Teachers By Status")
    public ResponseEntity<SisBaseApiResponse<List<TeacherInfoResponse>>> getAllTeachersByStatus(
            @RequestParam final TeacherStatus status) {

        final List<TeacherInfoResponse> infoResponses = teacherService.getAllTeachersByStatus(status);
        return successResponse(infoResponses);
    }

    @GetMapping(TeacherControllerEndpoint.BY_TEACHER_ID)
    @ApiOperation(value = "Get Teacher Detail By Teacher ID")
    public ResponseEntity<SisBaseApiResponse<TeacherInfoDetailResponse>> getTeacherDetailById(
            @PathVariable final Long teacherId)
            throws SisNotExistException {

        final TeacherInfoDetailResponse infoDetailResponse = teacherService.getTeacherDetailById(teacherId);
        return successResponse(infoDetailResponse);
    }

    @PostMapping(TeacherControllerEndpoint.BASE)
    @ApiOperation(value = "Save Teacher")
    public ResponseEntity<SisBaseApiResponse<TeacherInfoDetailResponse>> saveTeacher(
            @Valid @RequestBody final TeacherSaveRequest saveRequest) throws SisNotExistException {

        final TeacherInfoDetailResponse infoDetailResponse = teacherService.saveTeacher(saveRequest);
        return successResponse(infoDetailResponse);
    }

    @PutMapping(TeacherControllerEndpoint.ACADEMIC_INFO_BY_TEACHER_ID)
    @ApiOperation(value = "Update Teacher Academic Info")
    public ResponseEntity<SisBaseApiResponse<TeacherAcademicInfoResponse>> updateTeacherAcademicInfo(
            @PathVariable final Long teacherId,
            @Valid @RequestBody final TeacherAcademicInfoUpdateRequest academicInfoUpdateRequest)
            throws SisNotExistException {

        final TeacherAcademicInfoResponse academicInfoResponse = teacherService.updateTeacherAcademicInfo(teacherId, academicInfoUpdateRequest);
        return successResponse(academicInfoResponse);
    }

    @PutMapping(TeacherControllerEndpoint.PERSONAL_INFO_BY_TEACHER_ID)
    @ApiOperation(value = "Update Teacher Personal Info")
    public ResponseEntity<SisBaseApiResponse<TeacherPersonalInfoResponse>> updateTeacherPersonalInfo(
            @PathVariable final Long teacherId,
            @Valid @RequestBody final TeacherPersonalInfoUpdateRequest personalInfoUpdateRequest)
            throws SisNotExistException {

        final TeacherPersonalInfoResponse personalInfoResponse = teacherService.updateTeacherPersonalInfo(teacherId, personalInfoUpdateRequest);
        return successResponse(personalInfoResponse);
    }

    @DeleteMapping(TeacherControllerEndpoint.BASE)
    @ApiOperation(value = "Delete Teacher")
    public ResponseEntity<SisBaseApiResponse<TeacherInfoResponse>> deleteTeacher(
            @Valid @RequestBody final TeacherDeleteRequest deleteRequest)
            throws SisNotExistException, SisAlreadyException {

        final TeacherInfoResponse infoResponse = teacherService.deleteTeacher(deleteRequest);
        return successResponse(infoResponse);
    }

    @PatchMapping(TeacherControllerEndpoint.PASSIVATE)
    @ApiOperation(value = "Passivate Teacher")
    public ResponseEntity<SisBaseApiResponse<TeacherInfoResponse>> passivateTeacher(
            @Valid @RequestBody final TeacherPassivateRequest passivateRequest)
            throws SisNotExistException, SisAlreadyException {

        final TeacherInfoResponse infoResponse = teacherService.passivateTeacher(passivateRequest);
        return successResponse(infoResponse);
    }

    @PatchMapping(TeacherControllerEndpoint.ACTIVATE)
    @ApiOperation(value = "Activate Teacher")
    public ResponseEntity<SisBaseApiResponse<TeacherInfoResponse>> activateTeacher(
            @Valid @RequestBody final TeacherActivateRequest activateRequest)
            throws SisNotExistException, SisAlreadyException {

        final TeacherInfoResponse infoResponse = teacherService.activateTeacher(activateRequest);
        return successResponse(infoResponse);
    }
}
