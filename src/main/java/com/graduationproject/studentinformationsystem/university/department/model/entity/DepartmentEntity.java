package com.graduationproject.studentinformationsystem.university.department.model.entity;

import com.graduationproject.studentinformationsystem.common.model.entity.SisBaseEntity;
import com.graduationproject.studentinformationsystem.university.department.model.enums.DepartmentStatus;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class DepartmentEntity extends SisBaseEntity {

    private Long departmentId;
    private Long facultyId;
    private String name;
    private DepartmentStatus status;
    private Integer totalClassLevel;
    private Boolean isTherePreparatoryClass;
}
