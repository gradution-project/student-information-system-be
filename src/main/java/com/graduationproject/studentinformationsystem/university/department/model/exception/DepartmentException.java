package com.graduationproject.studentinformationsystem.university.department.model.exception;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;

public class DepartmentException extends SisException {

    private DepartmentException() {
    }

    public static void throwNotExistException(final Long departmentId) throws SisNotExistException {
        throwNotExistException("DEPARTMENT IS NOT EXIST! departmentId:" + departmentId);
    }

    public static void throwAlreadyActiveException(final Long departmentId) throws SisAlreadyException {
        throwAlreadyException("DEPARTMENT IS ALREADY ACTIVE! departmentId:" + departmentId);
    }

    public static void throwAlreadyPassiveException(final Long departmentId) throws SisAlreadyException {
        throwAlreadyException("DEPARTMENT IS ALREADY PASSIVE! departmentId:" + departmentId);
    }

    public static void throwAlreadyDeletedException(final Long departmentId) throws SisAlreadyException {
        throwAlreadyException("DEPARTMENT IS ALREADY DELETED! departmentId:" + departmentId);
    }
}
