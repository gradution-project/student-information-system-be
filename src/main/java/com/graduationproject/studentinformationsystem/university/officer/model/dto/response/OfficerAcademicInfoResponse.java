package com.graduationproject.studentinformationsystem.university.officer.model.dto.response;

import com.graduationproject.studentinformationsystem.common.util.controller.response.SisBaseAcademicInfoResponse;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class OfficerAcademicInfoResponse extends SisBaseAcademicInfoResponse {

    private Long officerId;
    private Long facultyId;
    private String OfficerAcademicInfoResponse;
}
