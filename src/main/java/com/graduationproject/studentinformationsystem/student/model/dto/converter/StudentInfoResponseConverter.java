package com.graduationproject.studentinformationsystem.student.model.dto.converter;

import com.graduationproject.studentinformationsystem.student.model.dto.response.StudentAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.student.model.dto.response.StudentInfoDetailResponse;
import com.graduationproject.studentinformationsystem.student.model.dto.response.StudentPersonalInfoResponse;

public class StudentInfoResponseConverter {

    private StudentInfoResponseConverter() {
    }

    public static StudentInfoDetailResponse convert(StudentAcademicInfoResponse academicInfoResponse,
                                                    StudentPersonalInfoResponse personalInfoResponse) {
        return StudentInfoDetailResponse.builder()
                .academicInfoResponse(academicInfoResponse)
                .personalInfoResponse(personalInfoResponse).build();
    }
}
