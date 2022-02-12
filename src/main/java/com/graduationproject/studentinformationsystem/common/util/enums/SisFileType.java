package com.graduationproject.studentinformationsystem.common.util.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SisFileType {

    PDF("application/pdf");

    private final String name;
}
