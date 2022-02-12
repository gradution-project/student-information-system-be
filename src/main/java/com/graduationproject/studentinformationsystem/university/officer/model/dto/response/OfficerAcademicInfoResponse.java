package com.graduationproject.studentinformationsystem.university.officer.model.dto.response;

import com.graduationproject.studentinformationsystem.common.util.controller.response.SisBaseAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.faculty.model.dto.response.FacultyResponse;
import com.graduationproject.studentinformationsystem.university.officer.model.enums.OfficerStatus;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class OfficerAcademicInfoResponse extends SisBaseAcademicInfoResponse {

    private Long officerId;
    private OfficerStatus status;
    private String phoneNumber;

    private FacultyResponse facultyResponse;
}
