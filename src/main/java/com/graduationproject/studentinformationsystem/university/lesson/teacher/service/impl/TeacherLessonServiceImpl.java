package com.graduationproject.studentinformationsystem.university.lesson.teacher.service.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.department.model.entity.DepartmentEntity;
import com.graduationproject.studentinformationsystem.university.department.repository.DepartmentRepository;
import com.graduationproject.studentinformationsystem.university.faculty.model.entity.FacultyEntity;
import com.graduationproject.studentinformationsystem.university.faculty.repository.FacultyRepository;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.entity.LessonEntity;
import com.graduationproject.studentinformationsystem.university.lesson.common.repository.LessonRepository;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.converter.TeacherLessonInfoConverter;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.request.TeacherLessonDeleteRequest;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.request.TeacherLessonInfoRequest;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.request.TeacherLessonSaveRequest;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.response.TeacherLessonResponse;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.entity.TeacherLessonDeleteEntity;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.entity.TeacherLessonEntity;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.entity.TeacherLessonSaveEntity;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.exception.TeacherLessonException;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.repository.TeacherLessonRepository;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.service.TeacherLessonService;
import com.graduationproject.studentinformationsystem.university.teacher.model.entity.TeacherAcademicInfoEntity;
import com.graduationproject.studentinformationsystem.university.teacher.repository.TeacherAcademicInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TeacherLessonServiceImpl implements TeacherLessonService {

    private final FacultyRepository facultyRepository;
    private final DepartmentRepository departmentRepository;
    private final LessonRepository lessonRepository;
    private final TeacherAcademicInfoRepository teacherAcademicInfoRepository;

    private final TeacherLessonRepository teacherLessonRepository;

    @Override
    public List<TeacherLessonResponse> getAllTeachersLessons() {
        final List<TeacherLessonEntity> teacherLessonEntities = teacherLessonRepository.getAllTeachersLessons();
        setLessonEntities(teacherLessonEntities);
        setTeacherAcademicInfoEntities(teacherLessonEntities);
        return TeacherLessonInfoConverter.entitiesToResponses(teacherLessonEntities);
    }

    @Override
    public List<TeacherLessonResponse> getTeacherLessonsById(final Long teacherId) {
        final List<TeacherLessonEntity> teacherLessonEntities = teacherLessonRepository.getTeacherLessonsByTeacherId(teacherId);
        setLessonEntities(teacherLessonEntities);
        setTeacherAcademicInfoEntities(teacherLessonEntities);
        return TeacherLessonInfoConverter.entitiesToResponses(teacherLessonEntities);
    }

    @Override
    public TeacherLessonResponse saveTeacherLesson(final TeacherLessonSaveRequest saveRequest) throws SisAlreadyException {
        checkBeforeSaving(saveRequest);

        final TeacherLessonSaveEntity saveEntity = TeacherLessonInfoConverter.generateSaveEntity(saveRequest);

        teacherLessonRepository.saveTeacherLesson(saveEntity);
        return getTeacherLessonResponse(saveEntity.getTeacherId(), saveEntity.getLessonId());
    }

    @Override
    public void deleteTeacherLesson(final TeacherLessonDeleteRequest deleteRequest) throws SisNotExistException {
        checkBeforeDeleting(deleteRequest);

        final TeacherLessonDeleteEntity deleteEntity = TeacherLessonInfoConverter.generateDeleteEntity(deleteRequest);
        teacherLessonRepository.deleteTeacherLesson(deleteEntity);
    }

    private TeacherLessonResponse getTeacherLessonResponse(final Long teacherId, final Long lessonId) {
        final TeacherLessonEntity teacherLessonEntity = teacherLessonRepository.getTeacherLessonByTeacherIdAndLessonId(teacherId, lessonId);
        setTeacherAcademicInfoEntity(teacherLessonEntity);
        setLessonEntity(teacherLessonEntity);
        return TeacherLessonInfoConverter.entityToResponse(teacherLessonEntity);
    }

    private void setTeacherAcademicInfoEntity(final TeacherLessonEntity teacherLessonEntity) {
        final Long teacherId = teacherLessonEntity.getTeacherId();
        final TeacherAcademicInfoEntity teacherAcademicInfoEntity = teacherAcademicInfoRepository.getTeacherAcademicInfoById(teacherId);
        setDepartmentEntity(teacherAcademicInfoEntity);
        teacherLessonEntity.setTeacherAcademicInfoEntity(teacherAcademicInfoEntity);
    }

    private void setTeacherAcademicInfoEntities(List<TeacherLessonEntity> teacherLessonEntities) {
        teacherLessonEntities.forEach(this::setTeacherAcademicInfoEntity);
    }

    private void setLessonEntity(final TeacherLessonEntity teacherLessonEntity) {
        final Long lessonId = teacherLessonEntity.getLessonId();
        final LessonEntity lessonEntity = lessonRepository.getLessonById(lessonId);
        setDepartmentEntity(lessonEntity);
        teacherLessonEntity.setLessonEntity(lessonEntity);
    }

    private void setLessonEntities(final List<TeacherLessonEntity> teacherLessonEntities) {
        teacherLessonEntities.forEach(this::setLessonEntity);
    }

    private void setDepartmentEntity(final TeacherAcademicInfoEntity teacherAcademicInfoEntity) {
        final Long departmentId = teacherAcademicInfoEntity.getDepartmentId();
        final DepartmentEntity departmentEntity = departmentRepository.getDepartmentById(departmentId);
        setFacultyEntity(departmentEntity);
        teacherAcademicInfoEntity.setDepartmentEntity(departmentEntity);
    }

    private void setDepartmentEntity(final LessonEntity lessonEntity) {
        final Long departmentId = lessonEntity.getDepartmentId();
        final DepartmentEntity departmentEntity = departmentRepository.getDepartmentById(departmentId);
        setFacultyEntity(departmentEntity);
        lessonEntity.setDepartmentEntity(departmentEntity);
    }

    private void setFacultyEntity(final DepartmentEntity departmentEntity) {
        final Long facultyId = departmentEntity.getFacultyId();
        final FacultyEntity facultyEntity = facultyRepository.getFacultyById(facultyId);
        departmentEntity.setFacultyEntity(facultyEntity);
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
        final TeacherLessonInfoRequest teacherLessonInfoRequest = deleteRequest.getTeacherLessonInfoRequest();
        ifTeacherLessonIsNotExistThrowNotExistException(teacherLessonInfoRequest.getTeacherId(), teacherLessonInfoRequest.getLessonId());
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
