package com.graduationproject.studentinformationsystem.university.teacher.service.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.converter.TeacherInfoResponseConverter;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.converter.TeacherResponseConverter;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherInfoDetailResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherInfoResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.exception.TeacherException;
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
    public TeacherInfoDetailResponse getTeacherInfoDetailResponse(final Long teacherId) {
        final TeacherAcademicInfoResponse academicInfoResponse = academicInfoService.getTeacherAcademicInfoById(teacherId);
        final TeacherPersonalInfoResponse personalInfoResponse = personalInfoService.getTeacherPersonalInfoById(teacherId);
        return TeacherInfoResponseConverter.convert(academicInfoResponse, personalInfoResponse);
    }

    @Override
    public TeacherInfoResponse getTeacherInfoResponse(Long teacherId) {
        final TeacherAcademicInfoResponse academicInfoResponse = academicInfoService.getTeacherAcademicInfoById(teacherId);
        final TeacherPersonalInfoResponse personalInfoResponse = personalInfoService.getTeacherPersonalInfoById(teacherId);
        return TeacherResponseConverter.infoResponsesToResponse(academicInfoResponse, personalInfoResponse);
    }

    @Override
    public void ifTeacherIsNotExistThrowNotExistException(Long teacherId) throws SisNotExistException {
        if (!academicInfoService.isTeacherExist(teacherId)) {
            TeacherException.throwNotExistException(teacherId);
        }
    }
}
