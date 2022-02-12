package com.graduationproject.studentinformationsystem.university.student.model.dto.response;

import com.graduationproject.studentinformationsystem.university.department.model.dto.response.DepartmentResponse;
import com.graduationproject.studentinformationsystem.university.student.model.enums.StudentClassLevel;
import com.graduationproject.studentinformationsystem.university.student.model.enums.StudentDegree;
import com.graduationproject.studentinformationsystem.university.student.model.enums.StudentStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentInfoResponse {

    private Long studentId;
    private StudentDegree degree;
    private StudentClassLevel classLevel;
    private String name;
    private String surname;
    private String email;
    private StudentStatus status;
    private String registrationDate;

    private DepartmentResponse departmentResponse;
}
