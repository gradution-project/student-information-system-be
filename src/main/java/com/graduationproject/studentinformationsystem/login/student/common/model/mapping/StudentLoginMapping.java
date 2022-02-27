package com.graduationproject.studentinformationsystem.login.student.common.model.mapping;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum StudentLoginMapping {

    STUDENT_ID("STUDENT_ID", "studentId"),
    PASSWORD("PASSWORD", "password"),
    FAIL_COUNTER("FAIL_COUNTER", "failCounter"),
    LAST_LOGIN_DATE("LAST_LOGIN_DATE", "lastLoginDate");

    private final String columnName;
    private final String modelName;

    public static final Map<String, String> COLUMN_MAPPINGS = Stream.of(values())
            .collect(Collectors.toMap(StudentLoginMapping::getColumnName, StudentLoginMapping::getModelName));
}
