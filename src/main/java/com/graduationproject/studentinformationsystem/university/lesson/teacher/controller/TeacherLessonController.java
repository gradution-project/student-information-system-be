package com.graduationproject.studentinformationsystem.university.lesson.teacher.controller;

import com.graduationproject.studentinformationsystem.common.util.controller.response.SisApiResponse;
import com.graduationproject.studentinformationsystem.common.util.controller.response.SisBaseApiResponse;
import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.lesson.common.controller.endpoint.LessonControllerEndpoint;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.request.TeacherLessonRequest;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.response.TeacherLessonResponse;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.service.TeacherLessonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.graduationproject.studentinformationsystem.common.config.SisSwaggerConfiguration.LESSON_API_TAG;
import static com.graduationproject.studentinformationsystem.common.util.controller.endpoint.SisControllerEndpoint.Path.LESSON;
import static com.graduationproject.studentinformationsystem.common.util.controller.response.SisResponseUtil.successResponse;

@RestController
@RequestMapping(LESSON)
@Api(tags = LESSON_API_TAG)
@RequiredArgsConstructor
public class TeacherLessonController {

    private final TeacherLessonService lessonService;

    @GetMapping(LessonControllerEndpoint.TEACHER)
    @ApiOperation(value = "Get All Teachers Lessons")
    public ResponseEntity<SisBaseApiResponse<List<TeacherLessonResponse>>> getAllTeachersLessons() {

        return successResponse(lessonService.getAllTeachersLessons());
    }

    @GetMapping(LessonControllerEndpoint.TEACHER_BY_ID)
    @ApiOperation(value = "Get Teacher All Lessons By Teacher ID")
    public ResponseEntity<SisBaseApiResponse<List<TeacherLessonResponse>>> getTeacherLessonsById(@PathVariable Long teacherId) {

        return successResponse(lessonService.getTeacherLessonsById(teacherId));
    }

    @PostMapping(LessonControllerEndpoint.TEACHER)
    @ApiOperation(value = "Save Teacher Lesson")
    public ResponseEntity<SisBaseApiResponse<TeacherLessonResponse>> saveTeacherLesson(
            @RequestBody @Valid TeacherLessonRequest teacherLessonRequest) throws SisAlreadyException {

        return successResponse(lessonService.saveTeacherLesson(teacherLessonRequest));
    }

    @DeleteMapping(LessonControllerEndpoint.TEACHER)
    @ApiOperation(value = "Delete Teacher Lesson")
    public ResponseEntity<SisApiResponse> deleteTeacherLesson(@RequestBody @Valid TeacherLessonRequest request)
            throws SisAlreadyException, SisNotExistException {

        lessonService.deleteTeacherLesson(request);
        return successResponse();
    }
}
