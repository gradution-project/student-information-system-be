package com.graduationproject.studentinformationsystem.university.featuretoggle.model.mapping;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum FeatureToggleMapping {

    ID("ID", "id"),
    NAME("NAME", "name"),
    IS_ENABLED("IS_ENABLED", "isEnabled"),
    START_DATE("START_DATE", "startDate"),
    END_DATE("END_DATE", "endDate"),
    CREATED_DATE("CREATED_DATE", "createdDate"),
    CREATED_USER_ID("CREATED_USER_ID", "createdUserId"),
    MODIFIED_DATE("MODIFIED_DATE", "modifiedDate"),
    MODIFIED_USER_ID("MODIFIED_USER_ID", "modifiedUserId");

    private final String columnName;
    private final String modelName;

    public static final Map<String, String> COLUMN_MAPPINGS = Stream.of(values())
            .collect(Collectors.toMap(FeatureToggleMapping::getColumnName, FeatureToggleMapping::getModelName));
}
