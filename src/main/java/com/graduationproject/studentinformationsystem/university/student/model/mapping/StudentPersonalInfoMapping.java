package com.graduationproject.studentinformationsystem.university.student.model.mapping;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum StudentPersonalInfoMapping {

    STUDENT_ID("STUDENT_ID", "studentId"),
    TC_NO("TC_NO", "tcNo"),
    NAME("NAME", "name"),
    SURNAME("SURNAME", "surname"),
    EMAIL("EMAIL", "email"),
    PHONE_NUMBER("PHONE_NUMBER", "phoneNumber"),
    STATUS("STATUS", "status"),
    PROFILE_PHOTO("PROFILE_PHOTO", "profilePhoto"),
    PROFILE_PHOTO_URL("PROFILE_PHOTO_URL", "profilePhotoUrl"),
    BIRTHDAY("BIRTHDAY", "birthday"),
    ADDRESS("ADDRESS", "address"),
    CREATED_DATE("CREATED_DATE", "createdDate"),
    CREATED_USER_ID("CREATED_USER_ID", "createdUserId"),
    MODIFIED_DATE("MODIFIED_DATE", "modifiedDate"),
    MODIFIED_USER_ID("MODIFIED_USER_ID", "modifiedUserId");

    private final String columnName;
    private final String modelName;

    public static final Map<String, String> COLUMN_MAPPINGS = Stream.of(values())
            .collect(Collectors.toMap(StudentPersonalInfoMapping::getColumnName, StudentPersonalInfoMapping::getModelName));
}
