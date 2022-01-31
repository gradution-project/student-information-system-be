package com.graduationproject.studentinformationsystem.university.department.model.dto.request;

import com.graduationproject.studentinformationsystem.common.model.dto.request.SisOperationInfoRequest;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter
public class DepartmentSaveRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 7395940158752339006L;

    @Valid
    @NotNull
    private DepartmentInfoRequest departmentInfoRequest;

    @Valid
    @NotNull
    private SisOperationInfoRequest operationInfoRequest;
}
