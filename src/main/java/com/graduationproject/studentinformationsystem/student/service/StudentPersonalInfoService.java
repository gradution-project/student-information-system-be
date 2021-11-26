package com.graduationproject.studentinformationsystem.student.service;

import com.graduationproject.studentinformationsystem.student.model.dto.request.StudentPersonalInfoRequest;
import com.graduationproject.studentinformationsystem.student.model.dto.response.StudentPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.student.model.enums.StudentStatus;

import java.util.List;

public interface StudentPersonalInfoService {

    List<StudentPersonalInfoResponse> getAllStudentPersonalInfosByStatus(StudentStatus status);

    StudentPersonalInfoResponse getStudentPersonalInfoById(Long studentId);

    StudentPersonalInfoResponse saveStudentPersonalInfo(Long studentId,
                                                        StudentPersonalInfoRequest personalInfoRequest);

    StudentPersonalInfoResponse updateStudentPersonalInfo(Long studentId,
                                                          StudentPersonalInfoRequest personalInfoRequest);

    void deleteStudentPersonalInfo(Long studentId);

    void passivateStudentPersonalInfo(Long studentId);

    void activateStudentPersonalInfo(Long studentId);

    void graduateStudentPersonalInfo(Long studentId);
}
