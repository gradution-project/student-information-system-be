package com.graduationproject.studentinformationsystem.university.teacher.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.graduationproject.studentinformationsystem.common.util.validation.PhoneNumber;
import com.graduationproject.studentinformationsystem.common.util.validation.id.DepartmentID;
import com.graduationproject.studentinformationsystem.university.teacher.model.enums.TeacherDegree;
import com.graduationproject.studentinformationsystem.university.teacher.model.enums.TeacherRole;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;

import static com.graduationproject.studentinformationsystem.common.util.constant.SisConstants.PHONE_NUMBER_PATTERN;

@Getter
public class TeacherAcademicInfoRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -672637680560977958L;

    @NotNull
    @DepartmentID
    private Long departmentId;

    @NotNull
    private TeacherDegree degree;

    @NotNull
    private TeacherRole role;

    @NotNull
    @Size(min = 3, max = 256)
    private String fieldOfStudy;

    @NotNull
    @PhoneNumber
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = PHONE_NUMBER_PATTERN)
    private String phoneNumber;
}
