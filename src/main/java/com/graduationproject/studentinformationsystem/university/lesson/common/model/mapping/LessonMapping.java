package com.graduationproject.studentinformationsystem.university.lesson.common.model.mapping;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum LessonMapping {

    LESSON_ID("LESSON_ID", "lessonId"),
    DEPARTMENT_ID("DEPARTMENT_ID", "departmentId"),
    NAME("NAME", "name"),
    SEMESTER("SEMESTER", "semester"),
    CREDIT("CREDIT", "credit"),
    THEORETICAL_HOURS("THEORETICAL_HOURS", "theoreticalHours"),
    PRACTICE_HOURS("PRACTICE_HOURS", "practiceHours"),
    COMPULSORY_OR_ELECTIVE("COMPULSORY_OR_ELECTIVE", "compulsoryOrElective"),
    STATUS("STATUS", "status"),
    CREATED_DATE("CREATED_DATE", "createdDate"),
    CREATED_USER_ID("CREATED_USER_ID", "createdUserId"),
    MODIFIED_DATE("MODIFIED_DATE", "modifiedDate"),
    MODIFIED_USER_ID("MODIFIED_USER_ID", "modifiedUserId");

    private final String columnName;
    private final String modelName;

    public static final Map<String, String> COLUMN_MAPPINGS = Stream.of(values())
            .collect(Collectors.toMap(LessonMapping::getColumnName, LessonMapping::getModelName));
}
