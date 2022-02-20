package com.graduationproject.studentinformationsystem.university.department.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.department.model.dto.response.DepartmentResponse;

public interface DepartmentOutService {

    DepartmentResponse getDepartmentResponse(Long departmentId);

    void ifDepartmentIsNotExistThrowNotExistException(Long departmentId) throws SisNotExistException;
}
