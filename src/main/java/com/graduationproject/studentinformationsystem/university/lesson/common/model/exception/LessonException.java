package com.graduationproject.studentinformationsystem.university.lesson.common.model.exception;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;

public class LessonException extends SisException {

    private LessonException() {
    }

    public static void throwNotExistException(final Long lessonId) throws SisNotExistException {
        throwNotExistException("LESSON IS NOT EXIST! lessonId:" + lessonId);
    }

    public static void throwAlreadyActiveException(final Long lessonId) throws SisAlreadyException {
        throwAlreadyException("LESSON IS ALREADY ACTIVE! lessonId:" + lessonId);
    }

    public static void throwAlreadyPassiveException(final Long lessonId) throws SisAlreadyException {
        throwAlreadyException("LESSON IS ALREADY PASSIVE! lessonId:" + lessonId);
    }

    public static void throwAlreadyDeletedException(final Long lessonId) throws SisAlreadyException {
        throwAlreadyException("LESSON IS ALREADY DELETED! lessonId:" + lessonId);
    }
}
