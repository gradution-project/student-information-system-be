package com.graduationproject.studentinformationsystem.university.student.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StudentClassLevel {
    PREPARATORY("Hazırlık Sınıfı"),
    FIRST("1. Sınıf"),
    SECOND("2. Sınıf"),
    THIRD("3. Sınıf"),
    FOURTH("4. Sınıf"),
    FIFTH("5. Sınıf"),
    SIXTH("6. Sınıf"),
    GRADUATE("Mezun");

    private final String name;
}
