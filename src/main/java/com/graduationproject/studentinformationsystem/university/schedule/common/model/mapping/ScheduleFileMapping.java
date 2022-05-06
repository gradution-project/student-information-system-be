package com.graduationproject.studentinformationsystem.university.schedule.common.model.mapping;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum ScheduleFileMapping {

    FILE_ID("FILE_ID", "fileId"),
    FACULTY_ID("FACULTY_ID", "facultyId"),
    DEPARTMENT_ID("DEPARTMENT_ID", "departmentId"),
    FILE_NAME("FILE_NAME", "fileName"),
    FILE_TYPE("FILE_TYPE", "fileType"),
    FILE("FILE", "file"),
    FILE_BYTE("FILE_BYTE", "fileByte"),
    FILE_SIZE("FILE_SIZE", "fileSize"),
    CREATED_DATE("CREATED_DATE", "createdDate"),
    CREATED_USER_ID("CREATED_USER_ID", "createdUserId");

    private final String columnName;
    private final String modelName;

    public static final Map<String, String> COLUMN_MAPPINGS = Stream.of(values())
            .collect(Collectors.toMap(ScheduleFileMapping::getColumnName, ScheduleFileMapping::getModelName));
}
