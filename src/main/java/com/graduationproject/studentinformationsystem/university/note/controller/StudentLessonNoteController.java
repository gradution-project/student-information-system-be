package com.graduationproject.studentinformationsystem.university.note.controller;

import com.graduationproject.studentinformationsystem.common.util.controller.response.SisBaseApiResponse;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.note.controller.endpoint.StudentLessonNoteControllerEndpoint;
import com.graduationproject.studentinformationsystem.university.note.model.dto.request.*;
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
    @ApiOperation(value = "Update Students Lesson Midterm Notes")
    public ResponseEntity<SisBaseApiResponse<List<StudentLessonNoteResponse>>> updateStudentsLessonMidtermNotes(
            @RequestBody @Valid final StudentsLessonMidtermNotesUpdateRequest updateRequest)
            throws SisNotExistException {

        final List<StudentLessonNoteResponse> noteResponses = studentLessonNoteService.updateStudentsLessonMidtermNotes(updateRequest);
        return successResponse(noteResponses);
    }

    @PatchMapping(StudentLessonNoteControllerEndpoint.MIDTERM_CONFIRM)
    @ApiOperation(value = "Confirm Students Lesson Midterm Notes")
    public ResponseEntity<SisBaseApiResponse<List<StudentLessonNoteResponse>>> confirmStudentsLessonMidtermNotes(
            @RequestBody final StudentsLessonMidtermNotesConfirmRequest confirmRequest)
            throws SisNotExistException {

        final List<StudentLessonNoteResponse> noteResponses = studentLessonNoteService.confirmStudentsLessonMidtermNotes(confirmRequest);
        return successResponse(noteResponses);
    }

    @PutMapping(StudentLessonNoteControllerEndpoint.FINAL)
    @ApiOperation(value = "Update Students Lesson Final Notes")
    public ResponseEntity<SisBaseApiResponse<List<StudentLessonNoteResponse>>> updateStudentsLessonFinalNotes(
            @RequestBody @Valid final StudentsLessonFinalNotesUpdateRequest updateRequest)
            throws SisNotExistException {

        final List<StudentLessonNoteResponse> noteResponses = studentLessonNoteService.updateStudentsLessonFinalNotes(updateRequest);
        return successResponse(noteResponses);
    }

    @PatchMapping(StudentLessonNoteControllerEndpoint.FINAL_CONFIRM)
    @ApiOperation(value = "Confirm Students Lesson Final Notes")
    public ResponseEntity<SisBaseApiResponse<List<StudentLessonNoteResponse>>> confirmStudentsLessonFinalNotes(
            @RequestBody final StudentsLessonFinalNotesConfirmRequest confirmRequest)
            throws SisNotExistException {

        final List<StudentLessonNoteResponse> noteResponses = studentLessonNoteService.confirmStudentsLessonFinalNotes(confirmRequest);
        return successResponse(noteResponses);
    }

    @PutMapping(StudentLessonNoteControllerEndpoint.RESIT)
    @ApiOperation(value = "Update Students Lesson Resit Notes")
    public ResponseEntity<SisBaseApiResponse<List<StudentLessonNoteResponse>>> updateStudentsLessonResitNotes(
            @RequestBody final StudentsLessonResitNotesUpdateRequest updateRequest)
            throws SisNotExistException {

        final List<StudentLessonNoteResponse> noteResponses = studentLessonNoteService.updateStudentsLessonResitNotes(updateRequest);
        return successResponse(noteResponses);
    }

    @PatchMapping(StudentLessonNoteControllerEndpoint.RESIT_CONFIRM)
    @ApiOperation(value = "Confirm Students Lesson Resit Notes")
    public ResponseEntity<SisBaseApiResponse<List<StudentLessonNoteResponse>>> confirmStudentsLessonResitNotes(
            @RequestBody final StudentsLessonResitNotesConfirmRequest confirmRequest)
            throws SisNotExistException {

        final List<StudentLessonNoteResponse> noteResponses = studentLessonNoteService.confirmStudentsLessonResitNotes(confirmRequest);
        return successResponse(noteResponses);
    }
}
