package com.graduationproject.studentinformationsystem.university.schedule.exam.model.mapping;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum ExamScheduleFileMapping {

    FILE_ID("FILE_ID", "fileId"),
    DEPARTMENT_ID("DEPARTMENT_ID", "departmentId"),
    PDF("PDF", "pdf"),
    CREATED_DATE("CREATED_DATE", "createdDate"),
    CREATED_USER_ID("CREATED_USER_ID", "createdUserId");

    private final String columnName;
    private final String modelName;

    public static final Map<String, String> COLUMN_MAPPINGS = Stream.of(values())
            .collect(Collectors.toMap(ExamScheduleFileMapping::getColumnName, ExamScheduleFileMapping::getModelName));
}
