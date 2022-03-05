package com.graduationproject.studentinformationsystem.university.officer.service;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.response.OfficerInfoDetailResponse;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.response.OfficerInfoResponse;

public interface OfficerOutService {

    OfficerInfoDetailResponse getOfficerInfoDetailResponse(Long officerId) throws SisNotExistException;

    OfficerInfoResponse getOfficerInfoResponse(Long officerId);

    void ifOfficerIsNotExistThrowNotExistException(Long officerId) throws SisNotExistException;
}
