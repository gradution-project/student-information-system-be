package com.graduationproject.studentinformationsystem.university.teacher.service;

import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherInfoResponse;

public interface TeacherOutService {

    TeacherInfoResponse getTeacherInfoResponse(Long teacherId);
}
