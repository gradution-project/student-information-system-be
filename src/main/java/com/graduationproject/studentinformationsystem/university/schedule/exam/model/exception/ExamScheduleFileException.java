package com.graduationproject.studentinformationsystem.university.schedule.exam.model.exception;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisFileTypeException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;

public class ExamScheduleFileException extends SisException {

    private ExamScheduleFileException() {
    }

    public static void throwNotExistException(final Long departmentId) throws SisNotExistException {
        SisException.throwNotExistException("EXAM SCHEDULE FILE IS NOT EXIST! departmentId:" + departmentId);
    }

    public static void throwNotExistException(final String fileId) throws SisNotExistException {
        SisException.throwNotExistException("EXAM SCHEDULE FILE IS NOT EXIST! fileId:" + fileId);
    }

    public static void throwAlreadyExistException(final String fileId) throws SisAlreadyException {
        SisException.throwAlreadyException("EXAM SCHEDULE FILE IS ALREADY EXIST! fileId:" + fileId);
    }

    public static void throwFileTypeNotPdfException(final String fileType) throws SisFileTypeException {
        SisException.throwFileTypeException("EXAM SCHEDULE FILE TYPE IS NOT PDF! fileType:" + fileType);
    }
}
