package com.graduationproject.studentinformationsystem.university.lesson.student.common.controller;

import com.graduationproject.studentinformationsystem.common.util.controller.response.SisApiResponse;
import com.graduationproject.studentinformationsystem.common.util.controller.response.SisBaseApiResponse;
import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.common.util.validation.id.StudentID;
import com.graduationproject.studentinformationsystem.university.lesson.student.common.controller.endpoint.StudentLessonControllerEndpoint;
import com.graduationproject.studentinformationsystem.university.lesson.student.common.model.dto.response.StudentLessonsResponse;
import com.graduationproject.studentinformationsystem.university.lesson.student.common.service.StudentLessonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.graduationproject.studentinformationsystem.common.config.SisSwaggerConfiguration.STUDENT_LESSON_API_TAG;
import static com.graduationproject.studentinformationsystem.common.util.controller.response.SisResponseUtil.successResponse;

@RestController
@RequestMapping
@Api(tags = STUDENT_LESSON_API_TAG)
@RequiredArgsConstructor
public class StudentLessonController {

    private final StudentLessonService lessonService;

    @GetMapping(StudentLessonControllerEndpoint.ALL_BY_STUDENT_ID)
    @ApiOperation(value = "Get Student All Lessons By Student ID")
    public ResponseEntity<SisBaseApiResponse<StudentLessonsResponse>> getStudentLessonsById(
            @PathVariable @StudentID final Long studentId)
            throws SisNotExistException {

        final StudentLessonsResponse lessonsResponse = lessonService.getStudentLessonsById(studentId);
        return successResponse(lessonsResponse);
    }

    @DeleteMapping(StudentLessonControllerEndpoint.BY_STUDENT_ID)
    @ApiOperation(value = "Delete Student All Lessons By Student ID")
    public ResponseEntity<SisApiResponse> deleteStudentLessons(
            @PathVariable @StudentID final Long studentId)
            throws SisAlreadyException, SisNotExistException {

        lessonService.deleteStudentLessons(studentId);
        return successResponse();
    }
}
