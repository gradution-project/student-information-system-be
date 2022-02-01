package com.graduationproject.studentinformationsystem.university.teacher.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TeacherStatus {
    ACTIVE("Aktif"),
    PASSIVE("Pasif"),
    DELETED("Silinmiş"),
    ALL("Hepsi");

    private final String name;
}
