package com.graduationproject.studentinformationsystem.university.absenteeism.service.impl;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisProcessException;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.dto.converter.StudentLessonAbsenteeismInfoConverter;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.dto.request.StudentLessonAbsenteeismSaveRequest;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.entity.StudentLessonAbsenteeismSaveEntity;
import com.graduationproject.studentinformationsystem.university.absenteeism.repository.StudentLessonAbsenteeismRepository;
import com.graduationproject.studentinformationsystem.university.absenteeism.service.StudentLessonAbsenteeismOutService;
import com.graduationproject.studentinformationsystem.university.absenteeism.service.StudentLessonAbsenteeismService;
import com.graduationproject.studentinformationsystem.university.absenteeism.util.AbsenteeismUtil;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.response.LessonResponse;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.service.TeacherLessonOutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentLessonAbsenteeismOutServiceImpl implements StudentLessonAbsenteeismOutService {

    private final TeacherLessonOutService teacherLessonOutService;

    private final StudentLessonAbsenteeismService studentLessonAbsenteeismService;
    private final StudentLessonAbsenteeismRepository lessonAbsenteeismRepository;
    private final StudentLessonAbsenteeismInfoConverter lessonAbsenteeismInfoConverter;

    @Override
    public void saveStudentLessonAbsenteeism(final Long studentId,
                                             final List<LessonResponse> lessonResponses,
                                             final SisOperationInfoRequest operationInfoRequest)
            throws SisNotExistException, ParseException, SisProcessException {

        final Integer totalWeek = studentLessonAbsenteeismService.getTotalLessonAbsenteeismWeek();
        for (LessonResponse lessonResponse : lessonResponses) {
            final Long lessonId = lessonResponse.getLessonId();
            final Long teacherId = teacherLessonOutService.getTeacherIdByLessonId(lessonId);

            final Integer maxTheoreticalHours = AbsenteeismUtil
                    .calculateMaxTheoreticalHours(lessonResponse.getTheoreticalHours(), totalWeek);
            final Integer maxPracticeHours = AbsenteeismUtil
                    .calculateMaxPracticeHours(lessonResponse.getPracticeHours(), totalWeek);

            for (int week = 1; week <= totalWeek; week++) {
                StudentLessonAbsenteeismSaveRequest saveRequest = StudentLessonAbsenteeismSaveRequest.builder()
                        .teacherId(teacherId)
                        .studentId(studentId)
                        .lessonId(lessonId)
                        .week(week)
                        .maxTheoreticalHours(maxTheoreticalHours)
                        .maxPracticeHours(maxPracticeHours)
                        .operationInfoRequest(operationInfoRequest)
                        .build();

                final StudentLessonAbsenteeismSaveEntity saveEntity = lessonAbsenteeismInfoConverter.generateSaveEntity(saveRequest);
                lessonAbsenteeismRepository.saveStudentLessonAbsenteeism(saveEntity);
            }
        }
    }
}
