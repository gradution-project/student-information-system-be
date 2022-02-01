package com.graduationproject.studentinformationsystem.university.department.model.dto.response;

import com.graduationproject.studentinformationsystem.common.model.dto.response.SisBaseResponse;
import com.graduationproject.studentinformationsystem.university.faculty.model.dto.response.FacultyResponse;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class DepartmentResponse extends SisBaseResponse {

    private Long departmentId;
    private String name;
    private String status;
    private Integer totalClassLevel;
    private Boolean isTherePreparatoryClass;

    private FacultyResponse facultyResponse;
}
