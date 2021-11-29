package com.graduationproject.studentinformationsystem.teacher.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherInfoRequest {

    private TeacherAcademicInfoRequest academicInfoRequest;
    private TeacherPersonalInfoRequest personalInfoRequest;
}
