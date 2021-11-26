package com.graduationproject.studentinformationsystem.student.repository;

import com.graduationproject.studentinformationsystem.student.model.entity.StudentAcademicInfoEntity;
import com.graduationproject.studentinformationsystem.student.model.enums.StudentStatus;

import java.util.List;

public interface StudentAcademicInfoRepository {

    List<StudentAcademicInfoEntity> getAllStudentAcademicInfosByStatus(StudentStatus status);

    StudentAcademicInfoEntity getStudentAcademicInfoById(Long studentId);

    void saveStudentAcademicInfo(StudentAcademicInfoEntity entity);

    void updateStudentAcademicInfo(StudentAcademicInfoEntity entity);

    void updateStudentAcademicInfoStatus(StudentAcademicInfoEntity entity);

    List<Long> getAllStudentIdsByDepartmentId(Long departmentId);

    boolean isStudentExist(Long studentId);
}
