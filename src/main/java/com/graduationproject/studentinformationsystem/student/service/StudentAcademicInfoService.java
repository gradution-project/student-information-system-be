package com.graduationproject.studentinformationsystem.student.service;

import com.graduationproject.studentinformationsystem.student.model.dto.request.StudentAcademicInfoRequest;
import com.graduationproject.studentinformationsystem.student.model.dto.response.StudentAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.student.model.enums.StudentStatus;

import java.util.List;

public interface StudentAcademicInfoService {

    List<StudentAcademicInfoResponse> getAllStudentAcademicInfosByStatus(StudentStatus status);

    StudentAcademicInfoResponse getStudentAcademicInfoById(Long studentId);

    void saveStudentAcademicInfo(Long studentId, String studentEmail, StudentAcademicInfoRequest academicInfoRequest);

    StudentAcademicInfoResponse updateStudentAcademicInfo(Long studentId, StudentAcademicInfoRequest academicInfoRequest);

    void deleteStudentAcademicInfo(Long studentId);

    void passivateStudentAcademicInfo(Long studentId);

    void activateStudentAcademicInfo(Long studentId);

    void graduateStudentAcademicInfo(Long studentId);

    List<Long> getAllStudentIdsByDepartmentId(Long departmentId);

    boolean isStudentExist(Long studentId);
}
