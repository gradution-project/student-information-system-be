package com.graduationproject.studentinformationsystem.university.lesson.student.common.model.exception;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;

public class StudentLessonException extends SisException {

    private StudentLessonException() {
    }

    public static void throwAlreadyExistException(final Long studentId, final Long lessonId) throws SisAlreadyException {
        SisException.throwAlreadyException("STUDENT LESSON IS ALREADY EXIST! studentId:" + studentId + " lessonId:" + lessonId);
    }

    public static void throwNotExistException(final Long studentId) throws SisNotExistException {
        SisException.throwNotExistException("STUDENT LESSONS IS NOT EXIST! studentId:" + studentId);
    }
}
