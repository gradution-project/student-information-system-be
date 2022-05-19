package com.graduationproject.studentinformationsystem.university.absenteeism.model.dto.converter;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.SisUtil;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.dto.request.StudentLessonAbsenteeismSaveRequest;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.dto.response.StudentLessonsAbsenteeismResponse;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.dto.response.StudentsLessonAbsenteeismResponse;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.entity.StudentLessonAbsenteeismSaveEntity;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.entity.StudentLessonAbsenteeismUpdateEntity;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.entity.StudentLessonsAbsenteeismEntity;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.entity.StudentsLessonAbsenteeismEntity;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.enums.StudentLessonAbsenteeismHoursState;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.enums.StudentLessonAbsenteeismStatus;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.response.LessonResponse;
import com.graduationproject.studentinformationsystem.university.lesson.common.service.LessonOutService;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentInfoResponse;
import com.graduationproject.studentinformationsystem.university.student.service.StudentOutService;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherInfoResponse;
import com.graduationproject.studentinformationsystem.university.teacher.service.TeacherOutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentLessonAbsenteeismInfoConverter {

    private final TeacherOutService teacherOutService;
    private final StudentOutService studentOutService;
    private final LessonOutService lessonOutService;

    public StudentLessonAbsenteeismSaveEntity generateSaveEntity(final StudentLessonAbsenteeismSaveRequest saveRequest) {

        final SisOperationInfoRequest operationInfoRequest = saveRequest.getOperationInfoRequest();

        final StudentLessonAbsenteeismHoursState theoreticalHoursState;
        if (saveRequest.getMaxTheoreticalHours() == 0) {
            theoreticalHoursState = StudentLessonAbsenteeismHoursState.NOT_EXIST;
        } else {
            theoreticalHoursState = StudentLessonAbsenteeismHoursState.NOT_ENTERED;
        }

        final StudentLessonAbsenteeismHoursState practiceHoursState;
        if (saveRequest.getMaxPracticeHours() == 0) {
            practiceHoursState = StudentLessonAbsenteeismHoursState.NOT_EXIST;
        } else {
            practiceHoursState = StudentLessonAbsenteeismHoursState.NOT_ENTERED;
        }

        return StudentLessonAbsenteeismSaveEntity.builder()
                .id(SisUtil.generateRandomUUID())
                .teacherId(saveRequest.getTeacherId())
                .studentId(saveRequest.getStudentId())
                .lessonId(saveRequest.getLessonId())
                .week(saveRequest.getWeek())
                .theoreticalHoursState(theoreticalHoursState)
                .maxTheoreticalHours(saveRequest.getMaxTheoreticalHours())
                .practiceHoursState(practiceHoursState)
                .maxPracticeHours(saveRequest.getMaxPracticeHours())
                .status(StudentLessonAbsenteeismStatus.NOT_ENTERED)
                .createdUserId(operationInfoRequest.getUserId())
                .createdDate(new Date())
                .build();
    }

    public StudentLessonAbsenteeismUpdateEntity generateUpdateEntity(final String absenteeismId,
                                                                     final Integer theoreticalHours,
                                                                     final Integer practiceHours,
                                                                     final SisOperationInfoRequest operationInfoRequest) {

        StudentLessonAbsenteeismHoursState theoreticalHoursState = StudentLessonAbsenteeismHoursState.NOT_ENTERED;
        if (theoreticalHours != null) {
            theoreticalHoursState = StudentLessonAbsenteeismHoursState.ENTERED;
        }

        StudentLessonAbsenteeismHoursState practiceHoursState = StudentLessonAbsenteeismHoursState.NOT_ENTERED;
        if (practiceHours != null) {
            practiceHoursState = StudentLessonAbsenteeismHoursState.ENTERED;
        }

        return StudentLessonAbsenteeismUpdateEntity.builder()
                .id(absenteeismId)
                .theoreticalHoursState(theoreticalHoursState)
                .theoreticalHours(theoreticalHours)
                .practiceHoursState(practiceHoursState)
                .practiceHours(practiceHours)
                .status(StudentLessonAbsenteeismStatus.ENTERED)
                .modifiedUserId(operationInfoRequest.getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public StudentLessonsAbsenteeismResponse entityToResponse(final Integer currentTheoreticalHours,
                                                              final Integer currentPracticeHours,
                                                              final Integer balanceTheoreticalHours,
                                                              final Integer balancePracticeHours,
                                                              final StudentLessonsAbsenteeismEntity absenteeismEntity) {

        final Long teacherId = absenteeismEntity.getTeacherId();
        final TeacherInfoResponse teacherResponse = teacherOutService.getTeacherInfoResponse(teacherId);

        final Long studentId = absenteeismEntity.getStudentId();
        final StudentInfoResponse studentResponse = studentOutService.getStudentInfoResponse(studentId);

        final Long lessonId = absenteeismEntity.getLessonId();
        final LessonResponse lessonResponse = lessonOutService.getLessonResponse(lessonId);

        StudentLessonAbsenteeismHoursState theoreticalHoursState = StudentLessonAbsenteeismHoursState.NOT_ENTERED;
        if (currentTheoreticalHours != 0 || balanceTheoreticalHours != 0) {
            theoreticalHoursState = StudentLessonAbsenteeismHoursState.ENTERED;
        }

        StudentLessonAbsenteeismHoursState practiceHoursState = StudentLessonAbsenteeismHoursState.NOT_ENTERED;
        if (currentPracticeHours != 0 || balancePracticeHours != 0) {
            practiceHoursState = StudentLessonAbsenteeismHoursState.ENTERED;
        }

        return StudentLessonsAbsenteeismResponse.builder()
                .id(absenteeismEntity.getId())
                .theoreticalHoursState(theoreticalHoursState)
                .currentTheoreticalHours(currentTheoreticalHours)
                .balanceTheoreticalHours(balanceTheoreticalHours)
                .practiceHoursState(practiceHoursState)
                .currentPracticeHours(currentPracticeHours)
                .balancePracticeHours(balancePracticeHours)
                .status(absenteeismEntity.getStatus())
                .createdDate(SisUtil.getFormattedDateTime(absenteeismEntity.getCreatedDate()))
                .createdUserId(absenteeismEntity.getCreatedUserId())
                .modifiedDate(SisUtil.getFormattedDateTime(absenteeismEntity.getModifiedDate()))
                .modifiedUserId(absenteeismEntity.getModifiedUserId())
                .teacherResponse(teacherResponse)
                .studentResponse(studentResponse)
                .lessonResponse(lessonResponse).build();
    }

    public StudentsLessonAbsenteeismResponse entityToResponse(final StudentsLessonAbsenteeismEntity absenteeismEntity) {

        final Long teacherId = absenteeismEntity.getTeacherId();
        final TeacherInfoResponse teacherResponse = teacherOutService.getTeacherInfoResponse(teacherId);

        final Long studentId = absenteeismEntity.getStudentId();
        final StudentInfoResponse studentResponse = studentOutService.getStudentInfoResponse(studentId);

        final Long lessonId = absenteeismEntity.getLessonId();
        final LessonResponse lessonResponse = lessonOutService.getLessonResponse(lessonId);

        return StudentsLessonAbsenteeismResponse.builder()
                .id(absenteeismEntity.getId())
                .week(absenteeismEntity.getWeek())
                .theoreticalHoursState(absenteeismEntity.getTheoreticalHoursState())
                .theoreticalHours(absenteeismEntity.getTheoreticalHours())
                .practiceHoursState(absenteeismEntity.getPracticeHoursState())
                .practiceHours(absenteeismEntity.getPracticeHours())
                .status(absenteeismEntity.getStatus())
                .createdDate(SisUtil.getFormattedDateTime(absenteeismEntity.getCreatedDate()))
                .createdUserId(absenteeismEntity.getCreatedUserId())
                .modifiedDate(SisUtil.getFormattedDateTime(absenteeismEntity.getModifiedDate()))
                .modifiedUserId(absenteeismEntity.getModifiedUserId())
                .teacherResponse(teacherResponse)
                .studentResponse(studentResponse)
                .lessonResponse(lessonResponse).build();
    }

    public List<StudentsLessonAbsenteeismResponse> entitiesToResponses(final List<StudentsLessonAbsenteeismEntity> absenteeismEntities) {
        List<StudentsLessonAbsenteeismResponse> absenteeismResponses = new ArrayList<>();
        absenteeismEntities.forEach(absenteeismEntity -> absenteeismResponses.add(entityToResponse(absenteeismEntity)));
        return absenteeismResponses;
    }
}
