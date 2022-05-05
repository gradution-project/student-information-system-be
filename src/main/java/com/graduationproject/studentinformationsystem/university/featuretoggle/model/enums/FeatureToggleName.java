package com.graduationproject.studentinformationsystem.university.featuretoggle.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FeatureToggleName {

    FIRST_SEMESTER_LESSON_DATE_RANGE("1. Dönem Ders Tarih Aralığı"),
    SECOND_SEMESTER_LESSON_DATE_RANGE("2. Dönem Ders Tarih Aralığı"),
    FIRST_SEMESTER_LESSON_REGISTRATION_OPERATIONS("1. Dönem Ders Kayıt İşlemleri"),
    SECOND_SEMESTER_LESSON_REGISTRATION_OPERATIONS("2. Dönem Ders Kayıt İşlemleri"),
    NOTE_OPERATIONS("Not İşlemleri"),
    MIDTERM_NOTE_OPERATIONS("Vize Not İşlemleri"),
    FINAL_NOTE_OPERATIONS("Final Not İşlemleri"),
    RESIT_NOTE_OPERATIONS("Bütünleme Not İşlemleri");

    private final String tr;
}
