package com.graduationproject.studentinformationsystem.university.lesson.common.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LessonStatus {
    ACTIVE("Aktif"),
    PASSIVE("Pasif"),
    DELETED("Silinmiş"),
    ALL("Hepsi");

    private final String name;
}
