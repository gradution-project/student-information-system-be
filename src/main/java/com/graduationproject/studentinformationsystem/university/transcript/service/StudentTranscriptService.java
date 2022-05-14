package com.graduationproject.studentinformationsystem.university.transcript.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisProcessException;
import com.graduationproject.studentinformationsystem.university.transcript.model.dto.response.StudentTranscriptResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface StudentTranscriptService {

    StudentTranscriptResponse getStudentTranscriptDetailByStudentId(Long studentId) throws SisNotExistException, SisAlreadyException;

    void exportStudentTranscriptFileByStudentId(Long studentId, HttpServletResponse httpServletResponse)
            throws SisNotExistException, SisAlreadyException, IOException, SisProcessException;
}
