package com.graduationproject.studentinformationsystem.university.student.repository;

import com.graduationproject.studentinformationsystem.university.student.model.entity.StudentPersonalInfoEntity;
import com.graduationproject.studentinformationsystem.university.student.model.enums.StudentStatus;

import java.util.List;

public interface StudentPersonalInfoRepository {

    List<StudentPersonalInfoEntity> getAllStudentPersonalInfosByStatus(StudentStatus status);

    StudentPersonalInfoEntity getStudentPersonalInfoById(Long studentId);

    void saveStudentPersonalInfo(StudentPersonalInfoEntity entity);

    void updateStudentPersonalInfo(StudentPersonalInfoEntity entity);

    void updateStudentPersonalInfoStatus(StudentPersonalInfoEntity entity);
}
