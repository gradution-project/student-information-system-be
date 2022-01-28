package com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.converter;

import com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.response.LessonResponse;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.entity.LessonEntity;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class LessonConverter {

    private LessonConverter() {
    }

    public static LessonResponse entityToResponse(final LessonEntity lessonEntity) {
        return new ModelMapper().map(lessonEntity, LessonResponse.class);
    }

    public static List<LessonResponse> entitiesToResponses(final List<LessonEntity> lessonEntities) {
        List<LessonResponse> lessonResponses = new ArrayList<>();
        lessonEntities.forEach(lessonEntity -> lessonResponses.add(entityToResponse(lessonEntity)));
        return lessonResponses;
    }
}
