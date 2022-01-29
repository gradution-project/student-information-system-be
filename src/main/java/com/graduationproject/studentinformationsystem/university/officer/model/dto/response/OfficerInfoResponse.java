package com.graduationproject.studentinformationsystem.university.officer.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OfficerInfoResponse {

    private Long officerId;
    private Long facultyId;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String status;
    private String registrationDate;
}
