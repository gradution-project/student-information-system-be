package com.graduationproject.studentinformationsystem.university.absenteeism.model.mapping;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum StudentLessonAbsenteeismMapping {

    ID("ID", "id"),
    TEACHER_ID("TEACHER_ID", "teacherId"),
    STUDENT_ID("STUDENT_ID", "studentId"),
    LESSON_ID("LESSON_ID", "lessonId"),
    WEEK("WEEK", "week"),
    THEORETICAL_HOURS("THEORETICAL_HOURS", "theoreticalHours"),
    MAX_THEORETICAL_HOURS("MAX_THEORETICAL_HOURS", "maxTheoreticalHours"),
    PRACTICE_HOURS("PRACTICE_HOURS", "practiceHours"),
    MAX_PRACTICE_HOURS("MAX_PRACTICE_HOURS", "maxPracticeHours"),
    STATUS("STATUS", "status"),
    CREATED_DATE("CREATED_DATE", "createdDate"),
    CREATED_USER_ID("CREATED_USER_ID", "createdUserId"),
    MODIFIED_DATE("MODIFIED_DATE", "modifiedDate"),
    MODIFIED_USER_ID("MODIFIED_USER_ID", "modifiedUserId");

    private final String columnName;
    private final String modelName;

    public static final Map<String, String> COLUMN_MAPPINGS = Stream.of(values())
            .collect(Collectors.toMap(StudentLessonAbsenteeismMapping::getColumnName, StudentLessonAbsenteeismMapping::getModelName));
}
