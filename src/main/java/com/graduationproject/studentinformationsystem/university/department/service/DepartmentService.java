package com.graduationproject.studentinformationsystem.university.department.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.department.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.department.model.dto.response.DepartmentResponse;
import com.graduationproject.studentinformationsystem.university.department.model.enums.DepartmentStatus;

import java.util.List;

public interface DepartmentService {

    List<DepartmentResponse> getAllDepartmentsByStatus(DepartmentStatus status);

    DepartmentResponse getDepartmentById(Long departmentId) throws SisNotExistException;

    DepartmentResponse saveDepartment(DepartmentSaveRequest saveRequest) throws SisNotExistException;

    DepartmentResponse updateDepartment(Long departmentId, DepartmentUpdateRequest updateRequest) throws SisNotExistException;

    DepartmentResponse deleteDepartment(DepartmentDeleteRequest deleteRequest) throws SisNotExistException, SisAlreadyException;

    DepartmentResponse passivateDepartment(DepartmentPassivateRequest passivateRequest) throws SisNotExistException, SisAlreadyException;

    DepartmentResponse activateDepartment(DepartmentActivateRequest activateRequest) throws SisNotExistException, SisAlreadyException;
}
