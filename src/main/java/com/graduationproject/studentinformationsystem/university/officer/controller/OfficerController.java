package com.graduationproject.studentinformationsystem.university.officer.controller;

import com.graduationproject.studentinformationsystem.common.util.controller.response.SisBaseApiResponse;
import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.officer.controller.endpoint.OfficerControllerEndpoint;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.response.OfficerAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.response.OfficerInfoDetailResponse;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.response.OfficerInfoResponse;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.response.OfficerPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.university.officer.model.enums.OfficerStatus;
import com.graduationproject.studentinformationsystem.university.officer.service.OfficerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.graduationproject.studentinformationsystem.common.config.SisSwaggerConfiguration.OFFICER_API_TAG;
import static com.graduationproject.studentinformationsystem.common.util.controller.response.SisResponseUtil.successResponse;

@RestController
@RequestMapping
@Api(tags = OFFICER_API_TAG)
@RequiredArgsConstructor
public class OfficerController {

    private final OfficerService officerService;

    @GetMapping(OfficerControllerEndpoint.ALL)
    @ApiOperation(value = "Get All Officers By Status")
    public ResponseEntity<SisBaseApiResponse<List<OfficerInfoResponse>>> getAllOfficersByStatus(
            @RequestParam final OfficerStatus status) {

        final List<OfficerInfoResponse> infoResponses = officerService.getAllOfficersByStatus(status);
        return successResponse(infoResponses);
    }

    @GetMapping(OfficerControllerEndpoint.BY_OFFICER_ID)
    @ApiOperation(value = "Get Officer Detail By Officer ID")
    public ResponseEntity<SisBaseApiResponse<OfficerInfoDetailResponse>> getOfficerDetailById(
            @PathVariable final Long officerId)
            throws SisNotExistException {

        final OfficerInfoDetailResponse infoDetailResponse = officerService.getOfficerDetailById(officerId);
        return successResponse(infoDetailResponse);
    }

    @PostMapping(OfficerControllerEndpoint.BASE)
    @ApiOperation(value = "Save Officer")
    public ResponseEntity<SisBaseApiResponse<OfficerInfoDetailResponse>> saveOfficer(
            @Valid @RequestBody final OfficerSaveRequest saveRequest) throws SisNotExistException {

        final OfficerInfoDetailResponse infoDetailResponse = officerService.saveOfficer(saveRequest);
        return successResponse(infoDetailResponse);
    }

    @PutMapping(OfficerControllerEndpoint.ACADEMIC_INFO_BY_OFFICER_ID)
    @ApiOperation(value = "Update Officer Academic Info")
    public ResponseEntity<SisBaseApiResponse<OfficerAcademicInfoResponse>> updateOfficerAcademicInfo(
            @PathVariable final Long officerId,
            @Valid @RequestBody final OfficerAcademicInfoUpdateRequest academicInfoUpdateRequest)
            throws SisNotExistException {

        final OfficerAcademicInfoResponse academicInfoResponse = officerService.updateOfficerAcademicInfo(officerId, academicInfoUpdateRequest);
        return successResponse(academicInfoResponse);
    }

    @PutMapping(OfficerControllerEndpoint.PERSONAL_INFO_BY_OFFICER_ID)
    @ApiOperation(value = "Update Officer Personal Info")
    public ResponseEntity<SisBaseApiResponse<OfficerPersonalInfoResponse>> updateOfficerPersonalInfo(
            @PathVariable final Long officerId,
            @Valid @RequestBody final OfficerPersonalInfoUpdateRequest personalInfoUpdateRequest)
            throws SisNotExistException {

        final OfficerPersonalInfoResponse personalInfoResponse = officerService.updateOfficerPersonalInfo(officerId, personalInfoUpdateRequest);
        return successResponse(personalInfoResponse);
    }

    @DeleteMapping(OfficerControllerEndpoint.BASE)
    @ApiOperation(value = "Delete Officer")
    public ResponseEntity<SisBaseApiResponse<OfficerInfoResponse>> deleteOfficer(
            @Valid @RequestBody final OfficerDeleteRequest deleteRequest)
            throws SisNotExistException, SisAlreadyException {

        final OfficerInfoResponse infoResponse = officerService.deleteOfficer(deleteRequest);
        return successResponse(infoResponse);
    }

    @PatchMapping(OfficerControllerEndpoint.PASSIVATE)
    @ApiOperation(value = "Passivate Officer")
    public ResponseEntity<SisBaseApiResponse<OfficerInfoResponse>> passivateOfficer(
            @Valid @RequestBody final OfficerPassivateRequest passivateRequest)
            throws SisNotExistException, SisAlreadyException {

        final OfficerInfoResponse infoResponse = officerService.passivateOfficer(passivateRequest);
        return successResponse(infoResponse);
    }

    @PatchMapping(OfficerControllerEndpoint.ACTIVATE)
    @ApiOperation(value = "Activate Officer")
    public ResponseEntity<SisBaseApiResponse<OfficerInfoResponse>> activateOfficer(
            @Valid @RequestBody final OfficerActivateRequest activateRequest)
            throws SisNotExistException, SisAlreadyException {

        final OfficerInfoResponse infoResponse = officerService.activateOfficer(activateRequest);
        return successResponse(infoResponse);
    }
}
