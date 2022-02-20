package com.graduationproject.studentinformationsystem.university.lesson.teacher.service.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.converter.TeacherLessonInfoConverter;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.request.TeacherLessonDeleteRequest;
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

    private final TeacherLessonRepository teacherLessonRepository;
    private final TeacherLessonInfoConverter teacherLessonInfoConverter;

    @Override
    public List<TeacherLessonResponse> getAllTeachersLessons() {
        final List<TeacherLessonEntity> teacherLessonEntities = teacherLessonRepository.getAllTeachersLessons();
        return teacherLessonInfoConverter.entitiesToResponses(teacherLessonEntities);
    }

    @Override
    public List<TeacherLessonResponse> getTeacherLessonsById(final Long teacherId) {

        final List<TeacherLessonEntity> teacherLessonEntities = teacherLessonRepository
                .getTeacherLessonsByTeacherId(teacherId);

        return teacherLessonInfoConverter.entitiesToResponses(teacherLessonEntities);
    }

    @Override
    public TeacherLessonResponse saveTeacherLesson(final TeacherLessonSaveRequest teacherLessonSaveRequest)
            throws SisAlreadyException {

        checkBeforeSaving(teacherLessonSaveRequest);

        final TeacherLessonSaveEntity teacherLessonSaveEntity = teacherLessonInfoConverter
                .generateSaveEntity(teacherLessonSaveRequest);

        teacherLessonRepository.saveTeacherLesson(teacherLessonSaveEntity);
        return getTeacherLessonResponse(teacherLessonSaveEntity.getTeacherId(), teacherLessonSaveEntity.getLessonId());
    }

    @Override
    public void deleteTeacherLesson(final TeacherLessonDeleteRequest teacherLessonDeleteRequest)
            throws SisNotExistException {

        checkBeforeDeleting(teacherLessonDeleteRequest);

        final TeacherLessonDeleteEntity teacherLessonDeleteEntity = teacherLessonInfoConverter
                .generateDeleteEntity(teacherLessonDeleteRequest);

        teacherLessonRepository.deleteTeacherLesson(teacherLessonDeleteEntity);
    }

    private TeacherLessonResponse getTeacherLessonResponse(final Long teacherId, final Long lessonId) {
        final TeacherLessonEntity teacherLessonEntity = teacherLessonRepository
                .getTeacherLessonByTeacherIdAndLessonId(teacherId, lessonId);

        return teacherLessonInfoConverter.entityToResponse(teacherLessonEntity);
    }


    /**
     * Checks Before Processing
     */

    private void checkBeforeSaving(final TeacherLessonSaveRequest saveRequest) throws SisAlreadyException {
        final Long teacherId = saveRequest.getTeacherLessonInfoRequest().getTeacherId();
        final Long lessonId = saveRequest.getTeacherLessonInfoRequest().getLessonId();

        ifTeacherLessonIsExistThrowAlreadyException(teacherId, lessonId);
    }

    private void checkBeforeDeleting(final TeacherLessonDeleteRequest deleteRequest) throws SisNotExistException {
        final Long teacherId = deleteRequest.getTeacherLessonInfoRequest().getTeacherId();
        final Long lessonId = deleteRequest.getTeacherLessonInfoRequest().getLessonId();

        ifTeacherLessonIsNotExistThrowNotExistException(teacherId, lessonId);
    }


    /**
     * Throw Exceptions
     */

    private void ifTeacherLessonIsExistThrowAlreadyException(final Long teacherId, final Long lessonId)
            throws SisAlreadyException {

        if (teacherLessonRepository.isTeacherLessonExist(teacherId, lessonId)) {
            TeacherLessonException.throwAlreadyExistException(teacherId, lessonId);
        }
    }

    private void ifTeacherLessonIsNotExistThrowNotExistException(final Long teacherId, final Long lessonId)
            throws SisNotExistException {

        if (!teacherLessonRepository.isTeacherLessonExist(teacherId, lessonId)) {
            TeacherLessonException.throwNotExistException(teacherId, lessonId);
        }
    }
}
