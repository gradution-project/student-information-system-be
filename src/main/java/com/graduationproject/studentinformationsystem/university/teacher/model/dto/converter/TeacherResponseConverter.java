package com.graduationproject.studentinformationsystem.university.teacher.model.dto.converter;

import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherInfoResponse;
import com.graduationproject.studentinformationsystem.university.teacher.model.dto.response.TeacherPersonalInfoResponse;

import java.util.ArrayList;
import java.util.List;

public class TeacherResponseConverter {

    private TeacherResponseConverter() {
    }

    public static TeacherInfoResponse infoResponsesToResponse(TeacherAcademicInfoResponse academicInfoResponse,
                                                              TeacherPersonalInfoResponse personalInfoResponse) {

        return TeacherInfoResponse.builder()
                .teacherId(academicInfoResponse.getTeacherId())
                .departmentResponse(academicInfoResponse.getDepartmentResponse())
                .degree(academicInfoResponse.getDegree())
                .role(academicInfoResponse.getRole())
                .fieldOfStudy(academicInfoResponse.getFieldOfStudy())
                .name(personalInfoResponse.getName())
                .surname(personalInfoResponse.getSurname())
                .email(academicInfoResponse.getEmail())
                .phoneNumber(academicInfoResponse.getPhoneNumber())
                .status(academicInfoResponse.getStatus())
                .registrationDate(academicInfoResponse.getRegistrationDate()).build();
    }

    public static List<TeacherInfoResponse> infoResponsesListToResponseList(List<TeacherAcademicInfoResponse> academicInfoResponses,
                                                                            List<TeacherPersonalInfoResponse> personalInfoResponses) {

        List<TeacherInfoResponse> infoResponses = new ArrayList<>();
        for (int i = 0; i < academicInfoResponses.size(); i++) {
            infoResponses.add(infoResponsesToResponse(academicInfoResponses.get(i), personalInfoResponses.get(i)));
        }
        return infoResponses;
    }
}
