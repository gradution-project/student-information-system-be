package com.graduationproject.studentinformationsystem.university.officer.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.graduationproject.studentinformationsystem.common.util.validation.PhoneNumber;
import com.graduationproject.studentinformationsystem.common.util.validation.id.FacultyID;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

import static com.graduationproject.studentinformationsystem.common.util.constant.SisConstants.PHONE_NUMBER_PATTERN;

@Getter
public class OfficerAcademicInfoRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -7958263203810413355L;

    @NotNull
    @FacultyID
    private Long facultyId;

    @NotNull
    @PhoneNumber
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = PHONE_NUMBER_PATTERN)
    private String phoneNumber;
}
