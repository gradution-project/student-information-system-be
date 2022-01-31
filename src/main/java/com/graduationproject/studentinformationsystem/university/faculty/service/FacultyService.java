package com.graduationproject.studentinformationsystem.university.faculty.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.faculty.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.faculty.model.dto.response.FacultyResponse;
import com.graduationproject.studentinformationsystem.university.faculty.model.enums.FacultyStatus;

import java.util.List;

public interface FacultyService {

    List<FacultyResponse> getAllFacultiesByStatus(FacultyStatus status);

    FacultyResponse getFacultyById(Long facultyId) throws SisNotExistException;

    FacultyResponse saveFaculty(FacultySaveRequest saveRequest);

    FacultyResponse updateFaculty(Long facultyId, FacultyUpdateRequest updateRequest) throws SisNotExistException;

    FacultyResponse deleteFaculty(FacultyDeleteRequest deleteRequest) throws SisNotExistException, SisAlreadyException;

    FacultyResponse passivateFaculty(FacultyPassivateRequest passivateRequest) throws SisNotExistException, SisAlreadyException;

    FacultyResponse activateFaculty(FacultyActivateRequest activateRequest) throws SisNotExistException, SisAlreadyException;
}
