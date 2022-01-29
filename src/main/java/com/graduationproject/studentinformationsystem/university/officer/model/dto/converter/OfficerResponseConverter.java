package com.graduationproject.studentinformationsystem.university.officer.model.dto.converter;

import com.graduationproject.studentinformationsystem.university.officer.model.dto.response.OfficerAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.response.OfficerInfoResponse;
import com.graduationproject.studentinformationsystem.university.officer.model.dto.response.OfficerPersonalInfoResponse;

import java.util.ArrayList;
import java.util.List;

public class OfficerResponseConverter {

    private OfficerResponseConverter() {
    }

    public static OfficerInfoResponse infoResponsesToResponse(OfficerAcademicInfoResponse academicInfoResponse,
                                                              OfficerPersonalInfoResponse personalInfoResponse) {

        return OfficerInfoResponse.builder()
                .officerId(academicInfoResponse.getOfficerId())
                .facultyId(academicInfoResponse.getFacultyId())
                .name(personalInfoResponse.getName())
                .surname(personalInfoResponse.getSurname())
                .email(academicInfoResponse.getEmail())
                .phoneNumber(academicInfoResponse.getPhoneNumber())
                .status(academicInfoResponse.getStatus())
                .registrationDate(academicInfoResponse.getRegistrationDate()).build();
    }

    public static List<OfficerInfoResponse> infoResponsesListToResponseList(List<OfficerAcademicInfoResponse> academicInfoResponses,
                                                                            List<OfficerPersonalInfoResponse> personalInfoResponses) {

        List<OfficerInfoResponse> infoResponses = new ArrayList<>();
        for (int i = 0; i < academicInfoResponses.size(); i++) {
            infoResponses.add(infoResponsesToResponse(academicInfoResponses.get(i), personalInfoResponses.get(i)));
        }
        return infoResponses;
    }
}
