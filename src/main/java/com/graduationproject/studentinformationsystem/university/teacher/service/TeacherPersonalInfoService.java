package com.graduationproject.studentinformationsystem.university.teacher.service;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.enums.TeacherStatus;

import java.util.List;

public interface TeacherPersonalInfoService {

    List<TeacherPersonalInfoResponse> getAllTeacherPersonalInfosByStatus(TeacherStatus status);

    TeacherPersonalInfoResponse getTeacherPersonalInfoById(Long teacherId);

    void saveTeacherPersonalInfo(Long teacherId,
                                 TeacherPersonalInfoRequest personalInfoRequest,
                                 SisOperationInfoRequest operationInfoRequest);

    TeacherPersonalInfoResponse updateTeacherPersonalInfo(Long teacherId,
                                                          TeacherPersonalInfoUpdateRequest personalInfoUpdateRequest);

    void deleteTeacherPersonalInfo(TeacherDeleteRequest deleteRequest);

    void passivateTeacherPersonalInfo(TeacherPassivateRequest passivateRequest);

    void activateTeacherPersonalInfo(TeacherActivateRequest activateRequest);
}
