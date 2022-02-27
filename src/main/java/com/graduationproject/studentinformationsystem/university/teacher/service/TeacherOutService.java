package com.graduationproject.studentinformationsystem.university.teacher.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherInfoDetailResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherInfoResponse;

public interface TeacherOutService {

    TeacherInfoDetailResponse getTeacherInfoDetailResponse(Long teacherId) throws SisNotExistException;

    TeacherInfoResponse getTeacherInfoResponse(Long teacherId);

    void ifTeacherIsNotExistThrowNotExistException(Long teacherId) throws SisNotExistException;
}
