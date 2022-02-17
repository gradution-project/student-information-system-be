package com.graduationproject.studentinformationsystem.university.lesson.common.service.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.converter.LessonInfoConverter;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.response.LessonResponse;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.entity.LessonEntity;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.exception.LessonException;
import com.graduationproject.studentinformationsystem.university.lesson.common.repository.LessonRepository;
import com.graduationproject.studentinformationsystem.university.lesson.common.service.LessonOutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonOutServiceImpl implements LessonOutService {

    private final LessonRepository lessonRepository;
    private final LessonInfoConverter lessonInfoConverter;

    @Override
    public LessonResponse getLessonResponse(Long lessonId) {
        final LessonEntity lessonEntity = lessonRepository.getLessonById(lessonId);
        return lessonInfoConverter.entityToResponse(lessonEntity);
    }

    @Override
    public void ifLessonIsNotExistThrowNotExistException(Long lessonId) throws SisNotExistException {
        if (!lessonRepository.isLessonExist(lessonId)) {
            LessonException.throwNotExistException(lessonId);
        }
    }
}
