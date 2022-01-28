package com.graduationproject.studentinformationsystem.university.lesson.teacher.service.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.converter.TeacherLessonConverter;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.request.TeacherLessonDeleteRequest;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.request.TeacherLessonRequest;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.request.TeacherLessonSaveRequest;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.response.TeacherLessonResponse;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.entity.TeacherLessonDeleteEntity;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.entity.TeacherLessonEntity;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.entity.TeacherLessonSaveEntity;
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
    public List<TeacherLessonResponse> getTeacherLessonsById(final Long teacherId) {
        return TeacherLessonConverter.entitiesToResponses(lessonRepository.getTeacherLessonsByTeacherId(teacherId));
    }

    @Override
    public TeacherLessonResponse saveTeacherLesson(final TeacherLessonSaveRequest saveRequest) throws SisAlreadyException {
        checkBeforeSaving(saveRequest);

        final TeacherLessonSaveEntity saveEntity = TeacherLessonConverter.generateSaveEntity(saveRequest);

        lessonRepository.saveTeacherLesson(saveEntity);
        return getTeacherLessonResponse(saveEntity.getTeacherId(), saveEntity.getLessonId());
    }

    @Override
    public void deleteTeacherLesson(final TeacherLessonDeleteRequest deleteRequest) throws SisNotExistException {
        checkBeforeDeleting(deleteRequest);

        final TeacherLessonDeleteEntity deleteEntity = TeacherLessonConverter.generateDeleteEntity(deleteRequest);
        lessonRepository.deleteTeacherLesson(deleteEntity);
    }

    private TeacherLessonResponse getTeacherLessonResponse(final Long teacherId, final Long lessonId) {
        final TeacherLessonEntity lessonEntity = lessonRepository.getTeacherLessonByTeacherIdAndLessonId(teacherId, lessonId);
        return TeacherLessonConverter.entityToResponse(lessonEntity);
    }


    /**
     * Checks Before Processing
     */

    private void checkBeforeSaving(final TeacherLessonSaveRequest saveRequest) throws SisAlreadyException {
        final TeacherLessonRequest lessonRequest = saveRequest.getLessonRequest();
        ifTeacherLessonIsExistThrowAlreadyException(lessonRequest.getTeacherId(), lessonRequest.getLessonId());
    }

    private void checkBeforeDeleting(final TeacherLessonDeleteRequest deleteRequest) throws SisNotExistException {
        final TeacherLessonRequest lessonRequest = deleteRequest.getLessonRequest();
        ifTeacherLessonIsNotExistThrowNotExistException(lessonRequest.getTeacherId(), lessonRequest.getLessonId());
    }


    /**
     * Throw Exceptions
     */

    private void ifTeacherLessonIsExistThrowAlreadyException(final Long teacherId, final Long lessonId) throws SisAlreadyException {
        if (lessonRepository.isTeacherLessonExist(teacherId, lessonId)) {
            TeacherLessonException.throwAlreadyExistException(teacherId, lessonId);
        }
    }

    private void ifTeacherLessonIsNotExistThrowNotExistException(final Long teacherId, final Long lessonId) throws SisNotExistException {
        if (!lessonRepository.isTeacherLessonExist(teacherId, lessonId)) {
            TeacherLessonException.throwNotExistException(teacherId, lessonId);
        }
    }
}
