package com.graduationproject.studentinformationsystem.university.teacher.repository;

import com.graduationproject.studentinformationsystem.university.teacher.model.entity.TeacherPersonalInfoEntity;
import com.graduationproject.studentinformationsystem.university.teacher.model.enums.TeacherStatus;

import java.util.List;

public interface TeacherPersonalInfoRepository {

    List<TeacherPersonalInfoEntity> getAllTeacherPersonalInfosByStatus(TeacherStatus status);

    TeacherPersonalInfoEntity getTeacherPersonalInfoById(Long teacherId);

    void saveTeacherPersonalInfo(TeacherPersonalInfoEntity entity);

    void updateTeacherPersonalInfo(TeacherPersonalInfoEntity entity);

    void updateTeacherPersonalInfoStatus(TeacherPersonalInfoEntity entity);
}
