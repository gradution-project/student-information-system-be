package com.graduationproject.studentinformationsystem.university.graduation.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StudentGraduationStatus {

    WAITING("Onay Bekleniyor"),
    APPROVED("Onaylandı"),
    REJECTED("Reddedildi"),
    CONFIRMED("Kesinleştirildi"),
    UNCONFIRMED("Kesinleştirilmedi"),
    ALL("Hepsi");

    private final String tr;
}
