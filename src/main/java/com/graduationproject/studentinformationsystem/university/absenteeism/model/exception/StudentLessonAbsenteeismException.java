package com.graduationproject.studentinformationsystem.university.absenteeism.model.exception;

import com.graduationproject.studentinformationsystem.common.util.exception.SisException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentLessonAbsenteeismException extends SisException {

    private StudentLessonAbsenteeismException() {
    }

    public static void throwNotExistExceptionById(final String id) throws SisNotExistException {
        SisException.throwNotExistException("STUDENT LESSON ABSENTEEISM ARE NOT EXIST! id:" + id);
    }

    public static void throwNotExistExceptionByStudentId(final Long studentId) throws SisNotExistException {
        SisException.throwNotExistException("STUDENT LESSON ABSENTEEISM ARE NOT EXIST! studentId:" + studentId);
    }
}
