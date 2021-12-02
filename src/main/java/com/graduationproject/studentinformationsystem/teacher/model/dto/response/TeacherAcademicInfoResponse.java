package com.graduationproject.studentinformationsystem.teacher.model.dto.response;

import com.graduationproject.studentinformationsystem.common.model.dto.response.BaseResponse;
import com.graduationproject.studentinformationsystem.teacher.model.enums.TeacherDegree;
import com.graduationproject.studentinformationsystem.teacher.model.enums.TeacherRole;
import com.graduationproject.studentinformationsystem.teacher.model.enums.TeacherStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TeacherAcademicInfoResponse extends BaseResponse {

    private Long teacherId;
    private Long departmentId;
    private TeacherDegree degree;
    private TeacherRole role;
    private String fieldOfStudy;
    private String phoneNumber;
    private String email;
    private TeacherStatus status;
    private Date registrationDate;
}
