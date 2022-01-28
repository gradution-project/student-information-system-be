package com.graduationproject.studentinformationsystem.university.teacher.model.exception;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;

public class TeacherException extends SisException {

    private TeacherException() {
    }

    public static void throwNotExistException(final Long teacherId) throws SisNotExistException {
        throwNotExistException("TEACHER IS NOT EXIST! teacherId:" + teacherId);
    }

    public static void throwAlreadyActiveException(final Long teacherId) throws SisAlreadyException {
        throwAlreadyException("TEACHER IS ALREADY ACTIVE! teacherId:" + teacherId);
    }

    public static void throwAlreadyPassiveException(final Long teacherId) throws SisAlreadyException {
        throwAlreadyException("TEACHER IS ALREADY PASSIVE! teacherId:" + teacherId);
    }

    public static void throwAlreadyDeletedException(final Long teacherId) throws SisAlreadyException {
        throwAlreadyException("TEACHER IS ALREADY DELETED! teacherId:" + teacherId);
    }
}
