package com.graduationproject.studentinformationsystem.university.student.model.mapping;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum StudentAcademicInfoMapping {

    STUDENT_ID("STUDENT_ID", "studentId"),
    DEPARTMENT_ID("DEPARTMENT_ID", "departmentId"),
    DEGREE("DEGREE", "degree"),
    CLASS_LEVEL("CLASS_LEVEL", "classLevel"),
    EMAIL("EMAIL", "email"),
    STATUS("STATUS", "status"),
    REGISTRATION_DATE("REGISTRATION_DATE", "registrationDate"),
    CREATED_DATE("CREATED_DATE", "createdDate"),
    CREATED_USER_ID("CREATED_USER_ID", "createdUserId"),
    MODIFIED_DATE("MODIFIED_DATE", "modifiedDate"),
    MODIFIED_USER_ID("MODIFIED_USER_ID", "modifiedUserId");

    private final String columnName;
    private final String modelName;

    public static final Map<String, String> COLUMN_MAPPINGS = Stream.of(values())
            .collect(Collectors.toMap(StudentAcademicInfoMapping::getColumnName, StudentAcademicInfoMapping::getModelName));
}
