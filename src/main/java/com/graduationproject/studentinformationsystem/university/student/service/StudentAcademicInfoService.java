package com.graduationproject.studentinformationsystem.university.student.service;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.university.student.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.student.model.enums.StudentStatus;

import java.util.List;

public interface StudentAcademicInfoService {

    List<StudentAcademicInfoResponse> getAllStudentAcademicInfosByStatus(StudentStatus status);

    StudentAcademicInfoResponse getStudentAcademicInfoById(Long studentId);

    void saveStudentAcademicInfo(Long studentId,
                                 String studentEmail,
                                 StudentAcademicInfoRequest academicInfoRequest,
                                 SisOperationInfoRequest operationInfoRequest);

    StudentAcademicInfoResponse updateStudentAcademicInfo(Long studentId,
                                                          StudentUpdateAcademicInfoRequest updateAcademicInfoRequest);

    void deleteStudentAcademicInfo(StudentDeleteRequest deleteRequest);

    void passivateStudentAcademicInfo(StudentPassivateRequest passivateRequest);

    void activateStudentAcademicInfo(StudentActivateRequest activateRequest);

    void graduateStudentAcademicInfo(StudentGraduateRequest graduateRequest);

    List<Long> getAllStudentIdsByDepartmentId(Long departmentId);

    boolean isStudentExist(Long studentId);

    boolean isStudentDeleted(Long studentId);

    boolean isStudentPassive(Long studentId);

    boolean isStudentActive(Long studentId);

    boolean isStudentGraduated(Long studentId);
}
