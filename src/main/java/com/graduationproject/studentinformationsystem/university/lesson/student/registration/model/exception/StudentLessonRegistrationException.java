package com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.exception;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;

public class StudentLessonRegistrationException extends SisException {

    private StudentLessonRegistrationException() {
    }

    public static void throwNotExistException(final String registrationId) throws SisNotExistException {
        SisException.throwNotExistException("STUDENT LESSON REGISTRATION IS NOT EXIST! registrationId:" + registrationId);
    }

    public static void throwAlreadyWaitingException(final String registrationId) throws SisAlreadyException {
        SisException.throwAlreadyException("STUDENT LESSON REGISTRATION IS ALREADY WAITING! registrationId:" + registrationId);
    }

    public static void throwAlreadyApprovedException(final String registrationId) throws SisAlreadyException {
        SisException.throwAlreadyException("STUDENT LESSON REGISTRATION IS ALREADY APPROVED! registrationId:" + registrationId);
    }

    public static void throwAlreadyRejectedException(final String registrationId) throws SisAlreadyException {
        SisException.throwAlreadyException("STUDENT LESSON REGISTRATION IS ALREADY REJECTED! registrationId:" + registrationId);
    }
}
