package com.graduationproject.studentinformationsystem.university.officer.service;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.response.OfficerPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.university.officer.model.enums.OfficerStatus;

import java.util.List;

public interface OfficerPersonalInfoService {

    List<OfficerPersonalInfoResponse> getAllOfficerPersonalInfosByStatus(OfficerStatus status);

    OfficerPersonalInfoResponse getOfficerPersonalInfoById(Long officerId);

    void saveOfficerPersonalInfo(Long officerId,
                                 OfficerPersonalInfoRequest personalInfoRequest,
                                 SisOperationInfoRequest operationInfoRequest);

    OfficerPersonalInfoResponse updateOfficerPersonalInfo(Long officerId,
                                                          OfficerPersonalInfoUpdateRequest personalInfoUpdateRequest);

    void deleteOfficerPersonalInfo(OfficerDeleteRequest deleteRequest);

    void passivateOfficerPersonalInfo(OfficerPassivateRequest passivateRequest);

    void activateOfficerPersonalInfo(OfficerActivateRequest activateRequest);
}
