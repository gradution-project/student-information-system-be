package com.graduationproject.studentinformationsystem.university.absenteeism.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StudentLessonAbsenteeismHoursState {

    NOT_EXIST("Ders Saati Bulunmuyor!"),
    NOT_ENTERED("Yoklama Girişi Yapılmamış"),
    ENTERED("Yoklama Girişi Yapılmış");

    private final String tr;
}
