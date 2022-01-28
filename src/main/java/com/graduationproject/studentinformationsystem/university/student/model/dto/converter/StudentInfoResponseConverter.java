package com.graduationproject.studentinformationsystem.university.student.model.dto.converter;

import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentInfoDetailResponse;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentPersonalInfoResponse;

public class StudentInfoResponseConverter {

    private StudentInfoResponseConverter() {
    }

    public static StudentInfoDetailResponse convert(final StudentAcademicInfoResponse academicInfoResponse,
                                                    final StudentPersonalInfoResponse personalInfoResponse) {

        return StudentInfoDetailResponse.builder()
                .academicInfoResponse(academicInfoResponse)
                .personalInfoResponse(personalInfoResponse).build();
    }
}
