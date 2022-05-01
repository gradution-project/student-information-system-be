package com.graduationproject.studentinformationsystem.university.note.model.dto.converter;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.SisUtil;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.response.LessonResponse;
import com.graduationproject.studentinformationsystem.university.lesson.common.service.LessonOutService;
import com.graduationproject.studentinformationsystem.university.note.model.dto.request.StudentLessonNoteSaveRequest;
import com.graduationproject.studentinformationsystem.university.note.model.dto.response.StudentLessonNoteResponse;
import com.graduationproject.studentinformationsystem.university.note.model.entity.*;
import com.graduationproject.studentinformationsystem.university.note.model.enums.StudentLessonNoteState;
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
                .midtermNoteState(StudentLessonNoteState.NOT_ENTERED)
                .finalNoteState(StudentLessonNoteState.NOT_ENTERED)
                .resitNoteState(StudentLessonNoteState.NOT_ENTERED)
                .status(StudentLessonNoteStatus.UNFINALISED)
                .createdUserId(operationInfoRequest.getUserId())
                .createdDate(new Date())
                .build();
    }

    public StudentLessonMidtermNoteUpdateEntity generateMidtermNoteUpdateEntity(final String lessonNoteId,
                                                                                final Double midtermNote,
                                                                                final SisOperationInfoRequest operationInfoRequest) {

        return StudentLessonMidtermNoteUpdateEntity.builder()
                .id(lessonNoteId)
                .midtermNote(midtermNote)
                .midtermNoteState(StudentLessonNoteState.UNCONFIRMED)
                .modifiedUserId(operationInfoRequest.getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public StudentLessonMidtermNoteConfirmEntity generateMidtermNoteConfirmEntity(final String lessonNoteId,
                                                                                  final SisOperationInfoRequest operationInfoRequest) {

        return StudentLessonMidtermNoteConfirmEntity.builder()
                .id(lessonNoteId)
                .noteState(StudentLessonNoteState.CONFIRMED)
                .modifiedUserId(operationInfoRequest.getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public StudentLessonFinalNoteUpdateEntity generateFinalNoteUpdateEntity(final String lessonNoteId,
                                                                            final Double finalNote,
                                                                            final Double meanOfNote,
                                                                            final StudentLessonNoteStatus status,
                                                                            final SisOperationInfoRequest operationInfoRequest) {

        return StudentLessonFinalNoteUpdateEntity.builder()
                .id(lessonNoteId)
                .finalNote(finalNote)
                .finalNoteState(StudentLessonNoteState.UNCONFIRMED)
                .meanOfNote(meanOfNote)
                .status(status)
                .modifiedUserId(operationInfoRequest.getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public StudentLessonFinalNoteConfirmEntity generateFinalNoteConfirmEntity(final String lessonNoteId,
                                                                              final SisOperationInfoRequest operationInfoRequest) {

        return StudentLessonFinalNoteConfirmEntity.builder()
                .id(lessonNoteId)
                .noteState(StudentLessonNoteState.CONFIRMED)
                .modifiedUserId(operationInfoRequest.getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public StudentLessonResitNoteUpdateEntity generateResitNoteUpdateEntity(final String lessonNoteId,
                                                                            final Double resitNote,
                                                                            final Double meanOfNote,
                                                                            final StudentLessonNoteStatus status,
                                                                            final SisOperationInfoRequest operationInfoRequest) {

        return StudentLessonResitNoteUpdateEntity.builder()
                .id(lessonNoteId)
                .resitNote(resitNote)
                .resitNoteState(StudentLessonNoteState.UNCONFIRMED)
                .meanOfNote(meanOfNote)
                .status(status)
                .modifiedUserId(operationInfoRequest.getUserId())
                .modifiedDate(new Date())
                .build();
    }

    public StudentLessonResitNoteConfirmEntity generateResitNoteConfirmEntity(final String lessonNoteId,
                                                                              final SisOperationInfoRequest operationInfoRequest) {

        return StudentLessonResitNoteConfirmEntity.builder()
                .id(lessonNoteId)
                .noteState(StudentLessonNoteState.CONFIRMED)
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
                .midtermNoteState(noteEntity.getMidtermNoteState())
                .finalNote(noteEntity.getFinalNote())
                .finalNoteState(noteEntity.getFinalNoteState())
                .resitNote(noteEntity.getResitNote())
                .resitNoteState(noteEntity.getResitNoteState())
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
