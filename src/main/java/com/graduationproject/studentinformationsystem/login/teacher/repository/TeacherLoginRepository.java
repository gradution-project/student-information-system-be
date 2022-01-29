package com.graduationproject.studentinformationsystem.login.teacher.repository;

import com.graduationproject.studentinformationsystem.login.teacher.model.entity.TeacherLoginInfoEntity;

public interface TeacherLoginRepository {

    Integer getFailCounter(Long teacherId);

    String getPassword(Long teacherId);

    void saveFirstPassword(Long teacherId, String password);

    void updatePassword(Long teacherId, String password);

    void updateLoginInfo(TeacherLoginInfoEntity loginInfoEntity);

    void updateFailCounter(Long teacherId);
}
