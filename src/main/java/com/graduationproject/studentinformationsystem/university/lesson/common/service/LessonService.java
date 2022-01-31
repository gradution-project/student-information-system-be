package com.graduationproject.studentinformationsystem.university.lesson.common.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.response.LessonResponse;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.enums.LessonStatus;

import java.util.List;

public interface LessonService {

    List<LessonResponse> getAllLessonsByStatus(LessonStatus status);

    LessonResponse getLessonById(Long lessonId) throws SisNotExistException;

    LessonResponse saveLesson(LessonSaveRequest saveRequest) throws SisNotExistException;

    LessonResponse updateLesson(Long lessonId, LessonUpdateRequest updateRequest) throws SisNotExistException;

    LessonResponse deleteLesson(LessonDeleteRequest deleteRequest) throws SisNotExistException, SisAlreadyException;

    LessonResponse passivateLesson(LessonPassivateRequest passivateRequest) throws SisNotExistException, SisAlreadyException;

    LessonResponse activateLesson(LessonActivateRequest activateRequest) throws SisNotExistException, SisAlreadyException;
}
