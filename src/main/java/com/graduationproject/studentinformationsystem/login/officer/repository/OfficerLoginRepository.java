package com.graduationproject.studentinformationsystem.login.officer.repository;

import com.graduationproject.studentinformationsystem.login.officer.model.entity.OfficerLoginInfoEntity;

public interface OfficerLoginRepository {

    Integer getFailCounter(final Long officerId);

    String getPassword(final Long officerId);

    void saveFirstPassword(final Long officerId, final String password);

    void updatePassword(final Long officerId, final String password);

    void updateLoginInfo(final OfficerLoginInfoEntity loginEntity);

    void updateFailCounter(final Long officerId);
}
