package com.graduationproject.studentinformationsystem.login.student.password.repository;

import com.graduationproject.studentinformationsystem.login.student.password.model.entity.StudentPasswordOperationEntity;

public interface StudentPasswordOperationRepository {

    StudentPasswordOperationEntity getPasswordOperation(Long studentId);

    void savePasswordOperation(StudentPasswordOperationEntity passwordOperationEntity);

    void updatePasswordOperation(StudentPasswordOperationEntity passwordOperationEntity);

    void deletePasswordOperation(String operationId);

    boolean isPasswordChangeEnabled(String operationId);

    boolean isOperationExist(Long studentId);

    Long getStudentId(String operationId);
}
