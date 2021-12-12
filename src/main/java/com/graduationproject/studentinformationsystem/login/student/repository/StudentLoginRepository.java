package com.graduationproject.studentinformationsystem.login.student.repository;

import com.graduationproject.studentinformationsystem.login.student.model.entity.StudentLoginInfoEntity;

public interface StudentLoginRepository {

    Integer getFailCounter(Long studentId);

    String getPassword(Long studentId);

    void saveFirstPassword(Long studentId, String password);

    void updatePassword(Long studentId, String password);

    void updateLoginInfo(StudentLoginInfoEntity loginEntity);

    void updateFailCounter(Long studentId);
}
