package com.graduationproject.studentinformationsystem.university.student.model.dto.response;

import com.graduationproject.studentinformationsystem.common.util.controller.response.SisBaseAcademicInfoResponse;
import com.graduationproject.studentinformationsystem.university.student.model.enums.StudentClassLevel;
import com.graduationproject.studentinformationsystem.university.student.model.enums.StudentDegree;
import com.graduationproject.studentinformationsystem.university.student.model.enums.StudentStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StudentAcademicInfoResponse extends SisBaseAcademicInfoResponse {

    private Long studentId;
    private Long departmentId;
    private StudentDegree degree;
    private StudentClassLevel classLevel;
    private StudentStatus status;
    private Date registrationDate;
}
