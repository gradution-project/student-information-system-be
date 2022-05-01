package com.graduationproject.studentinformationsystem.university.note.service;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.university.lesson.common.model.dto.response.LessonResponse;

import java.util.List;

public interface StudentLessonNoteOutService {

    void saveStudentLessonsNotesRegistrations(Long studentId,
                                              List<LessonResponse> lessonResponses,
                                              SisOperationInfoRequest operationInfoRequest);

    void hasTheStudentPassedAllLessons(Long studentId) throws SisAlreadyException;
}
