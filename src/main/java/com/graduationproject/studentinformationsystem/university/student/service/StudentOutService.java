package com.graduationproject.studentinformationsystem.university.student.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentInfoResponse;

public interface StudentOutService {

    StudentInfoResponse getStudentInfoResponse(Long studentId);

    void ifStudentIsNotExistThrowNotExistException(Long studentId) throws SisNotExistException;
}
