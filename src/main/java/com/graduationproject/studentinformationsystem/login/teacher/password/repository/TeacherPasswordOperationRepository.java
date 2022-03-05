package com.graduationproject.studentinformationsystem.login.teacher.password.repository;

import com.graduationproject.studentinformationsystem.login.teacher.password.model.entity.TeacherPasswordOperationEntity;

public interface TeacherPasswordOperationRepository {

    TeacherPasswordOperationEntity getPasswordOperation(Long teacherId);

    void savePasswordOperation(TeacherPasswordOperationEntity passwordOperationEntity);

    void updatePasswordOperation(TeacherPasswordOperationEntity passwordOperationEntity);

    void deletePasswordOperation(String operationId);

    boolean isPasswordChangeEnabled(String operationId);

    boolean isOperationExist(Long teacherId);

    Long getTeacherId(String operationId);
}
