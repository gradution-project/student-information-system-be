package com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.converter;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.SisUtil;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.converter.LessonInfoConverter;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.request.TeacherLessonDeleteRequest;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.request.TeacherLessonInfoRequest;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.request.TeacherLessonSaveRequest;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.response.TeacherLessonResponse;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.entity.TeacherLessonDeleteEntity;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.entity.TeacherLessonEntity;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.entity.TeacherLessonSaveEntity;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.converter.TeacherAcademicInfoConverter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TeacherLessonInfoConverter {

    private TeacherLessonInfoConverter() {
    }

    public static TeacherLessonSaveEntity generateSaveEntity(final TeacherLessonSaveRequest saveRequest) {

        final TeacherLessonInfoRequest teacherLessonInfoRequest = saveRequest.getTeacherLessonInfoRequest();
        final SisOperationInfoRequest operationInfoRequest = saveRequest.getOperationInfoRequest();

        return TeacherLessonSaveEntity.builder()
                .teacherId(teacherLessonInfoRequest.getTeacherId())
                .lessonId(teacherLessonInfoRequest.getLessonId())
                .createdUserId(operationInfoRequest.getUserId())
                .createdDate(new Date()).build();
    }

    public static TeacherLessonDeleteEntity generateDeleteEntity(final TeacherLessonDeleteRequest deleteRequest) {

        final TeacherLessonInfoRequest teacherLessonInfoRequest = deleteRequest.getTeacherLessonInfoRequest();

        return TeacherLessonDeleteEntity.builder()
                .teacherId(teacherLessonInfoRequest.getTeacherId())
                .lessonId(teacherLessonInfoRequest.getLessonId()).build();
    }

    public static TeacherLessonResponse entityToResponse(final TeacherLessonEntity teacherLessonEntity) {

        return TeacherLessonResponse.builder()
                .createdUserId(teacherLessonEntity.getCreatedUserId())
                .createdDate(SisUtil.getFormattedDateTime(teacherLessonEntity.getCreatedDate()))
                .teacherAcademicInfoResponse(TeacherAcademicInfoConverter.entityToResponse(teacherLessonEntity.getTeacherAcademicInfoEntity()))
                .lessonResponse(LessonInfoConverter.entityToResponse(teacherLessonEntity.getLessonEntity())).build();
    }

    public static List<TeacherLessonResponse> entitiesToResponses(final List<TeacherLessonEntity> lessonEntities) {
        final List<TeacherLessonResponse> lessonResponses = new ArrayList<>();
        lessonEntities.forEach(lessonEntity -> lessonResponses.add(entityToResponse(lessonEntity)));
        return lessonResponses;
    }
}
