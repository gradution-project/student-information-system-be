package com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.converter;

import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.request.TeacherLessonRequest;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.response.TeacherLessonResponse;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.entity.TeacherDeleteLessonEntity;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.entity.TeacherLessonEntity;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.entity.TeacherSaveLessonEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TeacherLessonConverter {

    private TeacherLessonConverter() {
    }

    public static TeacherSaveLessonEntity generateSaveEntity(TeacherLessonRequest request) {
        return TeacherSaveLessonEntity.builder()
                .teacherId(request.getTeacherId())
                .lessonId(request.getLessonId())
                .createdDate(new Date())
                .createdUserId(1L).build();
    }

    public static TeacherDeleteLessonEntity generateDeleteEntity(TeacherLessonRequest request) {
        return TeacherDeleteLessonEntity.builder()
                .teacherId(request.getTeacherId())
                .lessonId(request.getLessonId()).build();
    }

    public static TeacherLessonResponse entityToResponse(TeacherLessonEntity entity) {
        return TeacherLessonResponse.builder()
                .teacherId(entity.getTeacherId())
                .lessonId(entity.getLessonId())
                .departmentId(entity.getDepartmentId())
                .name(entity.getName())
                .semester(entity.getSemester())
                .credit(entity.getCredit())
                .compulsoryOrElective(entity.getCompulsoryOrElective().getName())
                .status(entity.getStatus().getName()).build();
    }

    public static List<TeacherLessonResponse> entitiesToResponses(List<TeacherLessonEntity> entities) {
        List<TeacherLessonResponse> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(entityToResponse(entity)));
        return dtos;
    }
}
