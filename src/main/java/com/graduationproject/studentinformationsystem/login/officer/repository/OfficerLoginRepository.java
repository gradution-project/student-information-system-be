package com.graduationproject.studentinformationsystem.login.officer.repository;

import com.graduationproject.studentinformationsystem.login.officer.model.entity.OfficerLoginInfoEntity;

public interface OfficerLoginRepository {

    Integer getFailCounter(Long officerId);

    String getPassword(Long officerId);

    void saveFirstPassword(Long officerId, String password);

    void updatePassword(Long officerId, String password);

    void updateLoginInfo(OfficerLoginInfoEntity loginInfoEntity);

    void updateFailCounter(Long officerId);
}
