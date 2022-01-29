package com.graduationproject.studentinformationsystem.university.officer.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OfficerInfoDetailResponse {

    private OfficerAcademicInfoResponse academicInfoResponse;
    private OfficerPersonalInfoResponse personalInfoResponse;
}
