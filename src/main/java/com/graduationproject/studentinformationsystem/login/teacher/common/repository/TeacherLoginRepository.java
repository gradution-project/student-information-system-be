package com.graduationproject.studentinformationsystem.login.teacher.common.repository;

import com.graduationproject.studentinformationsystem.login.teacher.common.model.entity.TeacherLoginInfoEntity;

public interface TeacherLoginRepository {

    Integer getFailCounter(Long teacherId);

    String getPassword(Long teacherId);

    void savePassword(Long teacherId, String encodedPassword);

    void updatePassword(Long teacherId, String encodedPassword);

    void updateLoginInfo(TeacherLoginInfoEntity loginInfoEntity);

    void updateFailCounter(Long teacherId);

    boolean isPasswordExist(Long teacherId);
}
