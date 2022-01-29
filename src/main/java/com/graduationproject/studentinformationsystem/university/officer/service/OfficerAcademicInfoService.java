package com.graduationproject.studentinformationsystem.university.officer.service;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.response.OfficerAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.officer.model.enums.OfficerStatus;

import java.util.List;

public interface OfficerAcademicInfoService {

    List<OfficerAcademicInfoResponse> getAllOfficerAcademicInfosByStatus(OfficerStatus status);

    OfficerAcademicInfoResponse getOfficerAcademicInfoById(Long officerId);

    void saveOfficerAcademicInfo(Long officerId,
                                 String officerEmail,
                                 OfficerAcademicInfoRequest academicInfoRequest,
                                 SisOperationInfoRequest operationInfoRequest);

    OfficerAcademicInfoResponse updateOfficerAcademicInfo(Long officerId,
                                                          OfficerAcademicInfoUpdateRequest academicInfoUpdateRequest);

    void deleteOfficerAcademicInfo(OfficerDeleteRequest deleteRequest);

    void passivateOfficerAcademicInfo(OfficerPassivateRequest passivateRequest);

    void activateOfficerAcademicInfo(OfficerActivateRequest activateRequest);

    List<Long> getAllOfficerIdsByFacultyId(Long facultyId);

    boolean isOfficerExist(Long officerId);

    boolean isOfficerDeleted(Long officerId);

    boolean isOfficerPassive(Long officerId);

    boolean isOfficerActive(Long officerId);
}
