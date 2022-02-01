package com.graduationproject.studentinformationsystem.university.student.repository;

import com.graduationproject.studentinformationsystem.university.student.model.entity.StudentAcademicInfoEntity;
import com.graduationproject.studentinformationsystem.university.student.model.enums.StudentStatus;

import java.util.List;

public interface StudentAcademicInfoRepository {

    List<StudentAcademicInfoEntity> getAllStudentAcademicInfosByStatus(StudentStatus status);

    StudentAcademicInfoEntity getStudentAcademicInfoById(Long studentId);

    void saveStudentAcademicInfo(StudentAcademicInfoEntity academicInfoEntity);

    void updateStudentAcademicInfo(StudentAcademicInfoEntity academicInfoEntity);

    void updateStudentAcademicInfoStatus(StudentAcademicInfoEntity academicInfoEntity);

    List<Long> getAllStudentIdsByDepartmentId(Long departmentId);

    boolean isStudentExist(Long studentId);

    boolean isStudentDeleted(Long studentId);

    boolean isStudentPassive(Long studentId);

    boolean isStudentActive(Long studentId);

    boolean isStudentGraduated(Long studentId);
}
