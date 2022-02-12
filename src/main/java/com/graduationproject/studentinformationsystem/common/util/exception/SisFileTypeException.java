package com.graduationproject.studentinformationsystem.common.util.exception;

import org.apache.tomcat.util.http.fileupload.FileUploadException;

import java.io.Serial;

public class SisFileTypeException extends FileUploadException {

    @Serial
    private static final long serialVersionUID = 8100917602219119429L;

    public SisFileTypeException(final String message) {
        super(message);
    }
}
