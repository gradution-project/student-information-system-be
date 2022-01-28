package com.graduationproject.studentinformationsystem.university.student.service;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.university.student.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.university.student.model.enums.StudentStatus;

import java.util.List;

public interface StudentPersonalInfoService {

    List<StudentPersonalInfoResponse> getAllStudentPersonalInfosByStatus(StudentStatus status);

    StudentPersonalInfoResponse getStudentPersonalInfoById(Long studentId);

    void saveStudentPersonalInfo(Long studentId,
                                 StudentPersonalInfoRequest personalInfoRequest,
                                 SisOperationInfoRequest operationInfoRequest);

    StudentPersonalInfoResponse updateStudentPersonalInfo(Long studentId,
                                                          StudentPersonalInfoUpdateRequest personalInfoUpdateRequest);

    void deleteStudentPersonalInfo(StudentDeleteRequest deleteRequest);

    void passivateStudentPersonalInfo(StudentPassivateRequest passivateRequest);

    void activateStudentPersonalInfo(StudentActivateRequest activateRequest);

    void graduateStudentPersonalInfo(StudentGraduateRequest graduateRequest);
}
