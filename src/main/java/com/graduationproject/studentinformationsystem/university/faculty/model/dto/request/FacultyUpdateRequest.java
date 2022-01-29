package com.graduationproject.studentinformationsystem.university.faculty.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class FacultyUpdateRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 2333257288801558329L;

    @Valid
    @NotNull
    private FacultyInfoRequest facultyInfoRequest;

    @Valid
    @NotNull
    private SisOperationInfoRequest operationInfoRequest;
}
