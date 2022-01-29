package com.graduationproject.studentinformationsystem.university.faculty.repository;

import com.graduationproject.studentinformationsystem.university.faculty.model.dto.entity.FacultyEntity;
import com.graduationproject.studentinformationsystem.university.faculty.model.enums.FacultyStatus;

import java.util.List;

public interface FacultyRepository {

    List<FacultyEntity> getAllFacultiesByStatus(FacultyStatus status);

    FacultyEntity getFacultyById(Long facultyId);

    void saveFaculty(FacultyEntity facultyEntity);

    void updateFaculty(FacultyEntity facultyEntity);

    void updateFacultyStatus(FacultyEntity facultyEntity);

    List<Long> getAllFacultyIds();

    boolean isFacultyExist(Long facultyId);

    boolean isFacultyDeleted(Long facultyId);

    boolean isFacultyPassive(Long facultyId);

    boolean isFacultyActive(Long facultyId);
}
