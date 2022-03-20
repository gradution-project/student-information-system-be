package com.graduationproject.studentinformationsystem.university.note.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.note.model.dto.request.StudentLessonFinalNoteUpdateRequest;
import com.graduationproject.studentinformationsystem.university.note.model.dto.request.StudentLessonMidtermNoteUpdateRequest;
import com.graduationproject.studentinformationsystem.university.note.model.dto.request.StudentLessonResitNoteUpdateRequest;
import com.graduationproject.studentinformationsystem.university.note.model.dto.response.StudentLessonNoteResponse;

import java.util.List;

public interface StudentLessonNoteService {

    List<StudentLessonNoteResponse> getAllStudentsLessonsNotesByLessonId(Long lessonId) throws SisNotExistException;

    List<StudentLessonNoteResponse> getAllStudentLessonsNotesByStudentId(Long studentId) throws SisNotExistException;

    StudentLessonNoteResponse updateStudentLessonMidtermNote(StudentLessonMidtermNoteUpdateRequest updateRequest) throws SisNotExistException;

    StudentLessonNoteResponse updateStudentLessonFinalNote(StudentLessonFinalNoteUpdateRequest updateRequest) throws SisNotExistException;

    StudentLessonNoteResponse updateStudentLessonResitNote(StudentLessonResitNoteUpdateRequest updateRequest) throws SisNotExistException;
}
