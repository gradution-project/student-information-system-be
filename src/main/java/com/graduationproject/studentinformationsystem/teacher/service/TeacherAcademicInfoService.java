package com.graduationproject.studentinformationsystem.teacher.service;

import com.graduationproject.studentinformationsystem.teacher.model.dto.request.TeacherAcademicInfoRequest;
import com.graduationproject.studentinformationsystem.teacher.model.dto.response.TeacherAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.teacher.model.enums.TeacherStatus;

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
