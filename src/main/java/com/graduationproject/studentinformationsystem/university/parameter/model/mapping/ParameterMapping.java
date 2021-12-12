package com.graduationproject.studentinformationsystem.university.parameter.model.mapping;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum ParameterMapping {

    NAME("NAME", "name"),
    VALUE("VALUE", "value");

    private final String columnName;
    private final String modelName;

    public static final Map<String, String> COLUMN_MAPPINGS = Stream.of(values()).collect(Collectors.toMap(ParameterMapping::getColumnName, ParameterMapping::getModelName));
}
