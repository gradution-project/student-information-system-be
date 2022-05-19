package com.graduationproject.studentinformationsystem.university.absenteeism.service.impl;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.SisUtil;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisProcessException;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.dto.converter.StudentLessonAbsenteeismInfoConverter;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.dto.request.StudentsLessonAbsenteeismUpdateRequest;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.dto.response.StudentLessonsAbsenteeismResponse;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.dto.response.StudentsLessonAbsenteeismResponse;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.entity.StudentLessonAbsenteeismUpdateEntity;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.entity.StudentLessonsAbsenteeismEntity;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.entity.StudentsLessonAbsenteeismEntity;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.enums.StudentLessonAbsenteeismStatus;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.exception.StudentLessonAbsenteeismException;
import com.graduationproject.studentinformationsystem.university.absenteeism.repository.StudentLessonAbsenteeismRepository;
import com.graduationproject.studentinformationsystem.university.absenteeism.service.StudentLessonAbsenteeismService;
import com.graduationproject.studentinformationsystem.university.absenteeism.util.AbsenteeismUtil;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.dto.response.FeatureToggleResponse;
import com.graduationproject.studentinformationsystem.university.featuretoggle.model.enums.FeatureToggleName;
import com.graduationproject.studentinformationsystem.university.featuretoggle.service.FeatureToggleOutService;
import com.graduationproject.studentinformationsystem.university.lesson.common.service.LessonOutService;
import com.graduationproject.studentinformationsystem.university.note.service.StudentLessonNoteOutService;
import com.graduationproject.studentinformationsystem.university.student.service.StudentOutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentLessonAbsenteeismServiceImpl implements StudentLessonAbsenteeismService {

    private final LessonOutService lessonOutService;
    private final StudentOutService studentOutService;
    private final StudentLessonNoteOutService studentLessonNoteOutService;
    private final FeatureToggleOutService featureToggleOutService;

    private final StudentLessonAbsenteeismRepository lessonAbsenteeismRepository;
    private final StudentLessonAbsenteeismInfoConverter lessonAbsenteeismInfoConverter;

    @Override
    public List<StudentLessonsAbsenteeismResponse> getAllStudentLessonsAbsenteeismByStudentId(final Long studentId)
            throws SisNotExistException {

        ifStudentIsNotExistThrowNotExistException(studentId);
        ifStudentLessonsNotesAreNotExistThrowNotExistException(studentId);

        final List<StudentLessonsAbsenteeismEntity> studentLessonsAbsenteeismEntities = lessonAbsenteeismRepository.getAllStudentLessonsAbsenteeismByStudentId(studentId);

        final List<StudentLessonsAbsenteeismEntity> tempStudentLessonsAbsenteeismEntities = new ArrayList<>();
        studentLessonsAbsenteeismEntities.forEach(studentLessonsAbsenteeismEntity -> {

            boolean isResponseExist = tempStudentLessonsAbsenteeismEntities.stream()
                    .anyMatch(entity -> entity.getLessonId().equals(studentLessonsAbsenteeismEntity.getLessonId()));

            if (!isResponseExist) {
                tempStudentLessonsAbsenteeismEntities.add(studentLessonsAbsenteeismEntity);
            }
        });

        final List<StudentLessonsAbsenteeismResponse> studentLessonsAbsenteeismResponses = new ArrayList<>();
        tempStudentLessonsAbsenteeismEntities.forEach(tempStudentLessonsAbsenteeismEntity -> {

            boolean isResponseExist = studentLessonsAbsenteeismResponses.stream()
                    .anyMatch(response -> response.getLessonResponse().getLessonId().equals(tempStudentLessonsAbsenteeismEntity.getLessonId()));

            if (!isResponseExist) {
                List<StudentLessonsAbsenteeismEntity> entities = studentLessonsAbsenteeismEntities.stream()
                        .filter(entity -> tempStudentLessonsAbsenteeismEntity.getLessonId().equals(entity.getLessonId()))
                        .toList();

                Integer currentTheoreticalHours = 0;
                Integer currentPracticeHours = 0;
                for (StudentLessonsAbsenteeismEntity entity : entities) {
                    if (entity.getTheoreticalHours() != null) {
                        currentTheoreticalHours = Integer.sum(currentTheoreticalHours, entity.getTheoreticalHours());
                    }
                    if (entity.getPracticeHours() != null) {
                        currentPracticeHours = Integer.sum(currentPracticeHours, entity.getPracticeHours());
                    }
                }

                final Integer maxTheoreticalHours = tempStudentLessonsAbsenteeismEntity.getMaxTheoreticalHours();
                final Integer maxPracticeHours = tempStudentLessonsAbsenteeismEntity.getMaxPracticeHours();
                final Integer balanceTheoreticalHours = maxTheoreticalHours - currentTheoreticalHours;
                final Integer balancePracticeHours = maxPracticeHours - currentPracticeHours;

                final StudentLessonsAbsenteeismResponse studentLessonsAbsenteeismResponse = lessonAbsenteeismInfoConverter
                        .entityToResponse(currentTheoreticalHours, currentPracticeHours, balanceTheoreticalHours, balancePracticeHours, tempStudentLessonsAbsenteeismEntity);

                studentLessonsAbsenteeismResponses.add(studentLessonsAbsenteeismResponse);
            }
        });

        return studentLessonsAbsenteeismResponses;
    }

    @Override
    public List<StudentsLessonAbsenteeismResponse> getAllStudentsLessonsAbsenteeismByLessonId(final Long lessonId,
                                                                                              final Integer week)
            throws SisNotExistException {

        ifLessonIsNotExistThrowNotExistException(lessonId);

        final List<StudentsLessonAbsenteeismEntity> entities = lessonAbsenteeismRepository.getAllStudentsLessonsAbsenteeismByLessonId(lessonId, week);
        return lessonAbsenteeismInfoConverter.entitiesToResponses(entities);
    }

    @Override
    public List<StudentsLessonAbsenteeismResponse> updateStudentLessonAbsenteeism(final StudentsLessonAbsenteeismUpdateRequest updateRequest)
            throws SisNotExistException, SisProcessException {

        final Map<String, Map<String, Integer>> absenteeismIdsAndTheoreticalHoursAndPracticeHours = updateRequest
                .getAbsenteeismIdsAndTheoreticalHoursAndPracticeHours();

        checkBeforeUpdateNotes(absenteeismIdsAndTheoreticalHoursAndPracticeHours);

        final List<StudentsLessonAbsenteeismResponse> studentsLessonAbsenteeismRespons = new ArrayList<>();
        for (Map.Entry<String, Map<String, Integer>> absenteeismIdAndTheoreticalHoursAndPracticeHours : absenteeismIdsAndTheoreticalHoursAndPracticeHours.entrySet()) {

            final String absenteeismId = absenteeismIdAndTheoreticalHoursAndPracticeHours.getKey();
            final Map<String, Integer> theoreticalHoursAndPracticeHours = absenteeismIdAndTheoreticalHoursAndPracticeHours.getValue();

            Integer theoreticalHours = null;
            Integer practiceHours = null;
            for (Map.Entry<String, Integer> theoreticalHourAndPracticeHour : theoreticalHoursAndPracticeHours.entrySet()) {
                String variableName = theoreticalHourAndPracticeHour.getKey();

                if ("theoreticalHours".equals(variableName)) {
                    theoreticalHours = theoreticalHourAndPracticeHour.getValue();
                } else if ("practiceHours".equals(variableName)) {
                    practiceHours = theoreticalHourAndPracticeHour.getValue();
                } else {
                    throw new SisProcessException("Variables Not a theoreticalHours or practiceHours!");
                }
            }

            final SisOperationInfoRequest operationInfoRequest = updateRequest.getOperationInfoRequest();

            updateStudentLessonAbsenteeism(absenteeismId, theoreticalHours, practiceHours, operationInfoRequest);

            checkTotalHoursIfExceededSetNoteStatusAndAbsenteeismStatusToFailed(absenteeismId, operationInfoRequest);

            addResponseToList(absenteeismId, studentsLessonAbsenteeismRespons);
        }

        return studentsLessonAbsenteeismRespons;
    }

    private void updateStudentLessonAbsenteeism(final String absenteeismId,
                                                final Integer theoreticalHours,
                                                final Integer practiceHours,
                                                final SisOperationInfoRequest operationInfoRequest) {

        final StudentLessonAbsenteeismUpdateEntity updateEntity = lessonAbsenteeismInfoConverter
                .generateUpdateEntity(absenteeismId, theoreticalHours, practiceHours, operationInfoRequest);

        lessonAbsenteeismRepository.updateStudentLessonAbsenteeism(updateEntity);
    }

    private void checkTotalHoursIfExceededSetNoteStatusAndAbsenteeismStatusToFailed(final String absenteeismId,
                                                                                    final SisOperationInfoRequest operationInfoRequest) {

        checkTheoreticalHoursIfExceededSetNoteStatusAndAbsenteeismStatusToFailed(absenteeismId, operationInfoRequest);
        checkPracticeHoursIfExceededSetNoteStatusAndAbsenteeismStatusToFailed(absenteeismId, operationInfoRequest);
    }

    private void checkPracticeHoursIfExceededSetNoteStatusAndAbsenteeismStatusToFailed(final String absenteeismId,
                                                                                       final SisOperationInfoRequest operationInfoRequest) {

        final Integer totalPracticeHours = lessonAbsenteeismRepository.calculateTotalPracticeHours(absenteeismId);
        if (totalPracticeHours != null) {

            final Integer maxPracticeHours = lessonAbsenteeismRepository.getMaxPracticeHoursById(absenteeismId);
            if (maxPracticeHours != null) {

                if (totalPracticeHours > maxPracticeHours) {
                    setNoteStatusAndAbsenteeismStatusToFailed(absenteeismId, operationInfoRequest);
                }
            }
        }
    }

    private void checkTheoreticalHoursIfExceededSetNoteStatusAndAbsenteeismStatusToFailed(final String absenteeismId,
                                                                                          final SisOperationInfoRequest operationInfoRequest) {

        final Integer totalTheoreticalHours = lessonAbsenteeismRepository.calculateTotalTheoreticalHours(absenteeismId);
        if (totalTheoreticalHours != null) {

            final Integer maxTheoreticalHours = lessonAbsenteeismRepository.getMaxTheoreticalHoursById(absenteeismId);
            if (maxTheoreticalHours != null) {

                if (totalTheoreticalHours > maxTheoreticalHours) {
                    setNoteStatusAndAbsenteeismStatusToFailed(absenteeismId, operationInfoRequest);
                }
            }
        }
    }

    private void setNoteStatusAndAbsenteeismStatusToFailed(final String absenteeismId,
                                                           final SisOperationInfoRequest operationInfoRequest) {

        lessonAbsenteeismRepository.updateStudentLessonAbsenteeismStatus(absenteeismId, StudentLessonAbsenteeismStatus.FAILED);

        final Long studentId = lessonAbsenteeismRepository.getStudentIdByAbsenteeismId(absenteeismId);
        final Long lessonId = lessonAbsenteeismRepository.getLessonIdByAbsenteeismId(absenteeismId);
        studentLessonNoteOutService
                .updateStudentLessonsNoteStatusToFailedFromAbsenteeism(studentId, lessonId, operationInfoRequest);
    }

    private void addResponseToList(final String absenteeismId,
                                   final List<StudentsLessonAbsenteeismResponse> studentsLessonAbsenteeismResponse) {

        final StudentsLessonAbsenteeismEntity absenteeismEntity = lessonAbsenteeismRepository.getStudentLessonAbsenteeismById(absenteeismId);
        final StudentsLessonAbsenteeismResponse absenteeismResponse = lessonAbsenteeismInfoConverter.entityToResponse(absenteeismEntity);
        studentsLessonAbsenteeismResponse.add(absenteeismResponse);
    }

    @Override
    public Integer getTotalLessonAbsenteeismWeek() throws SisNotExistException, SisProcessException, ParseException {

        final FeatureToggleResponse firstFeatureToggleResponse = featureToggleOutService
                .getFeatureToggleByName(FeatureToggleName.FIRST_SEMESTER_LESSON_DATE_RANGE);

        final Date firstSemesterStartDate = SisUtil.getDate(firstFeatureToggleResponse.getStartDate());
        final Date firstSemesterEndDate = SisUtil.getDate(firstFeatureToggleResponse.getEndDate());

        boolean isFirstSemester = firstSemesterStartDate.before(new Date()) && firstSemesterEndDate.after(new Date());

        if (isFirstSemester) {
            return AbsenteeismUtil.getTotalWeekBetween2Dates(firstSemesterStartDate, firstSemesterEndDate);
        }


        final FeatureToggleResponse secondFeatureToggleResponse = featureToggleOutService
                .getFeatureToggleByName(FeatureToggleName.SECOND_SEMESTER_LESSON_DATE_RANGE);

        final Date secondSemesterStartDate = SisUtil.getDate(secondFeatureToggleResponse.getStartDate());
        final Date secondSemesterEndDate = SisUtil.getDate(secondFeatureToggleResponse.getEndDate());

        boolean isSecondSemester = secondSemesterStartDate.before(new Date()) && secondSemesterEndDate.after(new Date());

        if (isSecondSemester) {
            return AbsenteeismUtil.getTotalWeekBetween2Dates(secondSemesterStartDate, secondSemesterEndDate);
        }

        throw new SisProcessException("Current Date Not a First or Second Semester!");
    }


    /**
     * Checks Before Processing
     */

    private void checkBeforeUpdateNotes(final Map<String, Map<String, Integer>> absenteeismIdsAndTheoreticalHoursAndPracticeHours)
            throws SisNotExistException {

        for (Map.Entry<String, Map<String, Integer>> absenteeismIdAndTheoreticalHoursAndPracticeHours : absenteeismIdsAndTheoreticalHoursAndPracticeHours.entrySet()) {
            final String absenteeismId = absenteeismIdAndTheoreticalHoursAndPracticeHours.getKey();
            ifStudentLessonAbsenteeismAreNotExistThrowNotExistException(absenteeismId);
        }
    }


    /**
     * Throw Exceptions
     */

    private void ifLessonIsNotExistThrowNotExistException(final Long lessonId) throws SisNotExistException {
        lessonOutService.ifLessonIsNotExistThrowNotExistException(lessonId);
    }

    private void ifStudentIsNotExistThrowNotExistException(final Long studentId) throws SisNotExistException {
        studentOutService.ifStudentIsNotExistThrowNotExistException(studentId);
    }

    private void ifStudentLessonsNotesAreNotExistThrowNotExistException(final Long studentId) throws SisNotExistException {
        if (!lessonAbsenteeismRepository.isStudentLessonAbsenteeismExist(studentId)) {
            StudentLessonAbsenteeismException.throwNotExistExceptionByStudentId(studentId);
        }
    }

    private void ifStudentLessonAbsenteeismAreNotExistThrowNotExistException(final String id) throws SisNotExistException {
        if (!lessonAbsenteeismRepository.isStudentLessonAbsenteeismExist(id)) {
            StudentLessonAbsenteeismException.throwNotExistExceptionById(id);
        }
    }
}
