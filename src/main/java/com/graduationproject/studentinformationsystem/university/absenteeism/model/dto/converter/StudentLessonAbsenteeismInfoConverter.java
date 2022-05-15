package com.graduationproject.studentinformationsystem.university.absenteeism.model.dto.converter;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.SisUtil;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.dto.request.StudentLessonAbsenteeismSaveRequest;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.dto.response.StudentLessonAbsenteeismResponse;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.entity.StudentLessonAbsenteeismEntity;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.entity.StudentLessonAbsenteeismSaveEntity;
import com.graduationproject.studentinformationsystem.university.absenteeism.model.entity.StudentLessonAbsenteeismUpdateEntity;
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

        return StudentLessonAbsenteeismSaveEntity.builder()
                .id(SisUtil.generateRandomUUID())
                .teacherId(saveRequest.getTeacherId())
                .studentId(saveRequest.getStudentId())
                .lessonId(saveRequest.getLessonId())
                .week(saveRequest.getWeek())
                .maxTheoreticalHours(saveRequest.getMaxTheoreticalHours())
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

        return StudentLessonAbsenteeismUpdateEntity.builder()
                .id(absenteeismId)
                .theoreticalHours(theoreticalHours)
                .practiceHours(practiceHours)
                .status(StudentLessonAbsenteeismStatus.ENTERED)
                .modifiedUserId(operationInfoRequest.getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public StudentLessonAbsenteeismResponse entityToResponse(final StudentLessonAbsenteeismEntity absenteeismEntity) {

        final Long teacherId = absenteeismEntity.getTeacherId();
        final TeacherInfoResponse teacherResponse = teacherOutService.getTeacherInfoResponse(teacherId);

        final Long studentId = absenteeismEntity.getStudentId();
        final StudentInfoResponse studentResponse = studentOutService.getStudentInfoResponse(studentId);

        final Long lessonId = absenteeismEntity.getLessonId();
        final LessonResponse lessonResponse = lessonOutService.getLessonResponse(lessonId);

        return StudentLessonAbsenteeismResponse.builder()
                .id(absenteeismEntity.getId())
                .week(absenteeismEntity.getWeek())
                .theoreticalHours(absenteeismEntity.getTheoreticalHours())
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

    public List<StudentLessonAbsenteeismResponse> entitiesToResponses(final List<StudentLessonAbsenteeismEntity> absenteeismEntities) {
        List<StudentLessonAbsenteeismResponse> absenteeismResponses = new ArrayList<>();
        absenteeismEntities.forEach(absenteeismEntity -> absenteeismResponses.add(entityToResponse(absenteeismEntity)));
        return absenteeismResponses;
    }
}
