package com.graduationproject.studentinformationsystem.university.transcript.model.exception;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentTranscriptException extends SisException {

    private StudentTranscriptException() {
    }

    public static void throwNotExistsException(final Long studentId) throws SisAlreadyException {
        SisException.throwAlreadyException("STUDENT'S PASSED OR FAILED LESSON NOTES ARE NOT EXIST! studentId:" + studentId);
    }
}
