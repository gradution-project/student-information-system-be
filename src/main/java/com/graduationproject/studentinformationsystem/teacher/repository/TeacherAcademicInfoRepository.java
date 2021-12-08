package com.graduationproject.studentinformationsystem.teacher.repository;

import com.graduationproject.studentinformationsystem.teacher.model.entity.TeacherAcademicInfoEntity;
import com.graduationproject.studentinformationsystem.teacher.model.enums.TeacherStatus;

import java.util.List;

public interface TeacherAcademicInfoRepository {

    List<TeacherAcademicInfoEntity> getAllTeacherAcademicInfosByStatus(TeacherStatus status);

    TeacherAcademicInfoEntity getTeacherAcademicInfoById(Long teacherId);

    void saveTeacherAcademicInfo(TeacherAcademicInfoEntity entity);

    void updateTeacherAcademicInfo(TeacherAcademicInfoEntity entity);

    void updateTeacherAcademicInfoStatus(TeacherAcademicInfoEntity entity);

    List<Long> getAllTeacherIdsByDepartmentId(Long departmentId);

    boolean isTeacherExist(Long teacherId);
}
