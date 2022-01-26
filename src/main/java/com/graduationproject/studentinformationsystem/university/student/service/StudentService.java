package com.graduationproject.studentinformationsystem.university.student.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.student.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentInfoDetailResponse;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentResponse;
import com.graduationproject.studentinformationsystem.university.student.model.enums.StudentStatus;

import java.util.List;

public interface StudentService {

    List<StudentResponse> getAllStudentsByStatus(StudentStatus status);

    StudentInfoDetailResponse getStudentDetailById(Long studentId)
            throws SisNotExistException;

    StudentInfoDetailResponse saveStudent(StudentSaveInfoRequest saveInfoRequest);

    StudentAcademicInfoResponse updateStudentAcademicInfo(Long studentId,
                                                          StudentUpdateAcademicInfoRequest updateAcademicInfoRequest)
            throws SisNotExistException;

    StudentPersonalInfoResponse updateStudentPersonalInfo(Long studentId,
                                                          StudentUpdatePersonalInfoRequest updatePersonalInfoRequest)
            throws SisNotExistException;

    StudentResponse deleteStudent(StudentDeleteRequest deleteRequest)
            throws SisNotExistException, SisAlreadyException;

    StudentResponse passivateStudent(StudentPassivateRequest passivateRequest)
            throws SisNotExistException, SisAlreadyException;

    StudentResponse activateStudent(StudentActivateRequest activateRequest)
            throws SisNotExistException, SisAlreadyException;

    StudentResponse graduateStudent(StudentGraduateRequest graduateRequest)
            throws SisNotExistException, SisAlreadyException;
}
