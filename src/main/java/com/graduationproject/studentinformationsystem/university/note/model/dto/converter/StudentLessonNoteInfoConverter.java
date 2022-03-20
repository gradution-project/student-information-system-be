package com.graduationproject.studentinformationsystem.university.note.model.dto.converter;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.SisUtil;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.response.LessonResponse;
import com.graduationproject.studentinformationsystem.university.lesson.common.service.LessonOutService;
import com.graduationproject.studentinformationsystem.university.note.model.dto.request.StudentLessonFinalNoteUpdateRequest;
import com.graduationproject.studentinformationsystem.university.note.model.dto.request.StudentLessonMidtermNoteUpdateRequest;
import com.graduationproject.studentinformationsystem.university.note.model.dto.request.StudentLessonNoteSaveRequest;
import com.graduationproject.studentinformationsystem.university.note.model.dto.request.StudentLessonResitNoteUpdateRequest;
import com.graduationproject.studentinformationsystem.university.note.model.dto.response.StudentLessonNoteResponse;
import com.graduationproject.studentinformationsystem.university.note.model.entity.*;
import com.graduationproject.studentinformationsystem.university.note.model.enums.StudentLessonNoteStatus;
import com.graduationproject.studentinformationsystem.university.note.util.StudentLessonNoteUtil;
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
public class StudentLessonNoteInfoConverter {

    private final TeacherOutService teacherOutService;
    private final StudentOutService studentOutService;
    private final LessonOutService lessonOutService;

    public StudentLessonNoteSaveEntity generateSaveEntity(final StudentLessonNoteSaveRequest saveRequest) {

        final SisOperationInfoRequest operationInfoRequest = saveRequest.getOperationInfoRequest();

        return StudentLessonNoteSaveEntity.builder()
                .id(SisUtil.generateRandomUUID())
                .teacherId(saveRequest.getTeacherId())
                .studentId(saveRequest.getStudentId())
                .lessonId(saveRequest.getLessonId())
                .status(StudentLessonNoteStatus.UNFINALISED)
                .createdUserId(operationInfoRequest.getUserId())
                .createdDate(new Date())
                .build();
    }

    public StudentLessonMidtermNoteUpdateEntity generateMidtermNoteUpdateEntity(final StudentLessonMidtermNoteUpdateRequest updateRequest) {

        final SisOperationInfoRequest operationInfoRequest = updateRequest.getOperationInfoRequest();

        return StudentLessonMidtermNoteUpdateEntity.builder()
                .id(updateRequest.getId())
                .midtermNote(updateRequest.getMidtermNote())
                .modifiedUserId(operationInfoRequest.getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public StudentLessonFinalNoteUpdateEntity generateFinalNoteUpdateEntity(final Double meanOfNote,
                                                                            final StudentLessonNoteStatus status,
                                                                            final StudentLessonFinalNoteUpdateRequest updateRequest) {

        final SisOperationInfoRequest operationInfoRequest = updateRequest.getOperationInfoRequest();

        return StudentLessonFinalNoteUpdateEntity.builder()
                .id(updateRequest.getId())
                .finalNote(updateRequest.getFinalNote())
                .meanOfNote(meanOfNote)
                .status(status)
                .modifiedUserId(operationInfoRequest.getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public StudentLessonResitNoteUpdateEntity generateResitNoteUpdateEntity(final Double meanOfNote,
                                                                            final StudentLessonNoteStatus status,
                                                                            final StudentLessonResitNoteUpdateRequest updateRequest) {

        final SisOperationInfoRequest operationInfoRequest = updateRequest.getOperationInfoRequest();

        return StudentLessonResitNoteUpdateEntity.builder()
                .id(updateRequest.getId())
                .resitNote(updateRequest.getResitNote())
                .meanOfNote(meanOfNote)
                .status(status)
                .modifiedUserId(operationInfoRequest.getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public StudentLessonNoteResponse entityToResponse(final StudentLessonNoteEntity noteEntity) {

        final Long teacherId = noteEntity.getTeacherId();
        final TeacherInfoResponse teacherResponse = teacherOutService.getTeacherInfoResponse(teacherId);

        final Long studentId = noteEntity.getStudentId();
        final StudentInfoResponse studentResponse = studentOutService.getStudentInfoResponse(studentId);

        final Long lessonId = noteEntity.getLessonId();
        final LessonResponse lessonResponse = lessonOutService.getLessonResponse(lessonId);

        return StudentLessonNoteResponse.builder()
                .id(noteEntity.getId())
                .teacherResponse(teacherResponse)
                .studentResponse(studentResponse)
                .lessonResponse(lessonResponse)
                .midtermNote(noteEntity.getMidtermNote())
                .finalNote(noteEntity.getFinalNote())
                .resitNote(noteEntity.getResitNote())
                .meanOfNote(StudentLessonNoteUtil.getMeanOfNoteWith2NumberAfterDot(noteEntity.getMeanOfNote()))
                .status(noteEntity.getStatus())
                .createdDate(SisUtil.getFormattedDateTime(noteEntity.getCreatedDate()))
                .createdUserId(noteEntity.getCreatedUserId())
                .modifiedDate(SisUtil.getFormattedDateTime(noteEntity.getModifiedDate()))
                .modifiedUserId(noteEntity.getModifiedUserId()).build();
    }

    public List<StudentLessonNoteResponse> entitiesToResponses(final List<StudentLessonNoteEntity> noteEntities) {
        List<StudentLessonNoteResponse> noteResponses = new ArrayList<>();
        noteEntities.forEach(noteEntity -> noteResponses.add(entityToResponse(noteEntity)));
        return noteResponses;
    }
}