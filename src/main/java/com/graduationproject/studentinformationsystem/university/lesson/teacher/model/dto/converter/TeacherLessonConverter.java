package com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.converter;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.request.TeacherLessonDeleteRequest;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.request.TeacherLessonRequest;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.request.TeacherLessonSaveRequest;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.dto.response.TeacherLessonResponse;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.entity.TeacherLessonDeleteEntity;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.entity.TeacherLessonEntity;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.model.entity.TeacherLessonSaveEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TeacherLessonConverter {

    private TeacherLessonConverter() {
    }

    public static TeacherLessonSaveEntity generateSaveEntity(final TeacherLessonSaveRequest saveRequest) {

        final TeacherLessonRequest lessonRequest = saveRequest.getLessonRequest();
        final SisOperationInfoRequest operationInfoRequest = saveRequest.getOperationInfoRequest();

        return TeacherLessonSaveEntity.builder()
                .teacherId(lessonRequest.getTeacherId())
                .lessonId(lessonRequest.getLessonId())
                .createdUserId(operationInfoRequest.getUserId())
                .createdDate(new Date()).build();
    }

    public static TeacherLessonDeleteEntity generateDeleteEntity(final TeacherLessonDeleteRequest deleteRequest) {

        final TeacherLessonRequest lessonRequest = deleteRequest.getLessonRequest();

        return TeacherLessonDeleteEntity.builder()
                .teacherId(lessonRequest.getTeacherId())
                .lessonId(lessonRequest.getLessonId()).build();
    }

    public static TeacherLessonResponse entityToResponse(final TeacherLessonEntity lessonEntity) {

        return TeacherLessonResponse.builder()
                .teacherId(lessonEntity.getTeacherId())
                .lessonId(lessonEntity.getLessonId())
                .departmentId(lessonEntity.getDepartmentId())
                .name(lessonEntity.getName())
                .semester(lessonEntity.getSemester())
                .credit(lessonEntity.getCredit())
                .compulsoryOrElective(lessonEntity.getCompulsoryOrElective().getName())
                .status(lessonEntity.getStatus().getName())
                .createdUserId(lessonEntity.getCreatedUserId())
                .createdDate(lessonEntity.getCreatedDate()).build();
    }

    public static List<TeacherLessonResponse> entitiesToResponses(final List<TeacherLessonEntity> lessonEntities) {
        final List<TeacherLessonResponse> lessonResponses = new ArrayList<>();
        lessonEntities.forEach(lessonEntity -> lessonResponses.add(entityToResponse(lessonEntity)));
        return lessonResponses;
    }
}
