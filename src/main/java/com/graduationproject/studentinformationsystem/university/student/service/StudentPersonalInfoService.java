package com.graduationproject.studentinformationsystem.university.student.service;

import com.graduationproject.studentinformationsystem.university.student.model.dto.request.StudentPersonalInfoRequest;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.university.student.model.enums.StudentStatus;

import java.util.List;

public interface StudentPersonalInfoService {

    List<StudentPersonalInfoResponse> getAllStudentPersonalInfosByStatus(StudentStatus status);

    StudentPersonalInfoResponse getStudentPersonalInfoById(Long studentId);

    void saveStudentPersonalInfo(Long studentId, StudentPersonalInfoRequest personalInfoRequest);

    StudentPersonalInfoResponse updateStudentPersonalInfo(Long studentId, StudentPersonalInfoRequest personalInfoRequest);

    void deleteStudentPersonalInfo(Long studentId);

    void passivateStudentPersonalInfo(Long studentId);

    void activateStudentPersonalInfo(Long studentId);

    void graduateStudentPersonalInfo(Long studentId);
}
