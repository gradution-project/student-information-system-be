package com.graduationproject.studentinformationsystem.university.lesson.teacher.model.mapping;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum TeacherLessonMapping {

    TEACHER_ID("TEACHER_ID", "teacherId"),
    LESSON_ID("LESSON_ID", "lessonId"),
    CREATED_DATE("CREATED_DATE", "createdDate"),
    CREATED_USER_ID("CREATED_USER_ID", "createdUserId");

    private final String columnName;
    private final String modelName;

    public static final Map<String, String> COLUMN_MAPPINGS = Stream.of(values())
            .collect(Collectors.toMap(TeacherLessonMapping::getColumnName, TeacherLessonMapping::getModelName));
}
