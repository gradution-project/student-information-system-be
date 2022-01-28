package com.graduationproject.studentinformationsystem.university.student.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.student.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentInfoDetailResponse;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentInfoResponse;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.university.student.model.enums.StudentStatus;

import java.util.List;

public interface StudentService {

    List<StudentInfoResponse> getAllStudentsByStatus(StudentStatus status);

    StudentInfoDetailResponse getStudentDetailById(Long studentId)
            throws SisNotExistException;

    StudentInfoDetailResponse saveStudent(StudentSaveRequest saveRequest);

    StudentAcademicInfoResponse updateStudentAcademicInfo(Long studentId,
                                                          StudentAcademicInfoUpdateRequest academicInfoUpdateRequest)
            throws SisNotExistException;

    StudentPersonalInfoResponse updateStudentPersonalInfo(Long studentId,
                                                          StudentPersonalInfoUpdateRequest personalInfoUpdateRequest)
            throws SisNotExistException;

    StudentInfoResponse deleteStudent(StudentDeleteRequest deleteRequest)
            throws SisNotExistException, SisAlreadyException;

    StudentInfoResponse passivateStudent(StudentPassivateRequest passivateRequest)
            throws SisNotExistException, SisAlreadyException;

    StudentInfoResponse activateStudent(StudentActivateRequest activateRequest)
            throws SisNotExistException, SisAlreadyException;

    StudentInfoResponse graduateStudent(StudentGraduateRequest graduateRequest)
            throws SisNotExistException, SisAlreadyException;
}
