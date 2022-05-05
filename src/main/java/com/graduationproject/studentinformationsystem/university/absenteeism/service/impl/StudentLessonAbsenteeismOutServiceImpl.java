package com.graduationproject.studentinformationsystem.university.absenteeism.service.impl;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.SisUtil;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisProcessException;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.dto.converter.StudentLessonAbsenteeismInfoConverter;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.dto.request.StudentLessonAbsenteeismSaveRequest;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.entity.StudentLessonAbsenteeismSaveEntity;
import com.graduationproject.studentinformationsystem.university.absenteeism.repository.StudentLessonAbsenteeismRepository;
import com.graduationproject.studentinformationsystem.university.absenteeism.service.StudentLessonAbsenteeismOutService;
import com.graduationproject.studentinformationsystem.university.absenteeism.util.AbsenteeismUtil;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.dto.response.FeatureToggleResponse;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.enums.FeatureToggleName;
import com.graduationproject.studentinformationsystem.university.featuretoggle.service.FeatureToggleOutService;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.response.LessonResponse;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.service.TeacherLessonOutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentLessonAbsenteeismOutServiceImpl implements StudentLessonAbsenteeismOutService {

    private final TeacherLessonOutService teacherLessonOutService;
    private final FeatureToggleOutService featureToggleOutService;

    private final StudentLessonAbsenteeismRepository lessonAbsenteeismRepository;
    private final StudentLessonAbsenteeismInfoConverter lessonAbsenteeismInfoConverter;

    @Override
    public void saveStudentLessonAbsenteeism(final Long studentId,
                                             final List<LessonResponse> lessonResponses,
                                             final SisOperationInfoRequest operationInfoRequest) throws SisNotExistException, ParseException, SisProcessException {


        final Integer totalWeek = getTotalWeek();
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

    private Integer getTotalWeek() throws SisNotExistException, ParseException, SisProcessException {
        final FeatureToggleResponse firstFeatureToggleResponse = featureToggleOutService.getFeatureToggleByName(FeatureToggleName.FIRST_SEMESTER_LESSON_DATE_RANGE);
        final FeatureToggleResponse secondFeatureToggleResponse = featureToggleOutService.getFeatureToggleByName(FeatureToggleName.SECOND_SEMESTER_LESSON_DATE_RANGE);


        final Date firstSemesterStartDate = SisUtil.getDate(firstFeatureToggleResponse.getStartDate());
        final Date firstSemesterEndDate = SisUtil.getDate(firstFeatureToggleResponse.getEndDate());

        boolean isFirstSemester = firstSemesterStartDate.before(new Date()) && firstSemesterEndDate.after(new Date());

        if (isFirstSemester) {
            return AbsenteeismUtil.getTotalWeekBetween2Dates(firstSemesterStartDate, firstSemesterEndDate);
        }

        final Date secondSemesterStartDate = SisUtil.getDate(secondFeatureToggleResponse.getStartDate());
        final Date secondSemesterEndDate = SisUtil.getDate(secondFeatureToggleResponse.getEndDate());

        boolean isSecondSemester = secondSemesterStartDate.before(new Date()) && secondSemesterEndDate.after(new Date());

        if (isSecondSemester) {
            return AbsenteeismUtil.getTotalWeekBetween2Dates(secondSemesterStartDate, secondSemesterEndDate);
        }

        throw new SisProcessException("Current Date Not a First or Second Semester!");
    }
}
