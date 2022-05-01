package com.graduationproject.studentinformationsystem.university.note.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.note.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.note.model.dto.response.StudentLessonNoteResponse;

import java.util.List;

public interface StudentLessonNoteService {

    List<StudentLessonNoteResponse> getAllStudentsLessonsNotesByLessonId(Long lessonId) throws SisNotExistException;

    List<StudentLessonNoteResponse> getAllStudentLessonsNotesByStudentId(Long studentId) throws SisNotExistException;

    List<StudentLessonNoteResponse> updateStudentsLessonMidtermNotes(StudentsLessonMidtermNotesUpdateRequest updateRequest)
            throws SisNotExistException;

    List<StudentLessonNoteResponse> confirmStudentsLessonMidtermNotes(StudentsLessonMidtermNotesConfirmRequest confirmRequest)
            throws SisNotExistException;

    List<StudentLessonNoteResponse> updateStudentsLessonFinalNotes(StudentsLessonFinalNotesUpdateRequest updateRequest)
            throws SisNotExistException;

    List<StudentLessonNoteResponse> confirmStudentsLessonFinalNotes(StudentsLessonFinalNotesConfirmRequest confirmRequest)
            throws SisNotExistException;

    List<StudentLessonNoteResponse> updateStudentsLessonResitNotes(StudentsLessonResitNotesUpdateRequest updateRequest)
            throws SisNotExistException;

    List<StudentLessonNoteResponse> confirmStudentsLessonResitNotes(StudentsLessonResitNotesConfirmRequest confirmRequest)
            throws SisNotExistException;
}
