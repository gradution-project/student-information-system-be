package com.graduationproject.studentinformationsystem.university.officer.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisAlreadyException;
import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.request.*;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.response.OfficerAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.response.OfficerInfoDetailResponse;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.response.OfficerInfoResponse;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.response.OfficerPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.university.officer.model.enums.OfficerStatus;

import java.util.List;

public interface OfficerService {

    List<OfficerInfoResponse> getAllOfficersByStatus(OfficerStatus status);

    OfficerInfoDetailResponse getOfficerDetailById(Long officerId)
            throws SisNotExistException;

    OfficerInfoDetailResponse saveOfficer(OfficerSaveRequest studentInfoRequest) throws SisNotExistException;

    OfficerAcademicInfoResponse updateOfficerAcademicInfo(Long officerId,
                                                          OfficerAcademicInfoUpdateRequest academicInfoUpdateRequest)
            throws SisNotExistException;

    OfficerPersonalInfoResponse updateOfficerPersonalInfo(Long officerId,
                                                          OfficerPersonalInfoUpdateRequest personalInfoUpdateRequest)
            throws SisNotExistException;

    OfficerInfoResponse deleteOfficer(OfficerDeleteRequest deleteRequest)
            throws SisNotExistException, SisAlreadyException;

    OfficerInfoResponse passivateOfficer(OfficerPassivateRequest passivateRequest)
            throws SisNotExistException, SisAlreadyException;

    OfficerInfoResponse activateOfficer(OfficerActivateRequest activateRequest)
            throws SisNotExistException, SisAlreadyException;
}
