package com.graduationproject.studentinformationsystem.teacher.model.exception;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;

public class TeacherException extends SisException {

    private TeacherException() {
    }

    public static void throwNotExistException(Long teacherId) throws SisNotExistException {
        throwNotExistException("TEACHER IS NOT EXIST! teacherId:" + teacherId);
    }

    public static void throwAlreadyActivatedException(Long teacherId) throws SisAlreadyException {
        throwAlreadyException("TEACHER IS ALREADY ACTIVATED! teacherId:" + teacherId);
    }

    public static void throwAlreadyPassivatedException(Long teacherId) throws SisAlreadyException {
        throwAlreadyException("TEACHER IS ALREADY PASSIVATED! teacherId:" + teacherId);
    }

    public static void throwAlreadyDeletedException(Long teacherId) throws SisAlreadyException {
        throwAlreadyException("TEACHER IS ALREADY DELETED! teacherId:" + teacherId);
    }
}
