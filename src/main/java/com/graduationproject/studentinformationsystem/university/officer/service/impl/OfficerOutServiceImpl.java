package com.graduationproject.studentinformationsystem.university.officer.service.impl;

import com.graduationproject.studentinformationsystem.common.util.exception.SisNotExistException;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.converter.OfficerInfoResponseConverter;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.converter.OfficerResponseConverter;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.response.OfficerAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.response.OfficerInfoDetailResponse;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.response.OfficerInfoResponse;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.response.OfficerPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.university.officer.model.exception.OfficerException;
import com.graduationproject.studentinformationsystem.university.officer.service.OfficerAcademicInfoService;
import com.graduationproject.studentinformationsystem.university.officer.service.OfficerOutService;
import com.graduationproject.studentinformationsystem.university.officer.service.OfficerPersonalInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OfficerOutServiceImpl implements OfficerOutService {

    private final OfficerAcademicInfoService academicInfoService;
    private final OfficerPersonalInfoService personalInfoService;

    @Override
    public OfficerInfoDetailResponse getOfficerInfoDetailResponse(final Long officerId) {
        final OfficerAcademicInfoResponse academicInfoResponse = academicInfoService.getOfficerAcademicInfoById(officerId);
        final OfficerPersonalInfoResponse personalInfoResponse = personalInfoService.getOfficerPersonalInfoById(officerId);
        return OfficerInfoResponseConverter.convert(academicInfoResponse, personalInfoResponse);
    }

    @Override
    public OfficerInfoResponse getOfficerInfoResponse(Long officerId) {
        final OfficerAcademicInfoResponse academicInfoResponse = academicInfoService.getOfficerAcademicInfoById(officerId);
        final OfficerPersonalInfoResponse personalInfoResponse = personalInfoService.getOfficerPersonalInfoById(officerId);
        return OfficerResponseConverter.infoResponsesToResponse(academicInfoResponse, personalInfoResponse);
    }

    @Override
    public void ifOfficerIsNotExistThrowNotExistException(Long officerId) throws SisNotExistException {
        if (!academicInfoService.isOfficerExist(officerId)) {
            OfficerException.throwNotExistException(officerId);
        }
    }
}
