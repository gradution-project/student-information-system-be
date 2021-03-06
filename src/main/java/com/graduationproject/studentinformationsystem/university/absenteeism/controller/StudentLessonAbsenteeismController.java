package com.graduationproject.studentinformationsystem.university.absenteeism.controller;

import com.graduationproject.studentinformationsystem.common.util.controller.response.SisBaseApiResponse;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisProcessException;
import com.graduationproject.studentinformationsystem.university.absenteeism.controller.endpoint.StudentLessonAbsenteeismControllerEndpoint;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.dto.request.StudentsLessonAbsenteeismUpdateRequest;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.dto.response.StudentLessonsAbsenteeismResponse;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.dto.response.StudentsLessonAbsenteeismResponse;
import com.graduationproject.studentinformationsystem.university.absenteeism.service.StudentLessonAbsenteeismService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

import static com.graduationproject.studentinformationsystem.common.config.SisSwaggerConfiguration.STUDENT_LESSON_ABSENTEEISM_API_TAG;
import static com.graduationproject.studentinformationsystem.common.util.controller.response.SisResponseUtil.failResponse;
import static com.graduationproject.studentinformationsystem.common.util.controller.response.SisResponseUtil.successResponse;

@RestController
@RequestMapping
@Api(tags = STUDENT_LESSON_ABSENTEEISM_API_TAG)
@RequiredArgsConstructor
public class StudentLessonAbsenteeismController {

    private final StudentLessonAbsenteeismService studentLessonAbsenteeismService;

    @GetMapping(StudentLessonAbsenteeismControllerEndpoint.BY_STUDENT_ID)
    @ApiOperation(value = "Get All Student Lessons Absenteeism By Student ID")
    public ResponseEntity<SisBaseApiResponse<List<StudentLessonsAbsenteeismResponse>>> getAllStudentLessonsAbsenteeismByStudentId(
            @PathVariable final Long studentId)
            throws SisNotExistException {

        final List<StudentLessonsAbsenteeismResponse> absenteeismResponses = studentLessonAbsenteeismService
                .getAllStudentLessonsAbsenteeismByStudentId(studentId);

        if (absenteeismResponses.isEmpty()) {
            return failResponse(absenteeismResponses);
        }

        return successResponse(absenteeismResponses);
    }

    @GetMapping(StudentLessonAbsenteeismControllerEndpoint.BY_LESSON_ID)
    @ApiOperation(value = "Get All Students Lesson Absenteeism By Lesson ID")
    public ResponseEntity<SisBaseApiResponse<List<StudentsLessonAbsenteeismResponse>>> getAllStudentsLessonAbsenteeismByLessonId(
            @PathVariable final Long lessonId,
            @RequestParam final Integer week)
            throws SisNotExistException {

        final List<StudentsLessonAbsenteeismResponse> absenteeismResponses = studentLessonAbsenteeismService
                .getAllStudentsLessonAbsenteeismByLessonId(lessonId, week);

        if (absenteeismResponses.isEmpty()) {
            return failResponse(absenteeismResponses);
        }

        return successResponse(absenteeismResponses);
    }

    @PutMapping(StudentLessonAbsenteeismControllerEndpoint.BASE)
    @ApiOperation(value = "Update Students Lesson Absenteeism")
    public ResponseEntity<SisBaseApiResponse<List<StudentsLessonAbsenteeismResponse>>> updateStudentLessonAbsenteeism(
            @RequestBody @Valid final StudentsLessonAbsenteeismUpdateRequest updateRequest)
            throws SisNotExistException, SisProcessException {

        final List<StudentsLessonAbsenteeismResponse> absenteeismResponses = studentLessonAbsenteeismService
                .updateStudentLessonAbsenteeism(updateRequest);

        return successResponse(absenteeismResponses);
    }

    @GetMapping(StudentLessonAbsenteeismControllerEndpoint.TOTAL_WEEK)
    @ApiOperation(value = "Get Total Lesson Absenteeism Week")
    public ResponseEntity<SisBaseApiResponse<Integer>> getTotalLessonAbsenteeismWeek()
            throws SisNotExistException, SisProcessException, ParseException {

        final Integer totalLessonAbsenteeismWeek = studentLessonAbsenteeismService.getTotalLessonAbsenteeismWeek();

        return successResponse(totalLessonAbsenteeismWeek);
    }
}
