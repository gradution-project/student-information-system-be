package com.graduationproject.studentinformationsystem.teacher.service.impl;

import com.graduationproject.studentinformationsystem.teacher.model.dto.converter.TeacherInfoResponseConverter;
import com.graduationproject.studentinformationsystem.teacher.model.dto.converter.TeacherResponseConverter;
import com.graduationproject.studentinformationsystem.teacher.model.dto.request.TeacherAcademicInfoRequest;
import com.graduationproject.studentinformationsystem.teacher.model.dto.request.TeacherInfoRequest;
import com.graduationproject.studentinformationsystem.teacher.model.dto.request.TeacherPersonalInfoRequest;
import com.graduationproject.studentinformationsystem.teacher.model.dto.response.TeacherAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.teacher.model.dto.response.TeacherInfoDetailResponse;
import com.graduationproject.studentinformationsystem.teacher.model.dto.response.TeacherPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.teacher.model.dto.response.TeacherResponse;
import com.graduationproject.studentinformationsystem.teacher.model.enums.TeacherStatus;
import com.graduationproject.studentinformationsystem.teacher.service.TeacherAcademicInfoService;
import com.graduationproject.studentinformationsystem.teacher.service.TeacherPersonalInfoService;
import com.graduationproject.studentinformationsystem.teacher.service.TeacherService;
import com.graduationproject.studentinformationsystem.teacher.util.TeacherUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherAcademicInfoService academicInfoService;
    private final TeacherPersonalInfoService personalInfoService;

    @Override
    public List<TeacherResponse> getAllTeachersByStatus(TeacherStatus status) {
        List<TeacherAcademicInfoResponse> academicInfoResponses = academicInfoService.getAllTeacherAcademicInfosByStatus(status);
        List<TeacherPersonalInfoResponse> personalInfoResponses = personalInfoService.getAllTeacherPersonalInfosByStatus(status);
        return TeacherResponseConverter.infoResponsesListToResponseList(academicInfoResponses, personalInfoResponses);
    }

    @Override
    public TeacherInfoDetailResponse getTeacherDetailById(Long teacherId) {
        return getTeacherInfoResponse(teacherId);
    }

    @Override
    public TeacherInfoDetailResponse saveTeacher(TeacherInfoRequest studentInfoRequest) {
        Long teacherId = generateTeacherId(studentInfoRequest.getAcademicInfoRequest().getDepartmentId());
        String studentEmail = generateTeacherEmail(studentInfoRequest);

        academicInfoService.saveTeacherAcademicInfo(teacherId, studentEmail, studentInfoRequest.getAcademicInfoRequest());
        personalInfoService.saveTeacherPersonalInfo(teacherId, studentInfoRequest.getPersonalInfoRequest());
        return getTeacherInfoResponse(teacherId);
    }

    @Override
    public TeacherAcademicInfoResponse updateTeacherAcademicInfo(Long teacherId, TeacherAcademicInfoRequest academicInfoRequest) {
        return academicInfoService.updateTeacherAcademicInfo(teacherId, academicInfoRequest);
    }

    @Override
    public TeacherPersonalInfoResponse updateTeacherPersonalInfo(Long teacherId, TeacherPersonalInfoRequest personalInfoRequest) {
        return personalInfoService.updateTeacherPersonalInfo(teacherId, personalInfoRequest);
    }

    @Override
    public TeacherResponse deleteTeacher(Long teacherId) {
        academicInfoService.deleteTeacherAcademicInfo(teacherId);
        personalInfoService.deleteTeacherPersonalInfo(teacherId);
        return getTeacherResponse(teacherId);
    }

    @Override
    public TeacherResponse passivateTeacher(Long teacherId) {
        academicInfoService.passivateTeacherAcademicInfo(teacherId);
        personalInfoService.passivateTeacherPersonalInfo(teacherId);
        return getTeacherResponse(teacherId);
    }

    @Override
    public TeacherResponse activateTeacher(Long teacherId) {
        academicInfoService.activateTeacherAcademicInfo(teacherId);
        personalInfoService.activateTeacherPersonalInfo(teacherId);
        return getTeacherResponse(teacherId);
    }

    private Long generateTeacherId(Long departmentId) {
        List<Long> teacherIds = academicInfoService.getAllTeacherIdsByDepartmentId(departmentId);
        return TeacherUtil.generateTeacherId(departmentId, teacherIds);
    }

    private String generateTeacherEmail(TeacherInfoRequest studentInfoRequest) {
        return TeacherUtil.generateTeacherEmail(
                studentInfoRequest.getPersonalInfoRequest().getName(),
                studentInfoRequest.getPersonalInfoRequest().getSurname());
    }

    private TeacherInfoDetailResponse getTeacherInfoResponse(Long teacherId) {
        TeacherAcademicInfoResponse academicInfoResponse = academicInfoService.getTeacherAcademicInfoById(teacherId);
        TeacherPersonalInfoResponse personalInfoResponse = personalInfoService.getTeacherPersonalInfoById(teacherId);
        return TeacherInfoResponseConverter.convert(academicInfoResponse, personalInfoResponse);
    }

    private TeacherResponse getTeacherResponse(Long teacherId) {
        TeacherAcademicInfoResponse academicInfoResponse = academicInfoService.getTeacherAcademicInfoById(teacherId);
        TeacherPersonalInfoResponse personalInfoResponse = personalInfoService.getTeacherPersonalInfoById(teacherId);
        return TeacherResponseConverter.infoResponsesToResponse(academicInfoResponse, personalInfoResponse);
    }
}
