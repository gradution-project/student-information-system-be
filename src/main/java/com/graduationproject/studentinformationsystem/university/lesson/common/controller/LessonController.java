package com.graduationproject.studentinformationsystem.university.lesson.common.controller;

import com.graduationproject.studentinformationsystem.common.util.controller.response.SisBaseApiResponse;
import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.lesson.common.controller.endpoint.LessonControllerEndpoint;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.response.LessonResponse;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.enums.LessonStatus;
import com.graduationproject.studentinformationsystem.university.lesson.common.service.LessonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.graduationproject.studentinformationsystem.common.config.SisSwaggerConfiguration.LESSON_API_TAG;
import static com.graduationproject.studentinformationsystem.common.util.controller.response.SisResponseUtil.successResponse;

@RestController
@RequestMapping
@Api(tags = LESSON_API_TAG)
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @GetMapping(LessonControllerEndpoint.ALL)
    @ApiOperation(value = "Get All Lessons By Status")
    public ResponseEntity<SisBaseApiResponse<List<LessonResponse>>> getAllFacultiesByStatus(
            @RequestParam final LessonStatus status) {

        final List<LessonResponse> lessonResponses = lessonService.getAllLessonsByStatus(status);
        return successResponse(lessonResponses);
    }

    @GetMapping(LessonControllerEndpoint.BY_LESSON_ID)
    @ApiOperation(value = "Get Lesson Detail By Lesson ID")
    public ResponseEntity<SisBaseApiResponse<LessonResponse>> getLessonById(
            @PathVariable final Long lessonId)
            throws SisNotExistException {

        final LessonResponse lessonResponse = lessonService.getLessonById(lessonId);
        return successResponse(lessonResponse);
    }

    @PostMapping(LessonControllerEndpoint.BASE)
    @ApiOperation(value = "Save Lesson")
    public ResponseEntity<SisBaseApiResponse<LessonResponse>> saveLesson(
            @Valid @RequestBody final LessonSaveRequest saveRequest) throws SisNotExistException {

        final LessonResponse lessonResponse = lessonService.saveLesson(saveRequest);
        return successResponse(lessonResponse);
    }

    @PutMapping(LessonControllerEndpoint.BY_LESSON_ID)
    @ApiOperation(value = "Update Lesson")
    public ResponseEntity<SisBaseApiResponse<LessonResponse>> updateLesson(
            @PathVariable final Long lessonId,
            @Valid @RequestBody final LessonUpdateRequest updateRequest)
            throws SisNotExistException {

        final LessonResponse lessonResponse = lessonService.updateLesson(lessonId, updateRequest);
        return successResponse(lessonResponse);
    }

    @DeleteMapping(LessonControllerEndpoint.BASE)
    @ApiOperation(value = "Delete Lesson")
    public ResponseEntity<SisBaseApiResponse<LessonResponse>> deleteLesson(
            @Valid @RequestBody final LessonDeleteRequest deleteRequest)
            throws SisNotExistException, SisAlreadyException {

        final LessonResponse lessonResponse = lessonService.deleteLesson(deleteRequest);
        return successResponse(lessonResponse);
    }

    @PatchMapping(LessonControllerEndpoint.PASSIVATE)
    @ApiOperation(value = "Passivate Lesson")
    public ResponseEntity<SisBaseApiResponse<LessonResponse>> passivateLesson(
            @Valid @RequestBody final LessonPassivateRequest passivateRequest)
            throws SisNotExistException, SisAlreadyException {

        final LessonResponse lessonResponse = lessonService.passivateLesson(passivateRequest);
        return successResponse(lessonResponse);
    }

    @PatchMapping(LessonControllerEndpoint.ACTIVATE)
    @ApiOperation(value = "Activate Lesson")
    public ResponseEntity<SisBaseApiResponse<LessonResponse>> activateLesson(
            @Valid @RequestBody final LessonActivateRequest activateRequest)
            throws SisNotExistException, SisAlreadyException {

        final LessonResponse lessonResponse = lessonService.activateLesson(activateRequest);
        return successResponse(lessonResponse);
    }
}
