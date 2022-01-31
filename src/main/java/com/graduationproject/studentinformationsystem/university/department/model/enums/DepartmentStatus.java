package com.graduationproject.studentinformationsystem.university.department.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DepartmentStatus {
    ACTIVE("Aktif"),
    PASSIVE("Pasif"),
    DELETED("Silinmiş"),
    ALL("Hepsi");

    private final String name;
}
