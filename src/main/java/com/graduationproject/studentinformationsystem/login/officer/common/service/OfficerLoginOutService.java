package com.graduationproject.studentinformationsystem.login.officer.common.service;

public interface OfficerLoginOutService {

    void saveOrUpdatePassword(Long officerId, String encodedPassword);
}
