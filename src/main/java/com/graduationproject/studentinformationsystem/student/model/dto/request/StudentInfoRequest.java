package com.graduationproject.studentinformationsystem.student.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentInfoRequest {

    private StudentAcademicInfoRequest academicInfoRequest;
    private StudentPersonalInfoRequest personalInfoRequest;
}
