package com.graduationproject.studentinformationsystem.university.schedule.lesson.model.exception;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisFileTypeException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;

public class LessonScheduleFileException extends SisException {

    private LessonScheduleFileException() {
    }

    public static void throwNotExistException(final Long departmentId) throws SisNotExistException {
        SisException.throwNotExistException("LESSON SCHEDULE FILE IS NOT EXIST! departmentId:" + departmentId);
    }

    public static void throwNotExistException(final String fileId) throws SisNotExistException {
        SisException.throwNotExistException("LESSON SCHEDULE FILE IS NOT EXIST! fileId:" + fileId);
    }

    public static void throwAlreadyExistException(final String fileId) throws SisAlreadyException {
        SisException.throwAlreadyException("LESSON SCHEDULE FILE IS ALREADY EXIST! fileId:" + fileId);
    }

    public static void throwFileTypeNotPdfException(final String fileType) throws SisFileTypeException {
        SisException.throwFileTypeException("LESSON SCHEDULE FILE TYPE IS NOT PDF! fileType:" + fileType);
    }
}
