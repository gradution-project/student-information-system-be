package com.graduationproject.studentinformationsystem.university.student.model.dto.converter;

import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentPersonalInfoResponse;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentResponse;

import java.util.ArrayList;
import java.util.List;

public class StudentResponseConverter {

    private StudentResponseConverter() {
    }

    public static StudentResponse infoResponsesToResponse(StudentAcademicInfoResponse academicInfoResponse,
                                                          StudentPersonalInfoResponse personalInfoResponse) {
        return StudentResponse.builder()
                .studentId(academicInfoResponse.getStudentId())
                .departmentId(academicInfoResponse.getDepartmentId())
                .degree(academicInfoResponse.getDegree())
                .classLevel(academicInfoResponse.getClassLevel())
                .name(personalInfoResponse.getName())
                .surname(personalInfoResponse.getSurname())
                .email(academicInfoResponse.getEmail())
                .status(academicInfoResponse.getStatus())
                .registrationDate(academicInfoResponse.getRegistrationDate()).build();
    }

    public static List<StudentResponse> infoResponsesListToResponseList(List<StudentAcademicInfoResponse> academicInfoResponseList,
                                                                        List<StudentPersonalInfoResponse> personalInfoResponseList) {

        List<StudentResponse> studentResponseList = new ArrayList<>();
        for (int i = 0; i < academicInfoResponseList.size(); i++) {
            studentResponseList.add(infoResponsesToResponse(academicInfoResponseList.get(i), personalInfoResponseList.get(i)));
        }
        return studentResponseList;
    }
}
