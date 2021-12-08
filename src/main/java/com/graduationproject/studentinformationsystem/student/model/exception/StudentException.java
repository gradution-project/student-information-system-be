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

    public static void throwAlreadyActiveException(Long studentId) throws SisAlreadyException {
        throwAlreadyException("STUDENT IS ALREADY ACTIVE! studentId:" + studentId);
    }

    public static void throwAlreadyPassiveException(Long studentId) throws SisAlreadyException {
        throwAlreadyException("STUDENT IS ALREADY PASSIVE! studentId:" + studentId);
    }

    public static void throwAlreadyDeletedException(Long studentId) throws SisAlreadyException {
        throwAlreadyException("STUDENT IS ALREADY DELETED! studentId:" + studentId);
    }

    public static void throwAlreadyGraduatedException(Long studentId) throws SisAlreadyException {
        throwAlreadyException("STUDENT IS ALREADY GRADUATED! studentId:" + studentId);
    }
}
