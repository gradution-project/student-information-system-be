package com.graduationproject.studentinformationsystem.university.officer.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OfficerStatus {
    ACTIVE("Aktif"),
    PASSIVE("Pasif"),
    DELETED("Silinmiş"),
    ALL("Hepsi");

    private final String name;
}
