package com.graduationproject.studentinformationsystem.university.featuretoggle.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FeatureToggleName {

    LESSON_REGISTRATION_OPERATIONS("Ders Kayıt İşlemleri"),
    NOTE_OPERATIONS("Not İşlemleri"),
    MIDTERM_NOTE_OPERATIONS("Vize Not İşlemleri"),
    FINAL_NOTE_OPERATIONS("Final Not İşlemleri"),
    RESIT_NOTE_OPERATIONS("Bütünleme Not İşlemleri");

    private final String tr;
}
