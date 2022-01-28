package com.graduationproject.studentinformationsystem.university.lesson.teacher.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.request.TeacherLessonDeleteRequest;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.request.TeacherLessonSaveRequest;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.response.TeacherLessonResponse;

import java.util.List;

public interface TeacherLessonService {

    List<TeacherLessonResponse> getAllTeachersLessons();

    List<TeacherLessonResponse> getTeacherLessonsById(final Long teacherId);

    TeacherLessonResponse saveTeacherLesson(final TeacherLessonSaveRequest saveRequest)
            throws SisAlreadyException;

    void deleteTeacherLesson(final TeacherLessonDeleteRequest deleteRequest)
            throws SisAlreadyException, SisNotExistException;
}
