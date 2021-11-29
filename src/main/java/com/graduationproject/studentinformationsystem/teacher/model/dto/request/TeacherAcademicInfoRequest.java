package com.graduationproject.studentinformationsystem.teacher.model.dto.request;

import com.graduationproject.studentinformationsystem.common.util.group.OnCreate;
import com.graduationproject.studentinformationsystem.common.util.validation.OnlyNumber;
import com.graduationproject.studentinformationsystem.teacher.model.enums.TeacherGroup;
import com.graduationproject.studentinformationsystem.teacher.model.enums.TeacherRole;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class TeacherAcademicInfoRequest {

    @OnlyNumber
    @Size(min = 5, max = 5)
    @NotNull(groups = {OnCreate.class})
    private Long departmentId;

    @NotNull(groups = {OnCreate.class})
    private TeacherGroup group;

    @NotNull(groups = {OnCreate.class})
    private TeacherRole role;

    @Size(max = 256)
    @NotNull(groups = {OnCreate.class})
    private String fieldOfStudy;

    @OnlyNumber
    @Size(max = 10)
    @NotNull(groups = {OnCreate.class})
    private Long phoneNumber;
}
