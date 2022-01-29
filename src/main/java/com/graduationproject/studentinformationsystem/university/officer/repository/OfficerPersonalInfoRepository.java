package com.graduationproject.studentinformationsystem.university.officer.repository;

import com.graduationproject.studentinformationsystem.university.officer.model.entity.OfficerPersonalInfoEntity;
import com.graduationproject.studentinformationsystem.university.officer.model.enums.OfficerStatus;

import java.util.List;

public interface OfficerPersonalInfoRepository {

    List<OfficerPersonalInfoEntity> getAllOfficerPersonalInfosByStatus(OfficerStatus status);

    OfficerPersonalInfoEntity getOfficerPersonalInfoById(Long officerId);

    void saveOfficerPersonalInfo(OfficerPersonalInfoEntity personalInfoEntity);

    void updateOfficerPersonalInfo(OfficerPersonalInfoEntity personalInfoEntity);

    void updateOfficerPersonalInfoStatus(OfficerPersonalInfoEntity personalInfoEntity);
}
