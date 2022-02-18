package com.graduationproject.studentinformationsystem.university.student.model.dto.converter;

import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentInfoResponse;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentPersonalInfoResponse;

import java.util.ArrayList;
import java.util.List;

public class StudentResponseConverter {

    private StudentResponseConverter() {
    }

    public static StudentInfoResponse infoResponsesToResponse(final StudentAcademicInfoResponse academicInfoResponse,
                                                              final StudentPersonalInfoResponse personalInfoResponse) {

        return StudentInfoResponse.builder()
                .studentId(academicInfoResponse.getStudentId())
                .degree(academicInfoResponse.getDegree())
                .classLevel(academicInfoResponse.getClassLevel())
                .name(personalInfoResponse.getName())
                .surname(personalInfoResponse.getSurname())
                .email(academicInfoResponse.getEmail())
                .status(academicInfoResponse.getStatus())
                .registrationDate(academicInfoResponse.getRegistrationDate())
                .departmentResponse(academicInfoResponse.getDepartmentResponse())
                .build();
    }

    public static List<StudentInfoResponse> infoResponsesListToResponseList(final List<StudentAcademicInfoResponse> academicInfoResponses,
                                                                            final List<StudentPersonalInfoResponse> personalInfoResponses) {

        List<StudentInfoResponse> infoResponses = new ArrayList<>();
        for (int i = 0; i < academicInfoResponses.size(); i++) {
            infoResponses.add(infoResponsesToResponse(academicInfoResponses.get(i), personalInfoResponses.get(i)));
        }
        return infoResponses;
    }
}
