package com.graduationproject.studentinformationsystem.university.lesson.common.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.response.LessonResponse;

public interface LessonOutService {

    LessonResponse getLessonResponse(Long lessonId);

    void ifLessonIsNotExistThrowNotExistException(Long lessonId) throws SisNotExistException;
}
