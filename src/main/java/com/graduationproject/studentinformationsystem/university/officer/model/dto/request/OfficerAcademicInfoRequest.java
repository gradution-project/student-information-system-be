package com.graduationproject.studentinformationsystem.university.officer.model.dto.request;

import com.graduationproject.studentinformationsystem.common.util.validation.PhoneNumber;
import com.graduationproject.studentinformationsystem.common.util.validation.id.FacultyID;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class OfficerAcademicInfoRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -7958263203810413355L;

    @NotNull
    @FacultyID
    private Long facultyId;

    @NotNull
    @PhoneNumber
    private Long phoneNumber;
}
