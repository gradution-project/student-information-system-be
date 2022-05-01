package com.graduationproject.studentinformationsystem.university.note.model.exception;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentLessonNoteException extends SisException {

    private StudentLessonNoteException() {
    }

    public static void throwNotExistExceptionById(final String id) throws SisNotExistException {
        SisException.throwNotExistException("STUDENT LESSON NOTES ARE NOT EXIST! id:" + id);
    }

    public static void throwNotExistExceptionByStudentId(final Long studentId) throws SisNotExistException {
        SisException.throwNotExistException("STUDENT LESSON NOTES ARE NOT EXIST! studentId:" + studentId);
    }

    public static void throwAlreadyExistException(final Long studentId) throws SisAlreadyException {
        SisException.throwAlreadyException("STUDENT'S FAILED OR UNFINALISED LESSON NOTE IS EXIST! studentId:" + studentId);
    }
}
