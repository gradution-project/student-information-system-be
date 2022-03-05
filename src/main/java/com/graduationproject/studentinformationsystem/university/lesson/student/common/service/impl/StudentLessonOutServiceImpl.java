package com.graduationproject.studentinformationsystem.university.lesson.student.common.service.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.response.LessonResponse;
import com.graduationproject.studentinformationsystem.university.lesson.student.common.model.dto.converter.StudentLessonInfoConverter;
import com.graduationproject.studentinformationsystem.university.lesson.student.common.model.dto.response.StudentLessonsResponse;
import com.graduationproject.studentinformationsystem.university.lesson.student.common.model.entity.StudentLessonEntity;
import com.graduationproject.studentinformationsystem.university.lesson.student.common.model.entity.StudentLessonSaveEntity;
import com.graduationproject.studentinformationsystem.university.lesson.student.common.model.exception.StudentLessonException;
import com.graduationproject.studentinformationsystem.university.lesson.student.common.repository.StudentLessonRepository;
import com.graduationproject.studentinformationsystem.university.lesson.student.common.service.StudentLessonOutService;
import com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.dto.response.StudentLessonRegistrationDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentLessonOutServiceImpl implements StudentLessonOutService {

    private final StudentLessonRepository studentLessonRepository;
    private final StudentLessonInfoConverter studentLessonInfoConverter;

    @Override
    public StudentLessonsResponse saveStudentLessons(final StudentLessonRegistrationDetailResponse registrationDetailResponse)
            throws SisAlreadyException {

        final Long studentId = registrationDetailResponse.getStudentInfoResponse().getStudentId();
        final List<LessonResponse> lessonResponses = registrationDetailResponse.getLessonResponses();
        final Long operationUserId = registrationDetailResponse.getModifiedUserId();

        for (LessonResponse lessonResponse : lessonResponses) {

            final Long lessonId = lessonResponse.getLessonId();

            checkBeforeSaving(studentId, lessonId);

            final StudentLessonSaveEntity studentLessonSaveEntity = studentLessonInfoConverter
                    .generateSaveEntity(studentId, lessonId, operationUserId);

            studentLessonRepository.saveStudentLesson(studentLessonSaveEntity);
        }

        return getStudentLessonsResponse(studentId);
    }

    private StudentLessonsResponse getStudentLessonsResponse(final Long studentId) {

        final List<StudentLessonEntity> studentLessonEntities = studentLessonRepository
                .getStudentLessonsByStudentId(studentId);

        return studentLessonInfoConverter.entitiesToResponse(studentLessonEntities);
    }


    /**
     * Checks Before Processing
     */

    private void checkBeforeSaving(final Long studentId, final Long lessonId) throws SisAlreadyException {
        ifStudentLessonIsExistThrowAlreadyException(studentId, lessonId);
    }


    /**
     * Throw Exceptions
     */

    private void ifStudentLessonIsExistThrowAlreadyException(final Long studentId, final Long lessonId)
            throws SisAlreadyException {

        if (studentLessonRepository.isStudentLessonExist(studentId, lessonId)) {
            StudentLessonException.throwAlreadyExistException(studentId, lessonId);
        }
    }
}
