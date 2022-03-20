package com.graduationproject.studentinformationsystem.university.featuretoggle.controller;

import com.graduationproject.studentinformationsystem.common.util.controller.response.SisBaseApiResponse;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.featuretoggle.controller.endpoint.FeatureToggleControllerEndpoint;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.dto.request.FeatureToggleRequest;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.dto.response.FeatureToggleIsEnabledResponse;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.dto.response.FeatureToggleResponse;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.enums.FeatureToggleName;
import com.graduationproject.studentinformationsystem.university.featuretoggle.service.FeatureToggleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.graduationproject.studentinformationsystem.common.config.SisSwaggerConfiguration.FEATURE_TOGGLE_API_TAG;
import static com.graduationproject.studentinformationsystem.common.util.controller.endpoint.SisControllerEndpoint.Path.FEATURE_TOGGLE;
import static com.graduationproject.studentinformationsystem.common.util.controller.response.SisResponseUtil.successResponse;

@RestController
@RequestMapping(FEATURE_TOGGLE)
@Api(tags = FEATURE_TOGGLE_API_TAG)
@RequiredArgsConstructor
public class FeatureToggleController {

    private final FeatureToggleService featureToggleService;

    @GetMapping
    @ApiOperation(value = "Get All Feature Toggles")
    public ResponseEntity<SisBaseApiResponse<List<FeatureToggleResponse>>> getAllFeatureToggles() {

        final List<FeatureToggleResponse> featureToggleResponses = featureToggleService.getAllFeatureToggles();
        return successResponse(featureToggleResponses);
    }

    @GetMapping(FeatureToggleControllerEndpoint.NAME)
    @ApiOperation(value = "Get Feature Toggle By Name")
    public ResponseEntity<SisBaseApiResponse<FeatureToggleResponse>> getFeatureToggleByName(
            @PathVariable final FeatureToggleName name)
            throws SisNotExistException {

        final FeatureToggleResponse featureToggleResponse = featureToggleService.getFeatureToggleByName(name);
        return successResponse(featureToggleResponse);
    }

    @GetMapping(FeatureToggleControllerEndpoint.ENABLED_BY_NAME)
    @ApiOperation(value = "Is Feature Toggle Enabled")
    public ResponseEntity<SisBaseApiResponse<FeatureToggleIsEnabledResponse>> isFeatureToggleEnabled(
            @PathVariable final FeatureToggleName name)
            throws SisNotExistException {

        final FeatureToggleIsEnabledResponse featureToggleIsEnabledResponse = featureToggleService.isFeatureToggleEnabled(name);
        return successResponse(featureToggleIsEnabledResponse);
    }

    @PatchMapping(FeatureToggleControllerEndpoint.ENABLE)
    @ApiOperation(value = "Enable Feature Toggle")
    public ResponseEntity<SisBaseApiResponse<FeatureToggleResponse>> enableFeatureToggle(
            @Valid @RequestBody final FeatureToggleRequest featureToggleRequest)
            throws SisNotExistException {

        final FeatureToggleResponse featureToggleResponse = featureToggleService.enableFeatureToggle(featureToggleRequest);
        return successResponse(featureToggleResponse);
    }

    @PatchMapping(FeatureToggleControllerEndpoint.DISABLE)
    @ApiOperation(value = "Disable Feature Toggle")
    public ResponseEntity<SisBaseApiResponse<FeatureToggleResponse>> disableFeatureToggle(
            @Valid @RequestBody final FeatureToggleRequest featureToggleRequest)
            throws SisNotExistException {

        final FeatureToggleResponse featureToggleResponse = featureToggleService.disableFeatureToggle(featureToggleRequest);
        return successResponse(featureToggleResponse);
    }
}
