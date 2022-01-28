package com.graduationproject.studentinformationsystem.university.student.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentInfoDetailResponse {

    private StudentAcademicInfoResponse academicInfoResponse;
    private StudentPersonalInfoResponse personalInfoResponse;
}
