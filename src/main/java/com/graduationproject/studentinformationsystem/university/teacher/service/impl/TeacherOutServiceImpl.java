package com.graduationproject.studentinformationsystem.university.teacher.service.impl;

import com.graduationproject.studentinformationsystem.university.teacher.model.dto.converter.TeacherResponseConverter;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherInfoResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.university.teacher.service.TeacherAcademicInfoService;
import com.graduationproject.studentinformationsystem.university.teacher.service.TeacherOutService;
import com.graduationproject.studentinformationsystem.university.teacher.service.TeacherPersonalInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherOutServiceImpl implements TeacherOutService {

    private final TeacherAcademicInfoService academicInfoService;
    private final TeacherPersonalInfoService personalInfoService;

    @Override
    public TeacherInfoResponse getTeacherInfoResponse(Long teacherId) {
        final TeacherAcademicInfoResponse academicInfoResponse = academicInfoService.getTeacherAcademicInfoById(teacherId);
        final TeacherPersonalInfoResponse personalInfoResponse = personalInfoService.getTeacherPersonalInfoById(teacherId);
        return TeacherResponseConverter.infoResponsesToResponse(academicInfoResponse, personalInfoResponse);
    }
}
