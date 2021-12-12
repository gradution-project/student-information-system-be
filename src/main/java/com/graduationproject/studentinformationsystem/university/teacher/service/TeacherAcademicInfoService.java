package com.graduationproject.studentinformationsystem.university.teacher.service;

import com.graduationproject.studentinformationsystem.university.teacher.model.dto.request.TeacherAcademicInfoRequest;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.enums.TeacherStatus;

import java.util.List;

public interface TeacherAcademicInfoService {

    List<TeacherAcademicInfoResponse> getAllTeacherAcademicInfosByStatus(TeacherStatus status);

    TeacherAcademicInfoResponse getTeacherAcademicInfoById(Long teacherId);

    void saveTeacherAcademicInfo(Long teacherId, String studentEmail, TeacherAcademicInfoRequest academicInfoRequest);

    TeacherAcademicInfoResponse updateTeacherAcademicInfo(Long teacherId, TeacherAcademicInfoRequest academicInfoRequest);

    void deleteTeacherAcademicInfo(Long teacherId);

    void passivateTeacherAcademicInfo(Long teacherId);

    void activateTeacherAcademicInfo(Long teacherId);

    List<Long> getAllTeacherIdsByDepartmentId(Long departmentId);

    boolean isTeacherExist(Long teacherId);

    boolean isTeacherDeleted(Long teacherId);

    boolean isTeacherPassive(Long teacherId);

    boolean isTeacherActive(Long teacherId);
}
