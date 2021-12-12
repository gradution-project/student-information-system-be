package com.graduationproject.studentinformationsystem.login.teacher.model.mapping;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum TeacherLoginMapping {

    TEACHER_ID("TEACHER_ID", "teacherId"),
    PASSWORD("PASSWORD", "password"),
    FAIL_COUNTER("FAIL_COUNTER", "failCounter"),
    LAST_LOGIN_DATE("LAST_LOGIN_DATE", "lastLoginDate");

    private final String columnName;
    private final String modelName;

    public static final Map<String, String> COLUMN_MAPPINGS = Stream.of(values()).collect(Collectors.toMap(TeacherLoginMapping::getColumnName, TeacherLoginMapping::getModelName));
}
