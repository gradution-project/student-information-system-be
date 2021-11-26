package com.graduationproject.studentinformationsystem.student.model.dto.request;

import com.graduationproject.studentinformationsystem.common.util.group.OnCreate;
import com.graduationproject.studentinformationsystem.common.util.validation.OnlyNumber;
import com.graduationproject.studentinformationsystem.student.model.enums.StudentClassLevel;
import com.graduationproject.studentinformationsystem.student.model.enums.StudentDegree;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class StudentAcademicInfoRequest {

    @OnlyNumber
    @Size(max = 5)
    @NotNull(groups = {OnCreate.class})
    private Long departmentId;

    @NotNull(groups = {OnCreate.class})
    private StudentDegree degree;

    @NotNull(groups = {OnCreate.class})
    private StudentClassLevel classLevel;
}
