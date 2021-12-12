package com.graduationproject.studentinformationsystem.university.student.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StudentInfoDetailResponse {

    private StudentAcademicInfoResponse academicInfoResponse;
    private StudentPersonalInfoResponse personalInfoResponse;
}
