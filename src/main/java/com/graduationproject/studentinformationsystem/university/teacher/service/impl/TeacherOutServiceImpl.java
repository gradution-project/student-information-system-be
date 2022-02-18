package com.graduationproject.studentinformationsystem.university.teacher.service.impl;

import com.graduationproject.studentinformationsystem.university.teacher.model.dto.converter.TeacherAcademicInfoConverter;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.entity.TeacherAcademicInfoEntity;
import com.graduationproject.studentinformationsystem.university.teacher.repository.TeacherAcademicInfoRepository;
import com.graduationproject.studentinformationsystem.university.teacher.service.TeacherOutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherOutServiceImpl implements TeacherOutService {

    private final TeacherAcademicInfoRepository academicInfoRepository;
    private final TeacherAcademicInfoConverter academicInfoConverter;

    @Override
    public TeacherAcademicInfoResponse getTeacherAcademicInfoResponse(Long teacherId) {
        final TeacherAcademicInfoEntity entity = academicInfoRepository.getTeacherAcademicInfoById(teacherId);
        return academicInfoConverter.entityToResponse(entity);
    }
}
