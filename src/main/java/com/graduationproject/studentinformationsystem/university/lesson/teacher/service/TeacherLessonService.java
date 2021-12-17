package com.graduationproject.studentinformationsystem.university.lesson.teacher.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.request.TeacherLessonRequest;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.response.TeacherLessonResponse;

import java.util.List;

public interface TeacherLessonService {

    List<TeacherLessonResponse> getAllTeachersLessons();

    List<TeacherLessonResponse> getTeacherLessonsById(Long teacherId);

    TeacherLessonResponse saveTeacherLesson(TeacherLessonRequest request) throws SisAlreadyException;

    void deleteTeacherLesson(TeacherLessonRequest request) throws SisAlreadyException, SisNotExistException;
}
