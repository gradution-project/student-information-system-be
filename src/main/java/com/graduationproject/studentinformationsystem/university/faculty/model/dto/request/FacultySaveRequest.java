package com.graduationproject.studentinformationsystem.university.faculty.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class FacultySaveRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -3307385414501857011L;

    @Valid
    @NotNull
    private FacultyInfoRequest facultyInfoRequest;

    @Valid
    @NotNull
    private SisOperationInfoRequest operationInfoRequest;
}
