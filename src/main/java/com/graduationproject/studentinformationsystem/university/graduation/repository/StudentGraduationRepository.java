package com.graduationproject.studentinformationsystem.university.graduation.repository;

import com.graduationproject.studentinformationsystem.university.graduation.model.entity.StudentGraduationEntity;
import com.graduationproject.studentinformationsystem.university.graduation.model.enums.StudentGraduationStatus;

import java.util.List;

public interface StudentGraduationRepository {

    List<StudentGraduationEntity> getAllStudentsGraduationsByStatus(StudentGraduationStatus status);

    StudentGraduationEntity getStudentGraduationDetailByGraduationId(String graduationId);

    void saveStudentGraduation(StudentGraduationEntity graduationEntity);

    void updateStudentGraduationStatus(StudentGraduationEntity graduationEntity);

    boolean isStudentGraduationExist(String graduationId);

    boolean isStudentGraduationWaiting(String graduationId);

    boolean isStudentGraduationApproved(String graduationId);

    boolean isStudentGraduationRejected(String graduationId);

    boolean isStudentGraduationConfirmed(String graduationId);

    boolean isStudentGraduationUnconfirmed(String graduationId);

    String getGraduationId(Long studentId);

    Long getStudentId(String graduationId);
}
