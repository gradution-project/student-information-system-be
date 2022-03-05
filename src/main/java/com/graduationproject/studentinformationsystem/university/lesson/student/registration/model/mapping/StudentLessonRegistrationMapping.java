package com.graduationproject.studentinformationsystem.university.lesson.student.registration.model.mapping;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum StudentLessonRegistrationMapping {

    REGISTRATION_ID("REGISTRATION_ID", "registrationId"),
    STUDENT_ID("STUDENT_ID", "studentId"),
    LESSONS_IDS("LESSONS_IDS", "lessonsIds"),
    STATUS("STATUS", "status"),
    CREATED_DATE("CREATED_DATE", "createdDate"),
    CREATED_USER_ID("CREATED_USER_ID", "createdUserId"),
    MODIFIED_DATE("MODIFIED_DATE", "modifiedDate"),
    MODIFIED_USER_ID("MODIFIED_USER_ID", "modifiedUserId");

    private final String columnName;
    private final String modelName;

    public static final Map<String, String> COLUMN_MAPPINGS = Stream.of(values())
            .collect(Collectors.toMap(StudentLessonRegistrationMapping::getColumnName, StudentLessonRegistrationMapping::getModelName));
}
