package com.graduationproject.studentinformationsystem.university.note.service.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.lesson.common.service.LessonOutService;
import com.graduationproject.studentinformationsystem.university.note.model.dto.converter.StudentLessonNoteInfoConverter;
import com.graduationproject.studentinformationsystem.university.note.model.dto.request.StudentLessonFinalNoteUpdateRequest;
import com.graduationproject.studentinformationsystem.university.note.model.dto.request.StudentLessonMidtermNoteUpdateRequest;
import com.graduationproject.studentinformationsystem.university.note.model.dto.request.StudentLessonResitNoteUpdateRequest;
import com.graduationproject.studentinformationsystem.university.note.model.dto.response.StudentLessonNoteResponse;
import com.graduationproject.studentinformationsystem.university.note.model.entity.StudentLessonFinalNoteUpdateEntity;
import com.graduationproject.studentinformationsystem.university.note.model.entity.StudentLessonMidtermNoteUpdateEntity;
import com.graduationproject.studentinformationsystem.university.note.model.entity.StudentLessonNoteEntity;
import com.graduationproject.studentinformationsystem.university.note.model.entity.StudentLessonResitNoteUpdateEntity;
import com.graduationproject.studentinformationsystem.university.note.model.enums.StudentLessonNoteStatus;
import com.graduationproject.studentinformationsystem.university.note.model.exception.StudentLessonNoteException;
import com.graduationproject.studentinformationsystem.university.note.repository.StudentLessonNoteRepository;
import com.graduationproject.studentinformationsystem.university.note.service.StudentLessonNoteService;
import com.graduationproject.studentinformationsystem.university.student.service.StudentOutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentLessonNoteServiceImpl implements StudentLessonNoteService {

    private final LessonOutService lessonOutService;
    private final StudentOutService studentOutService;

    private final StudentLessonNoteRepository lessonNoteRepository;
    private final StudentLessonNoteInfoConverter lessonNoteInfoConverter;

    @Override
    public List<StudentLessonNoteResponse> getAllStudentsLessonsNotesByLessonId(final Long lessonId)
            throws SisNotExistException {

        ifLessonIsNotExistThrowNotExistException(lessonId);

        final List<StudentLessonNoteEntity> entities = lessonNoteRepository.getAllStudentsLessonsNotesByLessonId(lessonId);
        return lessonNoteInfoConverter.entitiesToResponses(entities);
    }

    @Override
    public List<StudentLessonNoteResponse> getAllStudentLessonsNotesByStudentId(final Long studentId)
            throws SisNotExistException {

        ifStudentIsNotExistThrowNotExistException(studentId);

        final List<StudentLessonNoteEntity> entities = lessonNoteRepository.getAllStudentLessonsNotesByStudentId(studentId);
        return lessonNoteInfoConverter.entitiesToResponses(entities);
    }

    @Override
    public StudentLessonNoteResponse updateStudentLessonMidtermNote(final StudentLessonMidtermNoteUpdateRequest updateRequest)
            throws SisNotExistException {

        final String id = updateRequest.getId();

        ifStudentLessonNotesAreNotExistThrowNotExistException(id);

        final StudentLessonMidtermNoteUpdateEntity noteUpdateEntity = lessonNoteInfoConverter
                .generateMidtermNoteUpdateEntity(updateRequest);

        lessonNoteRepository.updateStudentLessonMidtermNote(noteUpdateEntity);

        return getStudentLessonNoteById(id);
    }

    @Override
    public StudentLessonNoteResponse updateStudentLessonFinalNote(final StudentLessonFinalNoteUpdateRequest updateRequest)
            throws SisNotExistException {

        final String id = updateRequest.getId();

        ifStudentLessonNotesAreNotExistThrowNotExistException(id);

        final Double midtermNote = lessonNoteRepository.getMidtermNoteById(id);

        Double meanOfNote;
        StudentLessonNoteStatus status;

        final Double finalNote = updateRequest.getFinalNote();
        if (finalNote >= 50.0) {

            meanOfNote = calculateMeanOfNoteWithFinalNote(midtermNote, finalNote);

            if (meanOfNote >= 60.0) {
                status = StudentLessonNoteStatus.PASSED;
            } else {
                status = StudentLessonNoteStatus.UNFINALISED;
            }
        } else {
            meanOfNote = calculateMeanOfNoteWithFinalNote(midtermNote, finalNote);
            status = StudentLessonNoteStatus.UNFINALISED;
        }

        final StudentLessonFinalNoteUpdateEntity noteUpdateEntity = lessonNoteInfoConverter
                .generateFinalNoteUpdateEntity(meanOfNote, status, updateRequest);

        lessonNoteRepository.updateStudentLessonFinalNote(noteUpdateEntity);

        return getStudentLessonNoteById(id);
    }

    @Override
    public StudentLessonNoteResponse updateStudentLessonResitNote(final StudentLessonResitNoteUpdateRequest updateRequest)
            throws SisNotExistException {

        final String id = updateRequest.getId();

        ifStudentLessonNotesAreNotExistThrowNotExistException(id);

        final Double midtermNote = lessonNoteRepository.getMidtermNoteById(id);

        Double meanOfNote;
        StudentLessonNoteStatus status;

        final Double resitNote = updateRequest.getResitNote();
        if (resitNote >= 50.0) {

            meanOfNote = calculateMeanOfNoteWithResitNote(midtermNote, resitNote);

            if (meanOfNote >= 60.0) {
                status = StudentLessonNoteStatus.PASSED;
            } else {
                status = StudentLessonNoteStatus.FAILED;
            }
        } else {
            meanOfNote = calculateMeanOfNoteWithResitNote(midtermNote, resitNote);
            status = StudentLessonNoteStatus.FAILED;
        }

        final StudentLessonResitNoteUpdateEntity noteUpdateEntity = lessonNoteInfoConverter
                .generateResitNoteUpdateEntity(meanOfNote, status, updateRequest);

        lessonNoteRepository.updateStudentLessonResitNote(noteUpdateEntity);

        return getStudentLessonNoteById(id);
    }


    private StudentLessonNoteResponse getStudentLessonNoteById(final String id) {
        final StudentLessonNoteEntity entity = lessonNoteRepository.getStudentLessonNotesById(id);
        return lessonNoteInfoConverter.entityToResponse(entity);
    }

    protected final Double calculateMeanOfNoteWithFinalNote(final Double midtermNote, Double finalNote) {
        return midtermNote * 0.4 + finalNote * 0.6;
    }

    protected final Double calculateMeanOfNoteWithResitNote(final Double midtermNote, Double finalNote) {
        return midtermNote * 0.4 + finalNote * 0.6;
    }


    /**
     * Throw Exceptions
     */

    private void ifLessonIsNotExistThrowNotExistException(final Long lessonId) throws SisNotExistException {
        lessonOutService.ifLessonIsNotExistThrowNotExistException(lessonId);
    }

    private void ifStudentIsNotExistThrowNotExistException(final Long studentId) throws SisNotExistException {
        studentOutService.ifStudentIsNotExistThrowNotExistException(studentId);
    }

    private void ifStudentLessonNotesAreNotExistThrowNotExistException(final String id) throws SisNotExistException {
        if (!lessonNoteRepository.isStudentLessonNotesExist(id)) {
            StudentLessonNoteException.throwNotExistException(id);
        }
    }
}
