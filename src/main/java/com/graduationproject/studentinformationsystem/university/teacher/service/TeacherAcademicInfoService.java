package com.graduationproject.studentinformationsystem.university.teacher.service;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.enums.TeacherStatus;

import java.util.List;

public interface TeacherAcademicInfoService {

    List<TeacherAcademicInfoResponse> getAllTeacherAcademicInfosByStatus(TeacherStatus status);

    TeacherAcademicInfoResponse getTeacherAcademicInfoById(Long teacherId);

    void saveTeacherAcademicInfo(Long teacherId,
                                 String teacherEmail,
                                 TeacherAcademicInfoRequest academicInfoRequest,
                                 SisOperationInfoRequest operationInfoRequest);

    TeacherAcademicInfoResponse updateTeacherAcademicInfo(Long teacherId,
                                                          TeacherAcademicInfoUpdateRequest academicInfoUpdateRequest);

    void deleteTeacherAcademicInfo(TeacherDeleteRequest deleteRequest);

    void passivateTeacherAcademicInfo(TeacherPassivateRequest passivateRequest);

    void activateTeacherAcademicInfo(TeacherActivateRequest activateRequest);

    List<Long> getAllTeacherIdsByDepartmentId(Long departmentId);

    boolean isTeacherExist(Long teacherId);

    boolean isTeacherDeleted(Long teacherId);

    boolean isTeacherPassive(Long teacherId);

    boolean isTeacherActive(Long teacherId);
}
