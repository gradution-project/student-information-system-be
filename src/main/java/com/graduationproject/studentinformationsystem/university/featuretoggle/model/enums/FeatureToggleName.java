package com.graduationproject.studentinformationsystem.university.featuretoggle.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FeatureToggleName {

    LESSON_REGISTRATION_OPERATIONS("Ders Kayıt İşlemleri"),
    NOTE_OPERATIONS("Not İşlemleri");

    private final String tr;
}
