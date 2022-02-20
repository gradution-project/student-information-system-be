package com.graduationproject.studentinformationsystem.university.faculty.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.faculty.model.dto.response.FacultyResponse;

public interface FacultyOutService {

    FacultyResponse getFacultyResponse(Long facultyId);

    void ifFacultyIsNotExistThrowNotExistException(Long facultyId) throws SisNotExistException;
}
