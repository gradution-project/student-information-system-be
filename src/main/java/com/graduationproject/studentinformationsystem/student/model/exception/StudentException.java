package com.graduationproject.studentinformationsystem.student.model.exception;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentException extends SisException {

    private StudentException() {
    }

    public static void throwNotExistException(Long studentId) throws SisNotExistException {
        throwNotExistException("STUDENT IS NOT EXIST! studentId:" + studentId);
    }

    public static void throwAlreadyActivatedException(Long studentId) {
        throwAlreadyActivatedException("STUDENT IS ALREADY ACTIVATED! studentId:" + studentId);
    }

    public static void throwAlreadyPassivatedException(Long studentId) {
        throwAlreadyPassivatedException("STUDENT IS ALREADY PASSIVATED! studentId:" + studentId);
    }

    public static void throwAlreadyDeletedException(Long studentId) {
        throwAlreadyDeletedException("STUDENT IS ALREADY DELETED! studentId:" + studentId);
    }

    public static void throwAlreadyGraduatedException(Long studentId) {
        try {
            throw new SisAlreadyException("STUDENT IS ALREADY GRADUATED! studentId:" + studentId);
        } catch (SisAlreadyException ignored) {
        }
    }
}
