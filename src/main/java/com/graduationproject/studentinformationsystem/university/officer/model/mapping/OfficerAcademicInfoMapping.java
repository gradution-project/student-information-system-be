package com.graduationproject.studentinformationsystem.university.officer.model.mapping;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum OfficerAcademicInfoMapping {

    OFFICER_ID("OFFICER_ID", "officerId"),
    FACULTY_ID("FACULTY_ID", "facultyId"),
    EMAIL("EMAIL", "email"),
    PHONE_NUMBER("PHONE_NUMBER", "phoneNumber"),
    STATUS("STATUS", "status"),
    REGISTRATION_DATE("REGISTRATION_DATE", "registrationDate"),
    CREATED_DATE("CREATED_DATE", "createdDate"),
    CREATED_USER_ID("CREATED_USER_ID", "createdUserId"),
    MODIFIED_DATE("MODIFIED_DATE", "modifiedDate"),
    MODIFIED_USER_ID("MODIFIED_USER_ID", "modifiedUserId");

    private final String columnName;
    private final String modelName;

    public static final Map<String, String> COLUMN_MAPPINGS = Stream.of(values())
            .collect(Collectors.toMap(OfficerAcademicInfoMapping::getColumnName, OfficerAcademicInfoMapping::getModelName));
}
