package com.graduationproject.studentinformationsystem.login.officer.common.repository;

import com.graduationproject.studentinformationsystem.login.officer.common.model.entity.OfficerLoginInfoEntity;

public interface OfficerLoginRepository {

    Integer getFailCounter(Long officerId);

    String getPassword(Long officerId);

    void savePassword(Long officerId, String encodedPassword);

    void updatePassword(Long officerId, String encodedPassword);

    void updateLoginInfo(OfficerLoginInfoEntity loginInfoEntity);

    void updateFailCounter(Long officerId);

    boolean isPasswordExist(Long officerId);
}
