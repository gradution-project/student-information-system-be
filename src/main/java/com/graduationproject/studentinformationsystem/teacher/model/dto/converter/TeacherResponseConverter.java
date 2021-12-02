package com.graduationproject.studentinformationsystem.teacher.model.dto.converter;

import com.graduationproject.studentinformationsystem.teacher.model.dto.response.TeacherAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.teacher.model.dto.response.TeacherPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.teacher.model.dto.response.TeacherResponse;

import java.util.ArrayList;
import java.util.List;

public class TeacherResponseConverter {

    private TeacherResponseConverter() {
    }

    public static TeacherResponse infoResponsesToResponse(TeacherAcademicInfoResponse academicInfoResponse,
                                                          TeacherPersonalInfoResponse personalInfoResponse) {
        return TeacherResponse.builder()
                .teacherId(academicInfoResponse.getTeacherId())
                .departmentId(academicInfoResponse.getDepartmentId())
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

    public static List<TeacherResponse> infoResponsesListToResponseList(List<TeacherAcademicInfoResponse> academicInfoResponses,
                                                                        List<TeacherPersonalInfoResponse> personalInfoResponses) {

        List<TeacherResponse> studentResponseList = new ArrayList<>();
        for (int i = 0; i < academicInfoResponses.size(); i++) {
            studentResponseList.add(infoResponsesToResponse(academicInfoResponses.get(i), personalInfoResponses.get(i)));
        }
        return studentResponseList;
    }
}
