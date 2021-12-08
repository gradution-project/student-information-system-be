package com.graduationproject.studentinformationsystem.teacher.model.exception;

import com.graduationproject.studentinformationsystem.common.util.exception.SisException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;

public class TeacherException extends SisException {

    private TeacherException() {
    }

    public static void throwNotExistException(Long teacherId) throws SisNotExistException {
        throwNotExistException("TEACHER IS NOT EXIST! teacherId:" + teacherId);
    }

    public static void throwAlreadyActivatedException(Long teacherId) {
        throwAlreadyActivatedException("TEACHER IS ALREADY ACTIVATED! teacherId:" + teacherId);
    }

    public static void throwAlreadyPassivatedException(Long teacherId) {
        throwAlreadyPassivatedException("TEACHER IS ALREADY PASSIVATED! teacherId:" + teacherId);
    }

    public static void throwAlreadyDeletedException(Long teacherId) {
        throwAlreadyDeletedException("TEACHER IS ALREADY DELETED! teacherId:" + teacherId);
    }
}
