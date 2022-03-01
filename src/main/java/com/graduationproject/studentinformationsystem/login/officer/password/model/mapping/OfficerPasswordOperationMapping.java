package com.graduationproject.studentinformationsystem.login.officer.password.model.mapping;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum OfficerPasswordOperationMapping {

    OPERATION_ID("OPERATION_ID", "operationId"),
    OFFICER_ID("OFFICER_ID", "officerId"),
    EXPIRE_DATE("EXPIRE_DATE", "expireDate"),
    FE_URL("FE_URL", "feUrl");

    private final String columnName;
    private final String modelName;

    public static final Map<String, String> COLUMN_MAPPINGS = Stream.of(values())
            .collect(Collectors.toMap(OfficerPasswordOperationMapping::getColumnName, OfficerPasswordOperationMapping::getModelName));
}
