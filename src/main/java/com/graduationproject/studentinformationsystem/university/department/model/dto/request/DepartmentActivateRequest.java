package com.graduationproject.studentinformationsystem.university.department.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import com.graduationproject.studentinformationsystem.common.util.validation.id.DepartmentID;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class DepartmentActivateRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 9048433735720467581L;

    @DepartmentID
    @NotNull
    private Long departmentId;

    @Valid
    @NotNull
    private SisOperationInfoRequest operationInfoRequest;
}
