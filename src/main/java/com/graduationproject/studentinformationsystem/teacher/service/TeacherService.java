package com.graduationproject.studentinformationsystem.teacher.service;

import com.graduationproject.studentinformationsystem.teacher.model.dto.request.TeacherAcademicInfoRequest;
import com.graduationproject.studentinformationsystem.teacher.model.dto.request.TeacherInfoRequest;
import com.graduationproject.studentinformationsystem.teacher.model.dto.request.TeacherPersonalInfoRequest;
import com.graduationproject.studentinformationsystem.teacher.model.dto.response.TeacherAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.teacher.model.dto.response.TeacherInfoDetailResponse;
import com.graduationproject.studentinformationsystem.teacher.model.dto.response.TeacherPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.teacher.model.dto.response.TeacherResponse;
import com.graduationproject.studentinformationsystem.teacher.model.enums.TeacherStatus;

import java.util.List;

public interface TeacherService {

    List<TeacherResponse> getAllTeachersByStatus(TeacherStatus status);

    TeacherInfoDetailResponse getTeacherDetailById(Long teacherId);

    TeacherInfoDetailResponse saveTeacher(TeacherInfoRequest studentInfoRequest);

    TeacherAcademicInfoResponse updateTeacherAcademicInfo(Long teacherId, TeacherAcademicInfoRequest academicInfoRequest);

    TeacherPersonalInfoResponse updateTeacherPersonalInfo(Long teacherId, TeacherPersonalInfoRequest personalInfoRequest);

    TeacherResponse deleteTeacher(Long teacherId);

    TeacherResponse passivateTeacher(Long teacherId);

    TeacherResponse activateTeacher(Long teacherId);
}
