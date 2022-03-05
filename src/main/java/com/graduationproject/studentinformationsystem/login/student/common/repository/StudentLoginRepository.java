package com.graduationproject.studentinformationsystem.login.student.common.repository;

import com.graduationproject.studentinformationsystem.login.student.common.model.entity.StudentLoginInfoEntity;

public interface StudentLoginRepository {

    Integer getFailCounter(Long studentId);

    String getPassword(Long studentId);

    void savePassword(Long studentId, String encodedPassword);

    void updatePassword(Long studentId, String encodedPassword);

    void updateLoginInfo(StudentLoginInfoEntity loginInfoEntity);

    void updateFailCounter(Long studentId);

    boolean isPasswordExist(Long studentId);
}
