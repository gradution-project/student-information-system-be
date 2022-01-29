package com.graduationproject.studentinformationsystem.university.student.model.dto.response;

import com.graduationproject.studentinformationsystem.common.util.controller.response.SisBaseAcademicInfoResponse;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class StudentAcademicInfoResponse extends SisBaseAcademicInfoResponse {

    private Long studentId;
    private Long departmentId;
    private String degree;
    private String classLevel;
}
