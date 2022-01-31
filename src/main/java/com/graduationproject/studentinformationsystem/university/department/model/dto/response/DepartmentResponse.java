package com.graduationproject.studentinformationsystem.university.department.model.dto.response;

import com.graduationproject.studentinformationsystem.common.model.dto.response.SisBaseResponse;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class DepartmentResponse extends SisBaseResponse {

    private Long departmentId;
    private Long facultyId;
    private String name;
    private String status;
    private Integer totalClassLevel;
    private Boolean isTherePreparatoryClass;
}
