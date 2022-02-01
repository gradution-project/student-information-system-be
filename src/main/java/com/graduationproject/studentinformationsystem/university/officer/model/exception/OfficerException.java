package com.graduationproject.studentinformationsystem.university.officer.model.exception;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;

public class OfficerException extends SisException {

    private OfficerException() {
    }

    public static void throwNotExistException(final Long officerId) throws SisNotExistException {
        throwNotExistException("OFFICER IS NOT EXIST! officerId:" + officerId);
    }

    public static void throwAlreadyActiveException(final Long officerId) throws SisAlreadyException {
        throwAlreadyException("OFFICER IS ALREADY ACTIVE! officerId:" + officerId);
    }

    public static void throwAlreadyPassiveException(final Long officerId) throws SisAlreadyException {
        throwAlreadyException("OFFICER IS ALREADY PASSIVE! officerId:" + officerId);
    }

    public static void throwAlreadyDeletedException(final Long officerId) throws SisAlreadyException {
        throwAlreadyException("OFFICER IS ALREADY DELETED! officerId:" + officerId);
    }
}
