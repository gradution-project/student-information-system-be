package com.graduationproject.studentinformationsystem.university.department.service.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.department.model.dto.converter.DepartmentInfoConverter;
import com.graduationproject.studentinformationsystem.university.department.model.dto.response.DepartmentResponse;
import com.graduationproject.studentinformationsystem.university.department.model.entity.DepartmentEntity;
import com.graduationproject.studentinformationsystem.university.department.model.exception.DepartmentException;
import com.graduationproject.studentinformationsystem.university.department.repository.DepartmentRepository;
import com.graduationproject.studentinformationsystem.university.department.service.DepartmentOutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentOutServiceImpl implements DepartmentOutService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentInfoConverter departmentInfoConverter;

    @Override
    public DepartmentResponse getDepartmentResponse(Long departmentId) {
        final DepartmentEntity departmentEntity = departmentRepository.getDepartmentById(departmentId);
        return departmentInfoConverter.entityToResponse(departmentEntity);
    }

    @Override
    public void ifDepartmentIsNotExistThrowNotExistException(Long departmentId) throws SisNotExistException {
        if (!departmentRepository.isDepartmentExist(departmentId)) {
            DepartmentException.throwNotExistException(departmentId);
        }
    }
}
