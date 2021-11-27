package com.graduationproject.studentinformationsystem.student.service;

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

    StudentInfoDetailResponse getStudentDetailById(Long studentId);

    StudentInfoDetailResponse saveStudent(StudentInfoRequest studentInfoRequest);

    StudentAcademicInfoResponse updateStudentAcademicInfo(Long studentId, StudentAcademicInfoRequest academicInfoRequest);

    StudentPersonalInfoResponse updateStudentPersonalInfo(Long studentId, StudentPersonalInfoRequest personalInfoRequest);

    StudentResponse deleteStudent(Long studentId);

    StudentResponse passivateStudent(Long studentId);

    StudentResponse activateStudent(Long studentId);

    StudentResponse graduateStudent(Long studentId);
}
