package com.graduationproject.studentinformationsystem.university.note.service.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.lesson.common.service.LessonOutService;
import com.graduationproject.studentinformationsystem.university.note.model.dto.converter.StudentLessonNoteInfoConverter;
import com.graduationproject.studentinformationsystem.university.note.model.dto.request.StudentsLessonFinalNotesUpdateRequest;
import com.graduationproject.studentinformationsystem.university.note.model.dto.request.StudentsLessonMidtermNotesUpdateRequest;
import com.graduationproject.studentinformationsystem.university.note.model.dto.request.StudentsLessonResitNotesUpdateRequest;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        ifStudentLessonsNotesAreNotExistThrowNotExistException(studentId);

        final List<StudentLessonNoteEntity> entities = lessonNoteRepository.getAllStudentLessonsNotesByStudentId(studentId);
        return lessonNoteInfoConverter.entitiesToResponses(entities);
    }

    @Override
    public List<StudentLessonNoteResponse> updateStudentsLessonMidtermNotes(final StudentsLessonMidtermNotesUpdateRequest updateRequest)
            throws SisNotExistException {

        final Map<String, Double> midtermNoteIdsAndNotes = updateRequest.getMidtermNoteIdsAndNotes();
        checkBeforeUpdateNotes(midtermNoteIdsAndNotes);

        final List<StudentLessonNoteResponse> studentLessonNoteResponses = new ArrayList<>();
        for (Map.Entry<String, Double> noteIdAndNote : midtermNoteIdsAndNotes.entrySet()) {
            final String lessonNoteId = noteIdAndNote.getKey();
            final Double midtermNote = noteIdAndNote.getValue();
            final StudentLessonMidtermNoteUpdateEntity noteUpdateEntity = lessonNoteInfoConverter
                    .generateMidtermNoteUpdateEntity(lessonNoteId, midtermNote, updateRequest.getOperationInfoRequest());

            lessonNoteRepository.updateStudentLessonMidtermNote(noteUpdateEntity);

            studentLessonNoteResponses.add(getStudentLessonNoteById(lessonNoteId));
        }

        return studentLessonNoteResponses;
    }

    @Override
    public List<StudentLessonNoteResponse> updateStudentsLessonFinalNotes(final StudentsLessonFinalNotesUpdateRequest updateRequest)
            throws SisNotExistException {

        final Map<String, Double> finalNoteIdsAndNotes = updateRequest.getFinalNoteIdsAndNotes();
        checkBeforeUpdateNotes(finalNoteIdsAndNotes);

        List<StudentLessonNoteResponse> studentLessonNoteResponses = new ArrayList<>();
        for (Map.Entry<String, Double> noteIdAndNote : finalNoteIdsAndNotes.entrySet()) {
            final String lessonNoteId = noteIdAndNote.getKey();
            final Double midtermNote = lessonNoteRepository.getMidtermNoteById(lessonNoteId);

            Double meanOfNote;
            StudentLessonNoteStatus status;

            final Double finalNote = noteIdAndNote.getValue();
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
                    .generateFinalNoteUpdateEntity(lessonNoteId, finalNote, meanOfNote, status, updateRequest.getOperationInfoRequest());

            lessonNoteRepository.updateStudentLessonFinalNote(noteUpdateEntity);

            studentLessonNoteResponses.add(getStudentLessonNoteById(lessonNoteId));
        }

        return studentLessonNoteResponses;
    }

    @Override
    public List<StudentLessonNoteResponse> updateStudentsLessonResitNotes(final StudentsLessonResitNotesUpdateRequest updateRequest)
            throws SisNotExistException {

        final Map<String, Double> resitNoteIdsAndNotes = updateRequest.getResitNoteIdsAndNotes();
        checkBeforeUpdateNotes(resitNoteIdsAndNotes);

        List<StudentLessonNoteResponse> studentLessonNoteResponses = new ArrayList<>();
        for (Map.Entry<String, Double> noteIdAndNote : resitNoteIdsAndNotes.entrySet()) {
            final String lessonNoteId = noteIdAndNote.getKey();
            final Double midtermNote = lessonNoteRepository.getMidtermNoteById(lessonNoteId);

            Double meanOfNote;
            StudentLessonNoteStatus status;

            final Double resitNote = noteIdAndNote.getValue();
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
                    .generateResitNoteUpdateEntity(lessonNoteId, midtermNote, meanOfNote, status, updateRequest.getOperationInfoRequest());

            lessonNoteRepository.updateStudentLessonResitNote(noteUpdateEntity);

            studentLessonNoteResponses.add(getStudentLessonNoteById(lessonNoteId));
        }

        return studentLessonNoteResponses;
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
     * Checks Before Processing
     */

    private void checkBeforeUpdateNotes(final Map<String, Double> noteIdsAndNotes)
            throws SisNotExistException {

        for (Map.Entry<String, Double> noteIdAndNote : noteIdsAndNotes.entrySet()) {
            final String lessonNoteId = noteIdAndNote.getKey();
            ifStudentLessonNotesAreNotExistThrowNotExistException(lessonNoteId);
        }
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

    private void ifStudentLessonsNotesAreNotExistThrowNotExistException(final Long studentId) throws SisNotExistException {
        if (!lessonNoteRepository.isStudentLessonsNotesExist(studentId)) {
            StudentLessonNoteException.throwNotExistExceptionByStudentId(studentId);
        }
    }

    private void ifStudentLessonNotesAreNotExistThrowNotExistException(final String id) throws SisNotExistException {
        if (!lessonNoteRepository.isStudentLessonNotesExist(id)) {
            StudentLessonNoteException.throwNotExistExceptionById(id);
        }
    }
}
