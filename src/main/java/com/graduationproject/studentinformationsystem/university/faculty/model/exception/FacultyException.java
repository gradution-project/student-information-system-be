package com.graduationproject.studentinformationsystem.university.faculty.model.exception;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;

public class FacultyException extends SisException {

    private FacultyException() {
    }

    public static void throwNotExistException(final Long facultyId) throws SisNotExistException {
        throwNotExistException("FACULTY IS NOT EXIST! facultyId:" + facultyId);
    }

    public static void throwAlreadyActiveException(final Long facultyId) throws SisAlreadyException {
        throwAlreadyException("FACULTY IS ALREADY ACTIVE! facultyId:" + facultyId);
    }

    public static void throwAlreadyPassiveException(final Long facultyId) throws SisAlreadyException {
        throwAlreadyException("FACULTY IS ALREADY PASSIVE! facultyId:" + facultyId);
    }

    public static void throwAlreadyDeletedException(final Long facultyId) throws SisAlreadyException {
        throwAlreadyException("FACULTY IS ALREADY DELETED! facultyId:" + facultyId);
    }
}
