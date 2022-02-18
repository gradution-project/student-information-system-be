package com.graduationproject.studentinformationsystem.university.teacher.service;

import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherAcademicInfoResponse;

public interface TeacherOutService {

    TeacherAcademicInfoResponse getTeacherAcademicInfoResponse(Long teacherId);
}
