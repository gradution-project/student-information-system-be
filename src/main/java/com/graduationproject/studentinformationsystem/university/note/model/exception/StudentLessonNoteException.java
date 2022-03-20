package com.graduationproject.studentinformationsystem.university.note.model.exception;

import com.graduationproject.studentinformationsystem.common.util.exception.SisException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentLessonNoteException extends SisException {

    private StudentLessonNoteException() {
    }

    public static void throwNotExistException(final String id) throws SisNotExistException {
        SisException.throwNotExistException("STUDENT LESSON NOTES ARE NOT EXIST! id:" + id);
    }
}