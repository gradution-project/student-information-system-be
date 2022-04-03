package com.graduationproject.studentinformationsystem.university.graduation.model.dto.response;

import com.graduationproject.studentinformationsystem.common.model.dto.response.SisBaseResponse;
import com.graduationproject.studentinformationsystem.university.graduation.model.enums.StudentGraduationStatus;
import com.graduationproject.studentinformationsystem.university.student.model.dto.response.StudentInfoResponse;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class StudentGraduationResponse extends SisBaseResponse {

    private String graduationId;
    private StudentGraduationStatus status;

    private StudentInfoResponse studentInfoResponse;
}
