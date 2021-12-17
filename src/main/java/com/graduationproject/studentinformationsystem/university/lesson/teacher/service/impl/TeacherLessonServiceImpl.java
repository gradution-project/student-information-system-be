package com.graduationproject.studentinformationsystem.university.lesson.teacher.service.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.converter.TeacherLessonConverter;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.request.TeacherLessonRequest;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.response.TeacherLessonResponse;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.exception.TeacherLessonException;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.repository.TeacherLessonRepository;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.service.TeacherLessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TeacherLessonServiceImpl implements TeacherLessonService {

    private final TeacherLessonRepository lessonRepository;

    @Override
    public List<TeacherLessonResponse> getAllTeachersLessons() {
        return TeacherLessonConverter.entitiesToResponses(lessonRepository.getAllTeachersLessons());
    }

    @Override
    public List<TeacherLessonResponse> getTeacherLessonsById(Long teacherId) {
        return TeacherLessonConverter.entitiesToResponses(lessonRepository.getTeacherLessonsByTeacherId(teacherId));
    }

    @Override
    public TeacherLessonResponse saveTeacherLesson(TeacherLessonRequest request) throws SisAlreadyException {
        checkBeforeSaving(request);

        lessonRepository.saveTeacherLesson(TeacherLessonConverter.generateSaveEntity(request));
        return getTeacherLessonResponse(request.getTeacherId(), request.getLessonId());
    }

    @Override
    public void deleteTeacherLesson(TeacherLessonRequest request) throws SisNotExistException {
        checkBeforeDeleting(request);

        lessonRepository.deleteTeacherLesson(TeacherLessonConverter.generateDeleteEntity(request));
    }

    private TeacherLessonResponse getTeacherLessonResponse(Long teacherId, Long lessonId) {
        return TeacherLessonConverter.entityToResponse(
                lessonRepository.getTeacherLessonByTeacherIdAndLessonId(teacherId, lessonId));
    }


    /**
     * Checks Before Processing
     */

    private void checkBeforeSaving(TeacherLessonRequest request) throws SisAlreadyException {
        ifTeacherLessonIsExistThrowAlreadyException(request.getTeacherId(), request.getLessonId());
    }

    private void checkBeforeDeleting(TeacherLessonRequest request) throws SisNotExistException {
        ifTeacherLessonIsNotExistThrowNotExistException(request.getTeacherId(), request.getLessonId());
    }


    /**
     * Throw Exceptions
     */

    private void ifTeacherLessonIsExistThrowAlreadyException(Long teacherId, Long lessonId) throws SisAlreadyException {
        if (lessonRepository.isTeacherLessonExist(teacherId, lessonId)) {
            TeacherLessonException.throwAlreadyExistException(teacherId, lessonId);
        }
    }

    private void ifTeacherLessonIsNotExistThrowNotExistException(Long teacherId, Long lessonId) throws SisNotExistException {
        if (!lessonRepository.isTeacherLessonExist(teacherId, lessonId)) {
            TeacherLessonException.throwNotExistException(teacherId, lessonId);
        }
    }
}
