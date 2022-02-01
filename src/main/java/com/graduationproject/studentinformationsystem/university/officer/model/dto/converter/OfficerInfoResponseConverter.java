package com.graduationproject.studentinformationsystem.university.officer.model.dto.converter;

import com.graduationproject.studentinformationsystem.university.officer.model.dto.response.OfficerAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.response.OfficerInfoDetailResponse;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.response.OfficerPersonalInfoResponse;

public class OfficerInfoResponseConverter {

    private OfficerInfoResponseConverter() {
    }

    public static OfficerInfoDetailResponse convert(OfficerAcademicInfoResponse academicInfoResponse,
                                                    OfficerPersonalInfoResponse personalInfoResponse) {

        return OfficerInfoDetailResponse.builder()
                .academicInfoResponse(academicInfoResponse)
                .personalInfoResponse(personalInfoResponse).build();
    }
}
