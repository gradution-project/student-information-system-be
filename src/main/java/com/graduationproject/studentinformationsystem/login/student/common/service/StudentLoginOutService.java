package com.graduationproject.studentinformationsystem.login.student.common.service;

public interface StudentLoginOutService {

    void saveOrUpdatePassword(Long studentId, String encodedPassword);
}
