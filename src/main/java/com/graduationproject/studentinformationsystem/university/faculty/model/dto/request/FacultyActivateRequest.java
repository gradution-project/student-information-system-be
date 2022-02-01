package com.graduationproject.studentinformationsystem.university.faculty.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.validation.id.FacultyID;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class FacultyActivateRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 6776153555816182150L;

    @FacultyID
    @NotNull
    private Long facultyId;

    @Valid
    @NotNull
    private SisOperationInfoRequest operationInfoRequest;
}
