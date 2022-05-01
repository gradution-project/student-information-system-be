package com.graduationproject.studentinformationsystem.university.student.service;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentInfoDetailResponse;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentInfoResponse;

public interface StudentOutService {

    StudentInfoDetailResponse getStudentInfoDetailResponse(Long studentId) throws SisNotExistException;

    StudentInfoResponse getStudentInfoResponse(Long studentId);

    StudentInfoResponse graduateStudent(Long studentId, SisOperationInfoRequest operationInfoRequest)
            throws SisNotExistException, SisAlreadyException;

    void ifStudentIsNotExistThrowNotExistException(Long studentId) throws SisNotExistException;
}
