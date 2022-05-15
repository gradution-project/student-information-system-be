package com.graduationproject.studentinformationsystem.university.note.repository;

import com.graduationproject.studentinformationsystem.university.note.model.entity.*;

import java.util.List;

public interface StudentLessonNoteRepository {

    List<StudentLessonNoteEntity> getAllStudentsLessonsNotesByLessonId(Long lessonId);

    List<StudentLessonNoteEntity> getAllStudentLessonsNotesByStudentId(Long studentId);

    StudentLessonNoteEntity getStudentLessonNotesById(String id);

    void saveStudentLessonNote(StudentLessonNoteSaveEntity saveEntity);

    void updateStudentLessonMidtermNote(StudentLessonMidtermNoteUpdateEntity updateEntity);

    void confirmStudentLessonMidtermNote(StudentLessonMidtermNoteConfirmEntity confirmEntity);

    void updateStudentLessonFinalNote(StudentLessonFinalNoteUpdateEntity updateEntity);

    void confirmStudentLessonFinalNote(StudentLessonFinalNoteConfirmEntity confirmEntity);

    void updateStudentLessonResitNote(StudentLessonResitNoteUpdateEntity updateEntity);

    void confirmStudentLessonResitNote(StudentLessonResitNoteConfirmEntity confirmEntity);

    void updateStudentLessonNoteStatus(StudentLessonNoteStatusUpdateEntity updateEntity);

    Double getMidtermNoteById(String id);

    boolean isStudentLessonNotesExist(String id);

    boolean isStudentLessonsNotesExist(Long studentId);

    boolean hasTheStudentPassedAllLessons(Long studentId);
}
