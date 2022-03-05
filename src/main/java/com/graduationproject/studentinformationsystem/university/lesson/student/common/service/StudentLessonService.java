package com.graduationproject.studentinformationsystem.university.lesson.student.common.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.lesson.student.common.model.dto.response.StudentLessonsResponse;

public interface StudentLessonService {

    StudentLessonsResponse getStudentLessonsById(Long studentId) throws SisNotExistException;

    void deleteStudentLessons(Long studentId) throws SisAlreadyException, SisNotExistException;
}
