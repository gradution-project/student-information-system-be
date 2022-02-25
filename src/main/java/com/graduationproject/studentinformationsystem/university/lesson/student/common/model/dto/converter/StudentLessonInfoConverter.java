package com.graduationproject.studentinformationsystem.university.lesson.student.common.model.dto.converter;

import com.graduationproject.studentinformationsystem.common.util.SisUtil;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.response.LessonResponse;
import com.graduationproject.studentinformationsystem.university.lesson.common.service.LessonOutService;
import com.graduationproject.studentinformationsystem.university.lesson.student.common.model.dto.response.StudentLessonResponse;
import com.graduationproject.studentinformationsystem.university.lesson.student.common.model.dto.response.StudentLessonsResponse;
import com.graduationproject.studentinformationsystem.university.lesson.student.common.model.entity.StudentLessonEntity;
import com.graduationproject.studentinformationsystem.university.lesson.student.common.model.entity.StudentLessonSaveEntity;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentInfoResponse;
import com.graduationproject.studentinformationsystem.university.student.service.StudentOutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentLessonInfoConverter {

    private final StudentOutService studentOutService;
    private final LessonOutService lessonOutService;

    public StudentLessonSaveEntity generateSaveEntity(final Long studentId,
                                                      final Long lessonId,
                                                      final Long operationUserId) {

        return StudentLessonSaveEntity.builder()
                .studentId(studentId)
                .lessonId(lessonId)
                .createdUserId(operationUserId)
                .createdDate(new Date()).build();
    }

    public StudentLessonResponse entityToResponse(final StudentLessonEntity studentLessonEntity) {

        final StudentInfoResponse studentInfoResponse = getStudentInfoResponse(studentLessonEntity.getStudentId());
        final LessonResponse lessonResponse = getLessonResponse(studentLessonEntity.getLessonId());

        return StudentLessonResponse.builder()
                .createdUserId(studentLessonEntity.getCreatedUserId())
                .createdDate(SisUtil.getFormattedDateTime(studentLessonEntity.getCreatedDate()))
                .studentInfoResponse(studentInfoResponse)
                .lessonResponse(lessonResponse).build();
    }

    public StudentLessonsResponse entitiesToResponse(final List<StudentLessonEntity> studentLessonEntities) {
        if (studentLessonEntities.isEmpty()) {
            return null;
        }

        final StudentInfoResponse studentInfoResponse = getStudentInfoResponse(studentLessonEntities.get(0).getStudentId());
        final List<LessonResponse> lessonResponses = new ArrayList<>();

        studentLessonEntities.forEach(studentLessonEntity -> {
            final LessonResponse lessonResponse = getLessonResponse(studentLessonEntity.getLessonId());
            lessonResponses.add(lessonResponse);
        });

        return StudentLessonsResponse.builder()
                .createdUserId(studentLessonEntities.get(0).getCreatedUserId())
                .createdDate(SisUtil.getFormattedDateTime(studentLessonEntities.get(0).getCreatedDate()))
                .lessonsResponses(lessonResponses)
                .studentInfoResponse(studentInfoResponse).build();
    }

    private StudentInfoResponse getStudentInfoResponse(Long studentId) {
        return studentOutService.getStudentInfoResponse(studentId);
    }

    private LessonResponse getLessonResponse(Long lessonId) {
        return lessonOutService.getLessonResponse(lessonId);
    }
}
