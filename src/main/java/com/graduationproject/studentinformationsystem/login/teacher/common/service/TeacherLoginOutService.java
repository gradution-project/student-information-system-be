package com.graduationproject.studentinformationsystem.login.teacher.common.service;

public interface TeacherLoginOutService {

    void saveOrUpdatePassword(Long teacherId, String encodedPassword);
}
