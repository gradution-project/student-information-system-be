package com.graduationproject.studentinformationsystem.university.note.service.impl;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.response.LessonResponse;
import com.graduationproject.studentinformationsystem.university.lesson.teacher.service.TeacherLessonOutService;
import com.graduationproject.studentinformationsystem.university.note.model.dto.converter.StudentLessonNoteInfoConverter;
import com.graduationproject.studentinformationsystem.university.note.model.dto.request.StudentLessonNoteSaveRequest;
import com.graduationproject.studentinformationsystem.university.note.model.entity.StudentLessonNoteSaveEntity;
import com.graduationproject.studentinformationsystem.university.note.model.exception.StudentLessonNoteException;
import com.graduationproject.studentinformationsystem.university.note.repository.StudentLessonNoteRepository;
import com.graduationproject.studentinformationsystem.university.note.service.StudentLessonNoteOutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentLessonNoteOutServiceImpl implements StudentLessonNoteOutService {

    private final TeacherLessonOutService teacherLessonOutService;

    private final StudentLessonNoteRepository lessonNoteRepository;
    private final StudentLessonNoteInfoConverter lessonNoteInfoConverter;

    @Override
    public void saveStudentLessonsNotesRegistrations(final Long studentId,
                                                     final List<LessonResponse> lessonResponses,
                                                     final SisOperationInfoRequest operationInfoRequest) {

        for (LessonResponse lessonResponse : lessonResponses) {
            final Long lessonId = lessonResponse.getLessonId();
            final Long teacherId = teacherLessonOutService.getTeacherIdByLessonId(lessonId);

            StudentLessonNoteSaveRequest saveRequest = StudentLessonNoteSaveRequest.builder()
                    .teacherId(teacherId)
                    .studentId(studentId)
                    .lessonId(lessonId)
                    .operationInfoRequest(operationInfoRequest)
                    .build();

            final StudentLessonNoteSaveEntity saveEntity = lessonNoteInfoConverter.generateSaveEntity(saveRequest);
            lessonNoteRepository.saveStudentLessonNote(saveEntity);
        }
    }

    @Override
    public void hasTheStudentPassedAllLessons(final Long studentId) throws SisAlreadyException {

        final boolean hasTheStudentPassedAllLessons = lessonNoteRepository.hasTheStudentPassedAllLessons(studentId);

        if (!hasTheStudentPassedAllLessons) {
            StudentLessonNoteException.throwAlreadyExistException(studentId);
        }
    }

    @Override
    public boolean isStudentGraduationEnabled(final Long studentId) {
        return lessonNoteRepository.hasTheStudentPassedAllLessons(studentId);
    }
}
