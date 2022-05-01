package com.graduationproject.studentinformationsystem.login.teacher.password.model.mapping;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum TeacherPasswordOperationMapping {

    OPERATION_ID("OPERATION_ID", "operationId"),
    TEACHER_ID("TEACHER_ID", "teacherId"),
    EXPIRE_DATE("EXPIRE_DATE", "expireDate");

    private final String columnName;
    private final String modelName;

    public static final Map<String, String> COLUMN_MAPPINGS = Stream.of(values())
            .collect(Collectors.toMap(TeacherPasswordOperationMapping::getColumnName, TeacherPasswordOperationMapping::getModelName));
}
