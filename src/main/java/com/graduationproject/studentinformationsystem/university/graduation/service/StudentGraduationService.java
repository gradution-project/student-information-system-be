package com.graduationproject.studentinformationsystem.university.graduation.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.graduation.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.graduation.model.dto.response.StudentGraduationResponse;
import com.graduationproject.studentinformationsystem.university.graduation.model.enums.StudentGraduationStatus;

import java.util.List;

public interface StudentGraduationService {

    List<StudentGraduationResponse> getAllStudentsGraduationsByStatus(StudentGraduationStatus status);

    StudentGraduationResponse getStudentGraduationDetailByGraduationId(String graduationId)
            throws SisNotExistException;

    StudentGraduationResponse saveStudentGraduation(StudentGraduationSaveRequest saveRequest)
            throws SisAlreadyException, SisNotExistException;

    StudentGraduationResponse approveStudentGraduation(StudentGraduationApproveRequest approveRequest)
            throws SisAlreadyException, SisNotExistException;

    StudentGraduationResponse rejectStudentGraduation(StudentGraduationRejectRequest rejectRequest)
            throws SisAlreadyException, SisNotExistException;

    StudentGraduationResponse confirmStudentGraduation(StudentGraduationConfirmRequest confirmRequest)
            throws SisAlreadyException, SisNotExistException;

    StudentGraduationResponse unconfirmStudentGraduation(StudentGraduationUnconfirmRequest unconfirmRequest)
            throws SisAlreadyException, SisNotExistException;

    boolean isStudentGraduationEnabled(Long studentId) throws SisAlreadyException, SisNotExistException;
}
