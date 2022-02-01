package com.graduationproject.studentinformationsystem.university.department.repository;

import com.graduationproject.studentinformationsystem.university.department.model.entity.DepartmentEntity;
import com.graduationproject.studentinformationsystem.university.department.model.enums.DepartmentStatus;

import java.util.List;

public interface DepartmentRepository {

    List<DepartmentEntity> getAllDepartmentsByStatus(DepartmentStatus status);

    DepartmentEntity getDepartmentById(Long departmentId);

    void saveDepartment(DepartmentEntity departmentEntity);

    void updateDepartment(DepartmentEntity departmentEntity);

    void updateDepartmentStatus(DepartmentEntity departmentEntity);

    List<Long> getAllDepartmentIdsByFacultyId(Long facultyId);

    boolean isDepartmentExist(Long departmentId);

    boolean isDepartmentDeleted(Long departmentId);

    boolean isDepartmentPassive(Long departmentId);

    boolean isDepartmentActive(Long departmentId);
}
