package com.graduationproject.studentinformationsystem.university.department.model.mapping;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum DepartmentMapping {

    DEPARTMENT_ID("DEPARTMENT_ID", "departmentId"),
    FACULTY_ID("FACULTY_ID", "facultyId"),
    NAME("NAME", "name"),
    STATUS("STATUS", "status"),
    TOTAL_CLASS_LEVEL("TOTAL_CLASS_LEVEL", "totalClassLevel"),
    IS_THERE_PREPARATORY_CLASS("IS_THERE_PREPARATORY_CLASS", "isTherePreparatoryClass"),
    CREATED_DATE("CREATED_DATE", "createdDate"),
    CREATED_USER_ID("CREATED_USER_ID", "createdUserId"),
    MODIFIED_DATE("MODIFIED_DATE", "modifiedDate"),
    MODIFIED_USER_ID("MODIFIED_USER_ID", "modifiedUserId");

    private final String columnName;
    private final String modelName;

    public static final Map<String, String> COLUMN_MAPPINGS = Stream.of(values())
            .collect(Collectors.toMap(DepartmentMapping::getColumnName, DepartmentMapping::getModelName));
}
