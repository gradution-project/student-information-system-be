package com.graduationproject.studentinformationsystem.university.faculty.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FacultyStatus {

    ACTIVE("Aktif"),
    PASSIVE("Pasif"),
    DELETED("Silinmiş"),
    ALL("Hepsi");

    private final String tr;
}
