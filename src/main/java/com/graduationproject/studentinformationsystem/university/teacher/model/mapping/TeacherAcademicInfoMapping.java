package com.graduationproject.studentinformationsystem.university.teacher.model.mapping;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum TeacherAcademicInfoMapping {

    TEACHER_ID("TEACHER_ID", "teacherId"),
    DEGREE("DEGREE", "degree"),
    ROLE("ROLE", "role"),
    DEPARTMENT_ID("DEPARTMENT_ID", "departmentId"),
    FIELD_OF_STUDY("FIELD_OF_STUDY", "fieldOfStudy"),
    EMAIL("EMAIL", "email"),
    PHONE_NUMBER("PHONE_NUMBER", "phoneNumber"),
    STATUS("STATUS", "status"),
    REGISTRATION_DATE("REGISTRATION_DATE", "registrationDate"),
    CREATED_DATE("CREATED_DATE", "createdDate"),
    CREATED_USER_ID("CREATED_USER_ID", "createdUserId"),
    MODIFIED_DATE("MODIFIED_DATE", "modifiedDate"),
    MODIFIED_USER_ID("MODIFIED_USER_ID", "modifiedUserId");

    private final String columnName;
    private final String modelName;

    public static final Map<String, String> COLUMN_MAPPINGS = Stream.of(values())
            .collect(Collectors.toMap(TeacherAcademicInfoMapping::getColumnName, TeacherAcademicInfoMapping::getModelName));
}
