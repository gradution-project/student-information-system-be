package com.graduationproject.studentinformationsystem.student.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.student.model.dto.request.StudentAcademicInfoRequest;
import com.graduationproject.studentinformationsystem.student.model.dto.request.StudentInfoRequest;
import com.graduationproject.studentinformationsystem.student.model.dto.request.StudentPersonalInfoRequest;
import com.graduationproject.studentinformationsystem.student.model.dto.response.StudentAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.student.model.dto.response.StudentInfoDetailResponse;
import com.graduationproject.studentinformationsystem.student.model.dto.response.StudentPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.student.model.dto.response.StudentResponse;
import com.graduationproject.studentinformationsystem.student.model.enums.StudentStatus;

import java.util.List;

public interface StudentService {

    List<StudentResponse> getAllStudentsByStatus(StudentStatus status);

    StudentInfoDetailResponse getStudentDetailById(Long studentId) throws SisNotExistException;

    StudentInfoDetailResponse saveStudent(StudentInfoRequest studentInfoRequest);

    StudentAcademicInfoResponse updateStudentAcademicInfo(Long studentId, StudentAcademicInfoRequest academicInfoRequest) throws SisNotExistException;

    StudentPersonalInfoResponse updateStudentPersonalInfo(Long studentId, StudentPersonalInfoRequest personalInfoRequest) throws SisNotExistException;

    StudentResponse deleteStudent(Long studentId) throws SisNotExistException, SisAlreadyException;

    StudentResponse passivateStudent(Long studentId) throws SisNotExistException, SisAlreadyException;

    StudentResponse activateStudent(Long studentId) throws SisNotExistException, SisAlreadyException;

    StudentResponse graduateStudent(Long studentId) throws SisNotExistException, SisAlreadyException;
}
