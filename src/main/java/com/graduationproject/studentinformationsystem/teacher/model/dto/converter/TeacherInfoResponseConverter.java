package com.graduationproject.studentinformationsystem.teacher.model.dto.converter;

import com.graduationproject.studentinformationsystem.teacher.model.dto.response.TeacherAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.teacher.model.dto.response.TeacherInfoDetailResponse;
import com.graduationproject.studentinformationsystem.teacher.model.dto.response.TeacherPersonalInfoResponse;

public class TeacherInfoResponseConverter {

    private TeacherInfoResponseConverter() {
    }

    public static TeacherInfoDetailResponse convert(TeacherAcademicInfoResponse academicInfoResponse,
                                                    TeacherPersonalInfoResponse personalInfoResponse) {
        return TeacherInfoDetailResponse.builder()
                .academicInfoResponse(academicInfoResponse)
                .personalInfoResponse(personalInfoResponse).build();
    }
}
