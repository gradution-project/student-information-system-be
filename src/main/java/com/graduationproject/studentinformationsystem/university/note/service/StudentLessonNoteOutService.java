package com.graduationproject.studentinformationsystem.university.note.service;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.response.LessonResponse;
import com.graduationproject.studentinformationsystem.university.note.model.dto.response.StudentLessonNoteResponse;

import java.util.List;

public interface StudentLessonNoteOutService {

    void saveStudentLessonsNotesRegistrations(Long studentId,
                                              List<LessonResponse> lessonResponses,
                                              SisOperationInfoRequest operationInfoRequest);

    void updateStudentLessonsNoteStatusToFailedFromAbsenteeism(Long studentId,
                                                               Long lessonId,
                                                               SisOperationInfoRequest operationInfoRequest);

    List<StudentLessonNoteResponse> getStudentLessonsAllConfirmedNotesByStudentId(Long studentId)
            throws SisAlreadyException, SisNotExistException;

    void hasTheStudentPassedAllLessons(Long studentId) throws SisAlreadyException;
}
