package com.graduationproject.studentinformationsystem.university.teacher.service;

import com.graduationproject.studentinformationsystem.university.teacher.model.dto.request.TeacherPersonalInfoRequest;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.enums.TeacherStatus;

import java.util.List;

public interface TeacherPersonalInfoService {

    List<TeacherPersonalInfoResponse> getAllTeacherPersonalInfosByStatus(TeacherStatus status);

    TeacherPersonalInfoResponse getTeacherPersonalInfoById(Long teacherId);

    void saveTeacherPersonalInfo(Long teacherId, TeacherPersonalInfoRequest personalInfoRequest);

    TeacherPersonalInfoResponse updateTeacherPersonalInfo(Long teacherId, TeacherPersonalInfoRequest personalInfoRequest);

    void deleteTeacherPersonalInfo(Long teacherId);

    void passivateTeacherPersonalInfo(Long teacherId);

    void activateTeacherPersonalInfo(Long teacherId);
}
