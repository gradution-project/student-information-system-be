package com.graduationproject.studentinformationsystem.university.student.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StudentStatus {
    ACTIVE("Aktif"),
    PASSIVE("Pasif"),
    DELETED("Silinmiş"),
    GRADUATED("Mezun"),
    ALL("Hepsi");

    private final String name;
}
