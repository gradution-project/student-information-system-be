package com.graduationproject.studentinformationsystem.university.teacher.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherInfoDetailResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherInfoResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.enums.TeacherStatus;

import java.util.List;

public interface TeacherService {

    List<TeacherInfoResponse> getAllTeachersByStatus(TeacherStatus status);

    TeacherInfoDetailResponse getTeacherDetailById(Long teacherId)
            throws SisNotExistException;

    TeacherInfoDetailResponse saveTeacher(TeacherSaveRequest studentInfoRequest) throws SisNotExistException;

    TeacherAcademicInfoResponse updateTeacherAcademicInfo(Long teacherId,
                                                          TeacherAcademicInfoUpdateRequest academicInfoUpdateRequest)
            throws SisNotExistException;

    TeacherPersonalInfoResponse updateTeacherPersonalInfo(Long teacherId,
                                                          TeacherPersonalInfoUpdateRequest personalInfoUpdateRequest)
            throws SisNotExistException;

    TeacherInfoResponse deleteTeacher(TeacherDeleteRequest deleteRequest)
            throws SisNotExistException, SisAlreadyException;

    TeacherInfoResponse passivateTeacher(TeacherPassivateRequest passivateRequest)
            throws SisNotExistException, SisAlreadyException;

    TeacherInfoResponse activateTeacher(TeacherActivateRequest activateRequest)
            throws SisNotExistException, SisAlreadyException;
}
