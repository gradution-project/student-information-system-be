package com.graduationproject.studentinformationsystem.university.faculty.service.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.faculty.model.dto.converter.FacultyInfoConverter;
import com.graduationproject.studentinformationsystem.university.faculty.model.dto.response.FacultyResponse;
import com.graduationproject.studentinformationsystem.university.faculty.model.entity.FacultyEntity;
import com.graduationproject.studentinformationsystem.university.faculty.model.exception.FacultyException;
import com.graduationproject.studentinformationsystem.university.faculty.repository.FacultyRepository;
import com.graduationproject.studentinformationsystem.university.faculty.service.FacultyOutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FacultyOutServiceImpl implements FacultyOutService {

    private final FacultyRepository facultyRepository;
    private final FacultyInfoConverter facultyInfoConverter;

    @Override
    public FacultyResponse getFacultyResponse(Long facultyId) {
        final FacultyEntity facultyEntity = facultyRepository.getFacultyById(facultyId);
        return facultyInfoConverter.entityToResponse(facultyEntity);
    }

    @Override
    public void ifFacultyIsNotExistThrowNotExistException(Long facultyId) throws SisNotExistException {
        if (!facultyRepository.isFacultyExist(facultyId)) {
            FacultyException.throwNotExistException(facultyId);
        }
    }
}
