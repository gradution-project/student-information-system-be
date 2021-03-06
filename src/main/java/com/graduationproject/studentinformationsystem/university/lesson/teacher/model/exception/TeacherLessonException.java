package com.graduationproject.studentinformationsystem.university.lesson.teacher.model.exception;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;

public class TeacherLessonException extends SisException {

    private TeacherLessonException() {
    }

    public static void throwAlreadyExistException(final Long teacherId, final Long lessonId) throws SisAlreadyException {
        throwAlreadyException("LESSON IS ALREADY EXIST! teacherId:" + teacherId + " lessonId:" + lessonId);
    }

    public static void throwNotExistException(final Long teacherId, final Long lessonId) throws SisNotExistException {
        throwNotExistException("LESSON IS NOT EXIST! teacherId:" + teacherId + " lessonId:" + lessonId);
    }
}
