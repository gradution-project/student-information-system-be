package com.graduationproject.studentinformationsystem.university.officer.model.dto.response;

import com.graduationproject.studentinformationsystem.university.faculty.model.dto.response.FacultyResponse;
import com.graduationproject.studentinformationsystem.university.officer.model.enums.OfficerStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OfficerInfoResponse {

    private Long officerId;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private OfficerStatus status;
    private String registrationDate;

    private FacultyResponse facultyResponse;
}
