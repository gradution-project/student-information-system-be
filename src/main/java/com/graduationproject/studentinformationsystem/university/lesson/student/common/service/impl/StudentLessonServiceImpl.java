package com.graduationproject.studentinformationsystem.university.lesson.student.common.service.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.lesson.student.common.model.dto.converter.StudentLessonInfoConverter;
import com.graduationproject.studentinformationsystem.university.lesson.student.common.model.dto.response.StudentLessonsResponse;
import com.graduationproject.studentinformationsystem.university.lesson.student.common.model.entity.StudentLessonEntity;
import com.graduationproject.studentinformationsystem.university.lesson.student.common.model.exception.StudentLessonException;
import com.graduationproject.studentinformationsystem.university.lesson.student.common.repository.StudentLessonRepository;
import com.graduationproject.studentinformationsystem.university.lesson.student.common.service.StudentLessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class StudentLessonServiceImpl implements StudentLessonService {

    private final StudentLessonRepository studentLessonRepository;
    private final StudentLessonInfoConverter studentLessonInfoConverter;

    @Override
    public StudentLessonsResponse getStudentLessonsById(final Long studentId) throws SisNotExistException {

        ifStudentLessonsIsNotExistThrowNotExistException(studentId);

        final List<StudentLessonEntity> studentLessonEntities = studentLessonRepository
                .getStudentLessonsByStudentId(studentId);

        return studentLessonInfoConverter.entitiesToResponse(studentLessonEntities);
    }

    @Override
    public void deleteStudentLessons(final Long studentId) throws SisNotExistException {

        ifStudentLessonsIsNotExistThrowNotExistException(studentId);

        studentLessonRepository.deleteStudentLessons(studentId);
    }


    /**
     * Throw Exceptions
     */

    private void ifStudentLessonsIsNotExistThrowNotExistException(final Long studentId)
            throws SisNotExistException {

        if (!studentLessonRepository.isStudentLessonsExist(studentId)) {
            StudentLessonException.throwNotExistException(studentId);
        }
    }
}
