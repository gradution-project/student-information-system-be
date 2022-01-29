package com.graduationproject.studentinformationsystem.university.officer.repository;

import com.graduationproject.studentinformationsystem.university.officer.model.entity.OfficerAcademicInfoEntity;
import com.graduationproject.studentinformationsystem.university.officer.model.enums.OfficerStatus;

import java.util.List;

public interface OfficerAcademicInfoRepository {

    List<OfficerAcademicInfoEntity> getAllOfficerAcademicInfosByStatus(OfficerStatus status);

    OfficerAcademicInfoEntity getOfficerAcademicInfoById(Long officerId);

    void saveOfficerAcademicInfo(OfficerAcademicInfoEntity academicInfoEntity);

    void updateOfficerAcademicInfo(OfficerAcademicInfoEntity academicInfoEntity);

    void updateOfficerAcademicInfoStatus(OfficerAcademicInfoEntity academicInfoEntity);

    List<Long> getAllOfficerIdsByFacultyId(Long departmentId);

    boolean isOfficerExist(Long officerId);

    boolean isOfficerDeleted(Long officerId);

    boolean isOfficerPassive(Long officerId);

    boolean isOfficerActive(Long officerId);
}
