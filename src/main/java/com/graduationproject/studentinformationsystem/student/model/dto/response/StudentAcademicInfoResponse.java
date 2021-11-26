package com.graduationproject.studentinformationsystem.student.model.dto.response;

import com.graduationproject.studentinformationsystem.common.model.dto.response.BaseResponse;
import com.graduationproject.studentinformationsystem.student.model.enums.StudentClassLevel;
import com.graduationproject.studentinformationsystem.student.model.enums.StudentDegree;
import com.graduationproject.studentinformationsystem.student.model.enums.StudentStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StudentAcademicInfoResponse extends BaseResponse {

    private Long studentId;
    private Long departmentId;
    private StudentDegree degree;
    private StudentClassLevel classLevel;
    private String email;
    private StudentStatus status;
    private Date registrationDate;
}
