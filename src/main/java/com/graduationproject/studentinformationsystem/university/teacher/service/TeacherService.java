package com.graduationproject.studentinformationsystem.university.teacher.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.request.TeacherAcademicInfoRequest;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.request.TeacherInfoRequest;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.request.TeacherPersonalInfoRequest;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherInfoDetailResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.enums.TeacherStatus;

import java.util.List;

public interface TeacherService {

    List<TeacherResponse> getAllTeachersByStatus(TeacherStatus status);

    TeacherInfoDetailResponse getTeacherDetailById(Long teacherId) throws SisNotExistException;

    TeacherInfoDetailResponse saveTeacher(TeacherInfoRequest studentInfoRequest);

    TeacherAcademicInfoResponse updateTeacherAcademicInfo(Long teacherId, TeacherAcademicInfoRequest academicInfoRequest) throws SisNotExistException;

    TeacherPersonalInfoResponse updateTeacherPersonalInfo(Long teacherId, TeacherPersonalInfoRequest personalInfoRequest) throws SisNotExistException;

    TeacherResponse deleteTeacher(Long teacherId) throws SisNotExistException, SisAlreadyException;

    TeacherResponse passivateTeacher(Long teacherId) throws SisNotExistException, SisAlreadyException;

    TeacherResponse activateTeacher(Long teacherId) throws SisNotExistException, SisAlreadyException;
}
