package com.graduationproject.studentinformationsystem.university.lesson.teacher.controller;

import com.graduationproject.studentinformationsystem.common.util.controller.response.SisApiResponse;
import com.graduationproject.studentinformationsystem.common.util.controller.response.SisBaseApiResponse;
import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.controller.endpoint.TeacherLessonControllerEndpoint;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.request.TeacherLessonDeleteRequest;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.request.TeacherLessonSaveRequest;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.response.TeacherLessonResponse;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.service.TeacherLessonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.graduationproject.studentinformationsystem.common.config.SisSwaggerConfiguration.TEACHER_LESSON_API_TAG;
import static com.graduationproject.studentinformationsystem.common.util.controller.response.SisResponseUtil.successResponse;

@RestController
@RequestMapping
@Api(tags = TEACHER_LESSON_API_TAG)
@RequiredArgsConstructor
public class TeacherLessonController {

    private final TeacherLessonService lessonService;

    @GetMapping(TeacherLessonControllerEndpoint.ALL)
    @ApiOperation(value = "Get All Teachers Lessons")
    public ResponseEntity<SisBaseApiResponse<List<TeacherLessonResponse>>> getAllTeachersLessons() {

        final List<TeacherLessonResponse> lessonResponses = lessonService.getAllTeachersLessons();
        return successResponse(lessonResponses);
    }

    @GetMapping(TeacherLessonControllerEndpoint.BY_TEACHER_ID)
    @ApiOperation(value = "Get Teacher All Lessons By Teacher ID")
    public ResponseEntity<SisBaseApiResponse<List<TeacherLessonResponse>>> getTeacherLessonsById(
            @PathVariable final Long teacherId) {

        final List<TeacherLessonResponse> lessonResponses = lessonService.getTeacherLessonsById(teacherId);
        return successResponse(lessonResponses);
    }

    @PostMapping(TeacherLessonControllerEndpoint.BASE)
    @ApiOperation(value = "Save Teacher Lesson")
    public ResponseEntity<SisBaseApiResponse<TeacherLessonResponse>> saveTeacherLesson(
            @RequestBody @Valid final TeacherLessonSaveRequest saveRequest)
            throws SisAlreadyException, SisNotExistException {

        final TeacherLessonResponse lessonResponse = lessonService.saveTeacherLesson(saveRequest);
        return successResponse(lessonResponse);
    }

    @DeleteMapping(TeacherLessonControllerEndpoint.BASE)
    @ApiOperation(value = "Delete Teacher Lesson")
    public ResponseEntity<SisApiResponse> deleteTeacherLesson(
            @RequestBody @Valid final TeacherLessonDeleteRequest deleteRequest)
            throws SisAlreadyException, SisNotExistException {

        lessonService.deleteTeacherLesson(deleteRequest);
        return successResponse();
    }
}
