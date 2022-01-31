package com.graduationproject.studentinformationsystem.university.lesson.teacher.service.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.entity.LessonEntity;
import com.graduationproject.studentinformationsystem.university.lesson.common.repository.LessonRepository;
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

    private final TeacherLessonRepository teacherLessonRepository;
    private final LessonRepository lessonRepository;

    @Override
    public List<TeacherLessonResponse> getAllTeachersLessons() {
        final List<TeacherLessonEntity> teacherLessonEntities = teacherLessonRepository.getAllTeachersLessons();
        setLessonEntities(teacherLessonEntities);
        return TeacherLessonConverter.entitiesToResponses(teacherLessonEntities);
    }

    @Override
    public List<TeacherLessonResponse> getTeacherLessonsById(final Long teacherId) {
        final List<TeacherLessonEntity> teacherLessonEntities = teacherLessonRepository.getTeacherLessonsByTeacherId(teacherId);
        setLessonEntities(teacherLessonEntities);
        return TeacherLessonConverter.entitiesToResponses(teacherLessonEntities);
    }

    @Override
    public TeacherLessonResponse saveTeacherLesson(final TeacherLessonSaveRequest saveRequest) throws SisAlreadyException {
        checkBeforeSaving(saveRequest);

        final TeacherLessonSaveEntity saveEntity = TeacherLessonConverter.generateSaveEntity(saveRequest);

        teacherLessonRepository.saveTeacherLesson(saveEntity);
        return getTeacherLessonResponse(saveEntity.getTeacherId(), saveEntity.getLessonId());
    }

    @Override
    public void deleteTeacherLesson(final TeacherLessonDeleteRequest deleteRequest) throws SisNotExistException {
        checkBeforeDeleting(deleteRequest);

        final TeacherLessonDeleteEntity deleteEntity = TeacherLessonConverter.generateDeleteEntity(deleteRequest);
        teacherLessonRepository.deleteTeacherLesson(deleteEntity);
    }

    private TeacherLessonResponse getTeacherLessonResponse(final Long teacherId, final Long lessonId) {
        final TeacherLessonEntity teacherLessonEntity = teacherLessonRepository.getTeacherLessonByTeacherIdAndLessonId(teacherId, lessonId);
        setLessonEntity(teacherLessonEntity);
        return TeacherLessonConverter.entityToResponse(teacherLessonEntity);
    }

    private void setLessonEntity(final TeacherLessonEntity teacherLessonEntity) {
        final Long lessonId = teacherLessonEntity.getLessonId();
        final LessonEntity lessonEntity = lessonRepository.getLessonById(lessonId);
        teacherLessonEntity.setLessonEntity(lessonEntity);
    }

    private void setLessonEntities(final List<TeacherLessonEntity> teacherLessonEntities) {
        teacherLessonEntities.forEach(this::setLessonEntity);
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
