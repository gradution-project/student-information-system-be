package com.graduationproject.studentinformationsystem.teacher.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TeacherInfoDetailResponse {

    private TeacherAcademicInfoResponse academicInfoResponse;
    private TeacherPersonalInfoResponse personalInfoResponse;
}
