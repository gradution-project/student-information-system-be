package com.graduationproject.studentinformationsystem.student.model.dto.response;

import com.graduationproject.studentinformationsystem.student.model.enums.StudentClassLevel;
import com.graduationproject.studentinformationsystem.student.model.enums.StudentDegree;
import com.graduationproject.studentinformationsystem.student.model.enums.StudentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class StudentResponse {

    private Long studentId;
    private Long departmentId;
    private StudentDegree degree;
    private StudentClassLevel classLevel;
    private String name;
    private String surname;
    private String email;
    private StudentStatus status;
    private Date registrationDate;
}
