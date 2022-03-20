package com.graduationproject.studentinformationsystem.university.note.controller;

import com.graduationproject.studentinformationsystem.common.util.controller.response.SisBaseApiResponse;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.note.controller.endpoint.StudentLessonNoteControllerEndpoint;
import com.graduationproject.studentinformationsystem.university.note.model.dto.request.StudentLessonFinalNoteUpdateRequest;
import com.graduationproject.studentinformationsystem.university.note.model.dto.request.StudentLessonMidtermNoteUpdateRequest;
import com.graduationproject.studentinformationsystem.university.note.model.dto.request.StudentLessonResitNoteUpdateRequest;
import com.graduationproject.studentinformationsystem.university.note.model.dto.response.StudentLessonNoteResponse;
import com.graduationproject.studentinformationsystem.university.note.service.StudentLessonNoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.graduationproject.studentinformationsystem.common.config.SisSwaggerConfiguration.STUDENT_LESSON_NOTE_API_TAG;
import static com.graduationproject.studentinformationsystem.common.util.controller.endpoint.SisControllerEndpoint.Path.STUDENT_LESSON_NOTE;
import static com.graduationproject.studentinformationsystem.common.util.controller.response.SisResponseUtil.successResponse;

@RestController
@RequestMapping(STUDENT_LESSON_NOTE)
@Api(tags = STUDENT_LESSON_NOTE_API_TAG)
@RequiredArgsConstructor
public class StudentLessonNoteController {


    private final StudentLessonNoteService studentLessonNoteService;

    @GetMapping(StudentLessonNoteControllerEndpoint.GET_ALL_BY_LESSON_ID)
    @ApiOperation(value = "Get All Students Lessons Notes By Lesson ID")
    public ResponseEntity<SisBaseApiResponse<List<StudentLessonNoteResponse>>> getAllStudentsLessonsNotesByLessonId(
            @PathVariable final Long lessonId)
            throws SisNotExistException {

        final List<StudentLessonNoteResponse> noteResponses = studentLessonNoteService.getAllStudentsLessonsNotesByLessonId(lessonId);
        return successResponse(noteResponses);
    }

    @GetMapping(StudentLessonNoteControllerEndpoint.GET_ALL_BY_STUDENT_ID)
    @ApiOperation(value = "Get All Student Lessons Notes By Student ID")
    public ResponseEntity<SisBaseApiResponse<List<StudentLessonNoteResponse>>> getAllStudentLessonsNotesByStudentId(
            @PathVariable final Long studentId)
            throws SisNotExistException {

        final List<StudentLessonNoteResponse> noteResponses = studentLessonNoteService.getAllStudentLessonsNotesByStudentId(studentId);
        return successResponse(noteResponses);
    }

    @PutMapping(StudentLessonNoteControllerEndpoint.MIDTERM)
    @ApiOperation(value = "Update Student Lesson Midterm Note")
    public ResponseEntity<SisBaseApiResponse<StudentLessonNoteResponse>> updateStudentLessonMidtermNote(
            @RequestBody @Valid final StudentLessonMidtermNoteUpdateRequest updateRequest)
            throws SisNotExistException {

        final StudentLessonNoteResponse noteResponse = studentLessonNoteService.updateStudentLessonMidtermNote(updateRequest);
        return successResponse(noteResponse);
    }

    @PutMapping(StudentLessonNoteControllerEndpoint.FINAL)
    @ApiOperation(value = "Update Student Lesson Final Note")
    public ResponseEntity<SisBaseApiResponse<StudentLessonNoteResponse>> updateStudentLessonFinalNote(
            @RequestBody @Valid final StudentLessonFinalNoteUpdateRequest updateRequest)
            throws SisNotExistException {

        final StudentLessonNoteResponse noteResponse = studentLessonNoteService.updateStudentLessonFinalNote(updateRequest);
        return successResponse(noteResponse);
    }

    @PutMapping(StudentLessonNoteControllerEndpoint.RESIT)
    @ApiOperation(value = "Update Student Lesson Resit Note")
    public ResponseEntity<SisBaseApiResponse<StudentLessonNoteResponse>> updateStudentLessonResitNote(
            @RequestBody final StudentLessonResitNoteUpdateRequest updateRequest)
            throws SisNotExistException {

        final StudentLessonNoteResponse noteResponse = studentLessonNoteService.updateStudentLessonResitNote(updateRequest);
        return successResponse(noteResponse);
    }
}
