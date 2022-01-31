package com.graduationproject.studentinformationsystem.university.faculty.controller;

import com.graduationproject.studentinformationsystem.common.util.controller.response.SisBaseApiResponse;
import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.faculty.controller.endpoint.FacultyControllerEndpoint;
import com.graduationproject.studentinformationsystem.university.faculty.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.faculty.model.dto.response.FacultyResponse;
import com.graduationproject.studentinformationsystem.university.faculty.model.enums.FacultyStatus;
import com.graduationproject.studentinformationsystem.university.faculty.service.FacultyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.graduationproject.studentinformationsystem.common.config.SisSwaggerConfiguration.FACULTY_API_TAG;
import static com.graduationproject.studentinformationsystem.common.util.controller.endpoint.SisControllerEndpoint.Path.FACULTY;
import static com.graduationproject.studentinformationsystem.common.util.controller.response.SisResponseUtil.successResponse;

@RestController
@RequestMapping(FACULTY)
@Api(tags = FACULTY_API_TAG)
@RequiredArgsConstructor
public class FacultyController {

    private final FacultyService facultyService;

    @GetMapping
    @ApiOperation(value = "Get All Faculty By Status")
    public ResponseEntity<SisBaseApiResponse<List<FacultyResponse>>> getAllFacultiesByStatus(
            final FacultyStatus status) {

        final List<FacultyResponse> facultyResponses = facultyService.getAllFacultiesByStatus(status);
        return successResponse(facultyResponses);
    }

    @GetMapping(FacultyControllerEndpoint.FACULTY_ID)
    @ApiOperation(value = "Get Faculty Detail By Faculty ID")
    public ResponseEntity<SisBaseApiResponse<FacultyResponse>> getFacultyById(
            @PathVariable final Long facultyId)
            throws SisNotExistException {

        final FacultyResponse facultyResponse = facultyService.getFacultyById(facultyId);
        return successResponse(facultyResponse);
    }

    @PostMapping(FacultyControllerEndpoint.SAVE)
    @ApiOperation(value = "Save Faculty")
    public ResponseEntity<SisBaseApiResponse<FacultyResponse>> saveFaculty(
            @Valid @RequestBody final FacultySaveRequest saveRequest) {

        final FacultyResponse facultyResponse = facultyService.saveFaculty(saveRequest);
        return successResponse(facultyResponse);
    }

    @PutMapping(FacultyControllerEndpoint.UPDATE_BY_FACULTY_ID)
    @ApiOperation(value = "Update Faculty Academic Info")
    public ResponseEntity<SisBaseApiResponse<FacultyResponse>> updateFaculty(
            @PathVariable final Long facultyId,
            @Valid @RequestBody final FacultyUpdateRequest updateRequest)
            throws SisNotExistException {

        final FacultyResponse facultyResponse = facultyService.updateFaculty(facultyId, updateRequest);
        return successResponse(facultyResponse);
    }

    @DeleteMapping(FacultyControllerEndpoint.DELETE)
    @ApiOperation(value = "Delete Faculty")
    public ResponseEntity<SisBaseApiResponse<FacultyResponse>> deleteFaculty(
            @Valid @RequestBody final FacultyDeleteRequest deleteRequest)
            throws SisNotExistException, SisAlreadyException {

        final FacultyResponse facultyResponse = facultyService.deleteFaculty(deleteRequest);
        return successResponse(facultyResponse);
    }

    @PatchMapping(FacultyControllerEndpoint.PASSIVATE)
    @ApiOperation(value = "Passivate Faculty")
    public ResponseEntity<SisBaseApiResponse<FacultyResponse>> passivateFaculty(
            @Valid @RequestBody final FacultyPassivateRequest passivateRequest)
            throws SisNotExistException, SisAlreadyException {

        final FacultyResponse facultyResponse = facultyService.passivateFaculty(passivateRequest);
        return successResponse(facultyResponse);
    }

    @PatchMapping(FacultyControllerEndpoint.ACTIVATE)
    @ApiOperation(value = "Activate Faculty")
    public ResponseEntity<SisBaseApiResponse<FacultyResponse>> activateFaculty(
            @Valid @RequestBody final FacultyActivateRequest activateRequest)
            throws SisNotExistException, SisAlreadyException {

        final FacultyResponse facultyResponse = facultyService.activateFaculty(activateRequest);
        return successResponse(facultyResponse);
    }
}
