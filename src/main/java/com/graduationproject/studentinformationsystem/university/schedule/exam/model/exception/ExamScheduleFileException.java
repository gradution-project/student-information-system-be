package com.graduationproject.studentinformationsystem.university.schedule.exam.model.exception;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;

public class ExamScheduleFileException extends SisException {

    private ExamScheduleFileException() {
    }

    public static void throwNotExistException(final Long fileId) throws SisNotExistException {
        throwNotExistException("EXAM SCHEDULE FILE IS NOT EXIST! fileId:" + fileId);
    }

    public static void throwAlreadyActiveException(final Long fileId) throws SisAlreadyException {
        throwAlreadyException("EXAM SCHEDULE FILE IS ALREADY ACTIVE! fileId:" + fileId);
    }

    public static void throwAlreadyPassiveException(final Long fileId) throws SisAlreadyException {
        throwAlreadyException("EXAM SCHEDULE FILE IS ALREADY PASSIVE! fileId:" + fileId);
    }

    public static void throwAlreadyDeletedException(final Long fileId) throws SisAlreadyException {
        throwAlreadyException("EXAM SCHEDULE FILE IS ALREADY DELETED! fileId:" + fileId);
    }
}
