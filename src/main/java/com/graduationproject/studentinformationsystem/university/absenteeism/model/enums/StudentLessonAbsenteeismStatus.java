package com.graduationproject.studentinformationsystem.university.absenteeism.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StudentLessonAbsenteeismStatus {

    NOT_ENTERED("Yoklama Girişi Yapılmamış"),
    ENTERED("Yoklama Girişi Yapılmış"),
    FAILED("Kaldı");

    private final String tr;
}
