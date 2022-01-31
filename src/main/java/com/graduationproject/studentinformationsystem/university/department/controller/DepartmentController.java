package com.graduationproject.studentinformationsystem.university.department.controller;

import com.graduationproject.studentinformationsystem.common.util.controller.response.SisBaseApiResponse;
import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.department.controller.endpoint.DepartmentControllerEndpoint;
import com.graduationproject.studentinformationsystem.university.department.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.department.model.dto.response.DepartmentResponse;
import com.graduationproject.studentinformationsystem.university.department.model.enums.DepartmentStatus;
import com.graduationproject.studentinformationsystem.university.department.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.graduationproject.studentinformationsystem.common.config.SisSwaggerConfiguration.DEPARTMENT_API_TAG;
import static com.graduationproject.studentinformationsystem.common.util.controller.endpoint.SisControllerEndpoint.Path.DEPARTMENT;
import static com.graduationproject.studentinformationsystem.common.util.controller.response.SisResponseUtil.successResponse;

@RestController
@RequestMapping(DEPARTMENT)
@Api(tags = DEPARTMENT_API_TAG)
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    @ApiOperation(value = "Get All Department By Status")
    public ResponseEntity<SisBaseApiResponse<List<DepartmentResponse>>> getAllDepartmentsByStatus(
            final DepartmentStatus status) {

        final List<DepartmentResponse> departmentResponses = departmentService.getAllDepartmentsByStatus(status);
        return successResponse(departmentResponses);
    }

    @GetMapping(DepartmentControllerEndpoint.DEPARTMENT_ID)
    @ApiOperation(value = "Get Department Detail By Department ID")
    public ResponseEntity<SisBaseApiResponse<DepartmentResponse>> getDepartmentById(
            @PathVariable final Long departmentId)
            throws SisNotExistException {

        final DepartmentResponse departmentResponse = departmentService.getDepartmentById(departmentId);
        return successResponse(departmentResponse);
    }

    @PostMapping(DepartmentControllerEndpoint.SAVE)
    @ApiOperation(value = "Save Department")
    public ResponseEntity<SisBaseApiResponse<DepartmentResponse>> saveDepartment(
            @Valid @RequestBody final DepartmentSaveRequest saveRequest)
            throws SisNotExistException {

        final DepartmentResponse departmentResponse = departmentService.saveDepartment(saveRequest);
        return successResponse(departmentResponse);
    }

    @PutMapping(DepartmentControllerEndpoint.UPDATE_BY_DEPARTMENT_ID)
    @ApiOperation(value = "Update Department Academic Info")
    public ResponseEntity<SisBaseApiResponse<DepartmentResponse>> updateDepartment(
            @PathVariable final Long departmentId,
            @Valid @RequestBody final DepartmentUpdateRequest updateRequest)
            throws SisNotExistException {

        final DepartmentResponse departmentResponse = departmentService.updateDepartment(departmentId, updateRequest);
        return successResponse(departmentResponse);
    }

    @DeleteMapping(DepartmentControllerEndpoint.DELETE)
    @ApiOperation(value = "Delete Department")
    public ResponseEntity<SisBaseApiResponse<DepartmentResponse>> deleteDepartment(
            @Valid @RequestBody final DepartmentDeleteRequest deleteRequest)
            throws SisNotExistException, SisAlreadyException {

        final DepartmentResponse departmentResponse = departmentService.deleteDepartment(deleteRequest);
        return successResponse(departmentResponse);
    }

    @PatchMapping(DepartmentControllerEndpoint.PASSIVATE)
    @ApiOperation(value = "Passivate Department")
    public ResponseEntity<SisBaseApiResponse<DepartmentResponse>> passivateDepartment(
            @Valid @RequestBody final DepartmentPassivateRequest passivateRequest)
            throws SisNotExistException, SisAlreadyException {

        final DepartmentResponse departmentResponse = departmentService.passivateDepartment(passivateRequest);
        return successResponse(departmentResponse);
    }

    @PatchMapping(DepartmentControllerEndpoint.ACTIVATE)
    @ApiOperation(value = "Activate Department")
    public ResponseEntity<SisBaseApiResponse<DepartmentResponse>> activateDepartment(
            @Valid @RequestBody final DepartmentActivateRequest activateRequest)
            throws SisNotExistException, SisAlreadyException {

        final DepartmentResponse departmentResponse = departmentService.activateDepartment(activateRequest);
        return successResponse(departmentResponse);
    }
}
