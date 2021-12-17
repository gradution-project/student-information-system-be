package com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.converter;

import com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.response.LessonResponse;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.entity.LessonEntity;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class LessonConverter {

    private LessonConverter() {
    }

    public static LessonResponse entityToResponse(LessonEntity entity) {
        return new ModelMapper().map(entity, LessonResponse.class);
    }

    public static List<LessonResponse> entitiesToResponses(List<LessonEntity> entities) {
        List<LessonResponse> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(entityToResponse(entity)));
        return dtos;
    }
}
