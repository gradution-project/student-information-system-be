package com.graduationproject.studentinformationsystem.university.department.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class DepartmentUpdateRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 5099554564277927145L;

    @Valid
    @NotNull
    private DepartmentInfoRequest departmentInfoRequest;

    @Valid
    @NotNull
    private SisOperationInfoRequest operationInfoRequest;
}
